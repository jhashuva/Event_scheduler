package com.example.demo.controllers;

import com.example.demo.models.Speaker;
import com.example.demo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    @Autowired
    SpeakerRepository speakerRepository;

    @GetMapping
    List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Speaker get(@RequestBody Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @GetMapping
    @RequestMapping("{id}")
    Speaker get(@PathVariable Long id){
        return speakerRepository.getReferenceById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepository.getReferenceById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }


}
