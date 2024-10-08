package com.gestion_taches.demo.services.utilisateur.tables;

import com.gestion_taches.demo.services.tache.tables.TacheTable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "utilisateurs")
public class UtilisateurTable {

    @Id
    private UUID id;
    @NotBlank(message = "Le nom est obligatoire.")
    private String nom;

    @Email(message = "L'email doit être valide.")
    @NotBlank(message = "L'email est obligatoire.")
    private String email;

    @OneToMany(mappedBy = "assignerA")
    private List<TacheTable> tacheTables;


}
