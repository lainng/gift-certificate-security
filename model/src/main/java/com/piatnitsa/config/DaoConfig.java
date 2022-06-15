package com.piatnitsa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This class contains Spring configuration for DAO module.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.piatnitsa.dao")
@EntityScan(basePackages = "com.piatnitsa.entity")
public class DaoConfig {

}
