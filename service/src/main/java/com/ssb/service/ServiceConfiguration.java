package com.ssb.service;

import com.ssb.data.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.ssb.service")
@Import(DataConfiguration.class)
public class ServiceConfiguration {

}
