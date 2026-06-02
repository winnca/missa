package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private StudentService service;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_ok() throws Exception {
        when(service.findAll()).thenReturn(List.of(new Student("A", "B")));
        mvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }
}