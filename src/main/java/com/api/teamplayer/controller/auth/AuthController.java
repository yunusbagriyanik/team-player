package com.api.teamplayer.controller.auth;

import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.dto.auth.AuthenticationRequest;
import com.api.teamplayer.dto.user.CreateUserRequest;
import com.api.teamplayer.dto.user.User;
import com.api.teamplayer.service.authentication.AuthenticationService;
import com.api.teamplayer.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/registerUser")
    public ResponseEntity<GenericResult<User>> registerUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(this.userService.registerUser(request));
    }

    @PostMapping
    public ResponseEntity<GenericResult<User>> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(this.authenticationService.authenticate(request));
    }
}
