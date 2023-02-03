package com.wsosnowska.githubservice.service;

import com.wsosnowska.githubservice.exception.QueryParameterRequiredException;
import com.wsosnowska.githubservice.http.HttpRequestUrl;
import com.wsosnowska.githubservice.model.GithubApiResponse;
import com.wsosnowska.githubservice.model.Limit;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    public GitHubService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GithubApiResponse getRepositories(final Limit limit, final LocalDate date, final String programmingLanguage) {
        final HttpRequestUrl httpRequestUrl = new HttpRequestUrl("/search/repositories",
                buildParameters(limit, date, programmingLanguage));

        return restTemplate.getForObject(httpRequestUrl.getUrl(), GithubApiResponse.class);
    }

    private Map<String, String> buildParameters(final Limit limit, final LocalDate date, final String programmingLanguage) {
        final Map<String, String> parameters = new HashMap<>();
        final String query = buildQueryParameter(date, programmingLanguage);
        if (Strings.isNotBlank(query)) {
            parameters.put("q", query);
        }
        mapLimit(limit, parameters);
        parameters.put("sort", "stars");
        parameters.put("order", "desc");
        return parameters;
    }

    private void mapLimit(final Limit limit, final Map<String, String> parameters) {
        if (Objects.nonNull(limit)) {
            switch (limit) {
                case TOP10 -> parameters.put("per_page", "10");
                case TOP50 -> parameters.put("per_page", "50");
                case TOP100 -> parameters.put("per_page", "100");
            }
        }
    }

    /**
     * @param date                LocalDate
     * @param programmingLanguage String
     * @return string with parameters in proper format, example created:>2019-01-10+language:java,
     * if no parameters provided method will return null
     */
    private String buildQueryParameter(final LocalDate date, final String programmingLanguage) {
        if (Objects.isNull(date) && Objects.isNull(programmingLanguage)) {
            throw new QueryParameterRequiredException();
        }

        final Map<String, String> parameters = new HashMap<>();
        mapDate(date, parameters);
        mapLanguage(programmingLanguage, parameters);

        if (parameters.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            parameters.forEach((key, value) -> stringBuilder.append(String.format("%s:%s+", key, value)));

            return StringUtils.chop(stringBuilder.toString());
        } else {
            return null;
        }
    }

    private void mapDate(final LocalDate date, final Map<String, String> parameters) {
        if (Objects.nonNull(date)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parameters.put("created", date.format(dateTimeFormatter));
        }
    }

    private void mapLanguage(final String language, final Map<String, String> parameters) {
        if (Strings.isNotBlank(language)) {
            parameters.put("language", language);
        }
    }
}
