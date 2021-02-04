package com.davidperezmillan.sandbox.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidperezmillan.sandbox.rest.entities.Comment;
import com.davidperezmillan.sandbox.rest.repositories.CommentDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "comment")
public class CommentController {

	@Autowired
	private CommentDao commentDao;
	
	
	@GetMapping
	public ResponseEntity<List<Comment>> getCommentInfos(){
		log.debug("find all Comment");
		return ResponseEntity.ok(commentDao.findAll());
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Comment> getInfoById(@PathVariable("id") Long id){
		Optional<Comment> optionalComment = commentDao.findById(id);
		return (optionalComment.isPresent()) ? ResponseEntity.ok(optionalComment.get()) : ResponseEntity.noContent().build(); 
			
	}
	
	@PostMapping
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
		Comment newComment = commentDao.save(comment);
		return ResponseEntity.ok(newComment);
	}
	
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id){
		commentDao.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	
}
