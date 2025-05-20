package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatistiqueTests {

    private StatistiqueImpl statistiqueImpl;

    @BeforeEach
    void setUp() {
        statistiqueImpl = new StatistiqueImpl();
    }

    @Test
    void testAjouterVoiture() {
        Voiture v1 = new Voiture("Toyota", 10000);
        statistiqueImpl.ajouter(v1);

        // Accès à la liste "voitures" via reflection pour vérification, ou via prixMoyen()
        Echantillon echantillon = statistiqueImpl.prixMoyen();
        assertEquals(1, echantillon.getNombreDeVoitures());
        assertEquals(10000, echantillon.getPrixMoyen());
    }

    @Test
    void testPrixMoyenAvecPlusieursVoitures() {
        Voiture v1 = new Voiture("Toyota", 10000);
        Voiture v2 = new Voiture("Honda", 20000);
        Voiture v3 = new Voiture("Ford", 15000);

        statistiqueImpl.ajouter(v1);
        statistiqueImpl.ajouter(v2);
        statistiqueImpl.ajouter(v3);

        Echantillon echantillon = statistiqueImpl.prixMoyen();
        assertEquals(3, echantillon.getNombreDeVoitures());
        assertEquals((10000 + 20000 + 15000) / 3, echantillon.getPrixMoyen());
    }

    @Test
    void testPrixMoyenSansVoitures() {
        // Cas où aucune voiture n'a été ajoutée, on s'attend à une ArithmeticException (division par zéro)
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            statistiqueImpl.prixMoyen();
        });
        assertEquals("/ by zero", exception.getMessage());
    }
}
