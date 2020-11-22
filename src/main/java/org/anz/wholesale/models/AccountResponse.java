package org.anz.wholesale.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {

    private Long number;
    private String name;
    private Currency currency;


}
