package com.api.zorvanz.controller;

import com.api.zorvanz.domain.users.UserService;
import com.api.zorvanz.infra.security.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // Solo carga el contexto del controlador
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserService userService;

    @Test
    void testRegisterSuccess () throws Exception {
        String jsonRequest = """
                    {
                        "username": "john_doe",
                        "email": "john@example.com",
                        "password": "123456"
                    }
                """;

        Mockito.when ( userService.registerUser ( Mockito.any () ) )
                .thenReturn ( CompletableFuture.completedFuture ( "fake-token" ) );

        mockMvc.perform ( post ( "/api/auth/register" )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .content ( jsonRequest ) )
                .andExpect ( status ().isOk () )
                .andExpect ( content ().string ( "fake-token" ) );
    }

    @Test
    void testRegisterFailure () throws Exception {
        String jsonRequest = """
                    {
                        "username": "john_doe",
                        "email": "john@example.com",
                        "password": "123456"
                    }
                """;

        Mockito.when ( userService.registerUser ( Mockito.any () ) )
                .thenReturn ( CompletableFuture.failedFuture ( new RuntimeException ( "fail" ) ) );

        mockMvc.perform ( post ( "/api/auth/register" )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .content ( jsonRequest ) )
                .andExpect ( status ().isBadRequest () );
    }

    @Test
    void testRefreshSuccess () throws Exception {
        String refreshToken = "refresh-token";
        String accessToken = "access-token";

        Mockito.when ( userService.refreshAccessToken ( refreshToken ) )
                .thenReturn ( CompletableFuture.completedFuture ( accessToken ) );

        mockMvc.perform ( post ( "/api/auth/refresh" )
                        .param ( "refreshToken", refreshToken ) )
                .andExpect ( status ().isOk () )
                .andExpect ( jsonPath ( "$.accessToken" ).value ( accessToken ) )
                .andExpect ( jsonPath ( "$.refreshToken" ).value ( refreshToken ) )
                .andExpect ( jsonPath ( "$.tokenType" ).value ( "Bearer" ) );
    }

    @Test
    void testRefreshFailure () throws Exception {
        String refreshToken = "bad-token";

        Mockito.when ( userService.refreshAccessToken ( refreshToken ) )
                .thenReturn ( CompletableFuture.failedFuture ( new RuntimeException ( "invalid" ) ) );

        mockMvc.perform ( post ( "/api/auth/refresh" )
                        .param ( "refreshToken", refreshToken ) )
                .andExpect ( status ().isBadRequest () );
    }
}
