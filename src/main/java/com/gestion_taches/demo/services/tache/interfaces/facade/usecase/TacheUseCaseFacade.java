package com.gestion_taches.demo.services.tache.interfaces.facade.usecase;

import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.DemarerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;

import java.util.UUID;

public interface TacheUseCaseFacade {
    void creer(CreerTacheCommande commande);

    void modifier(ModifierTacheCommande commande);

    Taches supprimer(UUID id);

    void executetache(DemarerTacheCommande commande);
}
