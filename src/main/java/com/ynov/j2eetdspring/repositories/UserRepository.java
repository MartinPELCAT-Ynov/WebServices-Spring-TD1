package com.ynov.j2eetdspring.repositories;

import com.ynov.j2eetdspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.username LIKE %?1% OR u.firstname like %?1% OR u.lastname LIKE %?1% OR u.telephone LIKE %?1%Ø")
    public List<User> searchUsers(String value);
}