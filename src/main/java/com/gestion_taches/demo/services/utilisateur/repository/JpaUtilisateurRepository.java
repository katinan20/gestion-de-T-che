package com.gestion_taches.demo.services.utilisateur.repository;

import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUtilisateurRepository extends JpaRepository<UtilisateurTable, UUID> {
    boolean existsByEmail(String email);
}
