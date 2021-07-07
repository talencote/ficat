package com.talencote.ficat.services.impl;

import com.talencote.ficat.dto.FanficDto;
import com.talencote.ficat.models.Fanfic;
import com.talencote.ficat.models.UserProfile;
import com.talencote.ficat.payload.response.MessageResponse;
import com.talencote.ficat.repository.FanficRepository;
import com.talencote.ficat.repository.UserProfileRepository;
import com.talencote.ficat.services.FanficService;
import com.talencote.ficat.services.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FanficServiceImpl implements FanficService {

    @Autowired
    FanficRepository fanficRepository;

    @Autowired
    UserProfileServiceImpl userProfileService;

    @Override
    public ResponseEntity<?> findFanficById(long id) {
        if (fanficRepository.existsById(id)) {
            ModelMapper modelMapper = new ModelMapper();

            Fanfic fanfic = fanficRepository.findById(id).orElseThrow();
            FanficDto fanficDto = modelMapper.map(fanfic, FanficDto.class);

            Set<String> strTags = new HashSet<>();
            fanfic.getTags().forEach(t -> {
                strTags.add(t.getName().getTag());
            });

            fanficDto.setAuthor(fanfic.getAuthor().getUsername());
            fanficDto.setTags(strTags);
            return ResponseEntity.ok(fanficDto);
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Finfic not found!"));
    }

    @Override
    public ResponseEntity<?> getPopularFanfics(int page) {
        LinkedList<FanficDto> fanficDtos = new LinkedList<>();
        List<Fanfic> fanfics = fanficRepository.findAll();
        fanficsToDtos(fanficDtos, fanfics);
        return ResponseEntity.ok(fanficDtos);
    }

    @Override
    public ResponseEntity<?> getFanficsForYou(int page, long id) {
        UserProfile userProfile = userProfileService.userProfileRepository.findByUserId(
                userProfileService.userRepository.findById(id).orElseThrow().getId()).orElseThrow();
        LinkedList<FanficDto> fanficDtos = new LinkedList<>();
        if(userProfile.getFavoriteFandoms() == null) return ResponseEntity.ok(fanficDtos);
        for(String s : userProfile.getFavoriteFandoms().split(";")) {
            List<Fanfic> fanfics = fanficRepository.findAllByFandom(s);
            fanficsToDtos(fanficDtos, fanfics);
        }
        Collections.shuffle(fanficDtos);
        return ResponseEntity.ok(fanficDtos);
    }

    private void fanficsToDtos(LinkedList<FanficDto> fanficDtos, List<Fanfic> fanfics) {
        ModelMapper modelMapper = new ModelMapper();
        for (Fanfic f : fanfics) {
            f.setContent(null);
            Set<String> strTags = new HashSet<>();
            f.getTags().forEach(t -> {
                strTags.add(t.getName().getTag());
            });
            FanficDto fanficDto = modelMapper.map(f , FanficDto.class);
            fanficDto.setTags(strTags);
            fanficDto.setAuthor(f.getAuthor().getUsername());
            fanficDtos.add(fanficDto);
        }
    }
}
