package com.talencote.ficat.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fanfics")
@Data
@NoArgsConstructor
@DynamicUpdate
public class Fanfic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @Column(columnDefinition = "longtext")
    private String content;

    private String fandom;

    private String imageUrl;

    private Date createDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fanfic_tags",
        joinColumns = @JoinColumn(name = "fanfic_id"),
        inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;

    public String getContent() {
        return (this.content != null) ? this.content : "content";
    }

    public Fanfic(String name, String description, String content, String fandom, String imageUrl, User author) {
        this.name = name;
        this.description = description;
        this.content = content;
        this.fandom = fandom;
        this.imageUrl = imageUrl;
        this.author = author;
    }

    public Date getCreateDate() {
        return (createDate != null) ? this.createDate : new Date(1);
    }

    @Override
    public String toString() {
        return "Fanfic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fandom='" + fandom + '\'' +
                ", imageUrl='" + imageUrl +
                '}';
    }
}
