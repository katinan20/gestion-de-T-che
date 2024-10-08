package com.gestion_taches.demo.core.tache.applications.query;

import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;

import java.util.UUID;

public class RecuperTacheParId {

    private final TacheRepositoryPort tacheRepositoryPort;

    public RecuperTacheParId(TacheRepositoryPort tacheRepositoryPort) {
        this.tacheRepositoryPort = tacheRepositoryPort;
    }

    public TacheDetailVM recupererTacheParId(UUID id) {
        return this.tacheRepositoryPort.recupererTacheDetailVMParId(id);
    }
}
