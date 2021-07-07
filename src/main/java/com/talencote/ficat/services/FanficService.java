package com.talencote.ficat.services;

import com.talencote.ficat.dto.FanficDto;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;

public interface FanficService {

    public ResponseEntity<?> findFanficById(long id);

    public ResponseEntity<?> getPopularFanfics(int page);

    public ResponseEntity<?> getFanficsForYou(int page, long id);
}
