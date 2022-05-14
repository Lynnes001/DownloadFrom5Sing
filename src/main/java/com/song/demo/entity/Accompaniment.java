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

    public Accompaniment(UUID accompanimentBinaryId, String songName, String downloadUrl, String searchKeyword){
        this.id = UUID.randomUUID();
        this.accompanimentBinaryId = accompanimentBinaryId;
        this.songName = songName;
        this.downloadUrl = downloadUrl;
        this.searchKeyword = searchKeyword;
    }

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private UUID accompanimentBinaryId;

    private String songName;

    private String downloadUrl;

    private String searchKeyword;

}
