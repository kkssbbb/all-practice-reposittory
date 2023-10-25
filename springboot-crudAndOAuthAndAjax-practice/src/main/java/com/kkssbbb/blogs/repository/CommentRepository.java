package com.kkssbbb.blogs.repository;

import com.kkssbbb.blogs.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Reply,Integer> {
}
