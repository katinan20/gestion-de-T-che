package com.gestion_taches.demo.core.tache.applications.usecase;

import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import com.gestion_taches.demo.core.utilisateur.applications.exception.NonTrouverUtilisateurException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;

import java.util.Objects;
import java.util.UUID;

public class CreerTache {

    private final TacheRepositoryPort tacheRepositoryPort;
    private final UtilisateurRepositoryPort utilisateurRepositoryPort;

    public CreerTache(TacheRepositoryPort tacheRepositoryPort, UtilisateurRepositoryPort utilisateurRepositoryPort) {
        this.tacheRepositoryPort = tacheRepositoryPort;
        this.utilisateurRepositoryPort = utilisateurRepositoryPort;
    }

    public void creer(CreerTacheCommande commande) {
        Taches taches = this.generate(commande);
        this.tacheRepositoryPort.enregistrer(taches);
    }

    private Taches generate(CreerTacheCommande commande) {

        Taches taches = new Taches();
        taches.setId(UUID.randomUUID());
        taches.setLibele(commande.getLibele());
        taches.setDescription(commande.getDescription());
        taches.setDateCreation(commande.getDateCreation());
        taches.setDateEcheance(commande.getDateEcheance());
        taches.setStatus(Status.A_FAIRE);

        boolean creerParExiste = this.utilisateurRepositoryPort.existeParId(commande.getCreerPar());
        if (creerParExiste) {
            taches.setCreerPar(commande.getCreerPar());
        }else {
            throw new NonTrouverUtilisateurException("L utilisateur createur n existe pas");
        }

        UUID assignerA = commande.getAssigneA();
        if (Objects.nonNull(assignerA)) {
            this.utilisateurRepositoryPort.recupererParId(assignerA).orElseThrow(
                    () -> new NonTrouverUtilisateurException("L utilisateur assigné n existe pas")
            );
            taches.setAssignerA(assignerA);
        } else {
            taches.setAssignerA(commande.getCreerPar());
        }
        return taches;
    }

}
