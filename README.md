# Camunda Spring Boot Application
Spring Boot Application using [Camunda](http://docs.camunda.org).

This project demonstrates how to configure and use XA datasources within one project.
IT uses Atomikos as XA Transaction manager.

## How does it work?
Both databases are initialized and exposed as AtomikosDatasourceBean, which will be controlled by the TransactionManager.
The configuration magic happens in the package ... xa.config

## How to use it?

1. Spin up a database (Postgres or MySQL)
	``docker-compose up -d``
2. For Postgres go to /pgdata and enable ``max_prepared_transactions = 30`` in postgresql.conf
3. For MySQL change the according ~DataSourceConfig.java files
4. restart the container ``docker-compose restart``
5. go to ``localhost:8088/`` and log into the database with the credentials provided by docker-compose.yml
6. create a database ``camunda`` and another ``domaindata``
7. start the application
8. send a POST request to ``localhost:8080/rest/order`` to create a new Order


    {
      "orderAmount": 500,
      "orderNumber": "sw-26",
      "orderFail": false,
      "processFail": false,
      "customer": {
          "address": {
              "street": "MyStreet 1",
              "zipCode": "12345",
              "city": "Berlin"
          },
          "firstName": "Paul",
          "lastName": "Zimmer"
      }
    }
9. For the sake of simplicity the OrderController simply persists the incoming objects without any further checks
10. You can pass whether orderFail or processFail to force an exception to prove that both database actions are rolled back.
11. The UserTask uses a so called TaskInfoObject in order to avoid several calls for fetching variables.
==> See also the checkOrder.html

### Unit Test
the tests are not fully implemented
```

### Running the application
You can also build and run the process application with Spring Boot.

#### Manually
1. Build the application using:
```bash
mvn clean package
```
2. Run the *.jar file from the `target` directory using:
```bash
java -jar target/Camunda Spring Boot Application.jar
```

For a faster 1-click (re-)deployment see the alternatives below.

#### Maven Spring Boot Plugin
1. Build and deploy the process application using:
```bash
mvn clean package spring-boot:run
```

#### Your Java IDE
1. Run the project as a Java application in your IDE using CamundaApplication as the main class.

### Run and Inspect with Tasklist and Cockpit
Once you deployed the application you can run it using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist)
and inspect it using
[Camunda Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit).

## Environment Restrictions
Built and tested against Camunda BPM version 7.11.0.

## Known Limitations

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

<!-- HTML snippet for index page
  <tr>
    <td><img src="snippets/domainData/src/main/resources/process.png" width="100"></td>
    <td><a href="snippets/domainData">Camunda Spring Boot Application</a></td>
    <td>Spring Boot Application using [Camunda](http://docs.camunda.org).</td>
  </tr>
-->
<!-- Tweet
New @Camunda example: Camunda Spring Boot Application - Spring Boot Application using [Camunda](http://docs.camunda.org). https://github.com/camunda-consulting/code/tree/master/snippets/domainData
-->