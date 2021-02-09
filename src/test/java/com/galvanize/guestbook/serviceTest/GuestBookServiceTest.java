package com.galvanize.guestbook.serviceTest;

import com.galvanize.guestbook.model.GuestComment;
import com.galvanize.guestbook.repository.GuestBookRepository;
import com.galvanize.guestbook.service.GuestBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        verify(guestBookRepository, times(1)).save(guestComment);

    }

   @Test
    public  void getAllComments(){
       GuestComment guestComment1 = new GuestComment("Ashwini", "hello");
       GuestComment guestComment2 = new GuestComment("Sudha", "Beautiful place");
       List<GuestComment> commentList = List.of(guestComment1, guestComment2);

       when(guestBookRepository.findAll()).thenReturn(commentList);
       assertEquals(commentList, guestBookService.getAllComments());
       verify(guestBookRepository, times(1)).findAll();
   }

}
