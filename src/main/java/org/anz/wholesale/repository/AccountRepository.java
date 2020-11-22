package org.anz.wholesale.repository;

import org.anz.wholesale.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface AccountRepository extends JpaRepository<Account, Long> {

    default Account findBy(Long id) throws EntityNotFoundException{
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
