package com.ssb.web;


import com.ssb.data.*;
import com.ssb.rest.*;
import com.ssb.security.*;
import com.ssb.service.*;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Set;

public class WebInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        // Init Spring context
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class);
        context.register(ServiceConfiguration.class);
        context.register(DataConfiguration.class);
        context.register(WebSecurityConfig.class);
        context.register(RestConfiguration.class);

        // Init servlet for Spring MVC
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        // Register servlet in Tomcat context
        final ServletRegistration.Dynamic servletRegistration =
                ctx.addServlet("dispatcherServlet", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

        long maxSize = 16L * 1024 * 1024;
        servletRegistration.setMultipartConfig(
                new MultipartConfigElement(null, maxSize, maxSize, 0));
    }
}