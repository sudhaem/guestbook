package com.galvanize.guestbook.service;

import com.galvanize.guestbook.model.GuestComment;
import com.galvanize.guestbook.repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookService {

    @Autowired
    private GuestBookRepository guestBookRepository;

    public GuestComment addComment(GuestComment guestComment) {
        return guestBookRepository.save(guestComment);
    }

    public List<GuestComment> getAllComments() {
        return guestBookRepository.findAll();
    }
}
