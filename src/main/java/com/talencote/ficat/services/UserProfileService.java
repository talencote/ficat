package com.talencote.ficat.services;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserProfileService {

    public ResponseEntity<?> setAvatar(String url, Long id);

    public ResponseEntity<?> testToken();

    public ResponseEntity<?> addFavoriteFandom(String fandom, Long id);

    public ResponseEntity<?> removeFavoriteFandom(String fandom, Long id);

    public ResponseEntity<?> addFavoriteFanfic(String fanfic, Long id);

    public ResponseEntity<?> removeFavoriteFanfic(String fanfic, Long id);

    public ResponseEntity<?> getFavoriteFandoms(Long id);

    public ResponseEntity<?> postFavoriteFandoms(String fandoms, Long id);
}
