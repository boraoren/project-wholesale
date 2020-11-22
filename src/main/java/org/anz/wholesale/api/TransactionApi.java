package org.anz.wholesale.api;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.models.Transaction;
import org.anz.wholesale.models.TransactionResponse;
import org.anz.wholesale.repository.AccountRepository;
import org.anz.wholesale.repository.TransactionRepository;
import org.anz.wholesale.util.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionApi(TransactionRepository transactionRepository,
                          AccountRepository accountRepository,
                          TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/v1/accounts/{accountNumber}/transactions")
    ResponseEntity<List<TransactionResponse>> getTransactionsBy(@PathVariable("accountNumber")
                                                                Long accountNumber) {

        try {
            Account account = accountRepository.findBy(accountNumber);
            List<Transaction> transactions = transactionRepository
                    .findAllByAccount(account);

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
