package com.ynov.j2eetdspring.controllers;

import com.ynov.j2eetdspring.entities.User;
import com.ynov.j2eetdspring.services.LoggerService;
import com.ynov.j2eetdspring.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoggerService loggerService;

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "username") String username) {
        return userService.getUserById(username);
    }

    @Operation(summary = "Création ou mise à jour d'un utilisateur")
    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public User addOrUpdateUser(@Valid @RequestBody User user) {
        loggerService.log(user.toString());
        return userService.createOrUpdate(user);
    }

    @Operation(summary = "Récupération de tous les utilisateurs")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Suppression d'un utilisateur à partir de son identifiant")
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "username") String username) {
        userService.deleteUser(username);
    }


    @Operation(summary = "Mise à jour du mot de passe d'un utilisateur")
    @RequestMapping(path = "/updatePassword", method = RequestMethod.POST)
    public void setPassword(@RequestParam(value = "userName") String userName,
                            @RequestParam(value = "new") String newPassword) {
        userService.setPassword(userName, newPassword);
    }
}
