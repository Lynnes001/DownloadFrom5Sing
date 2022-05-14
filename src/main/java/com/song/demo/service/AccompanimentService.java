package com.song.demo.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.demo.entity.Accompaniment;
import com.song.demo.entity.AccompanimentBinaryArchive;
import com.song.demo.model.SearchResults;
import com.song.demo.model.Song;
import com.song.demo.repository.AccompanimentBinaryRepository;
import com.song.demo.repository.AccompanimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccompanimentService {

    @Autowired
    AccompanimentRepository accompanimentRepository;

    @Autowired
    AccompanimentBinaryRepository accompanimentBinaryRepository;


    public List<Song> searchFrom5Sing(String keyword, int limit) throws IOException {
        String url = "http://search.5sing.kugou.com/home/json?keyword={keyword}&sort=1&page=1&filter=3&type=0";
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);

        String searchResponse = new RestTemplate().getForObject(url, String.class, params);

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SearchResults searchResults = objectMapper.readValue(searchResponse, SearchResults.class);

        return searchResults.getList().stream().limit(limit).collect(Collectors.toList());

//        return searchResults.getList().stream().limit(limit).map(Song::getDownloadurl).collect(Collectors.toList());
    }

    public Song searchFrom5Sing(String keyword) throws IOException {
        return this.searchFrom5Sing(keyword, 1).get(0);
    }

    // DownloadURL Only for now.
    public String getAccompaniment(String keyword) {

        // TODO: add a logic: get the one in the archive if searched before.

        Song song = null;

        try {
            song = this.searchFrom5Sing(keyword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AccompanimentBinaryArchive accompanimentBinaryArchive = accompanimentBinaryRepository.save(new AccompanimentBinaryArchive(SerializationUtils.serialize(song)));

        Accompaniment accompaniment = new Accompaniment(accompanimentBinaryArchive.getId(), song.getSongName(), song.getSongurl(), keyword);
        accompanimentRepository.save(accompaniment);

        return song.getDownloadurl();
    }

}

