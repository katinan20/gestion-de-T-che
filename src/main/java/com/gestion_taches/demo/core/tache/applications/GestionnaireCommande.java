package com.gestion_taches.demo.core.tache.applications;

import com.gestion_taches.demo.core.tache.domain.entite.Taches;

public interface GestionnaireCommande<C> {
    Taches execute(C vars);
}
