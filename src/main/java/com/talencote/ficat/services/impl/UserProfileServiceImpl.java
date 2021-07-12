package com.talencote.ficat.services.impl;

import com.talencote.ficat.dto.StringIdDto;
import com.talencote.ficat.models.UserProfile;
import com.talencote.ficat.payload.response.MessageResponse;
import com.talencote.ficat.repository.UserProfileRepository;
import com.talencote.ficat.repository.UserRepository;
import com.talencote.ficat.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public ResponseEntity<?> setAvatar(String url, Long id) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        userProfile.setAvatarUrl(url);
        userProfileRepository.save(userProfile);
        return ResponseEntity.ok(new MessageResponse("Avatar changed!"));
    }

    @Override
    public ResponseEntity<?> testToken() {
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<?> addFavoriteFandom(StringIdDto dto) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(dto.getId()).orElseThrow().getId()).orElseThrow();
        if (userProfile.getFavoriteFandoms() == null) {
            userProfile.setFavoriteFandoms(dto.getString() + ";");
        } else {
            userProfile.setFavoriteFandoms(userProfile.getFavoriteFandoms() + dto.getString() + ";");
        }
        userProfileRepository.save(userProfile);
        return ResponseEntity.ok(new MessageResponse("Fandom added to favorites!"));
    }

    @Override
    public ResponseEntity<?> removeFavoriteFandom(StringIdDto dto) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(dto.getId()).orElseThrow().getId()).orElseThrow();
        if (userProfile.getFavoriteFandoms() == null) {
            userProfile.setFavoriteFandoms("");
            userProfileRepository.save(userProfile);
        } else {
            removeSomethingFromList(dto.getString(), userProfile);
        }

        return ResponseEntity.ok(new MessageResponse("Fandom removed from favorites!"));
    }

    @Override
    public ResponseEntity<?> addFavoriteFanfic(String fanfic, Long id) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        if (userProfile.getFavoriteFanfics() != null) {
            userProfile.setFavoriteFanfics(userProfile.getFavoriteFanfics() + fanfic + ";");
        } else {
            userProfile.setFavoriteFanfics(fanfic + ";");
        }
        userProfileRepository.save(userProfile);
        return ResponseEntity.ok(new MessageResponse("Fanfic added to favorites!"));
    }

    @Override
    public ResponseEntity<?> removeFavoriteFanfic(String fanfic, Long id) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        if (userProfile.getFavoriteFanfics() != null) {
            List<String> fanficsList = new LinkedList<>(Arrays.asList(userProfile.getFavoriteFanfics().split(";")));
            if (fanficsList.size() == 1) {
                userProfile.setFavoriteFandoms("");
                userProfileRepository.save(userProfile);
            } else {
                removeSomethingFromList(fanfic, userProfile);
            }
        }

        return ResponseEntity.ok(new MessageResponse("Fanfic removed from favorites!"));
    }

    @Override
    public ResponseEntity<?> getFavoriteFandoms(Long id) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        return ResponseEntity.ok(new MessageResponse(userProfile.getFavoriteFandoms()));
    }

    @Override
    public ResponseEntity<?> postFavoriteFandoms(String fandoms, Long id) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        userProfile.setFavoriteFandoms(fandoms);
        userProfileRepository.save(userProfile);
        return ResponseEntity.ok(new MessageResponse("Fandoms saved"));
    }

    @Override
    public ResponseEntity<?> getUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findByUserId(
                userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        userProfile.setUser(null);
        return ResponseEntity.ok(userProfile);
    }

    private void removeSomethingFromList(String str, UserProfile userProfile) {
        List<String> stringList = new LinkedList<String>(Arrays.asList(userProfile.getFavoriteFandoms().split(";")));
        if (stringList.size() == 1) {
            userProfile.setFavoriteFandoms("");
        } else {
            stringList.remove(str);
            StringBuilder newFavorites= new StringBuilder();
            for (String e : stringList) {
                newFavorites.append(e).append(";");
            }
            userProfile.setFavoriteFandoms(newFavorites.toString());
        }
        userProfileRepository.save(userProfile);
    }
}
