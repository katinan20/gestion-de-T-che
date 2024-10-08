package com.gestion_taches.demo.core.tache.applications.gestinnairecommande;

import com.gestion_taches.demo.core.tache.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.usecase.SupprimerTache;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;

import java.util.UUID;

public class GestionnaireSupprimerTacheCommande implements GestionnaireCommande<UUID> {

    private final SupprimerTache supprimerTache;

    public GestionnaireSupprimerTacheCommande(TacheRepositoryPort tacheRepositoryPort) {
        supprimerTache = new SupprimerTache(tacheRepositoryPort);
    }

    @Override
    public Taches execute(UUID id) {
        this.supprimerTache.supprimer(id);
        return null;
    }
}
