package com.example.mockkclearingissue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MockkClearingIssueApplication

fun main(args: Array<String>) {
    runApplication<MockkClearingIssueApplication>(*args)
}
