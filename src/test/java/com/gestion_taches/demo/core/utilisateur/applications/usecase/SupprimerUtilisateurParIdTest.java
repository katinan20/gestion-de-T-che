package com.gestion_taches.demo.core.utilisateur.applications.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireSupprimerUtilisateurParId;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupprimerUtilisateurParIdTest {

    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnaireSupprimerUtilisateurParId gestionnaireSupprimerUtilisateurParId;

    @BeforeEach
    void setUp(){
        this.gestionnaireSupprimerUtilisateurParId = new GestionnaireSupprimerUtilisateurParId(utilisateurRepositoryPort);
    }

    @Test
    @DisplayName("Test de suppression d'un utilisateur")
    void supprimerUtilisateur(){
        //GIVEN
        Utilisateurs utilisateurs1 = new Utilisateurs();
        utilisateurs1.setId(UUID.randomUUID());
        utilisateurs1.setNom("Paulo");
        utilisateurs1.setEmail("paulo@gmail.com");

        //WHEN
        when(utilisateurRepositoryPort.existeParId(utilisateurs1.getId())).thenReturn(true);
        this.gestionnaireSupprimerUtilisateurParId.execute(utilisateurs1.getId());

        //THEN
        verify(utilisateurRepositoryPort, times(1)).supprimer(any(UUID.class));

    }

}