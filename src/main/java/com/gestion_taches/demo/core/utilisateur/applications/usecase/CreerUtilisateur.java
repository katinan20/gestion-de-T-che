package com.gestion_taches.demo.core.utilisateur.applications.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;

import java.util.UUID;

public class CreerUtilisateur {

    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public CreerUtilisateur(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public void creer(CreerUtilisateurCommande commande) {
        this.utlisateurExisteByEmail(commande.getEmail());
        Utilisateurs utilisateurs = this.generate(commande);
        this.utilisateurRepositoryPort.enregistrer(utilisateurs);
    }

    private Utilisateurs generate(CreerUtilisateurCommande commande) {
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setId(UUID.randomUUID());
        utilisateurs.setNom(commande.getNom());
        utilisateurs.setEmail(commande.getEmail());
        return utilisateurs;
    }

    private void utlisateurExisteByEmail(String email) {
        boolean isExiste = this.utilisateurRepositoryPort.existeParEmail(email);
        if (isExiste){
            throw new UtilisateurNotFoundException("l'email existe déjà");
        }
    }
}
