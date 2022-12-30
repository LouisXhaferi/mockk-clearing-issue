package com.example.mockkclearingissue

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "pingpong", url = "localhost:3000", primary = true)
interface PingPongFeignClient {
    @PostMapping(
        value = ["/pingpong"]
    )
    fun ping(ping: Int): Int

    @PostMapping(
        value = ["/doesnothing"]
    )
    fun doesNothing()
}