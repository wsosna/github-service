package com.wsosnowska.githubservice.controller;

import com.wsosnowska.githubservice.model.GithubApiResponse;
import com.wsosnowska.githubservice.model.Limit;
import com.wsosnowska.githubservice.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/github")
public class GithubController {

    private final GitHubService gitHubService;

    public GithubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/repositories")
    public GithubApiResponse getRepositories(@RequestParam(required = false) Limit limit,
                                             @RequestParam(required = false) LocalDate date,
                                             @RequestParam(required = false) String language) {
        return gitHubService.getRepositories(limit, date, language);
    }
}
