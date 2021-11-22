package com.ynov.j2eetdspring.repositories;

import com.ynov.j2eetdspring.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortieRepository extends JpaRepository<Sortie, Long> {

}
