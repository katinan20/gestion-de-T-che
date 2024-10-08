package com.gestion_taches.demo.services.utilisateur.repository.impl;

import com.gestion_taches.demo.core.utilisateur.applications.exception.UtilisateurNotFoundException;
import com.gestion_taches.demo.core.utilisateur.applications.port.UtilisateurRepositoryPort;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;
import com.gestion_taches.demo.core.utilisateur.domain.models.Utilisateurs;
import com.gestion_taches.demo.services.utilisateur.mappers.UtilisateurMapper;
import com.gestion_taches.demo.services.utilisateur.repository.JpaUtilisateurRepository;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PgJpaUtilisateurTableRepositoryAdapters implements UtilisateurRepositoryPort {

    private final JpaUtilisateurRepository jpaUtilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;


    public PgJpaUtilisateurTableRepositoryAdapters(JpaUtilisateurRepository jpaUtilisateurRepository) {
        this.jpaUtilisateurRepository = jpaUtilisateurRepository;
        this.utilisateurMapper = UtilisateurMapper.INSTANCE;
    }

    @Override
    public void enregistrer(Utilisateurs utilisateurs) {
        UtilisateurTable utilisateurTable = this.utilisateurMapper.utilisateurVersutilisateurTable(utilisateurs);
        this.jpaUtilisateurRepository.save(utilisateurTable);
    }


    @Override
    public Optional<Utilisateurs> recupererParId(UUID id) {
        UtilisateurTable utlisateurParId = this.jpaUtilisateurRepository.findById(id).orElseThrow(
                () -> new UtilisateurNotFoundException("Aucun utilisateur avec l'ID trouver")
        );
        return Optional.of(this.utilisateurMapper.utilisateurTableVersutilisateur(utlisateurParId));
    }

    @Override
    public List<UtilisateursEssentielVM> listeUtilisateur() {
        List<UtilisateurTable> utilisateurTableList = this.jpaUtilisateurRepository.findAll();
        return utilisateurTableList.stream()
                .map(utilisateurMapper::utilisateurTableVersutilisateurEssentielVM)
                .toList();
    }

    @Override
    public UtilisateurDetailVM recupererUtilisateurDetailVMParId(UUID id) {
        UtilisateurTable utilisateurRepositoryById = this.jpaUtilisateurRepository.findById(id).orElseThrow(
                () -> new UtilisateurNotFoundException("Aucun utilisateur avec l'ID trouver")
        );
        return this.utilisateurMapper.utilisateurTableVersutilisateurDetailVM(utilisateurRepositoryById);
    }


    @Override
    public void supprimer(UUID id) {
        try {
            this.jpaUtilisateurRepository.deleteById(id);
        } catch (Exception e) {
            throw new UtilisateurNotFoundException("Aucun utilisateur avec l'ID trouver");
        }
    }

    @Override
    public boolean existeParId(UUID creerPar) {
        return this.jpaUtilisateurRepository.existsById(creerPar);
    }

    @Override
    public boolean existeParEmail(String email) {
        return this.jpaUtilisateurRepository.existsByEmail(email);
    }


}
