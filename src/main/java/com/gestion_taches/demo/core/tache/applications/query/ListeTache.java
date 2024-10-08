package com.gestion_taches.demo.core.tache.applications.query;

import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;

import java.util.List;

public class ListeTache {

    private final TacheRepositoryPort tacheRepositoryPort;

    public ListeTache(TacheRepositoryPort tacheRepositoryPort) {
        this.tacheRepositoryPort = tacheRepositoryPort;
    }


    public List<TacheEssentielVM> listeTache(){
        return this.tacheRepositoryPort.listeTache();
    }


}
