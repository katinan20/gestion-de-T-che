package com.gestion_taches.demo.core.tache.applications.commandes;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreerTacheCommande {

    private String libele;

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    private String description;
    private LocalDateTime dateCreation;
    private LocalDateTime dateEcheance;
    @NotNull
    private UUID creerPar;
    private UUID assigneA;

    public CreerTacheCommande() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public  UUID getCreerPar() {
        return creerPar;
    }

    public void setCreerPar( UUID creerPar) {
        this.creerPar = creerPar;
    }

    public UUID getAssigneA() {
        return assigneA;
    }

    public void setAssigneA(UUID assigneA) {
        this.assigneA = assigneA;
    }
}
