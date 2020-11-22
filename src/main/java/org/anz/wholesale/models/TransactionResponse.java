package org.anz.wholesale.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import org.anz.wholesale.util.MoneyJsonSerializer;

import java.time.LocalDate;

@Data
@Builder
public class TransactionResponse {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "MMM dd, yyyy")
    private LocalDate valueDate;
    private Double debitAmount;

    @JsonSerialize(using = MoneyJsonSerializer.class)
    private Double creditAmount;

    private DebitCredit debitCredit;
    private String narrative;

    @JsonProperty("account")
    private AccountResponse accountResponse;

}
