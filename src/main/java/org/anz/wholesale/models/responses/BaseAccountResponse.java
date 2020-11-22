package org.anz.wholesale.models.responses;

import lombok.Builder;
import lombok.Data;
import org.anz.wholesale.models.enums.Currency;

@Data
@Builder
public class BaseAccountResponse {

    private Long number;
    private String name;
    private Currency currency;


}
