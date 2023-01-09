package org.Tim19.UberApp.service;

import org.Tim19.UberApp.dto.MessageDTO;
import org.Tim19.UberApp.model.MSGType;
import org.Tim19.UberApp.model.Message;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.User;
import org.Tim19.UberApp.repository.MessageRepository;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RideRepository rideRepository;
    public List<MessageDTO> findAllByUserId(Integer id){
        List<Message> messages = messageRepository.findAllBySenderId(id);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message m: messages) {
            MessageDTO messageDTO = new MessageDTO(m);
            messageDTOS.add(messageDTO);
        }

        return messageDTOS;
    }
    public List<Message> findAll(){return messageRepository.findAll();}

    public List<Message> findAllPanic(){return messageRepository.findByType(MSGType.PANIC);}


    private Boolean hasNull(MessageDTO messageDTO){
        if (userRepository.findOneById(messageDTO.getReceiverId()) == null
            ||userRepository.findOneById(messageDTO.getSenderId()) == null
            ||rideRepository.findOneById(messageDTO.getRideId()) == null){
            return true;
        }
        return false;
    }

    public MessageDTO save(MessageDTO messageDTO){

        if(hasNull(messageDTO)){
            return new MessageDTO();
        }
        User receiver = userRepository.findOneById(messageDTO.getReceiverId());
        User sender = userRepository.findOneById(messageDTO.getSenderId());
        Ride ride = rideRepository.findOneById(messageDTO.getRideId());



        Message message = new Message();
        message.setReceiverId(receiver);
        message.setSenderId(sender);
        message.setText(messageDTO.getText());
        message.setTime(messageDTO.getTimeOfSending());
        message.setType(messageDTO.getType());
        message.setRideId(ride);

        message = messageRepository.save(message);
        return new MessageDTO(message);
    }
}
