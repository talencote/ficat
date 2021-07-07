package com.talencote.ficat.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FanficDto {

    private long id;

    private String name;

    private String description;

    private String content;

    private String fandom;

    private String imageUrl;

    private Set<String> tags = new HashSet<>();

    private String author;
}
