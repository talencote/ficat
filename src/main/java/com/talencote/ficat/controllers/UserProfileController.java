package com.talencote.ficat.controllers;

import com.talencote.ficat.dto.FandomsDto;
import com.talencote.ficat.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @PostMapping("/test_token")
    public ResponseEntity<?> testToken() {
        return userProfileService.testToken();
    }

    @PostMapping("/add_favorite_fandom")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addFanfic(@RequestBody String fandom, @RequestBody Long id) {
        return userProfileService.addFavoriteFandom(fandom, id);
    }

    @PostMapping("/remove_favorite_fandom")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeFanfic(@RequestBody String fandom, @RequestBody Long id) {
        return userProfileService.removeFavoriteFandom(fandom, id);
    }

    @PostMapping("/set_avatar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> setAvatar(@RequestBody String url, @RequestBody Long id) {
        return userProfileService.setAvatar(url, id);
    }

    @GetMapping("/{id}/favorite_fandoms_string")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getFandomsString(@PathVariable Long id) {
        return userProfileService.getFavoriteFandoms(id);
    }

    @PostMapping("/{id}/favorite_fandoms_string/{fandoms}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> setFandomsString(@PathVariable Long id, @RequestBody FandomsDto fandoms) {
        return userProfileService.postFavoriteFandoms(fandoms.getFandoms(), id);
    }
}
