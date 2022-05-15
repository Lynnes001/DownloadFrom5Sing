package com.song.demo.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class SongInfo implements Serializable {

    private String songid;

    private String lqurl;

    private String lqurlBackup;

    private String lqsize;

    private String lqext;

}
