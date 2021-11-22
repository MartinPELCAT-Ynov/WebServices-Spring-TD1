package com.ynov.j2eetdspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.j2eetdspring.entities.Sortie;
import com.ynov.j2eetdspring.entities.User;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class SortieWithoutParticipants extends Sortie {
    @Override
    @JsonIgnore
    public List<User> getParticipants() {
        return super.getParticipants();
    }
}
