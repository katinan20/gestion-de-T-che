package com.gestion_taches.demo.core.utilisateur.applications.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.exception.NonTrouverUtilisateurException;
import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

public class ModifierUtilisateur {

    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public ModifierUtilisateur(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public void modifier(ModifierUtilisateurCommande commande){
        UUID id = commande.getId();
        this.utilisateurExiste(id);
        Utilisateurs utilisateurs = this.utilisateurRepositoryPort.recupererParId(id).orElseThrow(
                ()-> new NonTrouverUtilisateurException("l'utilisateur n existe pas")
        );

        utilisateurs.setNom(commande.getNom());
        utilisateurs.setEmail(commande.getEmail());
        this.utilisateurRepositoryPort.enregistrer(utilisateurs);
    }

    private void utilisateurExiste(UUID id) {
        boolean existeParId =this.utilisateurRepositoryPort.existeParId(id);
        if (!existeParId){
            throw new UtilisateurNotFoundException("L'utilisateur n'esxiste pas");
        }
    }



}
