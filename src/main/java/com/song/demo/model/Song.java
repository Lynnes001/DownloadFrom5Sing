package com.song.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Song implements Serializable {

    private String createTime;

    private String originSinger;

    private String songName;

    private int status;

    private int collectCnt;

    private String style;

    private int downloadCnt;

    private int playCnt;

    private String singer;

    private String postProduction;

    private int popularityCnt;

    private String songWriter;

    private String composer;

    private String songId;

    private String optComposer;

    private String ext;

    private int songSize;

    private String nickName;

    private String singerId;

    private int type;
    private String typeName;

    private String songurl;

    private String downloadurl;

    private String typeEname;

    @Override
    public String toString() {
        return "Song{" +
                "createTime='" + createTime + '\'' +
                ", originSinger='" + originSinger + '\'' +
                ", songName='" + songName + '\'' +
                ", status=" + status +
                ", collectCnt=" + collectCnt +
                ", style='" + style + '\'' +
                ", downloadCnt=" + downloadCnt +
                ", playCnt=" + playCnt +
                ", singer='" + singer + '\'' +
                ", postProduction='" + postProduction + '\'' +
                ", popularityCnt=" + popularityCnt +
                ", songWriter='" + songWriter + '\'' +
                ", composer='" + composer + '\'' +
                ", songId='" + songId + '\'' +
                ", optComposer='" + optComposer + '\'' +
                ", ext='" + ext + '\'' +
                ", songSize=" + songSize +
                ", nickName='" + nickName + '\'' +
                ", singerId='" + singerId + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", songurl='" + songurl + '\'' +
                ", downloadurl='" + downloadurl + '\'' +
                ", typeEname='" + typeEname + '\'' +
                '}';
    }
}
