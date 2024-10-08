package com.gestion_taches.demo.core.applications.usecase.usecastache;

import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireCreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreerTacheTest {

    @Mock
    TacheRepositoryPort tacheRepositoryPort;
    @Mock
    UtilisateurRepositoryPort utilisateurRepositoryPort;

    GestionnaireCreerTacheCommande gestionnaireCreerTacheCommande;

    @BeforeEach
    void setUp() {
        this.gestionnaireCreerTacheCommande = new GestionnaireCreerTacheCommande(tacheRepositoryPort, utilisateurRepositoryPort);
    }

    @Test
    @DisplayName("Test de creation d'une Tâche")
    void creer() {

        //Given
        UUID idUtilisateur = UUID.randomUUID();
        UUID idCreerPar = UUID.randomUUID();

        Utilisateurs assignerA = new Utilisateurs();
        assignerA.setId(idUtilisateur);
        assignerA.setNom("YAO ELOGE");
        assignerA.setEmail("eloge@gmail.com");

        Utilisateurs creerPar = new Utilisateurs();
        creerPar.setId(idCreerPar);
        creerPar.setNom("TOURE KATINAN");
        creerPar.setEmail("katinan@gmail.com");

        var commande = new CreerTacheCommande();
        commande.setDescription("gestion de tâche ");
        commande.setDateCreation(LocalDateTime.now());
        commande.setCreerPar(creerPar.getId());
        commande.setAssigneA(assignerA.getId());
        commande.setDateEcheance(LocalDateTime.of(2024, 10, 31, 23, 59));

        //when
        when(utilisateurRepositoryPort.recupererParId(idUtilisateur)).thenReturn(Optional.of(assignerA));
        when(utilisateurRepositoryPort.existeParId(idCreerPar)).thenReturn(true);

        this.gestionnaireCreerTacheCommande.execute(commande);

        //then
        verify(tacheRepositoryPort, times(1)).enregistrer(any(Taches.class));
    }

}