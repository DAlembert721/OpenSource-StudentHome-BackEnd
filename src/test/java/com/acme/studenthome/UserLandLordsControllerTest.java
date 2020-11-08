package com.acme.studenthome;

import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.repository.SubscriptionSystemRepository.SubscriptionRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.LandLordService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class UserLandLordsControllerTest {

    @Autowired
    private LandLordService landLordService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/users/{userId}/landlords")
    void testLandLordSave() throws Exception{
        LandLord landLordToPost = new LandLord();
        landLordToPost.setFirstName("mario");
        landLordToPost.setLastName("tataje");
        landLordToPost.setDni("73889623");
        landLordToPost.setSubscription(subscriptionRepository.findById((long) 1).orElse(null));
        landLordToPost.setPhone("995995408");
        LandLord landLordToReturn = new LandLord();
        landLordToPost.setId((long) 1);
        landLordToPost.setFirstName("mario");
        landLordToPost.setLastName("tataje");
        landLordToPost.setDni("73889623");
        landLordToPost.setPhone("995995408");
        Gson gson = new Gson();
        doReturn(landLordToReturn).when(landLordService).createLandLord((long) 1, (long) 1, any());

        mockMvc.perform(post("/api/users/1/landlords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(landLordToPost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/api/users/{userId}/landlords"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.first_name", is("mario")))
                .andExpect(jsonPath("$.last_name", is("tataje")))
                .andExpect(jsonPath("$.dni", is("73889623")))
                .andExpect(jsonPath("$.phone", is(995995408)));

    }
}
