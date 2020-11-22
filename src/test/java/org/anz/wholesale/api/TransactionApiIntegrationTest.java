package org.anz.wholesale.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories("org.anz.wholesale.repository")
@Sql({"/test-data.sql"})
public class TransactionApiIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void getTransactionsByAccountNumber_v1_shouldHttpStatusBe200AndReturnAccountRelatedTransactionList() throws Exception {
        //given
        Long accountNumber = 123456789L;

        //when
        ResultActions resultActions = this
                .mockMvc
                .perform(get("/api/v1/accounts/{accountNumber}/transactions", accountNumber))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk());

        resultActions
                .andExpect(jsonPath("$", hasSize(12)))
                .andExpect(jsonPath("$.[0].account.number")
                        .value("123456789"))
                .andExpect(jsonPath("$.[0].valueDate")
                        .value("Jan. 12, 2012"))
                .andExpect(jsonPath("$.[0].account.currency")
                        .value("SGD"))
                .andExpect(jsonPath("$.[0].creditAmount")
                        .value("9,540.98"))
                .andExpect(jsonPath("$.[0].debitCredit")
                        .value("Credit"));

    }

}
