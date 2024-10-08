package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.commandes.DemarerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.exception.TacheException;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireExecuterTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ExecuterTacheUtilisateurTest {

    @Mock
    TacheRepositoryPort tacheRepositoryPort;

    GestionnaireExecuterTacheCommande gestionnaireExecuterTacheCommande;

    @BeforeEach
    void setUp(){
        this.gestionnaireExecuterTacheCommande = new GestionnaireExecuterTacheCommande(tacheRepositoryPort);
    }

    @Test
    @DisplayName("Test d'execution avec l id  d'une tâche non trouver")
    void demarerAvecIdNonTrouver(){
        //Given
        UUID idTache = UUID.randomUUID();
        var commande = new DemarerTacheCommande();
        commande.setId(idTache);
        commande.setDateDebut(LocalDateTime.now());

        //when
        when(tacheRepositoryPort.existeParId(idTache)).thenReturn(false);

        //then
        assertThrows(TacheException.class, ()-> gestionnaireExecuterTacheCommande.execute(commande));
        verify(tacheRepositoryPort, never()).enregistrer(any(Taches.class));
    }


    @Test
    @DisplayName("Test d'execution d'une tâche avec le statut A FAIRE")
    void demarerAvecSucces(){
        //Given
        UUID idTache = UUID.randomUUID();
        Taches taches = new Taches();
        taches.setId(idTache);
        taches.setStatus(Status.A_FAIRE);

        var commande = new DemarerTacheCommande();
        commande.setId(idTache);
        commande.setDateDebut(LocalDateTime.now());

        //when
        when(tacheRepositoryPort.existeParId(idTache)).thenReturn(true);
        when(tacheRepositoryPort.recupererParId(idTache)).thenReturn(Optional.of(taches));
        this.gestionnaireExecuterTacheCommande.execute(commande);

        //then
        ArgumentCaptor<Taches> tachesArgumentCaptor = ArgumentCaptor.forClass(Taches.class);
        verify(tacheRepositoryPort, times(1)).enregistrer(tachesArgumentCaptor.capture());

        assertEquals(Status.EN_COURS, tachesArgumentCaptor.getValue().getStatus());

    }

 @Test
    @DisplayName("Test d'execution d'une tâche avec le statut different de  A FAIRE")
    void demareAvecStatutIvalide(){
        //Given
        UUID idTache = UUID.randomUUID();
        Taches taches = new Taches();
        taches.setId(idTache);
        taches.setStatus(Status.EN_COURS);

        var commande = new DemarerTacheCommande();
        commande.setId(idTache);
        commande.setDateDebut(LocalDateTime.now());

        //when
        when(tacheRepositoryPort.existeParId(idTache)).thenReturn(true);
        when(tacheRepositoryPort.recupererParId(idTache)).thenReturn(Optional.of(taches));

        //then
        assertThrows(TacheException.class, ()-> gestionnaireExecuterTacheCommande.execute(commande));

        ArgumentCaptor<Taches> tachesArgumentCaptor = ArgumentCaptor.forClass(Taches.class);
        verify(tacheRepositoryPort, never()).enregistrer(tachesArgumentCaptor.capture());

    }



}