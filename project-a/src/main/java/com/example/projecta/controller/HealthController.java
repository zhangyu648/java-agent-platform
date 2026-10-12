package com.example.projecta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 健康检查接口，用来验证框架和数据库连接都正常。
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "time", LocalDateTime.now().toString(),
                "javaVersion", System.getProperty("java.version"),
                "appName", "project-a"
        );
    }
}