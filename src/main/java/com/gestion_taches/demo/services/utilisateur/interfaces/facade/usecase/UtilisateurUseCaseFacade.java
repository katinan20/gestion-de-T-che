package com.gestion_taches.demo.services.utilisateur.interfaces.facade.usecase;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;

import java.util.UUID;

public interface UtilisateurUseCaseFacade {

    void creer(CreerUtilisateurCommande commande);
    void modifierUtilisateur(ModifierUtilisateurCommande commande);
    void supprimerUtilisatur(UUID id);

}
