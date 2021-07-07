package com.talencote.ficat.repository;

import com.talencote.ficat.models.Tag;
import com.talencote.ficat.models.en.ETag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(ETag name);

    Boolean existsTagByName(ETag name);
}
