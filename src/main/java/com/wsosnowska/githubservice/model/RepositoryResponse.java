package com.wsosnowska.githubservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RepositoryResponse {

    @JsonProperty("full_name")
    private String name;
    @JsonProperty("html_url")
    private String url;
    @JsonProperty("created_at")
    private LocalDateTime dateTime;
    @JsonProperty("language")
    private String language;

    public RepositoryResponse(String name, String url, LocalDateTime dateTime, String language) {
        this.name = name;
        this.url = url;
        this.dateTime = dateTime;
        this.language = language;
    }

    public RepositoryResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
