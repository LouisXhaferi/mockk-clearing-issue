package com.example.mockkclearingissue

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "pingpong", url = "localhost:3000", primary = true)
interface PingPongFeignClient {
    @PostMapping(
        value = ["/pingpong"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun ping(ping: Ping): Pong

    @JvmInline
    value class Ping(val number: Int)
    @JvmInline
    value class Pong(val number: Int)
}