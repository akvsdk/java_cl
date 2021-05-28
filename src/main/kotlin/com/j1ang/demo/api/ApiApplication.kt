package com.j1ang.demo.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
