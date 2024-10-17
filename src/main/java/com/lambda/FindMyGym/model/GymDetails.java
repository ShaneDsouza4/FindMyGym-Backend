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

    public GymDetails(String id, String name, String location, String city, String province, String phone, String pinLocation, List<String> amenities,
                      String membershipName, String membershipDuration, Integer GymAmenitieslength, String GymLocationAddressElement,String GymLocationAddressStreetNumber,
                      String GymLocationAddressCity,String GymLocationAddressProvince,String GymPinLocationLatitude,String GymPinLocationLongitude,
                      String GymLocationAddressPostalCode, String GymLocationAddressStreetName, String membershipPrice){
        this.ID = id;
        this.gym = name;
        this.GymLocationNameElement = location;
        this.city = city;
        this.province = province;
        this.GymLocationPhoneElement = phone;
        this.GymPinLocationElement = pinLocation;
        this.GymAmenitiesArrayList = amenities;
        this.membershipName = membershipName;
        this.membershipPrice = membershipPrice;
        this.GymAmenitieslength = GymAmenitieslength;
        this.GymLocationAddressElement = GymLocationAddressElement;
        this.GymLocationAddressStreetNumber = GymLocationAddressStreetNumber;
        this.GymLocationAddressCity = GymLocationAddressCity;
        this.GymLocationAddressStreetName = GymLocationAddressStreetName;
        this.GymLocationAddressProvince = GymLocationAddressProvince;
        this.GymLocationAddressPostalCode = GymLocationAddressPostalCode;
        this.GymPinLocationLatitude = GymPinLocationLatitude;
        this.membershipDuration = membershipDuration;
        this.GymPinLocationLongitude = GymPinLocationLongitude;


    }

    public String getID() {
        return ID;
    }

    public String getGymLocationNameElement() {
        return GymLocationNameElement;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getGymLocationPhoneElement() {
        return GymLocationPhoneElement;
    }

    public String getGymPinLocationElement() {
        return GymPinLocationElement;
    }

    public List<String> getGymAmenitiesArrayList() {
        return GymAmenitiesArrayList;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public String getMembershipPrice() {
        return membershipPrice;
    }

    public String getMembershipDuration() {
        return membershipDuration;
    }

    public Integer getGymAmenitieslength() {
        return GymAmenitieslength;
    }

    public String getGymLocationAddressElement() {
        return GymLocationAddressElement;
    }

    public String getGymLocationAddressStreetNumber() {
        return GymLocationAddressStreetNumber;
    }

    public String getGymLocationAddressStreetName() {
        return GymLocationAddressStreetName;
    }

    public String getGymLocationAddressCity() {
        return GymLocationAddressCity;
    }

    public String getGymLocationAddressProvince() {
        return GymLocationAddressProvince;
    }

    public String getGymLocationAddressPostalCode() {
        return GymLocationAddressPostalCode;
    }

    public String getGymPinLocationLatitude() {
        return GymPinLocationLatitude;
    }

    public String getGymPinLocationLongitude() {
        return GymPinLocationLongitude;
    }

    public String getGym() {
        return gym;
    }
}
