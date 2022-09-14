package com.ciandt.summit.bootcamp2022.application.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServicePort userServicePort;

    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId){
        if(userId == null || userId.equals(" "))
            throw new InvalidParameterException("Um id deve ser informado");
        return ResponseEntity.ok(userServicePort.getUserById(userId));
    }
}
