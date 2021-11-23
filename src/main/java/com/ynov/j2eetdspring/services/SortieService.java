package com.ynov.j2eetdspring.services;

import com.ynov.j2eetdspring.dto.SortieWithoutParticipants;
import com.ynov.j2eetdspring.entities.Sortie;
import com.ynov.j2eetdspring.entities.User;
import com.ynov.j2eetdspring.repositories.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortieService {

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private UserService userService;


    public List<Sortie> getAllSorties(Integer page, Integer limit) {
        if (page == null || limit == null) {
            return this.sortieRepository.findAll();
        }
        return this.sortieRepository.findAll(PageRequest.of(page.intValue() - 1, limit.intValue())).toList();
    }

    public Sortie getSortie(Long id) {
        return this.sortieRepository.findById(id).orElse(null);
    }

    public Sortie createOrUpdate(Sortie sortie) {
        return this.sortieRepository.save(sortie);
    }

    public void deleteSortie(Long id) {
        this.sortieRepository.deleteById(id);
    }

    public Sortie addUserToSortie(Long id, String username) {
        User user = this.userService.getUserById(username);
        Sortie sortie = this.sortieRepository.findById(id).orElse(null);
        if (user != null && sortie != null) {
            sortie.getParticipants().add(user);
            this.sortieRepository.save(sortie);
        }
        return sortie;
    }

    public Sortie removeUserFromSortie(Long id, String username) {
        User user = this.userService.getUserById(username);
        Sortie sortie = this.sortieRepository.findById(id).orElse(null);
        if (user != null && sortie != null) {
            sortie.getParticipants().remove(user);
            this.sortieRepository.save(sortie);
        }
        return sortie;
    }

    public List<Sortie> getMonthSorties(Integer year, Integer month) {
        return this.sortieRepository.sortiesInMonth(year, month);
    }


    public List<Sortie> getSortieWithNoParticipants() {
        return  this.sortieRepository.getSortieWithNoParticipants();
    }
}
