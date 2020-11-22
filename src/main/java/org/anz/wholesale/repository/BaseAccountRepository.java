package org.anz.wholesale.repository;

import org.anz.wholesale.models.BaseAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface BaseAccountRepository extends JpaRepository<BaseAccount, Long> {

    default BaseAccount findBy(Long id) throws EntityNotFoundException {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
