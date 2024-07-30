
# <img src="https://media.tenor.com/GfzFmhWoWdMAAAAi/pikachu-run.gif" align="" width="100"> Pokemon SOAP Service 

This project is part of challange, it exposes SOAP service that receive a string (name of the Pokemon) and also expose 6 different methods:
- abilities
- base_experience
- held_items
- id
- name
- location_area_encounters

and internally consumes

```bash
  https://pokeapi.co/api/v2/pokemon/{pokemonName}
```

the information from the https://pokeapi.co Api, need to be store using **H2, MySql** or other database
with the next fields


- ip origin
- request date
- execute method
- time execution
- request
- response







## <img src="https://i.gifer.com/ZDcp.gif" align="" width="55"> Installation

### Prerequisites

Make sure you have **Java 17** installed in your computer, if not, please follow the next link
[Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-9988A30D-7D01-426B-9735-17EC2F0B4A85)


Install my-project with npm

```bash
  npm install my-project
  cd my-project
```

### Quick Start

1.  Clone repository

```bash
  https://github.com/PalilloKun/PokeSoapService.git
```

2. Open it in your favourite IDE

3. Open a terminal in your IDE and run the following command

```bash
  mvn clean install
```
3. The dafault port is set in **8088**, you can change it in  **src/main/resources/application.yml** file
   Run rhe following command

```bash
  mvn spring-boot:run
```

4. **Important** Open the next url to verify if the **WSDL** service is working fine

```bash
  http://localhost:8919/ws/pokemons.wsdl
```




## <img src="https://i.gifer.com/WnES.gif" align="" width="55"> Databae
H2 databse was selected to store the field mencioned in the description of the project.

The configuration file is the next:

```http
    datasource:
        driver-class-name: org.h2.Driver
        password: ditto
        url: jdbc:h2:mem:pokeapidb
        username: pokemon
    h2:
        console:
            path: /h2-console
            enabled: true
    jpa:
        database-platform: org.hibernate.dialect.H2Dialect
        hibernate:
            ddl-auto: update
        show-sql: true
'
```

## <img src="https://i.gifer.com/YlVy.gif" align="" width="55">  Time to test

You can test the following 6 end-points:

**Suggestion** copy the Curl`s in your favorite IDE testing (Postman, Insomnia, etc)

#### Abilities

```http
curl -L -X POST 'http://localhost:8088/ws' \
-H 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getAbilityRequest xmlns="http://pokesoap.com/soap/pokemon">
      <name>{pokemonName}</name>
    </getAbilityRequest>
  </soap:Body>
</soap:Envelope>
'
```

| Parameter | Type     | 
| :-------- | :------- | 
| `{pokemonName}` | `string` | 

##### Respose
```bash
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GenericResponse xmlns:ns2="http://pokesoap.com/soap/pokemon">
            <ns2:status>200 OK</ns2:status>
            <ns2:message>[{"ability":{"name":"limber","url":"https://pokeapi.co/api/v2/ability/7/"},"slot":1,"is_hidden":false},{"ability":{"name":"imposter","url":"https://pokeapi.co/api/v2/ability/150/"},"slot":3,"is_hidden":true}]</ns2:message>
        </ns2:GenericResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


#### Base experience

```http
curl -L -X POST 'http://localhost:8088/ws' \
-H 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getBaseExperienceRequest xmlns="http://pokesoap.com/soap/pokemon">
      <name>ditto</name>
    </getBaseExperienceRequest>
  </soap:Body>
</soap:Envelope>
'
```

| Parameter | Type     | 
| :-------- | :------- | 
| `{pokemonName}` | `string` | 

##### Respose
```bash
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GenericResponse xmlns:ns2="http://pokesoap.com/soap/pokemon">
            <ns2:status>200 OK</ns2:status>
            <ns2:message>101</ns2:message>
        </ns2:GenericResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```



#### Held items

```http
curl -L -X POST 'http://localhost:8088/ws' \
-H 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getHeldItemsRequest xmlns="http://pokesoap.com/soap/pokemon">
      <name>ditto</name>
    </getHeldItemsRequest>
  </soap:Body>
</soap:Envelope>
'
```

| Parameter | Type     | 
| :-------- | :------- | 
| `{pokemonName}` | `string` | 

