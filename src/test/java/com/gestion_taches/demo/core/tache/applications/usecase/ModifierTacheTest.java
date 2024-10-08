package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;
import com.gestion_taches.demo.core.tache.applications.exception.TacheException;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import com.gestion_taches.demo.core.utilisateur.applications.exception.NonTrouverUtilisateurException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ModifierTacheTest {

    @Mock
    TacheRepositoryPort tacheRepositoryPort;
    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnaireModifierTacheCommande gestionnaireModifierTacheCommande;

    @BeforeEach
    void setUp() {
        this.gestionnaireModifierTacheCommande = new GestionnaireModifierTacheCommande(tacheRepositoryPort, utilisateurRepositoryPort);
    }


    @Test
    @DisplayName("tache de modification: la tache est introuvable")
    void modificationIntrouvable(){
        // GIVEN
        UUID tacheId = UUID.randomUUID();
        var commande = new ModifierTacheCommande();
        commande.setId(tacheId);
        commande.setDescription("modification d'une tâche inexistante");

        // WHEN
        when(tacheRepositoryPort.existeParId(tacheId)).thenReturn(false);

        // THEN
        assertThrows(TacheException.class, () -> gestionnaireModifierTacheCommande.execute(commande));

        verify(tacheRepositoryPort, never()).enregistrer(any(Taches.class));
    }


    @Test
    @DisplayName("Test de modification : Tâche présente mais non récupérable")
    void modifier_TacheNonRecuperable() {
        // GIVEN
        UUID tacheId = UUID.randomUUID();
        var commande = new ModifierTacheCommande();
        commande.setId(tacheId);
        commande.setDescription(" modification d'une tâche");

        // WHEN
        when(tacheRepositoryPort.existeParId(tacheId)).thenReturn(true);
        when(tacheRepositoryPort.recupererParId(tacheId)).thenReturn(Optional.empty());

        // THEN
        assertThrows(NonTrouveTacheException.class, () -> gestionnaireModifierTacheCommande.execute(commande));
        verify(tacheRepositoryPort, never()).enregistrer(any(Taches.class));
    }


    @Test
    @DisplayName("Test de modification: Utilisateur assigné introuvable")
    void modifier_UtilisateurAssigneIntrouvable() {
        // GIVEN
        UUID tacheId = UUID.randomUUID();
        UUID assignerA = UUID.randomUUID();
        Taches taches = new Taches();
        taches.setId(tacheId);

        var commande = new ModifierTacheCommande();
        commande.setId(tacheId);
        commande.setAssigneA(assignerA);

        // WHEN
        when(tacheRepositoryPort.existeParId(tacheId)).thenReturn(true);
        when(tacheRepositoryPort.recupererParId(tacheId)).thenReturn(Optional.of(taches));
        when(utilisateurRepositoryPort.recupererParId(assignerA)).thenReturn(Optional.empty());

        // THEN
        assertThrows(NonTrouverUtilisateurException.class, () -> gestionnaireModifierTacheCommande.execute(commande));
        verify(tacheRepositoryPort, never()).enregistrer(any(Taches.class));
    }


    @Test
    @DisplayName("Test de modification d'une Tâche avec succès")
    void modifier() {
        //GIVEN
        UUID assigneA = UUID.randomUUID();
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setId(assigneA);
        utilisateurs.setNom("Touré Katinan");
        utilisateurs.setEmail("katinan@gmail.com");

        Taches taches = new Taches();
        taches.setId(UUID.randomUUID());
        taches.setDescription("Modification de tâche");
        taches.setDateCreation(LocalDateTime.now());
        taches.setStatus(Status.A_FAIRE);
        taches.setAssignerA(assigneA);
        taches.setDateEcheance(LocalDateTime.of(2024, 10, 31, 23, 59));


        UUID assigneA1 = UUID.randomUUID();
        Utilisateurs utilisateurs1 = new Utilisateurs();
        utilisateurs1.setId(assigneA1);
        utilisateurs1.setNom("Touré Katinan");
        utilisateurs1.setEmail("katinan@gmail.com");

        UUID tacheId = UUID.randomUUID();
        var commande = new ModifierTacheCommande();
        commande.setId(tacheId);
        commande.setDescription("Modifier et afficher la tache");
        commande.setDateCreation(LocalDateTime.now());
        commande.setAssigneA(assigneA1);

        //WHEN
        when(tacheRepositoryPort.existeParId(tacheId)).thenReturn(true);
        when(tacheRepositoryPort.recupererParId(tacheId)).thenReturn(Optional.of(taches));
        when(utilisateurRepositoryPort.recupererParId(assigneA1)).thenReturn(Optional.of(utilisateurs1));
        this.gestionnaireModifierTacheCommande.execute(commande);

        //THEN
        verify(tacheRepositoryPort, times(1)).enregistrer(any(Taches.class));

    }
}