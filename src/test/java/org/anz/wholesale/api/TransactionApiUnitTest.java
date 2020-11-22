package org.anz.wholesale.api;

import org.anz.wholesale.models.BaseAccount;
import org.anz.wholesale.models.Transaction;
import org.anz.wholesale.models.enums.Currency;
import org.anz.wholesale.models.enums.DebitCredit;
import org.anz.wholesale.repository.BaseAccountRepository;
import org.anz.wholesale.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
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

@WebMvcTest(TransactionApi.class)
@AutoConfigureRestDocs
@ComponentScan("org.anz.wholesale.models.mappers")
@ExtendWith({RestDocumentationExtension.class})
public class TransactionApiUnitTest {

    public MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private BaseAccountRepository baseAccountRepository;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

    }

    @Test
    public void getTransactionsByAccountNumber_v1_shouldHttpStatusBe200AndReturnAccountRelatedTransactionList() throws Exception {

        //given
        Long accountNumber = 585309209L;
        BaseAccount expectedBaseAccount = getExpectedBaseAccount(accountNumber);

        given(baseAccountRepository.findBy(accountNumber))
                .willReturn(expectedBaseAccount);

        given(transactionRepository.findAllByBaseAccount(expectedBaseAccount))
                .willReturn(getExpectedTransactionList(accountNumber));

        //when
        ResultActions getResultActions = mockMvc
                .perform(get("/api/v1/accounts/{accountNumber}/transactions", accountNumber))
                .andDo(document("get-transactions",
                        getResponseFieldsSnippet()));

        //then
        getResultActions.andExpect(status().isOk());

    }

    private BaseAccount getExpectedBaseAccount(Long accountNumber) {

        return BaseAccount.builder()
                .number(accountNumber)
                .name("SGSavings726")
                .currency(Currency.SGD)
                .build();

    }

    private List<Transaction> getExpectedTransactionList(Long accountNumber) {

        DateTimeFormatter transactionDateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        BaseAccount baseAccount = BaseAccount.builder()
                .number(accountNumber)
                .name("SGSavings726")
                .currency(Currency.SGD)
                .build();

        Transaction transaction100 = Transaction.builder()
                .id(100L)
                .valueDate(LocalDate.parse("Jan. 12, 2012", transactionDateFormatter))
                .creditAmount(9540.98)
                .debitCredit(DebitCredit.Credit)
                .baseAccount(baseAccount).build();

        Transaction transaction200 = Transaction.builder()
                .id(200L)
                .valueDate(LocalDate.parse("Jan. 12, 2012", transactionDateFormatter))
                .creditAmount(7497.82)
                .debitCredit(DebitCredit.Credit)
                .baseAccount(baseAccount).build();

        return new ArrayList<Transaction>() {{
            add(transaction100);
            add(transaction200);
        }};

    }

    private ResponseFieldsSnippet getResponseFieldsSnippet() {
        return responseFields(attributes(key("title")
                        .value("Fields for list transaction by account number")),
                fieldWithPath("[]")
                        .attributes(key("constraints")
                                .value("Must not be null. Must not be empty"))
                        .description("An array of transactions"))
                .andWithPrefix("[].", getResponseFieldDescriptor());
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[]{
                fieldWithPath("id").description("Id of the transaction")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("valueDate").description("Value Date of the transaction")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("debitAmount").description("Debit amount of the transaction")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("creditAmount").description("Credit amount of the transaction")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("debitCredit").description("Debit or Credit of the transaction")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("narrative").description("Narrative of the transaction")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                // ACCOUNT

                fieldWithPath("account.number").description("Id of the account")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("account.name").description("Name of the account")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

                fieldWithPath("account.currency").description("Currency of the account (SGD, AUD etc.)")
                        .attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),

        };
    }


}
