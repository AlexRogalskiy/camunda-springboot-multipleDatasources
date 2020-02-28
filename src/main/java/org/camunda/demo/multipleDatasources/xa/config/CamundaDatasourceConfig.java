package org.camunda.demo.multipleDatasources.xa.config;

import java.sql.SQLException;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

@Configuration
@DependsOn("transactionManager")
@EnableConfigurationProperties(CamundaDatasourceProperties.class)
public class CamundaDatasourceConfig {

	@Autowired
	private CamundaDatasourceProperties camundaDatasourceProperties;

	@Bean(name = "camundaBpmDataSource", initMethod = "init", destroyMethod = "close")
	@Primary
	public AtomikosDataSourceBean camundaDataSource() throws SQLException {
//		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
//		mysqlXaDataSource.setUrl(camundaDatasourceProperties.getUrl());
//		mysqlXaDataSource.setPassword(camundaDatasourceProperties.getPassword());
//		mysqlXaDataSource.setUser(camundaDatasourceProperties.getUsername());
//		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		PGXADataSource pgXaDatasource = new PGXADataSource();
		pgXaDatasource.setPassword(camundaDatasourceProperties.getPassword());
		pgXaDatasource.setUser(camundaDatasourceProperties.getUsername());
		pgXaDatasource.setUrl(camundaDatasourceProperties.getUrl());

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setLocalTransactionMode(true);
		xaDataSource.setPoolSize(3);
		xaDataSource.setMaxPoolSize(camundaDatasourceProperties.getMaxPoolSize());
		xaDataSource.setXaDataSource(pgXaDatasource);
		xaDataSource.setUniqueResourceName("camundaDS");

		return xaDataSource;
	}

}
