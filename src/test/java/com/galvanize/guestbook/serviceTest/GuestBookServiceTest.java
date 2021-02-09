package com.galvanize.guestbook.serviceTest;

import com.galvanize.guestbook.model.GuestComment;
import com.galvanize.guestbook.repository.GuestBookRepository;
import com.galvanize.guestbook.service.GuestBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuestBookServiceTest {

    @Mock
    GuestBookRepository guestBookRepository;
    @InjectMocks
    GuestBookService guestBookService;

    @Test
    public void addGuestComment() {
        GuestComment guestComment = new GuestComment("Ashwini", "hello");
        when(guestBookRepository.save(guestComment)).thenReturn(guestComment);

        assertEquals(guestComment,guestBookService.addComment(guestComment));

    }

}
