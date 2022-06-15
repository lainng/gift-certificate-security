package com.piatnitsa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@EnableAutoConfiguration
@ComponentScan("com.piatnitsa")
@Profile("test")
public class DaoTestConfig {
}
