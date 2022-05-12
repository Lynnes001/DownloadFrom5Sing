package com.song.demo.controller;

import com.song.demo.entity.Test;
import com.song.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class MyController {

    @Autowired
    TestRepository repository;

    @GetMapping("/get")
    public ResponseEntity<Test> aFunction(){

        Test test = repository.getById("1");


        return ResponseEntity.ok(test);
    }


}
