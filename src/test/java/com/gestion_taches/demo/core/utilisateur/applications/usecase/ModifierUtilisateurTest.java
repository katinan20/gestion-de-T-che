package com.gestion_taches.demo.core.utilisateur.applications.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnairModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModifierUtilisateurTest {

    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnairModifierUtilisateurCommande gestionnairModifierUtilisateurCommande;

    @BeforeEach
    void setUp(){
        this.gestionnairModifierUtilisateurCommande = new GestionnairModifierUtilisateurCommande(utilisateurRepositoryPort);
    }

    @Test
    @DisplayName("Test de modification d'un utlisateur")
    void modifierUtilisateur(){
        //GIVEN
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setId(UUID.randomUUID());
        utilisateurs.setNom("Touré Katinan");
        utilisateurs.setEmail("katinan@gmail.com");

        var commande = new ModifierUtilisateurCommande();
        commande.setId(utilisateurs.getId());
        commande.setNom("YaoEloge");
        commande.setEmail("eloge@gmail.com");

        //WHEN
        when(utilisateurRepositoryPort.existeParId(commande.getId())).thenReturn(true);
        when(utilisateurRepositoryPort.recupererParId(commande.getId())).thenReturn(Optional.of(utilisateurs));
        this.gestionnairModifierUtilisateurCommande.execute(commande);

        //THEN
        verify(utilisateurRepositoryPort, times(1)).enregistrer(any(Utilisateurs.class));
    }

}