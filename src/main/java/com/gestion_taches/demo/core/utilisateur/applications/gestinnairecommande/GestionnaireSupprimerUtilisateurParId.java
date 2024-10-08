package com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.usecase.SupprimerUtilisateurParId;

import java.util.UUID;

public class GestionnaireSupprimerUtilisateurParId implements GestionnaireCommande<UUID> {

    private final SupprimerUtilisateurParId supprimerUtilisateurParId;

    public GestionnaireSupprimerUtilisateurParId(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        supprimerUtilisateurParId = new SupprimerUtilisateurParId(utilisateurRepositoryPort);
    }

    @Override
    public void execute(UUID id) {
        this.supprimerUtilisateurParId.supprimer(id);
    }
}
