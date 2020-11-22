package org.anz.wholesale.models.mappers;

import org.anz.wholesale.models.BaseAccount;
import org.anz.wholesale.models.responses.BaseAccountResponse;
import org.anz.wholesale.models.Transaction;
import org.anz.wholesale.models.responses.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    public List<TransactionResponse> convertToResponse(List<Transaction> transactions) {

        BaseAccount baseAccount = transactions.get(0).getBaseAccount();

        BaseAccountResponse baseAccountResponse = BaseAccountResponse
                .builder()
                .currency(baseAccount.getCurrency())
                .name(baseAccount.getName())
                .number(baseAccount.getNumber()).build();

        return transactions.stream().map(transaction ->
                TransactionResponse
                        .builder()
                        .baseAccountResponse(baseAccountResponse)
                        .creditAmount(transaction.getCreditAmount())
                        .debitAmount(transaction.getDebitAmount())
                        .debitCredit(transaction.getDebitCredit())
                        .id(transaction.getId())
                        .narrative(transaction.getNarrative())
                        .valueDate(transaction.getValueDate())
                        .build()).collect(Collectors.toList());
    }

}
