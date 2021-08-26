package com.kelaskoding.controller;

import com.kelaskoding.entity.UserDetail;
import com.kelaskoding.repo.UserDetailRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserDetailController {

    @Autowired
    private UserDetailRepo repo;

    @PostMapping
    public UserDetail createOne(@RequestBody UserDetail user) {
        return repo.save(user);
    }

    @GetMapping
    public Iterable<UserDetail> findAll() {
        return repo.findAll();
    }
}
