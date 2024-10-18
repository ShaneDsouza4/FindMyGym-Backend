package com.lambda.FindMyGym.service;
import java.util.List;
import com.lambda.FindMyGym.model.GymDetails;
import com.lambda.FindMyGym.model.Task;
import com.lambda.FindMyGym.repository.GymDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GymDetailsService {

    @Autowired
    private GymDetailsRepository repository;

    public void addGymDetailsBulk(List<GymDetails> gymList) {
        repository.saveAll(gymList);
    }
}
