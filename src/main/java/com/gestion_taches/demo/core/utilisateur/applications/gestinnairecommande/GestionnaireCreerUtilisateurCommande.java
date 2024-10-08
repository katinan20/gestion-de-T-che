package com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.usecase.CreerUtilisateur;

public class GestionnaireCreerUtilisateurCommande implements GestionnaireCommande<CreerUtilisateurCommande> {

    private final CreerUtilisateur creerUtilisateur;

    public GestionnaireCreerUtilisateurCommande(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        creerUtilisateur = new CreerUtilisateur(utilisateurRepositoryPort);
    }


    @Override
    public void execute(CreerUtilisateurCommande commande) {
        this.creerUtilisateur.creer(commande);
    }
}
