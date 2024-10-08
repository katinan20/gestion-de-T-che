package com.gestion_taches.demo.core.tache.applications.gestinnairecommande;

import com.gestion_taches.demo.core.tache.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.DemarerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.usecase.ExecuterTacheUtilisateur;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;

public class GestionnaireExecuterTacheCommande implements GestionnaireCommande<DemarerTacheCommande> {

    private final ExecuterTacheUtilisateur executerTacheUtilisateur;

    public GestionnaireExecuterTacheCommande(TacheRepositoryPort tacheRepositoryPort) {
        executerTacheUtilisateur = new ExecuterTacheUtilisateur(tacheRepositoryPort);
    }

    @Override
    public Taches execute(DemarerTacheCommande commande) {
        this.executerTacheUtilisateur.demarerTache(commande);
        return null;
    }
}
