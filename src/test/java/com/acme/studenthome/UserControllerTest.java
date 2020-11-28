package com.acme.studenthome;

import com.acme.studenthome.domain.model.UserAccountSystem.Auth.User;
import com.acme.studenthome.domain.service.UserAccountSystemService.Auth.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @MockBean
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /api/users/1")
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId((long) 1);
        user.setUsername("magotor130@gmail.com");
        user.setPassword("pacheco98");
        doReturn(Optional.of(user)).when(userService).getUserById((long) 1);

        mockMvc.perform(get("/api/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("magotor1304@gmail.com")))
                .andExpect(jsonPath("$.password", is("pacheco98")));
    }

    @Test
    @DisplayName("GET /api/users/1 - Not Found")
    void testGetUserByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(userService).getUserById((long) 2);
        mockMvc.perform(get("/api/users/{id}", 2))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/users")
    void testUserSave() throws Exception{
        User userToPost = new User();
        userToPost.setUsername("magotor1304@gmail.com");
        userToPost.setPassword("pacheco98");
        User userToReturn = new User();
        userToReturn.setId((long) 1);
        userToReturn.setUsername("magotor1304@gmail.com");
        userToReturn.setPassword("pacheco98");
        Gson gson = new Gson();
        doReturn(userToReturn).when(userService).createUser(any());

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userToPost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("magotor1304@gmail.com")))
                .andExpect(jsonPath("$.password", is("pacheco98")));

    }
}
