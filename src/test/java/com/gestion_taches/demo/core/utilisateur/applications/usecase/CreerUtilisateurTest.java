package com.gestion_taches.demo.core.utilisateur.applications.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireCreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreerUtilisateurTest {

    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnaireCreerUtilisateurCommande gestionnaireCreerUtilisateurCommande;

    @BeforeEach
    void setUp() {
        this.gestionnaireCreerUtilisateurCommande = new GestionnaireCreerUtilisateurCommande(utilisateurRepositoryPort);
    }

    @Test
    @DisplayName("Test de creation d'un utilisateur")
    void creerUtilisateur() {
        //given
        var commande = new CreerUtilisateurCommande();
        commande.setNom("Touré");
        commande.setEmail("katinan@gmail.com");


        //when
        when(utilisateurRepositoryPort.existeParEmail(commande.getEmail())).thenReturn(false);
        this.gestionnaireCreerUtilisateurCommande.execute(commande);

        //then
        verify(utilisateurRepositoryPort, times(1)).enregistrer(any(Utilisateurs.class));

    }

}