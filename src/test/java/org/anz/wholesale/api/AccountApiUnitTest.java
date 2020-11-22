package org.anz.wholesale.api;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.models.AccountType;
import org.anz.wholesale.models.Currency;
import org.anz.wholesale.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountApi.class)
@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class})
public class AccountApiUnitTest {

    public MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

    }

    @Test
    public void get_v1_shouldHttpStatusBe200AndReturnAccountList()
            throws Exception {

        //given
        given(accountRepository.findAll())
                .willReturn(getExpectedAccountList());

        //when
        ResultActions getResultActions = mockMvc
                .perform(get("/api/v1/accounts"))
                .andDo(document("get-accounts",
                        getResponseFieldsSnippet()));

        //then
        getResultActions.andExpect(status().isOk());

    }

    private ResponseFieldsSnippet getResponseFieldsSnippet() {
        return responseFields(attributes(key("title")
                        .value("Fields for list accounts")),
                fieldWithPath("[]")
                        .attributes(key("constraints")
                                .value("Must not be null. Must not be empty"))
                        .description("An array of accounts"))
                .andWithPrefix("[].", getResponseFieldDescriptor());
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[]{
                fieldWithPath("number").description("Id of the account")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("name").description("Name of the account")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("type").description("Type of the account (Savings, Current etc.)")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("balanceDate").description("Balance Date of the account")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("currency").description("Currency of the account (SGD, AUD etc.)")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("openingAvailableBalance").description("Opening available balance of the account")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty"))
        };
    }

    private List<Account> getExpectedAccountList() {

        DateTimeFormatter
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Account account585309209 = Account.builder()
                .balanceDate(LocalDate.parse("08/11/2018", formatter))
                .currency(Currency.SGD)
                .name("SGSavings726")
                .number(123456789L)
                .openingAvailableBalance(84327.51)
                .type(AccountType.Savings).build();

        Account account791066619L = Account.builder()
                .balanceDate(LocalDate.parse("08/11/2018", formatter))
                .currency(Currency.AUD)
                .name("AUSavings933")
                .number(234567891L)
                .openingAvailableBalance(88005.93)
                .type(AccountType.Savings).build();

        return new ArrayList<Account>() {{
            add(account585309209);
            add(account791066619L);
        }};

    }

}
