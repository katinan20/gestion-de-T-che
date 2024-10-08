package com.gestion_taches.demo.core.tache.domain.entite;

import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Taches {

    private UUID id;
    private String libele;
    private String description;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private LocalDateTime dateEcheance;
    private Status status;
    private UUID assignerA;
    private UUID creerPar;

    public UUID getAssignerA() {
        return assignerA;
    }

    public void setAssignerA(UUID assignerA) {
        this.assignerA = assignerA;
    }


    public Taches() {
    }

    public UUID getCreerPar() {
        return creerPar;
    }

    public void setCreerPar(UUID creerPar) {
        this.creerPar = creerPar;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
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

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

}
