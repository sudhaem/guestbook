package com.galvanize.guestbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GuestComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String comment;

    public GuestComment(){

    }
    public GuestComment(String name, String comment) {
        this.name = name;
        this.comment = comment;

    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
