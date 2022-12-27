package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.MessageDTO;
import org.Tim19.UberApp.dto.PaginatedData.*;
import org.Tim19.UberApp.dto.PanicDTO;
import org.Tim19.UberApp.dto.PanicUserDTO;
import org.Tim19.UberApp.model.Message;
import org.Tim19.UberApp.model.VehicleType;
import org.Tim19.UberApp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/api/panic")

public class PanicController {
    @Autowired
    private MessageService messageService;
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllPanics(){
        List<Message> messages = messageService.findAllPanic();
        Set<MessageDTO> messageDTOS = new HashSet<>();

        for (Message m: messages) {
            MessageDTO messageDTO = new MessageDTO(m);
            messageDTOS.add(messageDTO);
        }



        Map<String, Object> response = new HashMap<>();
        response.put("totalCount",messageDTOS.size());
        response.put("results", messageDTOS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}