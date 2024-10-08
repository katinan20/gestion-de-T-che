package com.gestion_taches.demo.services.utilisateur.mappers;

import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public abstract class UtilisateurMapper {

    public static final UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    public abstract UtilisateurTable utilisateurVersutilisateurTable(Utilisateurs utilisateurs);

    public abstract Utilisateurs utilisateurTableVersutilisateur(UtilisateurTable utilisateurTable);

    public abstract UtilisateursEssentielVM utilisateurTableVersutilisateurEssentielVM(UtilisateurTable utilisateurTable);

    public abstract UtilisateurDetailVM utilisateurTableVersutilisateurDetailVM(UtilisateurTable utilisateurTable);

}
