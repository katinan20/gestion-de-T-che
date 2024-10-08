package com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.usecase.ModifierUtilisateur;

public class GestionnairModifierUtilisateurCommande implements GestionnaireCommande<ModifierUtilisateurCommande> {

    private final ModifierUtilisateur modifierUtilisateur;

    public GestionnairModifierUtilisateurCommande(UtilisateurRepositoryPort utilisateurRepositoryPort) {

        modifierUtilisateur = new ModifierUtilisateur(utilisateurRepositoryPort);
    }

    @Override
    public void execute(ModifierUtilisateurCommande commande) {
        this.modifierUtilisateur.modifier(commande);
    }
}
