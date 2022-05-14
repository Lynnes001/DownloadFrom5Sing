package com.song.demo.controller;


import com.song.demo.service.AccompanimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accompaniment")
public class AccompanimentController {


    @Autowired
    private AccompanimentService accompanimentService;

    @GetMapping
    public String search(@RequestParam("keyword") String keyword){
        //TODO: save to search history

        // Return 5 download urls for self-downloading
        return accompanimentService.getAccompaniment(keyword);
    }
}
