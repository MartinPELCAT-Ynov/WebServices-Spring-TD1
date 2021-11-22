package com.ynov.j2eetdspring.controllers;

import com.ynov.j2eetdspring.entities.Sortie;
import com.ynov.j2eetdspring.services.LoggerService;
import com.ynov.j2eetdspring.services.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/sorties")
public class SortieController {

    @Autowired
    private SortieService sortieService;

    @Autowired
    private LoggerService loggerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Sortie> getAllSorties() {
        return this.sortieService.getAllSorties();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sortie getSortie(@PathVariable("id") Long id) {
        loggerService.log("Sortie " + id + " requested");
        return this.sortieService.getSortie(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Sortie createSortie(@RequestBody Sortie sortie) {
        return this.sortieService.createOrUpdate(sortie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Sortie updateSortie(@PathVariable("id") Long id, @RequestBody Sortie sortie) {
        sortie.setId(id);
        return this.sortieService.createOrUpdate(sortie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSortie(@PathVariable("id") Long id) {
        this.sortieService.deleteSortie(id);
        return;
    }


    @RequestMapping(value = "/{id}/join/{user}", method = RequestMethod.GET)
    public Sortie getParticipants(@PathVariable("id") Long id, @PathVariable("user") String username) {
        return this.sortieService.addUserToSortie(id, username);
    }

}
