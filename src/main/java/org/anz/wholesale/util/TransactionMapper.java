package org.anz.wholesale.util;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.models.AccountResponse;
import org.anz.wholesale.models.Transaction;
import org.anz.wholesale.models.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    public List<TransactionResponse> convertToResponse(List<Transaction> transactions) {

        Account account = transactions.get(0).getAccount();
        AccountResponse accountResponse = AccountResponse
                .builder()
                .currency(account.getCurrency())
                .name(account.getName())
                .number(account.getNumber()).build();

        return transactions.stream().map(transaction ->
                TransactionResponse
                        .builder()
                        .accountResponse(accountResponse)
                        .creditAmount(transaction.getCreditAmount())
                        .debitAmount(transaction.getDebitAmount())
                        .debitCredit(transaction.getDebitCredit())
                        .id(transaction.getId())
                        .narrative(transaction.getNarrative())
                        .valueDate(transaction.getValueDate())
                        .build()).collect(Collectors.toList());
    }

}
