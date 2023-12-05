package com.example.address.service;

import com.example.address.entity.Address;
import com.example.address.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public Address createAddress(Address address) {
        address.setId(UUID.randomUUID().toString());
        Address createdAddress = repository.save(address);
        log.info("Address is created");
        return createdAddress;
    }

    @Override
    public List<Address> getAll() {
        List<Address> addresses = repository.findAll();
        log.info("Fetched all addresses");
        return addresses;
    }

    @Override
    public Address getByAddressId(String addressId) {
        Address address = repository.findById(addressId).get();
        log.info("Address is fetched by provided id {}", addressId);
        return address;
    }

    @Override
    public List<Address> getAddressesByStudentId(String studentId) {
        List<Address> addressesByStudentId = repository.findByStudentId(studentId);
        log.info("Address is fetched by provided student id {}", studentId);
        return addressesByStudentId;
    }
}