##### Respose
```bash
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GenericResponse xmlns:ns2="http://pokesoap.com/soap/pokemon">
            <ns2:status>200 OK</ns2:status>
            <ns2:message>[{"item":{"name":"metal-powder","url":"https://pokeapi.co/api/v2/item/234/"},"version_details":[{"rarity":5,"version":{"name":"ruby","url":"https://pokeapi.co/api/v2/version/7/"}},{"rarity":5,"version":{"name":"sapphire","url":"https://pokeapi.co/api/v2/version/8/"}},{"rarity":5,"version":{"name":"emerald","url":"https://pokeapi.co/api/v2/version/9/"}},{"rarity":5,"version":{"name":"firered","url":"https://pokeapi.co/api/v2/version/10/"}},{"rarity":5,"version":{"name":"leafgreen","url":"https://pokeapi.co/api/v2/version/11/"}},{"rarity":5,"version":{"name":"diamond","url":"https://pokeapi.co/api/v2/version/12/"}},{"rarity":5,"version":{"name":"pearl","url":"https://pokeapi.co/api/v2/version/13/"}},{"rarity":5,"version":{"name":"platinum","url":"https://pokeapi.co/api/v2/version/14/"}},{"rarity":5,"version":{"name":"heartgold","url":"https://pokeapi.co/api/v2/version/15/"}},{"rarity":5,"version":{"name":"soulsilver","url":"https://pokeapi.co/api/v2/version/16/"}},{"rarity":5,"version":{"name":"black","url":"https://pokeapi.co/api/v2/version/17/"}},{"rarity":5,"version":{"name":"white","url":"https://pokeapi.co/api/v2/version/18/"}},{"rarity":5,"version":{"name":"black-2","url":"https://pokeapi.co/api/v2/version/21/"}},{"rarity":5,"version":{"name":"white-2","url":"https://pokeapi.co/api/v2/version/22/"}},{"rarity":5,"version":{"name":"x","url":"https://pokeapi.co/api/v2/version/23/"}},{"rarity":5,"version":{"name":"y","url":"https://pokeapi.co/api/v2/version/24/"}},{"rarity":5,"version":{"name":"omega-ruby","url":"https://pokeapi.co/api/v2/version/25/"}},{"rarity":5,"version":{"name":"alpha-sapphire","url":"https://pokeapi.co/api/v2/version/26/"}},{"rarity":5,"version":{"name":"sun","url":"https://pokeapi.co/api/v2/version/27/"}},{"rarity":5,"version":{"name":"moon","url":"https://pokeapi.co/api/v2/version/28/"}},{"rarity":5,"version":{"name":"ultra-sun","url":"https://pokeapi.co/api/v2/version/29/"}},{"rarity":5,"version":{"name":"ultra-moon","url":"https://pokeapi.co/api/v2/version/30/"}}]},{"item":{"name":"quick-powder","url":"https://pokeapi.co/api/v2/item/251/"},"version_details":[{"rarity":50,"version":{"name":"diamond","url":"https://pokeapi.co/api/v2/version/12/"}},{"rarity":50,"version":{"name":"pearl","url":"https://pokeapi.co/api/v2/version/13/"}},{"rarity":50,"version":{"name":"platinum","url":"https://pokeapi.co/api/v2/version/14/"}},{"rarity":50,"version":{"name":"heartgold","url":"https://pokeapi.co/api/v2/version/15/"}},{"rarity":50,"version":{"name":"soulsilver","url":"https://pokeapi.co/api/v2/version/16/"}},{"rarity":50,"version":{"name":"black","url":"https://pokeapi.co/api/v2/version/17/"}},{"rarity":50,"version":{"name":"white","url":"https://pokeapi.co/api/v2/version/18/"}},{"rarity":50,"version":{"name":"black-2","url":"https://pokeapi.co/api/v2/version/21/"}},{"rarity":50,"version":{"name":"white-2","url":"https://pokeapi.co/api/v2/version/22/"}},{"rarity":50,"version":{"name":"x","url":"https://pokeapi.co/api/v2/version/23/"}},{"rarity":50,"version":{"name":"y","url":"https://pokeapi.co/api/v2/version/24/"}},{"rarity":50,"version":{"name":"omega-ruby","url":"https://pokeapi.co/api/v2/version/25/"}},{"rarity":50,"version":{"name":"alpha-sapphire","url":"https://pokeapi.co/api/v2/version/26/"}},{"rarity":50,"version":{"name":"sun","url":"https://pokeapi.co/api/v2/version/27/"}},{"rarity":50,"version":{"name":"moon","url":"https://pokeapi.co/api/v2/version/28/"}},{"rarity":50,"version":{"name":"ultra-sun","url":"https://pokeapi.co/api/v2/version/29/"}},{"rarity":50,"version":{"name":"ultra-moon","url":"https://pokeapi.co/api/v2/version/30/"}}]}]</ns2:message>
        </ns2:GenericResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


#### Id

```http
curl -L -X POST 'http://localhost:8088/ws' \
-H 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getIdRequest xmlns="http://pokesoap.com/soap/pokemon">
      <name>ditto</name>
    </getIdRequest>
  </soap:Body>
</soap:Envelope>
'
```

| Parameter | Type     | 
| :-------- | :------- | 
| `{pokemonName}` | `string` | 

##### Respose
```bash
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GenericResponse xmlns:ns2="http://pokesoap.com/soap/pokemon">
            <ns2:status>200 OK</ns2:status>
            <ns2:message>132</ns2:message>
        </ns2:GenericResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


