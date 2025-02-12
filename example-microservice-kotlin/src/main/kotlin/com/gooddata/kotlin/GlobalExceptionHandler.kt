package com.example.kotlin;

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<Map<String, String>> {
        val errorResponse = mapOf("message" to(e.reason ?: "Unknown error"))
        return ResponseEntity(errorResponse, e.statusCode)
    }

}
