package com.talencote.ficat.controllers;

import com.talencote.ficat.services.FanficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fanfic")
public class FanficController {

    @Autowired
    FanficService fanficService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findFanficById(@PathVariable long id) {
        return fanficService.findFanficById(id);
    }

    @GetMapping("/popular/{page}")
    public ResponseEntity<?> popularFanfics(@PathVariable int page) {
        return fanficService.getPopularFanfics(page);
    }

    @GetMapping("/foryou/{id}/{page}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> foryouFanfics(@PathVariable int page, @PathVariable long id) {
        return fanficService.getFanficsForYou(page, id);
    }
}
