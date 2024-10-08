package com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireRequete;
import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.query.ListeUtilisateur;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;

import java.util.List;

public class GestionnaireListeUtilisateurQuery implements GestionnaireRequete<List<UtilisateursEssentielVM>, Void> {

    private final ListeUtilisateur listeUtilisateur;

    public GestionnaireListeUtilisateurQuery(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        listeUtilisateur = new ListeUtilisateur(utilisateurRepositoryPort);
    }

    @Override
    public List<UtilisateursEssentielVM> query(Void var) throws UtilisateurNotFoundException {
        return this.listeUtilisateur.listeUtilisteur();
    }
}