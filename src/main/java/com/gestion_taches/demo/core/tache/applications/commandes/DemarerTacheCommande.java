package com.gestion_taches.demo.core.tache.applications.commandes;

import java.time.LocalDateTime;
import java.util.UUID;

public class DemarerTacheCommande {

    private UUID id;
    private LocalDateTime dateDebut;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public DemarerTacheCommande() {
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }


}
