package com.gestion_taches.demo.core.tache.applications.query;

import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.gestionnairequery.GestionnaireRecupererTacheParIdRequete;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecuperTacheParIdTest {

    @Mock
    TacheRepositoryPort tacheRepositoryPort;

    GestionnaireRecupererTacheParIdRequete gestionnaireRecupererTacheParIdRequete;

    @BeforeEach
    void setUp() {
        this.gestionnaireRecupererTacheParIdRequete = new GestionnaireRecupererTacheParIdRequete(tacheRepositoryPort);
    }


    @Test
    @DisplayName("Test de recuperation d'une tache par son Id")
    void recuperTacheParId() {
        //GIVEN
        Utilisateurs utilisateurCreerPar = new Utilisateurs();
        utilisateurCreerPar.setId(UUID.randomUUID());
        utilisateurCreerPar.setNom("YAO ELOGE");

        Utilisateurs utilisateurAssigne = new Utilisateurs();
        utilisateurAssigne.setId(UUID.randomUUID());
        utilisateurAssigne.setNom("TOURE KATINAN");
        utilisateurAssigne.setEmail("katinan@gmail.com");


        UUID idTache = UUID.randomUUID();
        TacheDetailVM tacheDetailVM = new TacheDetailVM();
        tacheDetailVM.setId(idTache);
        tacheDetailVM.setDescription("acheté du café");
        tacheDetailVM.setDateCreation(LocalDateTime.now().plusDays(-4));
        tacheDetailVM.setDateEcheance(LocalDateTime.now().plusDays(6));
        tacheDetailVM.setStatus(Status.CLOTURE);
        tacheDetailVM.setCreerPar(utilisateurCreerPar.getId());
        tacheDetailVM.setAssignerA(utilisateurAssigne.getId());

        //WHEN

        when(tacheRepositoryPort.recupererTacheDetailVMParId(idTache)).thenReturn(any(TacheDetailVM.class));
        this.gestionnaireRecupererTacheParIdRequete.query(tacheDetailVM.getId());

        //THEN
        verify(tacheRepositoryPort, times(1)).recupererTacheDetailVMParId(idTache);

    }

}