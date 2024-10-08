package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.commandes.DemarerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;
import com.gestion_taches.demo.core.tache.applications.exception.TacheException;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;

import java.util.UUID;

public class ExecuterTacheUtilisateur {

    private final TacheRepositoryPort tacheRepositoryPort;


    public ExecuterTacheUtilisateur(TacheRepositoryPort tacheRepositoryPort) {
        this.tacheRepositoryPort = tacheRepositoryPort;
    }

    public void demarerTache(DemarerTacheCommande commande) {
        UUID id = commande.getId();
        this.tacheExiste(id);
        Taches taches = this.tacheRepositoryPort.recupererParId(id).orElseThrow(
                () -> new NonTrouveTacheException("la tâche n existe pas")
        );
        this.tacheStatus(taches.getStatus());
        taches.setId(commande.getId());
        taches.setDateDebut(commande.getDateDebut());
        taches.setStatus(Status.EN_COURS);
        this.tacheRepositoryPort.enregistrer(taches);
    }

    private void tacheStatus(Status status) {
        if (!status.equals(Status.A_FAIRE)){
            throw new TacheException("LA tache n est pas a faire");
        }
    }

    private void tacheExiste(UUID id) {
        boolean isExiste = this.tacheRepositoryPort.existeParId(id);
        if (!isExiste) {
            throw new TacheException("Aucune Tâche avec cet id n existe");
        }
    }

}
