package com.gestion_taches.demo.core.tache.applications.gestinnairecommande.gestionnairequery;

import com.gestion_taches.demo.core.tache.applications.GestionnaireRequete;
import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.query.RecuperTacheParId;
import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;

import java.util.UUID;

public class GestionnaireRecupererTacheParIdRequete implements GestionnaireRequete<TacheDetailVM, UUID> {

    private final RecuperTacheParId recuperTacheParId;

    public GestionnaireRecupererTacheParIdRequete(TacheRepositoryPort tacheRepositoryPort) {
        recuperTacheParId = new RecuperTacheParId(tacheRepositoryPort);
    }


    @Override
    public TacheDetailVM query(UUID id) throws NonTrouveTacheException {
        return this.recuperTacheParId.recupererTacheParId(id);
    }
}
