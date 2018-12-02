package com.globant.bootcamp.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@Api
public class HelloWorld {
	@GetMapping("/quote")
	public String helloWorld() {
		return("{\"quote\":\"Hola Mundo\"}");
	}
}
