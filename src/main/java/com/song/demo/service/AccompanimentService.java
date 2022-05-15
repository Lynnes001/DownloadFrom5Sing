package com.song.demo.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.demo.entity.Accompaniment;
import com.song.demo.entity.AccompanimentBinaryArchive;
import com.song.demo.entity.AccompanimentSearchHistory;
import com.song.demo.exception.AccompanimentException;
import com.song.demo.model.SearchResults;
import com.song.demo.model.Song;
import com.song.demo.model.SongInfoResponse;
import com.song.demo.repository.AccompanimentBinaryRepository;
import com.song.demo.repository.AccompanimentRepository;
import com.song.demo.repository.AccompanimentSearchHistoryRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccompanimentService {

    @Autowired
    AccompanimentRepository accompanimentRepository;

    @Autowired
    AccompanimentBinaryRepository accompanimentBinaryRepository;

    @Autowired
    AccompanimentSearchHistoryRepository accompanimentSearchHistoryRepository;

    private final String SearchUrlBase = "http://search.5sing.kugou.com/home/json";

    private final String MobileAPIBase = "http://mobileapi.5sing.kugou.com";

    private final String APIVersion = "6.0.3";


    public List<Song> searchFrom5Sing(String keyword, int limit) {
        SearchResults searchResults;
        String url = SearchUrlBase + "?keyword={keyword}&sort=1&page=1&filter=3&type=0";
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);

        String searchResponse = new RestTemplate().getForObject(url, String.class, params);

        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            searchResults = objectMapper.readValue(searchResponse, SearchResults.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return searchResults.getList().stream().limit(limit).collect(Collectors.toList());
    }

    public Song searchFrom5Sing(String keyword) {
        return this.searchFrom5Sing(keyword, 1).get(0);
    }

    @SneakyThrows
    public Accompaniment getAccompaniment(String keyword) {
        // Search in existing database.
        Accompaniment existingAccompaniment = accompanimentRepository.findBySearchKeyword(keyword);

        if ( null == existingAccompaniment) {
            Song song = this.searchFrom5Sing(keyword); //TODO: add filter for more accurate filtering
            if (null == song) {
                throw new AccompanimentException("Not found or network issue occurs.");
            }
            return new Accompaniment(song.getSongId(), null, song.getSongName(), keyword);
        } else {
            return existingAccompaniment;
        }
    }


    private String getDownloadUrlById(String songId){
        String url = MobileAPIBase + "/song/getSongUrl" + "?songtype=bz&songid={songid}&version={apiversion}";
        Map<String, String> params = new HashMap<>();
        params.put("songid", songId);
        params.put("apiversion", APIVersion);

        SongInfoResponse response = new RestTemplate().getForObject(url, SongInfoResponse.class, params);

        if (null == response){
            return null;
        } else {
            return response.getData().getLqurl();
        }
    }

    @SneakyThrows
    private UUID downloadSongById(String songId) {
        ByteArrayOutputStream downloadFile = new ByteArrayOutputStream();

        String downloadUrl = this.getDownloadUrlById(songId);
        if (null == downloadUrl) {
            throw new AccompanimentException("Fail to get download url by song id "+songId);
        }


        try (BufferedInputStream in = new BufferedInputStream(new URL(downloadUrl).openStream())) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadFile.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        AccompanimentBinaryArchive accompanimentBinaryArchive = accompanimentBinaryRepository.save(new AccompanimentBinaryArchive(downloadFile.toByteArray()));
        return accompanimentBinaryArchive.getId();
    }

    public String getAccompanimentTest(String keyword){
        // init search history record.
        AccompanimentSearchHistory accompanimentSearchHistory = accompanimentSearchHistoryRepository.save(new AccompanimentSearchHistory(keyword));

        Accompaniment accompaniment = this.getAccompaniment(keyword);

        if (null == accompaniment.getAccompanimentBinaryId()){
            UUID binaryArchiveId = this.downloadSongById(accompaniment.getId());
            accompaniment.setAccompanimentBinaryId(binaryArchiveId);
        }


        return accompaniment.getId();

        // update hisotry
    }

    public byte[] testGetFileById(String id) {
        AccompanimentBinaryArchive accompanimentBinaryArchive = accompanimentBinaryRepository.getById(UUID.fromString(id));
        return accompanimentBinaryArchive.getAccompanimentBinary();
    }
}


