package org.arisys.user_registration.repository;


import org.arisys.user_registration.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserlistRepository extends JpaRepository<UsersEntity, Long>, QuerydslPredicateExecutor<UsersEntity> {
}
