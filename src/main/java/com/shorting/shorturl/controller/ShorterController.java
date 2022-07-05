package com.shorting.shorturl.controller;



import com.shorting.shorturl.entities.ShorterEntity;
import com.shorting.shorturl.repository.ShorterRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/")
public class ShorterController {
    @Autowired
    private ShorterRepository shorterRepository;

    @PostMapping(path = "/")
    public String createShortUrl(@RequestParam("url") String str) throws NoSuchAlgorithmException {
        Hashids hashids = new Hashids(str);
        String hash = hashids.encode(str.length());

        if(!str.isBlank()) {
            if(!shorterRepository.existsByHash(hash)){
                ShorterEntity shorterEntity = new ShorterEntity(hash, str);
                shorterRepository.save(shorterEntity);
            }

            return hash;
        } else {
            return null;
        }
    }

    @GetMapping("/{hash}")
    public ResponseEntity redirectShorter(@PathVariable("hash") String hash)
    {
        ShorterEntity shorterEntity = shorterRepository.findByHash(hash);
        if (shorterEntity != null) {
            //если мы нашли код, то делаем редирект на ссылку
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", shorterEntity.getOriginalUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        } else {
            //не нашли, выкидываем ошибку не найден 404
            return ResponseEntity.notFound().build();
        }
    }
}
