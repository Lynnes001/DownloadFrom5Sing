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
@Table(name = "accompaniment_search_history")
@NoArgsConstructor
public class AccompanimentSearchHistory {

    public AccompanimentSearchHistory(String searchKeyword){
        this.searchKeyword = searchKeyword;
        this.id = UUID.randomUUID();
    }

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private String searchKeyword;

    private UUID accompanimentId;

}
