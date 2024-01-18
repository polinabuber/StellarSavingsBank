package com.ssb.rest;

import com.ssb.data.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.ssb.rest")
@Import(DataConfiguration.class)
public class RestConfiguration {
}
