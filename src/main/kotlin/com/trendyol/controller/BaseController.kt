package com.trendyol.controller

import com.trendyol.SectionNotFoundException
import com.trendyol.ShortLinkNotFoundException
import com.trendyol.web.Result
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler

abstract class BaseController {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleException(e: Exception): Any {
        log.error("", e)
        return Result.Error(HttpStatus.BAD_REQUEST)
                .message("Invalid input")
                .add("details", e.cause?.message ?: "")
                .build()
    }

    @ExceptionHandler(SectionNotFoundException::class)
    fun handleSectionException(e: Exception): Any {
        log.error("", e)
        return Result.Error(HttpStatus.BAD_REQUEST)
                .message("Section not found")
                .add("details", e.message ?: "")
                .build()
    }

    @ExceptionHandler(ShortLinkNotFoundException::class)
    fun handleShortLinkException(e: Exception): Any {
        log.error("", e)
        return Result.Error(HttpStatus.NOT_FOUND)
                .message("Short link not found")
                .add("details", e.message ?: "")
                .build()
    }
}