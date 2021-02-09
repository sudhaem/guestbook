package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.model.GuestComment;
import com.galvanize.guestbook.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guestbook/guestcomment")
public class GuestbookController {

    @Autowired
    private GuestBookService guestBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestComment addGuestComment(@RequestBody GuestComment guestComment) {
        return guestBookService.addComment(guestComment);
    }

    @GetMapping
    public List<GuestComment> getAllComments(){
        return guestBookService.getAllComments();
    }

}
