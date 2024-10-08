package com.gestion_taches.demo.core.utilisateur.applications.port;

import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepositoryPort {


    void enregistrer(Utilisateurs utilisateurs);

    Optional<Utilisateurs> recupererParId(UUID id);

    List<UtilisateursEssentielVM> listeUtilisateur();

    UtilisateurDetailVM recupererUtilisateurDetailVMParId(UUID id);

    void supprimer(UUID id);

    boolean existeParId(UUID creerPar);

    boolean existeParEmail(String email);
}
