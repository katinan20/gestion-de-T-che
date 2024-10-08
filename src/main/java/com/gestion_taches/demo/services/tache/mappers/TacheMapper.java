package com.gestion_taches.demo.services.tache.mappers;

import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.services.tache.tables.TacheTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TacheMapper {

    public final static TacheMapper INSTANCE = Mappers.getMapper(TacheMapper.class);

    @Mapping(target = "assignerA.id", source = "assignerA")
    @Mapping(target = "creerPar.id", source = "creerPar")
    public abstract TacheTable tachesVerstacheTable(Taches taches);

    @Mapping(target = "assignerA", source = "assignerA.id")
    @Mapping(target = "creerPar", source = "creerPar.id")
    public abstract Taches tacheTableVerstaches(TacheTable tacheTable);

    @Mapping(target = "assignerA", source = "assignerA.id")
    public abstract TacheEssentielVM tacheTableVerstachesEssentielVM(TacheTable tacheTable);

    @Mapping(target = "assignerA", source = "assignerA.id")
    @Mapping(target = "creerPar", source = "creerPar.id")
    public abstract TacheDetailVM tacheTableVerstachesDetailVM(TacheTable tacheTable);

}
