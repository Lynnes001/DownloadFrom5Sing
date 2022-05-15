package com.song.demo.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class SongInfoResponse implements Serializable {

    private int code;

    private String message;

    private SongInfo data;

    private String success;
}
