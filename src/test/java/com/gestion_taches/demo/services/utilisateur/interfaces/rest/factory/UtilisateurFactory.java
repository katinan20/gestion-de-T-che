package com.gestion_taches.demo.services.utilisateur.interfaces.rest.factory;

import com.gestion_taches.demo.services.utilisateur.repository.JpaUtilisateurRepository;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UtilisateurFactory {

    private final JpaUtilisateurRepository jpaUtilisateurRepository;

    public UtilisateurFactory(JpaUtilisateurRepository jpaUtilisateurRepository) {
        this.jpaUtilisateurRepository = jpaUtilisateurRepository;
    }


    public UtilisateurTable creerUtilisateur(){
        var utilisateurTable = new UtilisateurTable();
        utilisateurTable.setId(UUID.randomUUID());
        utilisateurTable.setNom("TOURE KATINAN");
        utilisateurTable.setEmail("paul@gmail.com");
        this.jpaUtilisateurRepository.save(utilisateurTable);
        return utilisateurTable;
    }


    public UtilisateurTable creerUtilisateur1(){
        var utilisateurTable = new UtilisateurTable();
        utilisateurTable.setId(UUID.randomUUID());
        utilisateurTable.setNom("Yao Eloge");
        utilisateurTable.setEmail("eloge@gmail.com");
        this.jpaUtilisateurRepository.save(utilisateurTable);
        return utilisateurTable;
    }


    public UtilisateurTable creerUtilisateur2(){
        var utilisateurTable = new UtilisateurTable();
        utilisateurTable.setId(UUID.randomUUID());
        utilisateurTable.setNom("SORO Ibrahim");
        utilisateurTable.setEmail("ibrahim@gmail.com");
        this.jpaUtilisateurRepository.save(utilisateurTable);
        return utilisateurTable;
    }



}
