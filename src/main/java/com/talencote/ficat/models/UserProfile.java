package com.talencote.ficat.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@DynamicUpdate
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String avatarUrl;

    private String description;

    private String favoriteFandoms;

    private String favoriteFanfics;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", description='" + description + '\'' +
                ", favoriteFandoms='" + favoriteFandoms + '\'' +
                ", user ID=" + user.getId() +
                '}';
    }
}
