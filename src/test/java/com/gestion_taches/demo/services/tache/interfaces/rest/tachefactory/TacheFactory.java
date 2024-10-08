package com.gestion_taches.demo.services.tache.interfaces.rest.tachefactory;

import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import com.gestion_taches.demo.services.tache.repository.JpaTacheRepository;
import com.gestion_taches.demo.services.tache.tables.TacheTable;
import com.gestion_taches.demo.services.utilisateur.interfaces.rest.factory.UtilisateurFactory;
import com.gestion_taches.demo.services.utilisateur.repository.JpaUtilisateurRepository;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TacheFactory {

    private final JpaTacheRepository jpaTacheRepository;
    private final JpaUtilisateurRepository jpaUtilisateurRepository;
    private final UtilisateurFactory utilisateurFactory;

    public TacheFactory(JpaTacheRepository jpaTacheRepository, JpaUtilisateurRepository jpaUtilisateurRepository, UtilisateurFactory utilisateurFactory) {
        this.jpaTacheRepository = jpaTacheRepository;
        this.jpaUtilisateurRepository = jpaUtilisateurRepository;
        this.utilisateurFactory = utilisateurFactory;
    }

    public TacheTable creerTache(UUID creerParId, UUID assignerAId) {

        var createur = jpaUtilisateurRepository.findById(creerParId)
                .orElseThrow(() -> new UtilisateurNotFoundException("User with ID " + creerParId + " does not exist."));
        var assignee = jpaUtilisateurRepository.findById(assignerAId)
                .orElseThrow(() -> new UtilisateurNotFoundException("User with ID " + assignerAId + " does not exist."));

        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Supprimer");
        tacheTable.setDescription("supprimer la tache");
        tacheTable.setStatus(Status.A_FAIRE);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(+7));
        tacheTable.setAssignerA(createur);
        tacheTable.setCreerPar(assignee);
        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }

    public TacheTable creerTache1() {

        UtilisateurTable utilisateurTable = this.utilisateurFactory.creerUtilisateur2();
        UUID creerPar = utilisateurTable.getId();

        UtilisateurTable utilisateurTable1 = this.utilisateurFactory.creerUtilisateur1();
        UUID assigneA = utilisateurTable1.getId();

        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Supprimer");
        tacheTable.setDescription("supprimer la tache");
        tacheTable.setStatus(Status.A_FAIRE);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(+7));
        tacheTable.setAssignerA(utilisateurTable1);
        tacheTable.setCreerPar(utilisateurTable);

        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }

    public TacheTable creerTaches1(UtilisateurTable assignerA, UtilisateurTable creerPar) {
        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Préparer le rapport de fin d'année");
        tacheTable.setDescription("Rédiger et compiler le rapport de fin d'année pour l'équipe.");
        tacheTable.setStatus(Status.A_FAIRE);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(14));
        tacheTable.setAssignerA(assignerA);
        tacheTable.setCreerPar(creerPar);
        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }

    public TacheTable creerTache2(UtilisateurTable assignerA, UtilisateurTable creerPar) {
        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Révision des budgets trimestriels");
        tacheTable.setDescription("Analyser et revoir les budgets pour le prochain trimestre.");
        tacheTable.setStatus(Status.EN_COURS);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(7));
        tacheTable.setAssignerA(assignerA);
        tacheTable.setCreerPar(creerPar);
        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }

    public TacheTable creerTache3(UtilisateurTable assignerA, UtilisateurTable creerPar) {
        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Organiser la réunion de lancement de projet");
        tacheTable.setDescription("Planifier la réunion de démarrage du projet et inviter toutes les parties prenantes.");
        tacheTable.setStatus(Status.CLOTURE);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(1)); // Tâche terminée rapidement
        tacheTable.setAssignerA(assignerA);
        tacheTable.setCreerPar(creerPar);
        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }

    public TacheTable creerTache4(UtilisateurTable assignerA, UtilisateurTable creerPar) {
        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Mise à jour des informations du site web");
        tacheTable.setDescription("Actualiser le contenu du site web avec les informations les plus récentes.");
        tacheTable.setStatus(Status.A_FAIRE);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(10));
        tacheTable.setAssignerA(assignerA);
        tacheTable.setCreerPar(creerPar);
        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }

    public TacheTable creerTache5(UtilisateurTable assignerA, UtilisateurTable creerPar) {
        var tacheTable = new TacheTable();
        tacheTable.setId(UUID.randomUUID());
        tacheTable.setLibele("Préparer le support de présentation");
        tacheTable.setDescription("Créer le support de présentation pour la prochaine réunion avec les investisseurs.");
        tacheTable.setStatus(Status.EN_COURS);
        tacheTable.setDateCreation(LocalDateTime.now());
        tacheTable.setDateEcheance(LocalDateTime.now().plusDays(5));
        tacheTable.setAssignerA(assignerA);
        tacheTable.setCreerPar(creerPar);
        this.jpaTacheRepository.save(tacheTable);
        return tacheTable;
    }


}
