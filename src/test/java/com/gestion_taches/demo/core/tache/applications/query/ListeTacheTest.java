package com.gestion_taches.demo.core.tache.applications.query;

import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.gestionnairequery.GestionnaireListeTacheRequete;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListeTacheTest {

    @Mock
    TacheRepositoryPort tacheRepositoryPort;

    GestionnaireListeTacheRequete gestionnaireListeTacheRequete;

    @BeforeEach
    void setUp() {
        this.gestionnaireListeTacheRequete = new GestionnaireListeTacheRequete(tacheRepositoryPort);
    }

    @Test
    @DisplayName("Test de listage des taches")
    void lister() {
        //Given
        Utilisateurs utilisateurs1 = new Utilisateurs();
        utilisateurs1.setId(UUID.randomUUID());
        utilisateurs1.setNom("TOURE KATINAN");
        utilisateurs1.setEmail("katinan@gmail.com");

        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setId(UUID.randomUUID());
        utilisateurs.setNom("YAO ELOGE");
        utilisateurs.setEmail("eloge@gmail.com");


        TacheEssentielVM tacheEssentielVM1 = new TacheEssentielVM();

        tacheEssentielVM1.setDescription("première tâche");
        tacheEssentielVM1.setStatus(Status.CLOTURE);
        tacheEssentielVM1.setDateCreation(LocalDateTime.now().plusDays(-7));
        tacheEssentielVM1.setDateEcheance(LocalDateTime.now().plusDays(7));
        tacheEssentielVM1.setAssignerA(utilisateurs.getId());

        TacheEssentielVM tacheEssentielVM2 = new TacheEssentielVM();
        tacheEssentielVM2.setDescription("deuxième tâche");
        tacheEssentielVM2.setStatus(Status.CLOTURE);
        tacheEssentielVM2.setDateCreation(LocalDateTime.now().plusDays(-8));
        tacheEssentielVM2.setDateEcheance(LocalDateTime.now().plusDays(10));
        tacheEssentielVM2.setAssignerA(utilisateurs1.getId());

        List<TacheEssentielVM> tacheEssentielVMS = Arrays.asList(tacheEssentielVM1,tacheEssentielVM2);

        //when
        when(tacheRepositoryPort.listeTache()).thenReturn(tacheEssentielVMS);
        List<TacheEssentielVM> query = this.gestionnaireListeTacheRequete.query(null);


        //then
        assertNotNull(query, "La liste de tâches ne doit pas être nulle");
        assertEquals(2, query.size(), "La taille de la liste renvoyée est incorrecte");
        verify(tacheRepositoryPort, times(1)).listeTache();
    }

}