package com.talencote.ficat.services;

import com.talencote.ficat.payload.request.LoginRequest;
import com.talencote.ficat.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface AuthService {

    public ResponseEntity<?> authenticateUser(@Valid LoginRequest loginRequest);

    public ResponseEntity<?> registerUser(@Valid SignupRequest signUpRequest);
}
