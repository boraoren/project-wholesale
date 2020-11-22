package org.anz.wholesale.models.mappers;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.models.BaseAccount;
import org.anz.wholesale.models.responses.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public List<AccountResponse> convertToResponse(List<Account> accounts) {

        BaseAccount baseAccount = accounts.get(0).getBaseAccount();

        return accounts.stream().map(account ->
                AccountResponse
                        .builder()
                        .number(baseAccount.getNumber())
                        .name(baseAccount.getName())
                        .currency(baseAccount.getCurrency())
                        .balanceDate(account.getBalanceDate())
                        .openingAvailableBalance(account.getOpeningAvailableBalance())
                        .type(account.getType()).build()).collect(Collectors.toList());
    }


}
