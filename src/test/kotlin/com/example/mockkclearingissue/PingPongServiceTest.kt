package com.example.mockkclearingissue

import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class PingPongServiceTest(
    @Autowired private val client: PingPongFeignClient,
    @Autowired private val service: PingPongService
) {
    @Test
    @Order(1)
    fun `setup pong to always return 100 in this test case`() {
        every { client.ping(any()) } returns PingPongFeignClient.Pong(100)
        assertThat(client.ping(PingPongFeignClient.Ping(5))).isEqualTo(PingPongFeignClient.Pong(100))
    }
    @Test
    @Order(2)
    fun `should fail because there is no API to call and bean is not mocked, but fails because previous case is leaky`() {
        val pong = service.pingpong(1)
        verify { client.ping(PingPongFeignClient.Ping(1)) }
        assertThat(pong).isEqualTo(1)
    }
}