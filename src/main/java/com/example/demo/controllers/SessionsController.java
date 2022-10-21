package com.example.demo.controllers;


import com.example.demo.models.Session;
import com.example.demo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    SessionRepository sessionRepository;

    @GetMapping
    List<Session> list(){
        return sessionRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @GetMapping
    @RequestMapping("{id}")
    Session get(@PathVariable Long id){
        return sessionRepository.getReferenceById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    Session update(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepository.getReferenceById(id);
        BeanUtils.copyProperties(session, existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }


}
