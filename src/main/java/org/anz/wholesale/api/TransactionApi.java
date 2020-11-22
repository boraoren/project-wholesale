package org.anz.wholesale.api;

import org.anz.wholesale.models.BaseAccount;
import org.anz.wholesale.models.Transaction;
import org.anz.wholesale.models.mappers.TransactionMapper;
import org.anz.wholesale.models.responses.TransactionResponse;
import org.anz.wholesale.repository.BaseAccountRepository;
import org.anz.wholesale.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionApi {

    private final BaseAccountRepository baseAccountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionApi(TransactionRepository transactionRepository,
                          BaseAccountRepository baseAccountRepository,
                          TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.baseAccountRepository = baseAccountRepository;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/v1/accounts/{accountNumber}/transactions")
    ResponseEntity<List<TransactionResponse>> getTransactionsBy(@PathVariable("accountNumber")
                                                                Long accountNumber) {

        try {
            BaseAccount baseAccount = baseAccountRepository.findBy(accountNumber);
            List<Transaction> transactions = transactionRepository
                    .findAllByBaseAccount(baseAccount);

            if (transactions.size() > 0) {
                List<TransactionResponse> transactionResponseList = transactionMapper
                        .convertToResponse(transactions);

                return ResponseEntity.ok().body(transactionResponseList);
            }
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.noContent().build();
    }
}
