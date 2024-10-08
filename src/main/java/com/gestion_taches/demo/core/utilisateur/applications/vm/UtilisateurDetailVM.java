package com.gestion_taches.demo.core.utilisateur.applications.vm;

import com.gestion_taches.demo.core.tache.domain.entite.Taches;

import java.util.List;
import java.util.UUID;

public class UtilisateurDetailVM {

    private UUID id;
    private String nom;
    private String email;
    private List<Taches> taches;

    public UtilisateurDetailVM() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Taches> getTaches() {
        return taches;
    }

    public void setTaches(List<Taches> taches) {
        this.taches = taches;
    }
}
