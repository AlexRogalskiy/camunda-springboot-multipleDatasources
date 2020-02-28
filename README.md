# Camunda Spring Boot Application
Spring Boot Application using [Camunda](http://docs.camunda.org).

This project demonstrates how to configure two datasources in a Camunda Springboot application with two transaction managers.

## How to use it?

1. Spin up a database (Postgres or MySQL)
	``docker-compose up -d``
2. For MySQL change the according ~DataSourceConfig.java files
3. restart the container ``docker-compose restart``
4. go to ``localhost:8088/`` and log into the database with the credentials provided by docker-compose.yml (all fields: postgres)
5. create a database ``camunda`` and another ``domaindata``
6. start the application
7. send a POST request to ``localhost:8080/rest/order`` to create a new Order


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

8. You can pass whether orderFail or processFail to force an exception. That leads usually to inconsistent states after executing the OrderController. 
To solve that one can leverage XATransactions (see branch XA) or provide eventual consistency by implementing the SAGA pattern ==> provide compensation activities. The latter approach is the most recommended one.


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