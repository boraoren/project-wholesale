package org.anz.wholesale.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql({"/test-data.sql"})
public class AccountApiIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void get_v1_shouldHttpStatusBe200AndReturnAccountList() throws Exception {
        //when
        ResultActions resultActions = this
                .mockMvc
                .perform(get("/api/v1/accounts"))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk());

        resultActions
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].number")
                        .value("585309209"))
                .andExpect(jsonPath("$.[0].name")
                        .value("SGSavings726"));

    }

}