#### Name

```http
curl -L -X POST 'http://localhost:8088/ws' \
-H 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getNameRequest xmlns="http://pokesoap.com/soap/pokemon">
      <name>ditto</name>
    </getNameRequest>
  </soap:Body>
</soap:Envelope>
'
```

| Parameter | Type     | 
| :-------- | :------- | 
| `{pokemonName}` | `string` | 

##### Respose
```bash
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GenericResponse xmlns:ns2="http://pokesoap.com/soap/pokemon">
            <ns2:status>200 OK</ns2:status>
            <ns2:message>ditto</ns2:message>
        </ns2:GenericResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```



#### Location area encounters

```http
curl -L -X POST 'http://localhost:8088/ws' \
-H 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getLocationAreaEncountersRequest xmlns="http://pokesoap.com/soap/pokemon">
      <name>ditto</name>
    </getLocationAreaEncountersRequest>
  </soap:Body>
</soap:Envelope>
'
```

| Parameter | Type     | 
| :-------- | :------- | 
| `{pokemonName}` | `string` | 

##### Respose
```bash
  <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GenericResponse xmlns:ns2="http://pokesoap.com/soap/pokemon">
            <ns2:status>200 OK</ns2:status>
            <ns2:message>https://pokeapi.co/api/v2/pokemon/132/encounters</ns2:message>
        </ns2:GenericResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```



## <img src="https://media.tenor.com/40q2xXN6gfMAAAAi/meowth-pokemon.gif" align="" width="55">  Testing

To run the Junit  test run the following command

```bash
  mvn test 
```



## <img src="https://media.tenor.com/iGSsICUR-2oAAAAi/mewtwo-sprite.gif" align="" width="55">   SonarQube

Make sure you have already installed SonarQube in your computer, if not check the next link
[SonarQube Documentation](https://docs.sonarsource.com/sonarqube/latest/setup-and-upgrade/install-the-server/introduction/) to generate

- projectKey
- projetName
- token

Open a terminal, run the following command with the correct values

```bash
  mvn clean verify sonar:sonar \                                                                                           
  -Dsonar.projectKey=<PROJECTKEY> \
  -Dsonar.projectName='<PROJECTNAME>' \
  -Dsonar.host.url=<SET-URL-TO-SONARQUBE> \
  -Dsonar.token=<TOKE>
```

or if you prefer modify next properties in sonar-project.properties

sonar.projectKey=PROJECT_KEY<br>
sonar.projectName= PROJECT_NAME<br>
sonar.login=SONAR_TOKEN<br>
sonar.host.url=URL_SONAR<br>


and run

```bash
   mvn clean verify sonar:sonar -Dsonar.login=SONAR_TOKEN

```







## <img src="https://i.gifer.com/4QYe.gif" align="" width="55">  Tech Stack

|              |
| ----------------- |
| Java 17 | 
| Spring Boot 3 | 
| H2 (Database)| 
| JPA|
| JUnit 5|
| Cumcumber|
| SonarQube|


|      Dependencies        |
| ----------------- |
| spring-boot-starter-web | 
| spring-boot-starter-web-services| 
| spring-boot-starter-webflux| 
| spring-boot-starter-webflux| 
| spring-boot-starter-data-jpa| 
| h2| 
| spring-boot-devtools| 
| lombok| 


|      Dependencies for Testing       |
| ----------------- |
| spring-boot-starter-test | 
| mockito-core| 
| mockito-junit-jupiter| 
| spring-ws-test| 
| wiremock-standalone| 

|      Dependencies for Cucumber       |
| ----------------- |
| cucumber-java | 
| cucumber-spring| 
| cucumber-junit| 
| cucumber-junit-platform-engine| 
| junit-vintage-engine| 


|      Dependencies for SonarQube       |
| ----------------- |
| sonar-maven-plugin | 
| jacoco-maven-plugin| 




## Evidence JUnit

To run tests, run the following command

```bash
  npm run test
```


## <img src="https://i.gifer.com/ZAbj.gif" align="" width="55"> Evidence Screenshots


### Run locally


![alt](https://raw.githubusercontent.com/PalilloKun/PokeSoapService/c094b0305a96eb05b59dab3c5a55284b515b4202/evidence/run_locally.png)

### Run Junit

![alt](https://raw.githubusercontent.com/PalilloKun/PokeSoapService/c094b0305a96eb05b59dab3c5a55284b515b4202/evidence/run_junit.png)

### Run Cucumber

![alt](https://raw.githubusercontent.com/PalilloKun/PokeSoapService/c094b0305a96eb05b59dab3c5a55284b515b4202/evidence/run_cucumber.png)


### Run SonarQube

![alt](https://raw.githubusercontent.com/PalilloKun/PokeSoapService/c094b0305a96eb05b59dab3c5a55284b515b4202/evidence/run_sonarqube.png)




