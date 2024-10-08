package com.gestion_taches.demo.services.tache.interfaces.facade.query.impl;

import com.gestion_taches.demo.core.tache.applications.GestionnaireRequete;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.gestionnairequery.GestionnaireListeTacheRequete;
import com.gestion_taches.demo.core.tache.applications.gestinnairecommande.gestionnairequery.GestionnaireRecupererTacheParIdRequete;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.services.tache.interfaces.facade.query.TacheUseCaseQueryFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TacheQueryFacadeImpl implements TacheUseCaseQueryFacade {
    private final GestionnaireRequete<List<TacheEssentielVM>, Void> gestionnaireRequete;
    private final GestionnaireRequete<TacheDetailVM, UUID> gestionnaireRecupererParIdRequete;

    public TacheQueryFacadeImpl(TacheRepositoryPort portTache) {
        gestionnaireRequete = new GestionnaireListeTacheRequete(portTache);
        gestionnaireRecupererParIdRequete = new GestionnaireRecupererTacheParIdRequete(portTache);
    }


    @Override
    public List<TacheEssentielVM> listeTache() {
        return this.gestionnaireRequete.query(null);
    }

    @Override
    public TacheDetailVM recupererTacheDetailVM(UUID id) {
        return this.gestionnaireRecupererParIdRequete.query(id);
    }
}

