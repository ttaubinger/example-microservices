package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleMicroserviceKotlinApplication

fun main(args: Array<String>) {
	runApplication<ExampleMicroserviceKotlinApplication>(*args)
}
