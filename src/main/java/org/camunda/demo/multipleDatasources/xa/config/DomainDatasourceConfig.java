package org.camunda.demo.multipleDatasources.xa.config;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "org.camunda.demo.multipleDatasources.repository", entityManagerFactoryRef = "domainEntityManager", transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(DomainDatasourceProperties.class)
public class DomainDatasourceConfig {

	@Autowired
	private DomainDatasourceProperties domainDatasourceProperties;

	@Bean(name = "domainDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource orderDataSource() throws SQLException {
//		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
//		mysqlXaDataSource.setUrl(domainDatasourceProperties.getUrl());
//		mysqlXaDataSource.setPassword(domainDatasourceProperties.getPassword());
//		mysqlXaDataSource.setUser(domainDatasourceProperties.getUsername());
//		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		PGXADataSource pgXaDatasource = new PGXADataSource();
		pgXaDatasource.setPassword(domainDatasourceProperties.getPassword());
		pgXaDatasource.setUser(domainDatasourceProperties.getUsername());
		pgXaDatasource.setUrl(domainDatasourceProperties.getUrl());

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setLocalTransactionMode(true);
		xaDataSource.setXaDataSource(pgXaDatasource);
		xaDataSource.setUniqueResourceName("domainDS");
		xaDataSource.setPoolSize(3);
		xaDataSource.setMaxPoolSize(domainDatasourceProperties.getMaxPoolSize());
		return xaDataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
//		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean(name = "domainEntityManager")
	public LocalContainerEntityManagerFactoryBean domainEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(orderDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter());
		entityManager.setPackagesToScan("org.camunda.demo.multipleDatasources.entity");
		entityManager.setPersistenceUnitName("domainPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
