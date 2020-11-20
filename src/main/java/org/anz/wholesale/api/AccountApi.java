package org.anz.wholesale.api;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountApi {

    private final AccountRepository accountRepository;

    public AccountApi(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/v1/accounts")
    List<Account> getAccounts() {
        return accountRepository.findAll();
    }


}
