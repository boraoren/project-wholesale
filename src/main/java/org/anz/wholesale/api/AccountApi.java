package org.anz.wholesale.api;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.models.mappers.AccountMapper;
import org.anz.wholesale.models.responses.AccountResponse;
import org.anz.wholesale.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountApi {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountApi(AccountRepository accountRepository,
                      AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/v1/accounts")
    ResponseEntity<List<AccountResponse>> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.size() > 0) {
            List<AccountResponse> accountResponseList = accountMapper
                    .convertToResponse(accounts);
            return ResponseEntity.ok().body(accountResponseList);
        }

        return ResponseEntity.noContent().build();

    }


}
