package com.acme.studenthome;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.DistrictRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class LandLordPropertiesControllerTest {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private DistrictRepository districtRepository;

    @MockBean
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/landlords/{landlordId}/properties")
    void testLandLordSave() throws Exception {
        Property propertyToPost = new Property();
        propertyToPost.setRooms((long) 2);
        propertyToPost.setSize((float) 75.8);
        propertyToPost.setCost((float) 1500.50);
        propertyToPost.setActive(false);
        propertyToPost.setAddress("Mi casa");
        propertyToPost.setDistrict(districtRepository.findById((long) 1).orElse(null));
        Property propertyToReturn = new Property();
        propertyToReturn.setId((long) 1);
        propertyToReturn.setRooms((long) 2);
        propertyToReturn.setSize((float) 75.8);
        propertyToReturn.setCost((float) 1500.50);
        propertyToReturn.setActive(false);
        propertyToReturn.setAddress("Mi casa");
        Gson gson = new Gson();
        doReturn(propertyToReturn).when(propertyService).createProperty((long) 1, (long) 1, any());

        mockMvc.perform(post("/api/landlords/1/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(propertyToPost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/api/users/{userId}/landlords"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.rooms", is((long) 2)))
                .andExpect(jsonPath("$.size", is((float) 75.8)))
                .andExpect(jsonPath("$.cost", is((float) 1500.50)))
                .andExpect(jsonPath("$.active", is(false)))
                .andExpect(jsonPath("$.address", is("Mi casa")));
    }

}
