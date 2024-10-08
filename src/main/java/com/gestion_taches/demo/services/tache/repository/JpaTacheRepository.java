package com.gestion_taches.demo.services.tache.repository;

import com.gestion_taches.demo.services.tache.tables.TacheTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTacheRepository extends JpaRepository<TacheTable, UUID> {
}
