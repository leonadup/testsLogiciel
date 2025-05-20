package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCreerVoiture() throws Exception {
        String voitureJson = "{\"marque\":\"Toyota\",\"prix\":15000,\"id\":1}";

        // Pas besoin de stub pour 'ajouter', méthode void, on vérifie uniquement que la requête passe
        mockMvc.perform(post("/voiture")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voitureJson))
                .andExpect(status().isOk());

        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }

    @Test
    void testGetStatistiques() throws Exception {
        Echantillon echantillonMock = new Echantillon(3, 15000);
        when(statistiqueImpl.prixMoyen()).thenReturn(echantillonMock);

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(3))
                .andExpect(jsonPath("$.prixMoyen").value(15000));
    }

    @Test
    void testGetStatistiquesNoVoiture() throws Exception {
        // Simuler l'exception qui sera traduite en erreur par le contrôleur
        when(statistiqueImpl.prixMoyen()).thenThrow(new ArithmeticException());

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isInternalServerError());
        // Tu peux aussi tester un message d’erreur JSON si tu as un @ExceptionHandler défini
    }
}
