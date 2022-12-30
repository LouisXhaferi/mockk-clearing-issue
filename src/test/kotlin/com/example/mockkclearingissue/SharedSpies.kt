package com.example.mockkclearingissue

import com.ninjasquad.springmockk.SpykBean
import org.springframework.context.annotation.Configuration

@Configuration
class SharedSpies {
    @SpykBean
    lateinit var pingPongFeignClient: PingPongFeignClient
}