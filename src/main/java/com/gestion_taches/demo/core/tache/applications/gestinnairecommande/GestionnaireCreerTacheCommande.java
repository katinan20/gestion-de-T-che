package com.gestion_taches.demo.core.tache.applications.gestinnairecommande;

import com.gestion_taches.demo.core.tache.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.usecase.CreerTache;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;

public class GestionnaireCreerTacheCommande implements GestionnaireCommande<CreerTacheCommande> {

    private final CreerTache creerTache;

    public GestionnaireCreerTacheCommande(TacheRepositoryPort tacheRepositoryPort, UtilisateurRepositoryPort utilisateurRepositoryPort) {
        creerTache = new CreerTache(tacheRepositoryPort, utilisateurRepositoryPort);
    }

    @Override
    public Taches execute(CreerTacheCommande commande) {
        this.creerTache.creer(commande);
        return null;
    }
}
