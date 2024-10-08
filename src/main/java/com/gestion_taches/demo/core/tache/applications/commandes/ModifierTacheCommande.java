package com.gestion_taches.demo.core.tache.applications.commandes;

import java.util.UUID;

public class ModifierTacheCommande extends CreerTacheCommande {

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
