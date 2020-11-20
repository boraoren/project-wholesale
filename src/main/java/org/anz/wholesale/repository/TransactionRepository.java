package org.anz.wholesale.repository;

import org.anz.wholesale.models.Account;
import org.anz.wholesale.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccount(Account account);
}
