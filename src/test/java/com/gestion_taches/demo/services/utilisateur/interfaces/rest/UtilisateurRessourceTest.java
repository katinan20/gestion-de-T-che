package com.gestion_taches.demo.services.utilisateur.interfaces.rest;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;
import com.gestion_taches.demo.services.utilisateur.interfaces.rest.factory.UtilisateurFactory;
import com.gestion_taches.demo.services.utilisateur.repository.JpaUtilisateurRepository;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UtilisateurRessourceTest {

    private static final String API_URL = "/api/utilisateurs";

    @Autowired
    JpaUtilisateurRepository jpaUtilisateurRepository;

    @Autowired
    UtilisateurRessource utilisateurRessource;

    @Autowired
    UtilisateurFactory utilisateurFactory;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(utilisateurRessource)
                .setMessageConverters(mappingJackson2HttpMessageConverter).build();
    }

    @Test
    void creerUtilisateur() throws Exception {
        //GIVEN
        var commande = new CreerUtilisateurCommande();
        commande.setNom("TOURE KATINAN");
        commande.setEmail("paul@gmail.com");

        //WHEN
        var mvcResult = this.mockMvc.perform(post(API_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(commande))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //THEN
        mvcResult.andExpect(status().isCreated());
    }

    @Test
    void modifierUtilisateur() throws Exception {
        //GIVEN
        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur1();
        UUID id = utilisateurTable.getId();

        var commande = new ModifierUtilisateurCommande();
        commande.setId(id);
        commande.setNom("KONE KARIM");
        commande.setEmail("karim@gmail.com");
        var mvcResult = this.mockMvc.perform(put(API_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(commande))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //THEN
        mvcResult.andExpect(status().isOk());
    }

    @Test
    void recupererParId() throws Exception {
        //GIVEN
        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur();
        UUID id = utilisateurTable.getId();

        var mvcResult = this.mockMvc.perform(get(API_URL, id));

        //THEN
        mvcResult.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());

    }

    @Test
    void utilisateurListe() throws Exception {
        this.utilisateurFactory.creerUtilisateur();
        this.utilisateurFactory.creerUtilisateur1();
        this.utilisateurFactory.creerUtilisateur2();

        var mvcResult = this.mockMvc.perform(get(API_URL)).andDo(print());

        mvcResult.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());
        List<UtilisateurTable> utilisateurTables = jpaUtilisateurRepository.findAll();
        Assertions.assertEquals(utilisateurTables.size(), 3);

    }

    @Test
    void supprimerUtilisateur() throws Exception {
        //GIVEN
        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur();
        UUID id = utilisateurTable.getId();

        var mvcResult = this.mockMvc.perform(delete(API_URL + "/{UtilisateurId}", id));

        mvcResult.andExpect(status().isOk());

    }
}