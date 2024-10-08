package com.gestion_taches.demo.core.utilisateur.applications;

public interface GestionnaireCommande<C> {
    void execute(C vars);
}
