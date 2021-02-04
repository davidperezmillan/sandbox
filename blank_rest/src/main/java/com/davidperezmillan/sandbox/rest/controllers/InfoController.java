package com.davidperezmillan.sandbox.rest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.davidperezmillan.sandbox.rest.entities.Info;
import com.davidperezmillan.sandbox.rest.repositories.InfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/info")
public class InfoController {

//	 @Autowired
//	 private InfoDao infoDao;

	@Autowired
	private InfoService infoService;


	@Value("${spring.application.name:demoservice}")
	private String name;
	
	@GetMapping
	public ResponseEntity<List<Info>> getInfos() {
		log.info("Pidiendo a " + name);
		return ResponseEntity.ok(infoService.getInfos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Info> getInfo(@PathVariable long id) {
		Info Info = infoService.getInfo(id);
		if (Info == null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(Info);

	}

	@PostMapping
	public ResponseEntity<Info> setInfo(@RequestBody Info Info) {
		try {
			infoService.saveInfo(Info);
			//Create resource location
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                                    .path("/{id}")
	                                    .buildAndExpand(Info.getId())
	                                    .toUri();
			return ResponseEntity.created(location).build();
//			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Info> delInfo(@PathVariable long id) {
		try {
			return infoService.deleteInfo(id) ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error("error", e.fillInStackTrace());
			return ResponseEntity.badRequest().build();
		}
		
	}

}
