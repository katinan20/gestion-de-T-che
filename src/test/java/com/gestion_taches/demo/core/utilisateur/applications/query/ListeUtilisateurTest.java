package com.gestion_taches.demo.core.utilisateur.applications.query;


import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireListeUtilisateurQuery;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListeUtilisateurTest {

    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnaireListeUtilisateurQuery gestionnaireListeUtilisateurQuery;

    @BeforeEach
    void setUp() {
        this.gestionnaireListeUtilisateurQuery = new GestionnaireListeUtilisateurQuery(utilisateurRepositoryPort);
    }

    @Test
    @DisplayName("Test de listage des utilisateurs")
    void listeUtiliseatuer() {
        //GIVEN
        UtilisateursEssentielVM utilisateursEssentielVM1 = new UtilisateursEssentielVM();
        utilisateursEssentielVM1.setNom("TOURE KATINAN");
        utilisateursEssentielVM1.setEmail("katinan@gmail.com");

        UtilisateursEssentielVM utilisateursEssentielVM2 = new UtilisateursEssentielVM();
        utilisateursEssentielVM2.setNom("Yao Eloge");
        utilisateursEssentielVM2.setEmail("eloge@gmail.com");

        UtilisateursEssentielVM utilisateursEssentielVM3 = new UtilisateursEssentielVM();
        utilisateursEssentielVM3.setNom("SORO Ibrahim");
        utilisateursEssentielVM3.setEmail("ibrahim@gmail.com");

        List<UtilisateursEssentielVM> utilisateursEssentielVMList = Arrays.asList(utilisateursEssentielVM1, utilisateursEssentielVM2, utilisateursEssentielVM3);

        //WHEN
        when(utilisateurRepositoryPort.listeUtilisateur()).thenReturn(utilisateursEssentielVMList);
        var query = this.gestionnaireListeUtilisateurQuery.query(null);

        //THEN
        assertNotNull(query, "la liste des utilisateurs ne doit pas être null");
        assertEquals(3,query.size(),"la taille de la liste renvoyé est incorecte ");
        verify(utilisateurRepositoryPort, times(1)).listeUtilisateur();

    }

}