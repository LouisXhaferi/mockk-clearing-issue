package com.example.mockkclearingissue

import org.springframework.context.annotation.Configuration
import com.ninjasquad.springmockk.SpykBean

@Configuration
class SharedSpyBeans {
    @SpykBean
    lateinit var pingPongFeignClient: PingPongFeignClient
}