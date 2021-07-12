package com.talencote.ficat.services;

import com.talencote.ficat.dto.StringIdDto;
import org.springframework.http.ResponseEntity;

public interface UserProfileService {

    public ResponseEntity<?> setAvatar(String url, Long id);

    public ResponseEntity<?> testToken();

    public ResponseEntity<?> addFavoriteFandom(StringIdDto dto);

    public ResponseEntity<?> removeFavoriteFandom(StringIdDto dto);

    public ResponseEntity<?> addFavoriteFanfic(String fanfic, Long id);

    public ResponseEntity<?> removeFavoriteFanfic(String fanfic, Long id);

    public ResponseEntity<?> getFavoriteFandoms(Long id);

    public ResponseEntity<?> postFavoriteFandoms(String fandoms, Long id);

    public ResponseEntity<?> getUserProfile(Long id);
}
