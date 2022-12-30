package com.example.mockkclearingissue

import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.boot.test.mock.mockito.SpyBean

@SpringBootTest(webEnvironment = NONE)
class BugExampleTest(
    @Autowired private val client: PingPongFeignClient
) {
    @Test
    @Order(1)
    fun `setup pong to always return 100 in this test case`() {
        every { client.ping(any()) } returns 100
        assertThat(client.ping(Int.MAX_VALUE)).isEqualTo(100)
    }
    @Test
    @Order(2)
    fun `should fail because there is no API to call and bean is not mocked, but runs and returns 100`() {
        val pong = client.ping(1)
        assertThat(pong).isEqualTo(100)
    }

    @Test
    @Order(3)
    fun `if we instruct a method returning Unit to fail, it does so`() {
        every { client.doesNothing() } throws RuntimeException("BOOM!")
        assertThrows<RuntimeException> {
            client.doesNothing()
        }
    }

    @Test
    @Order(4)
    fun `but it also does not clear and explodes here, where we would not expect this kind of exception`() {
        assertThrows<RuntimeException> {
            client.doesNothing()
        }
    }
}