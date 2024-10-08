package com.gestion_taches.demo.core.utilisateur.applications;

import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;

public interface GestionnaireRequete<R, P> {
    R query(P var) throws UtilisateurNotFoundException;
}
