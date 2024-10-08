package com.gestion_taches.demo.core.utilisateur.applications.commandes;

import java.util.UUID;

public class ModifierUtilisateurCommande extends CreerUtilisateurCommande {

    private UUID id;

    public ModifierUtilisateurCommande() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
