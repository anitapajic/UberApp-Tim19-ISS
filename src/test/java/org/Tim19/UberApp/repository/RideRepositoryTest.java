package org.Tim19.UberApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

class RideRepositoryTest {
    @Autowired
    private RideRepository rideRepository;

    @Test
    void findOneById() {
        rideRepository.findOneById(1);
    }
}