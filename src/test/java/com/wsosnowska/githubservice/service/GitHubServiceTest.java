package com.wsosnowska.githubservice.service;

import com.wsosnowska.githubservice.exception.QueryParameterRequiredException;
import com.wsosnowska.githubservice.model.GithubApiResponse;
import com.wsosnowska.githubservice.model.Limit;
import com.wsosnowska.githubservice.model.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GitHubServiceTest {

    @Autowired
    GitHubService gitHubService;

    @Test
    void contextLoads() {
        assertNotNull(gitHubService);
    }

    @Test
    void shouldThrowExceptionWhenEmptyQuery() {
        assertThrows(QueryParameterRequiredException.class, () -> {
            gitHubService.getRepositories(Limit.TOP10,
                    null, null);
        });

        assertThrows(QueryParameterRequiredException.class, () -> {
            gitHubService.getRepositories(Limit.NO_LIMIT,
                    null, null);
        });

        assertThrows(QueryParameterRequiredException.class, () -> {
            gitHubService.getRepositories(null,
                    null, null);
        });
    }

    @Test
    void shouldReturnProperValuesWhenOnlyDateProvided() {
        GithubApiResponse response = gitHubService.getRepositories(null,
                LocalDate.of(2019, 3, 6), null);
        assertNotNull(response);
        List<RepositoryResponse> repositories = response.getRepositories();
        assertNotNull(repositories);
        boolean result = repositories.stream().allMatch(e -> e.getDateTime().getYear() == 2019 &&
                e.getDateTime().getMonth() == Month.MARCH && e.getDateTime().getDayOfMonth() == 6);
        assertTrue(result, "Should return only repositories with create date 2019-03-06");
    }

    @Test
    void shouldReturnProperValuesWhenOnlyLanguageProvided() {
        GithubApiResponse response = gitHubService.getRepositories(null,
                null, "java");
        assertNotNull(response);
        List<RepositoryResponse> repositories = response.getRepositories();
        assertNotNull(repositories);
        boolean result = repositories.stream().allMatch(e -> "java".equalsIgnoreCase(e.getLanguage()));
        assertTrue(result, "Should return only repositories with language java");
    }

    @Test
    void shouldReturnProperValuesWhenAllQueryParametersProvided() {
        GithubApiResponse response = gitHubService.getRepositories(null,
                LocalDate.of(2019, 3, 6), "java");
        assertNotNull(response);
        List<RepositoryResponse> repositories = response.getRepositories();
        assertNotNull(repositories);
        boolean result = repositories.stream().allMatch(e -> e.getDateTime().getYear() == 2019 &&
                e.getDateTime().getMonth() == Month.MARCH && e.getDateTime().getDayOfMonth() == 6 &&
                "java".equalsIgnoreCase(e.getLanguage()));
        assertTrue(result, "Should return only repositories with language java and create date 2019-03-06");
    }

    @Test
    void shouldReturn10Items() {
        GithubApiResponse response = gitHubService.getRepositories(Limit.TOP10,
                null, "java");
        assertNotNull(response);
        List<RepositoryResponse> repositories = response.getRepositories();
        assertNotNull(repositories);
        assertEquals(10, repositories.size());

        boolean result = repositories.stream().allMatch(e -> "java".equalsIgnoreCase(e.getLanguage()));
        assertTrue(result, "Should return only repositories with language java");
    }

    @Test
    void shouldReturn50Items() {
        GithubApiResponse response = gitHubService.getRepositories(Limit.TOP50,
                null, "python");
        assertNotNull(response);
        List<RepositoryResponse> repositories = response.getRepositories();
        assertNotNull(repositories);
        assertEquals(50, repositories.size());

        boolean result = repositories.stream().allMatch(e -> "python".equalsIgnoreCase(e.getLanguage()));
        assertTrue(result, "Should return only repositories with language python");
    }

    @Test
    void shouldReturn100Items() {
        GithubApiResponse response = gitHubService.getRepositories(Limit.TOP100,
                null, "ruby");
        assertNotNull(response);
        List<RepositoryResponse> repositories = response.getRepositories();
        assertNotNull(repositories);
        assertEquals(100, repositories.size());

        boolean result = repositories.stream().allMatch(e -> "ruby".equalsIgnoreCase(e.getLanguage()));
        assertTrue(result, "Should return only repositories with language ruby");
    }

}
