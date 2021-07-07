package com.talencote.ficat.repository;

import com.talencote.ficat.models.Fanfic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FanficRepository extends JpaRepository<Fanfic, Long> {

    public List<Fanfic> findAllByFandom(String fandom);
}
