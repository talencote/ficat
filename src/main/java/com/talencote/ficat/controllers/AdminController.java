package com.talencote.ficat.controllers;

import com.talencote.ficat.payload.response.MessageResponse;
import com.talencote.ficat.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User Delete!"));
    }
}
