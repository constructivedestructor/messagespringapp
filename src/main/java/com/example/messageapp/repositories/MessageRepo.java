package com.example.messageapp.repositories;

import com.example.messageapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findMessagesByAuthor(String author);
}
