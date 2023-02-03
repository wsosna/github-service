package com.wsosnowska.githubservice.exception;

public class QueryParameterRequiredException extends RuntimeException {

    public QueryParameterRequiredException() {
        super("At least one query parameter is required");
    }
}
