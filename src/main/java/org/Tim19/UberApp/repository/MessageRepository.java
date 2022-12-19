package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {


    public Page<Message> findAll(Pageable pageable);

    public List<Message> findAllBySenderId(Integer id);

    public Message save(Message message);
}
