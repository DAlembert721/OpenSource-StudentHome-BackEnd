package com.acme.studenthome;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.StudentOpinion;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.LandLordRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentOpinionRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentOpinionService;
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
public class StudentOpinionsControllerTest {

    @Autowired
    private LandLordRepository landLordRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentOpinionService studentOpinionService;

    @MockBean
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/landlords/{landLordId}/students/{studentId}/studentOpinions")
    void testStudentOpinionSave() throws Exception{

        StudentOpinion newStudentOpinion = new StudentOpinion();
        newStudentOpinion.setContent("Ha pagado a tiempo, pero a veces hacía bulla");
        newStudentOpinion.setScore((long)500);
        newStudentOpinion.setStudent(studentRepository.findById((long) 1).orElse(null));
        newStudentOpinion.setLandLord(landLordRepository.findById((long) 1).orElse(null));
        StudentOpinion studentOpinionToReturn = new StudentOpinion();
        newStudentOpinion.setId((long) 1);
        newStudentOpinion.setContent("Ha pagado a tiempo, pero a veces hacía bulla");
        newStudentOpinion.setScore((long)500);
        Gson gson = new Gson();
        doReturn(studentOpinionToReturn).when(studentOpinionService)
                .createStudentOpinion((long) 1, (long) 1, any());

        mockMvc.perform(post("/api/landlords/1/students/1/studentOpinions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(newStudentOpinion)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/api/users/{userId}/landlords"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("Ha pagado a tiempo, pero a veces hacía bulla")))
                .andExpect(jsonPath("$.score", is(500)));
    }
}