package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private JavaMailSender mailSender;

    public Passenger findOne(Integer id){return passengerRepository.findById(id).orElseGet(null);}

    public List<Passenger> findAll(){return passengerRepository.findAll();}

    public Page<Passenger> findAll(Pageable page){return passengerRepository.findAll(page);}

//    public Page<Integer> findAllRidesFromPassenger(Pageable page, Integer passengerId) {
//        return passengerRepository.findAllRidesFromPassenger(page, passengerId);
//    }

    public Passenger save(Passenger passenger){return passengerRepository.save(passenger);}

    public void remove(Integer id){passengerRepository.deleteById(id);}

    public Passenger findByEmail(String email){return passengerRepository.findOneByUsername(email);}

    public List<Passenger> findByNameAndSurnameAllIgnoringCase(String firstname, String lastname){
        return passengerRepository.findByNameAndSurnameAllIgnoringCase(firstname, lastname);
    }

    public void sendMail(Integer id) throws MessagingException, UnsupportedEncodingException {
        String subject = "Please verify your registration";
        String senderName = "TAAXI";

        String mailContent = "<p>Dear, user </p>";
        mailContent +="<p>Please click the link below to verify your registration:</p>";
        mailContent +="<h3><a href=\"" + "http://localhost:8085/api/passenger/activate/" + id + "\">VERIFY</a></h3>";
        mailContent +="<p>Thank you<br>TAAXI Team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("UberAppTim19@gmail.com", senderName);
        helper.setTo("tamara_dzambic@hotmail.com");
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }


}
