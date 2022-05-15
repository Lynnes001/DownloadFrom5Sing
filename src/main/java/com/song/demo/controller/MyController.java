package com.song.demo.controller;

import com.song.demo.entity.Test;
import com.song.demo.repository.TestRepository;
import com.song.demo.service.AccompanimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class MyController {

    @Autowired
    TestRepository repository;

    @Autowired
    AccompanimentService accompanimentService;

    @GetMapping("/get")
    public ResponseEntity<Test> aFunction(){

        Test test = repository.getById("1");


        return ResponseEntity.ok(test);
    }

    @GetMapping("/getFileById")
    public ResponseEntity<byte[]> getAccompaniment(@RequestParam("id") String id){
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".mp3\"")
                .body(accompanimentService.testGetFileById(id));
    }


}
