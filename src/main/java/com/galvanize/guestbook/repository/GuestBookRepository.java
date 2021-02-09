package com.galvanize.guestbook.repository;

import com.galvanize.guestbook.model.GuestComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestComment, Long> {
}
