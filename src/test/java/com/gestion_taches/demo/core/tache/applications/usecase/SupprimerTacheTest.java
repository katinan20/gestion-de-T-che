package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.exception.TacheException;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireSupprimerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupprimerTacheTest {

    @Mock
    TacheRepositoryPort tacheRepositoryPort;

    GestionnaireSupprimerTacheCommande gestionnaireSupprimerTacheCommande;

    @BeforeEach
    void setUp() {
        this.gestionnaireSupprimerTacheCommande = new GestionnaireSupprimerTacheCommande(tacheRepositoryPort);
    }

    @Test
    @DisplayName("Test de suppression: la tache n existe pas")
    void supprimerImposible(){
        //GIVEN
        UUID idTache = UUID.randomUUID();

        //when
        when(tacheRepositoryPort.existeParId(idTache)).thenReturn(false);

        //then
        assertThrows(TacheException.class, ()-> gestionnaireSupprimerTacheCommande.execute(idTache));
        verify(tacheRepositoryPort, never()).supprimer(any(UUID.class));
    }

    @Test
    @DisplayName("Test de suppression avec succès")
    void supprimer(){

        //GIVEN
        UUID idTache = UUID.randomUUID();
        Taches taches = new Taches();

        taches.setId(idTache);
        taches.setDescription("suppression de tâche");
        taches.setDateCreation(LocalDateTime.now());
        taches.setStatus(Status.A_FAIRE);
        taches.setDateEcheance(LocalDateTime.of(2024, 10, 31, 23, 59));


        //WHEN
        when(tacheRepositoryPort.existeParId(idTache)).thenReturn(true);
        this.gestionnaireSupprimerTacheCommande.execute(idTache);


        //THEN
        verify(tacheRepositoryPort, times(1)).supprimer(any(UUID.class));


    }
}