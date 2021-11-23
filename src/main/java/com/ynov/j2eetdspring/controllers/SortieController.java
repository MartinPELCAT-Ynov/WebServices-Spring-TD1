package com.ynov.j2eetdspring.controllers;

import com.ynov.j2eetdspring.dto.SortieWithoutParticipants;
import com.ynov.j2eetdspring.entities.Sortie;
import com.ynov.j2eetdspring.services.LoggerService;
import com.ynov.j2eetdspring.services.SortieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sorties")
public class SortieController {

    @Autowired
    private SortieService sortieService;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private ModelMapper mapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Sortie> getAllSorties(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "limit", required = false) Integer limit) {
        return this.sortieService.getAllSorties(page, limit);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sortie getSortie(@PathVariable("id") Long id) {
        loggerService.log("Sortie " + id + " requested");
        return this.sortieService.getSortie(id);
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    public Sortie createSortie(@Valid @RequestBody Sortie sortie) {
        return this.sortieService.createOrUpdate(sortie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSortie(@PathVariable("id") Long id) {
        this.sortieService.deleteSortie(id);
        return;
    }


    @RequestMapping(value = "/{id}/join/{user}", method = RequestMethod.GET)
    public Sortie addParticipant(@PathVariable("id") Long id, @PathVariable("user") String username) {
        return this.sortieService.addUserToSortie(id, username);
    }

    @RequestMapping(value = "/{id}/leave/{user}", method = RequestMethod.GET)
    public Sortie removeParticipants(@PathVariable("id") Long id, @PathVariable("user") String username) {
        return this.sortieService.removeUserFromSortie(id, username);
    }

    @RequestMapping(value = "/{year}/{month}", method = RequestMethod.GET)
    public List<SortieWithoutParticipants> getMonthSorties(@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
        return this.sortieService.getMonthSorties(year, month)
                .stream()
                .map(sortie -> mapper.map(sortie, SortieWithoutParticipants.class))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public List<Sortie> getSortieWithNoParticipants() {
        return this.sortieService.getSortieWithNoParticipants();
    }

    @RequestMapping(value = "/{id}/join", method = RequestMethod.GET)
    public Sortie joinSortie(@PathVariable("id") Long id, Principal principal) {

        String username = principal.getName();
        return this.sortieService.addUserToSortie(id, username);
    }

    @RequestMapping(value = "/{id}/leave", method = RequestMethod.GET)
    public Sortie leaveSortie(@PathVariable("id") Long id, Principal principal) {
        String username = principal.getName();
        return this.sortieService.removeUserFromSortie(id, username);
    }

}
