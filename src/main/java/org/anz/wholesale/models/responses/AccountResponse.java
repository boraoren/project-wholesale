package org.anz.wholesale.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import org.anz.wholesale.models.enums.AccountType;
import org.anz.wholesale.models.enums.Currency;
import org.anz.wholesale.util.MoneyJsonSerializer;

import java.time.LocalDate;

@Data
@Builder
public class AccountResponse {

    private Long number;
    private String name;
    private AccountType type;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy")
    private LocalDate balanceDate;

    private Currency currency;

    @JsonSerialize(using = MoneyJsonSerializer.class)
    private double openingAvailableBalance;

}
