package com.gestion_taches.demo.core.utilisateur.applications.query;

import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;

import java.util.UUID;

public class RecupererUtilisateurParId {

    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public RecupererUtilisateurParId(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public UtilisateurDetailVM recupereParId(UUID id){
        return this.utilisateurRepositoryPort.recupererUtilisateurDetailVMParId(id);
    }
}
