package com.practice.student.client;

import com.practice.student.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE")
public interface AddressClient {

    @GetMapping("/addresses/students/{studentId}")
    List<Address> getAddressesByStudentId(@PathVariable("studentId") String studentId);
}
