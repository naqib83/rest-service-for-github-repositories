package com.github.api.controller;

import com.github.api.model.GithubRepositoriesResponse;
import com.github.api.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RepositoryController {

    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/repositories")
    public List<Repository> repositories(@RequestParam(name = "q") String query) {
        ResponseEntity<GithubRepositoriesResponse> repositories =
                restTemplate.getForEntity(String.format("https://api.github.com/search/repositories?q=%s+language:php+language:javascript", query), GithubRepositoriesResponse.class);
        return repositories.getBody().getItems().stream().filter(item -> item.getName().contains(query)).collect(Collectors.toList());
    }
}
