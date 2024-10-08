package com.gestion_taches.demo.services.utilisateur.interfaces.facade.query;

import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;

import java.util.List;
import java.util.UUID;

public interface UtilisateurUseCaseQueryFacade {

    List<UtilisateursEssentielVM> listeUtilisateurVM();
    UtilisateurDetailVM recuperParIdUtilisateurUtilisateurVM(UUID id);

}
