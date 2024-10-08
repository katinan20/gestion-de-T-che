package com.gestion_taches.demo.services.tache.interfaces.facade.usecase.impl;

import com.gestion_taches.demo.core.tache.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.DemarerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireCreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireExecuterTacheCommande;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.GestionnaireSupprimerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.services.tache.interfaces.facade.usecase.TacheUseCaseFacade;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TacheUseCaseFacadeImpl implements TacheUseCaseFacade {

    private final GestionnaireCommande<CreerTacheCommande> gestionnaireCreerTacheCommande;
    private final GestionnaireCommande<ModifierTacheCommande> gestionnaireModifierTacheCommande;
    private final GestionnaireCommande<UUID> gestionnaireSupprimerTacheCommande;
    private final GestionnaireCommande<DemarerTacheCommande> gestionnaireDemarerTacheCommande;

    public TacheUseCaseFacadeImpl(TacheRepositoryPort portTache, UtilisateurRepositoryPort utilisateurRepositoryPort) {
        gestionnaireCreerTacheCommande = new GestionnaireCreerTacheCommande(portTache, utilisateurRepositoryPort);

        gestionnaireModifierTacheCommande = new GestionnaireModifierTacheCommande(portTache, utilisateurRepositoryPort);
        gestionnaireSupprimerTacheCommande = new GestionnaireSupprimerTacheCommande(portTache);
        gestionnaireDemarerTacheCommande = new GestionnaireExecuterTacheCommande(portTache);
    }

    @Override
    public void creer(CreerTacheCommande commande) {
        this.gestionnaireCreerTacheCommande.execute(commande);

    }

    @Override
    public void modifier(ModifierTacheCommande commande) {
        this.gestionnaireModifierTacheCommande.execute(commande);
    }

    @Override
    public Taches supprimer(UUID id) {
        return this.gestionnaireSupprimerTacheCommande.execute(id);
    }

    @Override
    public void executetache(DemarerTacheCommande commande) {
        this.gestionnaireDemarerTacheCommande.execute(commande);
    }


}

