package com.example.address.service;

import com.example.address.entity.Address;

import java.util.List;

public interface AddressService {

    //Create New Address
    Address createAddress(Address address);

    // Fetch All Addresses
    List<Address> getAll();

    // Fetch Address By Address ID
    Address getByAddressId(String addressId);

    // Fetch Addresses By Student ID
    List<Address> getAddressesByStudentId(String studentId);
}
