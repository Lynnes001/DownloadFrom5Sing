package com.song.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchResults {

    private List<Song> list;

    private int type;

    @Override
    public String toString() {
        return "SearchResults{" +
                "list=" + list +
                ", type=" + type +
                '}';
    }
}
