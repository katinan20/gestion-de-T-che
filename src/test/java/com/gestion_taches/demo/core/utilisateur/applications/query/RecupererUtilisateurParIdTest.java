package com.gestion_taches.demo.core.utilisateur.applications.query;


import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireRecupererUtilisateurParIdQuery;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecupererUtilisateurParIdTest {

    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnaireRecupererUtilisateurParIdQuery gestionnaireRecupererUtilisateurParIdQuery;

    @BeforeEach
    void setUp() {
        this.gestionnaireRecupererUtilisateurParIdQuery = new GestionnaireRecupererUtilisateurParIdQuery(utilisateurRepositoryPort);
    }

    @Test
    @DisplayName("Test de Recuperation par Id")
    void recuperUtilisateurParId() {
        //GIVEN
        Taches taches1 = new Taches();
        taches1.setId(UUID.randomUUID());
        taches1.setLibele("Modifier");

        Taches taches2 = new Taches();
        taches2.setId(UUID.randomUUID());
        taches2.setLibele("Supprimer");

        Taches taches3 = new Taches();
        taches3.setId(UUID.randomUUID());
        taches3.setLibele("Ajouter");

        List<Taches> tachesList = Arrays.asList(taches1, taches2, taches3);

        var commande = new UtilisateurDetailVM();
        commande.setId(UUID.randomUUID());
        commande.setNom("TOURE KATINAN");
        commande.setEmail("katinan@gmail.com");
        commande.setTaches(tachesList);

        //WHEN
        when(utilisateurRepositoryPort.recupererUtilisateurDetailVMParId(commande.getId())).thenReturn(commande);
        var resultats = this.gestionnaireRecupererUtilisateurParIdQuery.query(commande.getId());

        assert resultats!= null;
        assert resultats.getId().equals(commande.getId()) : "Les ID doivent correspondres";
        assert resultats.getNom().equals("TOURE KATINAN"): "le nom del'utilisateur doit être TOURE KATINAN";

        //THEN
        verify(utilisateurRepositoryPort, times(1)).recupererUtilisateurDetailVMParId(commande.getId());

    }
}