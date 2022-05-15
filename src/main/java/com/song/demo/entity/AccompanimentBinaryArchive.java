package com.song.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    public AccompanimentBinaryArchive(byte[] accompanimentBinary){
        this.id = UUID.randomUUID();
        this.accompanimentBinary = accompanimentBinary;
    }

    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    private byte[] accompanimentBinary;

}
