package com.gestion_taches.demo.core.tache.applications.port;

import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TacheRepositoryPort {

    void enregistrer(Taches taches);

    Optional<Taches> recupererParId(UUID id);

    boolean existeParId(UUID id);

    void supprimer(UUID id);

    List<TacheEssentielVM> listeTache();

    TacheDetailVM recupererTacheDetailVMParId(UUID id);

}
