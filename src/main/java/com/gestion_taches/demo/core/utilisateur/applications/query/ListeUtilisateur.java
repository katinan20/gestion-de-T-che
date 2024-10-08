package com.gestion_taches.demo.core.utilisateur.applications.query;

import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;

import java.util.List;

public class ListeUtilisateur {

    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public ListeUtilisateur(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public List<UtilisateursEssentielVM> listeUtilisteur(){
      return this.utilisateurRepositoryPort.listeUtilisateur();
    }
}
