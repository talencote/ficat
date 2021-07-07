package com.talencote.ficat.controllers;

import com.talencote.ficat.models.Tag;
import com.talencote.ficat.models.en.ETag;
import com.talencote.ficat.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @PostMapping("/update_tags")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateTagsTable() {
        for (ETag etag : ETag.values()) {
            if (!tagRepository.existsTagByName(etag)) {
                System.out.println("123");
                Tag tag = new Tag();
                tag.setName(etag);
                tagRepository.save(tag);
            }
        }
        return "Tags updated!";
    }
}
