package com.wsosnowska.githubservice.http;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class HttpRequestUrl {
    private final String baseUrl;
    private final Map<String, String> urlParameters;

    public HttpRequestUrl(String baseUrl, Map<String, String> urlParameters) {
        this.baseUrl = baseUrl;
        this.urlParameters = urlParameters;
    }

    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        if (Objects.nonNull(urlParameters) && urlParameters.size() > 0) {
            stringBuilder.append("?");
            urlParameters.forEach((key, value) -> {
                stringBuilder.append(String.format("%s=%s&", key, value));
            });
            return StringUtils.chop(stringBuilder.toString());
        }
        return baseUrl;
    }
}
