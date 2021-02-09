package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.model.GuestComment;
import com.galvanize.guestbook.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GuestbookController {

    @Autowired
    private GuestBookService guestBookService;

    @PostMapping("/guestbook/guestcomment")
    @ResponseStatus(HttpStatus.CREATED)
    public GuestComment addGuestComment(@RequestBody GuestComment guestComment) {
        return guestBookService.addComment(guestComment);
    }
}
