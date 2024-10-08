package com.gestion_taches.demo.services.utilisateur.interfaces.facade.usecase.impl;

import com.gestion_taches.demo.core.utilisateur.applications.GestionnaireCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnairModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireCreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.gestinnairecommande.GestionnaireSupprimerUtilisateurParId;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.services.utilisateur.interfaces.facade.usecase.UtilisateurUseCaseFacade;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UtilisateurUseCaseFacadeImpl implements UtilisateurUseCaseFacade {

    private final GestionnaireCommande<CreerUtilisateurCommande> gestionnaireCreerCommande;
    private final GestionnaireCommande<ModifierUtilisateurCommande> gestionnaireModifierCommande;
    private final GestionnaireCommande<UUID> gestionnaireSupprimerCommande;

    public UtilisateurUseCaseFacadeImpl(UtilisateurRepositoryPort utilisateurRepositoryPort) {
        gestionnaireCreerCommande = new GestionnaireCreerUtilisateurCommande(utilisateurRepositoryPort);
        gestionnaireModifierCommande = new GestionnairModifierUtilisateurCommande(utilisateurRepositoryPort);
        gestionnaireSupprimerCommande = new GestionnaireSupprimerUtilisateurParId(utilisateurRepositoryPort);
    }

    @Override
    public void creer(CreerUtilisateurCommande commande) {
        this.gestionnaireCreerCommande.execute(commande);
    }

    @Override
    public void modifierUtilisateur(ModifierUtilisateurCommande commande) {
        this.gestionnaireModifierCommande.execute(commande);
    }

    @Override
    public void supprimerUtilisatur(UUID id) {
        gestionnaireSupprimerCommande.execute(id);
    }
}

