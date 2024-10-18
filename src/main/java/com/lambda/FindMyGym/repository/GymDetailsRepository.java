package com.lambda.FindMyGym.repository;

import com.lambda.FindMyGym.model.GymDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GymDetailsRepository extends MongoRepository<GymDetails, String> {
}
