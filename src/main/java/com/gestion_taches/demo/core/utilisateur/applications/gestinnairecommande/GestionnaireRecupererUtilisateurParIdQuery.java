package com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireRequete;
import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.query.RecupererUtilisateurParId;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;

import java.util.UUID;

public class GestionnaireRecupererUtilisateurParIdQuery implements GestionnaireRequete<UtilisateurDetailVM, UUID> {
    private final RecupererUtilisateurParId recupererUtilisateurParId;
    public GestionnaireRecupererUtilisateurParIdQuery(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        recupererUtilisateurParId = new RecupererUtilisateurParId(utilisateurRepositoryPort);
    }

    @Override
    public UtilisateurDetailVM query(UUID id) throws UtilisateurNotFoundException {
        return this.recupererUtilisateurParId.recupereParId(id);
    }
}