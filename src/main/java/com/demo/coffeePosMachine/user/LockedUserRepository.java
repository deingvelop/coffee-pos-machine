package com.demo.coffeePosMachine.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface LockedUserRepository extends JpaRepository<LockedUser, Long> {
    //Optimistic Lock
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select u from locked_user u where u.id = :id")
    Optional<LockedUser> findByIdWithOptimisticLock(@Param("id") Long id);
}