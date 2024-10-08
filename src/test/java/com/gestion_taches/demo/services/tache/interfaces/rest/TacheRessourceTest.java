package com.gestion_taches.demo.services.tache.interfaces.rest;


import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.services.tache.interfaces.rest.tachefactory.TacheFactory;
import com.gestion_taches.demo.services.tache.repository.JpaTacheRepository;
import com.gestion_taches.demo.services.tache.tables.TacheTable;
import com.gestion_taches.demo.services.utilisateur.interfaces.rest.TestUtils;
import com.gestion_taches.demo.services.utilisateur.interfaces.rest.factory.UtilisateurFactory;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TacheRessourceTest {

    private static final String API_URL = "/api/taches";

    @Autowired
    private JpaTacheRepository jpaTacheRepository;

    @Autowired
    private TacheRessource tacheRessource;

    @Autowired
    UtilisateurFactory utilisateurFactory;

    @Autowired
    private TacheFactory tacheFactory;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(tacheRessource)
                .setMessageConverters(mappingJackson2HttpMessageConverter).build();
    }

    @Test
    void creerTache() throws Exception {
        //GIVEN
        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur2();
        UUID id = utilisateurTable.getId();

        UtilisateurTable utilisateurTable1 = this.utilisateurFactory.creerUtilisateur1();
        UUID id1 = utilisateurTable1.getId();


        var commande = new CreerTacheCommande();
        commande.setLibele("Modifier");
        commande.setDescription("modifier la tache");
        commande.setDateEcheance(LocalDateTime.now().plusDays(7));
        commande.setDateCreation(LocalDateTime.now());
        commande.setCreerPar(id);
        commande.setAssigneA(id1);

        // When
        var mvcResult = this.mockMvc.perform(post(API_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.convertObjectToJsonBytes(commande))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print());
        // Then
        mvcResult.andExpect(status().isCreated());
    }

    @Test
    void modifierTache() throws Exception {
        //given
        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur2();
        UUID creerPar = utilisateurTable.getId();

        UtilisateurTable utilisateurTable1 = this.utilisateurFactory.creerUtilisateur1();
        UUID assigneA = utilisateurTable1.getId();

        TacheTable tacheTable = this.tacheFactory.creerTache(creerPar, assigneA);
        UUID id = tacheTable.getId();

        var commande = new ModifierTacheCommande();
        commande.setId(id);
        commande.setDescription("modifier la tache");
        commande.setLibele("modifier");
        commande.setAssigneA(assigneA);
        commande.setCreerPar(creerPar);

        //when
        var mvcResult = this.mockMvc.perform(put(API_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.convertObjectToJsonBytes(commande))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print());

        //then
        mvcResult.andExpect(status().isOk());
    }

    @Test
    void recupererTacheParId() throws Exception {
        //given
        TacheTable produitTable = this.tacheFactory.creerTache1();
        UUID id = produitTable.getId();

        //when
        var mvcResult = this.mockMvc.perform(get(API_URL, id)).andDo(print());

        //then
        mvcResult.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void tacheListe() throws Exception {

        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur2();
        UtilisateurTable utilisateurTable1 = this.utilisateurFactory.creerUtilisateur1();
        UtilisateurTable utilisateurTable3 = this.utilisateurFactory.creerUtilisateur();

        this.tacheFactory.creerTaches1(utilisateurTable1, utilisateurTable);
        this.tacheFactory.creerTache2(utilisateurTable3, utilisateurTable);
        this.tacheFactory.creerTache3(utilisateurTable1, utilisateurTable);
        this.tacheFactory.creerTache4(utilisateurTable3, utilisateurTable);

        var mvcResult = this.mockMvc.perform(get(API_URL)).andDo(print());

        mvcResult.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());
        List<TacheTable> tacheTables = jpaTacheRepository.findAll();
        assertEquals(7, tacheTables.size(),"la taille de la liste renvoyé est incorecte ");

    }


}