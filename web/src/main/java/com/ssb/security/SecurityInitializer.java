package com.ssb.security;

import org.springframework.security.web.context.*;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(WebSecurityConfig.class);
    }
}
