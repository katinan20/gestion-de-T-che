package com.gestion_taches.demo.services.tache.interfaces.rest;

import com.gestion_taches.demo.core.tache.applications.commandes.CreerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.DemarerTacheCommande;
import com.gestion_taches.demo.core.tache.applications.commandes.ModifierTacheCommande;
import com.gestion_taches.demo.core.tache.applications.vm.TacheDetailVM;
import com.gestion_taches.demo.core.tache.applications.vm.TacheEssentielVM;
import com.gestion_taches.demo.services.tache.interfaces.facade.query.TacheUseCaseQueryFacade;
import com.gestion_taches.demo.services.tache.interfaces.facade.usecase.TacheUseCaseFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/taches")
public class TacheRessource {

    private final TacheUseCaseFacade tacheUseCaseFacade;
    private final TacheUseCaseQueryFacade tacheUseCaseQueryFacade;

    public TacheRessource(TacheUseCaseFacade tacheUseCaseFacade, TacheUseCaseQueryFacade tacheUseCaseQueryFacade) {
        this.tacheUseCaseFacade = tacheUseCaseFacade;
        this.tacheUseCaseQueryFacade = tacheUseCaseQueryFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creerTache(@Valid @RequestBody CreerTacheCommande commande) {
        this.tacheUseCaseFacade.creer(commande);
    }

    @PutMapping
    public void modiffier(@Valid @RequestBody ModifierTacheCommande commande) {
        this.tacheUseCaseFacade.modifier(commande);
    }

    @DeleteMapping("/{id}")
    void supprimer(@PathVariable("id") UUID id) {
        this.tacheUseCaseFacade.supprimer(id);
    }

    @PutMapping("/demarer")
    void executerTche(@RequestBody DemarerTacheCommande commande) {
        this.tacheUseCaseFacade.executetache(commande);
    }

    @GetMapping
    public ResponseEntity<List<TacheEssentielVM>> listeTache() {
        List<TacheEssentielVM> tacheEssentielVMS = this.tacheUseCaseQueryFacade.listeTache();
        return ResponseEntity.ok(tacheEssentielVMS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacheDetailVM> recuperTacheParId(@PathVariable("id") UUID id) {
        TacheDetailVM tacheDetailVM = this.tacheUseCaseQueryFacade.recupererTacheDetailVM(id);
        return ResponseEntity.ok(tacheDetailVM);
    }
}
