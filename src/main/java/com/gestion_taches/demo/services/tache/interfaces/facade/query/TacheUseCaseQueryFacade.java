package com.gestion_taches.demo.services.tache.interfaces.facade.query;

import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;

import java.util.List;
import java.util.UUID;

public interface TacheUseCaseQueryFacade {
    List<TacheEssentielVM> listeTache();
    TacheDetailVM recupererTacheDetailVM(UUID id);
}
