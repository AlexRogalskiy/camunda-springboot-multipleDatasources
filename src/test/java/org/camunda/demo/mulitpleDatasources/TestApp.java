package org.camunda.demo.mulitpleDatasources;

import org.camunda.demo.multipleDatasources.xa.config.CamundaDatasourceProperties;
import org.camunda.demo.multipleDatasources.xa.config.DomainDatasourceProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableAutoConfiguration
@EnableConfigurationProperties(value = { CamundaDatasourceProperties.class, DomainDatasourceProperties.class })
public class TestApp {}