package com.davidperezmillan.sandbox.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidperezmillan.sandbox.rest.entities.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>{

}
