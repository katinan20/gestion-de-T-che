package com.gestion_taches.demo.services.utilisateur.interfaces.facade.query.impl;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireRequete;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireListeUtilisateurQuery;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireRecupererUtilisateurParIdQuery;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;
import com.gestion_taches.demo.services.utilisateur.interfaces.facade.query.UtilisateurUseCaseQueryFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtilisateursUseCaseQueryFacadeImpl implements UtilisateurUseCaseQueryFacade {
    private final GestionnaireRequete<List<UtilisateursEssentielVM>, Void> gestionnaireListeteQuery;
    private final GestionnaireRequete<UtilisateurDetailVM, UUID> gestionnaireRecupererPArIdQuery;

    public UtilisateursUseCaseQueryFacadeImpl(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        gestionnaireListeteQuery = new GestionnaireListeUtilisateurQuery(utilisateurRepositoryPort);
        gestionnaireRecupererPArIdQuery = new GestionnaireRecupererUtilisateurParIdQuery(utilisateurRepositoryPort);
    }

    @Override
    public List<UtilisateursEssentielVM> listeUtilisateurVM() {
        return this.gestionnaireListeteQuery.query(null);
    }

    @Override
    public UtilisateurDetailVM recuperParIdUtilisateurUtilisateurVM(UUID id) {
        return this.gestionnaireRecupererPArIdQuery.query(id);
    }
}
