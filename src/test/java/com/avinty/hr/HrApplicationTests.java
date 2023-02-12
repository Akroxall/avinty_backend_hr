package com.avinty.hr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HrApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void employeesTest() throws Exception {
        this.mockMvc.perform(get("/API/V1/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void employeesFilterTest() throws Exception {
        this.mockMvc.perform(get("/API/V1/employees").contentType(MediaType.APPLICATION_JSON).content("{\"fullName\":\"Eszter\",\"email\":\"eszter\"}")).andExpect(status().isOk());
    }

    @Test
    void depEmpTest() throws Exception {
        this.mockMvc.perform(get("/API/V1/dep-emp").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void departmentSearchTest() throws Exception {
        this.mockMvc.perform(get("/API/V1/department").contentType(MediaType.APPLICATION_JSON).queryParam("name", "sales")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN", value = "florine.delicia@avinty.eu")
    void setManagerTest() throws Exception {
        this.mockMvc.perform(patch("/API/V1/department/{departmentId}/set-manager/{employeeId}", 4, 2)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN", value = "florine.delicia@avinty.eu")
    void deleteDepartmentTest() throws Exception {
        this.mockMvc.perform(delete("/API/V1/department/{departmentId}", 10)).andExpect(status().is4xxClientError());
    }
}
