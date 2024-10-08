package com.gestion_taches.demo.core.utilisateur.applications.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;

import java.util.UUID;

public class SupprimerUtilisateurParId {

    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public SupprimerUtilisateurParId(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public void supprimer(UUID id){
        this.utilisateurExiste(id);
        this.utilisateurRepositoryPort.supprimer(id);
    }

    private void utilisateurExiste(UUID id) {
        boolean isExiste = utilisateurRepositoryPort.existeParId(id);
        if (!isExiste){
            throw new UtilisateurNotFoundException("L'utilisateur n'existe pas");
        }
    }
}
