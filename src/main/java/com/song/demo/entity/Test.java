package com.song.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Test {

    @Id
    private String id;

    private String name;

}
