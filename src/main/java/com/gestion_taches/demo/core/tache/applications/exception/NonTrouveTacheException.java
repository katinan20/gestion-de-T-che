package com.gestion_taches.demo.core.tache.applications.exception;

public class NonTrouveTacheException extends RuntimeException{

    private String information;

    public NonTrouveTacheException(String message) {
        super(message);
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
