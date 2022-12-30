package com.example.mockkclearingissue

import org.springframework.stereotype.Service

@Service
class PingPongService(
    private val client: PingPongFeignClient
) {
    fun pingpong(number: Int) = client.ping(PingPongFeignClient.Ping(number)).number
}