package com.talencote.ficat.services;

import com.talencote.ficat.dto.FanficDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

public interface FanficEditService {

    public ResponseEntity<?> addFanfic(@RequestBody FanficDto fanficDto, Principal principal);
}
