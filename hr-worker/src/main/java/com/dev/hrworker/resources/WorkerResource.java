package com.dev.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.hrworker.entities.Worker;
import com.dev.hrworker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Autowired
    private WorkerRepository repository;

    @Autowired
    private Environment environment;

    @Value("${test.config:Default value}")
    private String testConfig;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        logger.info("Buscando todos os workers - Port: {}",
                   environment.getProperty("local.server.port"));

        List<Worker> list = repository.findAll();
        logger.info("Encontrados {} workers", list.size());

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("Buscando worker ID: {} - Port: {} - Config: {}",
                   id,
                   environment.getProperty("local.server.port"),
                   testConfig);

        Worker obj = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Worker não encontrado com ID: {}", id);
                    return new RuntimeException("Worker not found");
                });

        logger.info("Worker encontrado: {} - Salário: R$ {}",
                   obj.getName(), obj.getDailyIncome());

        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<String> getConfigs() {
        logger.info("Acessando configurações - Port: {}",
                   environment.getProperty("local.server.port"));

        String configInfo = String.format(
            "Port: %s | Test Config: %s",
            environment.getProperty("local.server.port"),
            testConfig
        );

        return ResponseEntity.ok(configInfo);
    }
}