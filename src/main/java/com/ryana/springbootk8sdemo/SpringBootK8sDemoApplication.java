package com.ryana.springbootk8sdemo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@SpringBootApplication
public class SpringBootK8sDemoApplication {

	private final Environment environment;

	@Value("${DB_HOST:DEFAULT_HOST}")
	private String dbhost;
	@Value("${DB_NAME:DEFAULT_NAME}")
	private String dbname;
	@Value("${DB_USERNAME:DEFAULT_USERNAME}")
	private String dbusername;
	@Value("${DB_PASSWORD:DEFAULT_PASSWORD}")
	private String dbpassword;

	@GetMapping("/key/{key}")
	public String getEnvironment(@PathVariable String key) {
		return environment.getProperty(key);
	}

	@GetMapping("/map")
	public Map<String, String> getK8sConfigMapSecrets() {
		return Map.of("dbhost", dbhost,
				"dbname", dbname,
				"dbusername", dbusername,
				"dbpassword", dbpassword);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootK8sDemoApplication.class, args);
	}

}
