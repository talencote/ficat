package com.talencote.ficat.services.impl;

import com.talencote.ficat.dto.FanficDto;
import com.talencote.ficat.models.Fanfic;
import com.talencote.ficat.models.Tag;
import com.talencote.ficat.models.en.ETag;
import com.talencote.ficat.payload.response.MessageResponse;
import com.talencote.ficat.repository.FanficRepository;
import com.talencote.ficat.repository.TagRepository;
import com.talencote.ficat.repository.UserRepository;
import com.talencote.ficat.services.FanficEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class FanficEditServiceImpl implements FanficEditService {

    @Autowired
    UserRepository userRepository; // ???? Hz tak mojno ili net   --- Nado checknyc'

    @Autowired
    TagRepository tagRepository;

    @Autowired
    FanficRepository fanficRepository;

    @Override
    public ResponseEntity<?> addFanfic(FanficDto fanficDto, Long id) {

        Fanfic fanfic = new Fanfic(
                fanficDto.getName(),
                fanficDto.getDescription(),
                fanficDto.getContent(),
                fanficDto.getFandom(),
                fanficDto.getImageUrl(),
                userRepository.findById(id).orElseThrow()
        );

        Set<String> strTags = fanficDto.getTags();
        Set<Tag> tagSet = new HashSet<>();

        if (strTags == null) {
            fanfic.setTags(tagSet);
        } else {
            for (ETag etag : ETag.values()) {
                if (strTags.contains(etag.getTag())) {
                    Tag tag = tagRepository.findByName(etag).
                            orElseThrow(() -> new RuntimeException("Error: Tag is not found."));
                    tagSet.add(tag);
                }
            }
        }

        fanfic.setTags(tagSet);
        fanfic.setCreateDate(new Date());
        fanficRepository.save(fanfic);

        return ResponseEntity.ok(new MessageResponse("Fanfic registered successfully!"));
    }
}
