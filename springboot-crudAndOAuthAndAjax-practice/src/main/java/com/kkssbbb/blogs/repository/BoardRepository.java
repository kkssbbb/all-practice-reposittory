package com.kkssbbb.blogs.repository;

import com.kkssbbb.blogs.model.Board;
import com.kkssbbb.blogs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Integer> {

}




