package com.song.demo.controller;


import com.song.demo.service.AccompanimentService;
import com.song.demo.utils.AccountSignHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accompaniment")
public class AccompanimentController {

    @Autowired
    private AccompanimentService accompanimentService;

    @GetMapping
    public String search(@RequestParam("keyword") String keyword){
        // Return top 1 download url.
        return accompanimentService.getAccompanimentTest(keyword);
    }
}
