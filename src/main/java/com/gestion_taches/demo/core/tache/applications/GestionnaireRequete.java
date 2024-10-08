package com.gestion_taches.demo.core.tache.applications;

import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;

public interface GestionnaireRequete<R, P> {
    R query(P var) throws NonTrouveTacheException;
}
