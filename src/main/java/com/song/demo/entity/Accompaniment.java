package com.song.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "accompaniment")
@NoArgsConstructor
public class Accompaniment {

    public Accompaniment(String songId, UUID accompanimentBinaryId, String songName, String searchKeyword){
        this.id = songId;
        this.accompanimentBinaryId = accompanimentBinaryId;
        this.songName = songName;
        this.searchKeyword = searchKeyword;
    }

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private UUID accompanimentBinaryId;

    private String songName;

    private String searchKeyword;

}
