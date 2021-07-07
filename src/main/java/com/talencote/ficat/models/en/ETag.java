package com.talencote.ficat.models.en;

public enum ETag {
    ACTION("Action"),
    ROMANCE("Romance"),
    DETECTIVE("Detective"),
    SPORT("Sport"),
    FANTASY("Fantasy"),
    CYBERPUNK("Cyberpunk"),
    STEAMPUNK("Steampunk"),
    HORROR("Horror"),
    ANIME("Anime");

    private String tag;

    ETag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
