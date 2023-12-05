package com.example.address.controller;

import com.example.address.entity.Address;
import com.example.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok().body(addressService.getAll());
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        return ResponseEntity.ok().body(addressService.createAddress(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAllAddresses(@PathVariable("id") String addressId) {
        return ResponseEntity.ok().body(addressService.getByAddressId(addressId));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<Address>> getAllAddressesByStudentId(@PathVariable("id") String studentId) {
        return ResponseEntity.ok().body(addressService.getAddressesByStudentId(studentId));
    }
}
