package com.gestion_taches.demo.services.tache.repository.impl;

import com.gestion_taches.demo.core.tache.applications.exception.NonTrouveTacheException;
import com.gestion_taches.demo.core.tache.applications.port.TacheRepositoryPort;
import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;
import com.gestion_taches.demo.core.tache.domain.entite.Taches;
import com.gestion_taches.demo.services.tache.mappers.TacheMapper;
import com.gestion_taches.demo.services.tache.repository.JpaTacheRepository;
import com.gestion_taches.demo.services.tache.tables.TacheTable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PgJpaTacheRepositoryTableAdapters implements TacheRepositoryPort {

    private final JpaTacheRepository jpaTacheRepository;
    private final TacheMapper tacheMapper;

    public PgJpaTacheRepositoryTableAdapters(JpaTacheRepository jpaTacheRepository) {
        this.jpaTacheRepository = jpaTacheRepository;
        this.tacheMapper = TacheMapper.INSTANCE;

    }


    @Override
    public void enregistrer(Taches taches) {
        TacheTable tacheTable = this.tacheMapper.tachesVerstacheTable(taches);
        this.jpaTacheRepository.save(tacheTable);
    }


    @Override
    public Optional<Taches> recupererParId(UUID id) {
        TacheTable tacheTable = this.jpaTacheRepository.findById(id).orElseThrow(
                () -> new NonTrouveTacheException("Aucune tâche avec l Id n existe")
        );
        return Optional.ofNullable(this.tacheMapper.tacheTableVerstaches(tacheTable));
    }

    @Override
    public boolean existeParId(UUID id) {
        return this.jpaTacheRepository.existsById(id);
    }

    @Override
    public void supprimer(UUID id) {
        try {
            this.jpaTacheRepository.findById(id);
        } catch (Exception e) {
            throw new NonTrouveTacheException("Aune Tâche avec l'id n'existe");
        }
    }

    @Override
    public List<TacheEssentielVM> listeTache() {
        List<TacheTable> tacheTables = this.jpaTacheRepository.findAll();
        return tacheTables.stream()
                .map(tacheMapper::tacheTableVerstachesEssentielVM)
                .toList();
    }

    @Override
    public TacheDetailVM recupererTacheDetailVMParId(UUID id) {
        TacheTable tacheTable = this.jpaTacheRepository.findById(id).orElseThrow(
                () -> new NonTrouveTacheException("Aune Tâche avec l'id n'existe")
        );
        return this.tacheMapper.tacheTableVerstachesDetailVM(tacheTable);

    }
}
