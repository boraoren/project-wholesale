package org.anz.wholesale.repository;

import org.anz.wholesale.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}
