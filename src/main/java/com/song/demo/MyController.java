package com.song.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class MyController {

    @GetMapping("/get")
    public ResponseEntity<String> aFunction(){
        return ResponseEntity.ok("Here you are.");
    }


}
