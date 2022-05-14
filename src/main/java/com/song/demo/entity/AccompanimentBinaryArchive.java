package com.song.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "accompaniment_binary_archive")
@NoArgsConstructor
public class AccompanimentBinaryArchive {

    public AccompanimentBinaryArchive(byte[] accompaniment_binary){
        this.id = UUID.randomUUID();
        this.accompaniment_binary = accompaniment_binary;
    }

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private byte[] accompaniment_binary;

}
