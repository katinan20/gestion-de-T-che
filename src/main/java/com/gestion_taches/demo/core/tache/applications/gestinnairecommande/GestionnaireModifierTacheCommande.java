package com.gestion_taches.demo.core.tache.applications.gestinnairecommande;

import com.gestion_taches.demo.core.tache.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.usecase.ModifierTache;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;

public class GestionnaireModifierTacheCommande implements GestionnaireCommande<ModifierTacheCommande> {

    private final ModifierTache modifierTache;

    public GestionnaireModifierTacheCommande(TacheRepositoryPort tacheRepositoryPort, UtilisateurRepositoryPort utilisateurRepositoryPort) {
        modifierTache = new ModifierTache(tacheRepositoryPort, utilisateurRepositoryPort);
    }

    @Override
    public Taches execute(ModifierTacheCommande commande) {
        this.modifierTache.modifier(commande);
        return null;
    }
}
