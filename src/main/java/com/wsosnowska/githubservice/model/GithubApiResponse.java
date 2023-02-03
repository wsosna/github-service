package com.wsosnowska.githubservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GithubApiResponse {

    @JsonProperty("total_count")
    private int count;
    @JsonProperty("items")
    private List<RepositoryResponse> repositories;

    public GithubApiResponse(int count, List<RepositoryResponse> repositories) {
        this.count = count;
        this.repositories = repositories;
    }

    public GithubApiResponse() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RepositoryResponse> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepositoryResponse> repositories) {
        this.repositories = repositories;
    }
}
