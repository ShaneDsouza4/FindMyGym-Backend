package com.lambda.FindMyGym.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class GymDetails {

    private String ID;
    private String gym;
    private  String city;
    private  String province;
    private  String GymLocationPhoneElement;
    private  String GymPinLocationElement;
    private  String GymLocationNameElement;
    private  List<String> GymAmenitiesArrayList;
    private  Integer GymAmenitieslength;
    private  String GymLocationAddressElement;
    private  String GymLocationAddressStreetNumber;
    private  String GymLocationAddressStreetName;
    private  String GymLocationAddressCity;
    private  String GymLocationAddressProvince;
    private  String GymLocationAddressPostalCode;
    private  String GymPinLocationLatitude;
    private  String GymPinLocationLongitude;
    private  String membershipName;
    private  String membershipPrice;
    private  String membershipDuration;


}
