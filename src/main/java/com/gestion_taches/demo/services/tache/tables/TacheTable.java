package com.gestion_taches.demo.services.tache.tables;

import com.gestion_taches.demo.core.tache.domain.objetvalue.Status;
import com.gestion_taches.demo.services.utilisateur.tables.UtilisateurTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tache")
public class TacheTable {

    @Id
    private UUID id;
    private String libele;
    @NotBlank(message = "La description ne peut pas être vide.")
    private String description;

    @NotNull(message = "La date de création est obligatoire.")
    private LocalDateTime dateCreation;
    private LocalDateTime dateEcheance;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le statut est obligatoire.")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "creerPar_id")
    private UtilisateurTable creerPar;

    @ManyToOne
    @JoinColumn(name = "assignerA_id")
    private UtilisateurTable assignerA;

}
