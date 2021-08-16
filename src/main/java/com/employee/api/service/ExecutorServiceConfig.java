package com.employee.api.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfig {

	@Bean("fixedThreadPool")
	public ExecutorService fixedThreadPool() {
		return Executors.newFixedThreadPool(10);
	}

	@Bean("singleThreaded")
	public ExecutorService singleThreadedExecutor() {
		return Executors.newSingleThreadExecutor();
	}

	@Bean("cachedThreadPool")
	public ExecutorService cachedThreadPool() {
		return Executors.newCachedThreadPool();
	}
}