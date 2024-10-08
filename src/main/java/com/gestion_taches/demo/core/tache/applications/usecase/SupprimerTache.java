package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.exception.TacheException;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;

import java.util.UUID;

public class SupprimerTache {

    private final TacheRepositoryPort tacheRepositoryPort;

    public SupprimerTache(TacheRepositoryPort tacheRepositoryPort) {
        this.tacheRepositoryPort = tacheRepositoryPort;
    }

    public void supprimer(UUID id){
        this.tacheExiste(id);
        this.tacheRepositoryPort.supprimer(id);

    }

    private void tacheExiste(UUID id) {
        boolean isExiste = this.tacheRepositoryPort.existeParId(id);
        if (!isExiste){
            throw new TacheException("Aucune tache avec l id n existe");
        }
    }
}
