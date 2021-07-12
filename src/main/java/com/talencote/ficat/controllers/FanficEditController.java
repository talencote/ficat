package com.talencote.ficat.controllers;

import com.talencote.ficat.dto.FanficDto;
import com.talencote.ficat.services.FanficEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fanfic/edit")
public class FanficEditController {

    @Autowired
    FanficEditService fanficEditService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addFanfic(@RequestBody FanficDto fanficDto, Long id) {
        return fanficEditService.addFanfic(fanficDto, id);
    }
}
