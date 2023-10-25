package com.muscleduck.repository;

import com.muscleduck.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
}
