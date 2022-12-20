package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {


    public Page<Message> findAll(Pageable pageable);
    public List<Message> findAll();

    @Query(value = "select * from message m where m.sender_id = ?1", nativeQuery = true)
    public List<Message> findAllBySenderId(Integer id);

    public Message save(Message message);
}
