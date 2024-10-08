package com.gestion_taches.demo.core.tache.applications.vm;

import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class TacheDetailVM {

    private UUID id;
    private String description;
    private LocalDateTime dateCreation;
    private LocalDateTime dateEcheance;
    private Status status;
    private UUID assignerA;
    private UUID creerPar;

    public TacheDetailVM() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getAssignerA() {
        return assignerA;
    }

    public void setAssignerA(UUID assignerA) {
        this.assignerA = assignerA;
    }

    public UUID getCreerPar() {
        return creerPar;
    }

    public void setCreerPar(UUID creerPar) {
        this.creerPar = creerPar;
    }
}
