package com.gestion_taches.demo.core.tache.applications.gestinnairecommande.gestionnairequery;

import com.gestion_taches.demo.core.tache.applications.GestionnaireRequete;
import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.query.ListeTache;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;

import java.util.List;

public class GestionnaireListeTacheRequete implements GestionnaireRequete<List<TacheEssentielVM>, Void> {

    private final ListeTache listeTache;

    public GestionnaireListeTacheRequete(TacheRepositoryPort tacheRepositoryPort) {
        listeTache = new ListeTache(tacheRepositoryPort);
    }


    @Override
    public List<TacheEssentielVM> query(Void var) {
        return this.listeTache.listeTache();
    }
}
