package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;
import com.gestion_taches.demo.core.tache.applications.exception.TacheException;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.utilisateur.applications.exception.NonTrouverUtilisateurException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;

import java.util.Objects;
import java.util.UUID;

public class ModifierTache {

    private final TacheRepositoryPort tacheRepositoryPort;
    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public ModifierTache(TacheRepositoryPort tacheRepositoryPort, UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.tacheRepositoryPort = tacheRepositoryPort;
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public void modifier(ModifierTacheCommande commande){
        UUID id = commande.getId();
        this.tacheExiste(id);
        Taches taches = this.tacheRepositoryPort.recupererParId(id).orElseThrow(
                () -> new NonTrouveTacheException("la tâche n existe pas")
        );

        taches.setDateCreation(commande.getDateCreation());
        taches.setDateEcheance(commande.getDateEcheance());
        taches.setDescription(commande.getDescription());

        UUID assignerA = commande.getAssigneA();
        if (Objects.nonNull(assignerA)) {
            this.utilisateurRepositoryPort.recupererParId(assignerA).orElseThrow(
                    () -> new NonTrouverUtilisateurException("L utilisateur assigné n existe pas")
            );
            taches.setAssignerA(assignerA);
        } else {
            taches.setAssignerA(commande.getCreerPar());
        }

        this.tacheRepositoryPort.enregistrer(taches);

    }

    private void tacheExiste(UUID id) {
        boolean isExiste = this.tacheRepositoryPort.existeParId(id);
        if (!isExiste){
            throw new TacheException("Aucune tache avec cet id n existe");
        }
    }
}
