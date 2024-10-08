package com.gestion_taches.demo.services.utilisateur.interfaces.rest;

import com.gestion_taches.demo.core.utilisateur.applications.commandes.CreerUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.commandes.ModifierUtilisateurCommande;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateurDetailVM;
import com.gestion_taches.demo.core.utilisateur.applications.vm.UtilisateursEssentielVM;
import com.gestion_taches.demo.services.utilisateur.interfaces.facade.usecase.UtilisateurUseCaseFacade;
import com.gestion_taches.demo.services.utilisateur.interfaces.facade.usecase.impl.UtilisateurUseCaseFacadeImpl;
import com.gestion_taches.demo.services.utilisateur.interfaces.facade.query.UtilisateurUseCaseQueryFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurRessource {

    private final UtilisateurUseCaseQueryFacade utilisateurQueryFacade;
    private final UtilisateurUseCaseFacade utilisateurUseCaseFacade;

    public UtilisateurRessource(UtilisateurUseCaseQueryFacade utilisateurQueryFacade, UtilisateurUseCaseFacade utilisateurUseCaseFacade) {
        this.utilisateurQueryFacade = utilisateurQueryFacade;
        this.utilisateurUseCaseFacade = utilisateurUseCaseFacade;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creerUtilisateur(@Valid @RequestBody CreerUtilisateurCommande commande){
        this.utilisateurUseCaseFacade.creer(commande);
    }

    @PutMapping
    public void modifierUtilisateur(@Valid @RequestBody ModifierUtilisateurCommande commande){
        this.utilisateurUseCaseFacade.modifierUtilisateur(commande);
    }

    @DeleteMapping("/{UtilisateurId}")
    @ResponseStatus(HttpStatus.OK)
    public void supprimerUtilisateur(@PathVariable("UtilisateurId")UUID id){
        this.utilisateurUseCaseFacade.supprimerUtilisatur(id);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateursEssentielVM>> listeUtilisatur(){
        List<UtilisateursEssentielVM> utilisateursEssentielVMList = this.utilisateurQueryFacade.listeUtilisateurVM();
        return ResponseEntity.ok(utilisateursEssentielVMList);
    }

    @GetMapping("/{UtilisateurId}")
    public ResponseEntity<UtilisateurDetailVM> recupererUtilisateurPArId(@PathVariable("UtilisateurId") UUID id){
        UtilisateurDetailVM utilisateurDetailVM = this.utilisateurQueryFacade.recuperParIdUtilisateurUtilisateurVM(id);
        return ResponseEntity.ok(utilisateurDetailVM);
    }
}
