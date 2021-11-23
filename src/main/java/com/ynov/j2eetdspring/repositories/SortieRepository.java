package com.ynov.j2eetdspring.repositories;

import com.ynov.j2eetdspring.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortieRepository extends JpaRepository<Sortie, Long> {

    @Query("select s from Sortie s where s.nom LIKE %?1% OR s.lieu like %?1% OR s.description LIKE %?1%" )
    public List<Sortie> searchSorties(String value);


    @Query("select s from Sortie s where year(s.date) = ?1 and month(s.date) = ?2")
    public List<Sortie> sortiesInMonth(Integer year, Integer month);

    @Query("select s from Sortie s where size(s.participants) = 0")
    List<Sortie> getSortieWithNoParticipants();
}
