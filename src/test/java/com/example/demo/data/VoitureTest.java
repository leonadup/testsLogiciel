package com.example.demo.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoitureTest {

    @Test
    void testCreationVoiture() {
        // Création d'une voiture avec constructeur
        Voiture voiture = new Voiture("Peugeot", 12000);

        // Vérification des valeurs
        assertEquals("Peugeot", voiture.getMarque());
        assertEquals(12000, voiture.getPrix());
    }

    @Test
    void testSettersEtGetters() {
        Voiture voiture = new Voiture();

        // Utilisation des setters
        voiture.setMarque("Renault");
        voiture.setPrix(15000);
        voiture.setId(42);

        // Vérification avec les getters
        assertEquals("Renault", voiture.getMarque());
        assertEquals(15000, voiture.getPrix());
        assertEquals(42, voiture.getId());
    }

    @Test
    void testToString() {
        Voiture voiture = new Voiture("Tesla", 50000);
        voiture.setId(7);

        String expected = "Car{marque='Tesla', prix=50000, id=7}";
        assertEquals(expected, voiture.toString());
    }
}
