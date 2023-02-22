# iis-2-cinema-managament

![dotnet-client](https://github.com/frederik-hoeft/iis-2-cinema-managament/actions/workflows/dotnet.yml/badge.svg)
![java-service](https://github.com/frederik-hoeft/iis-2-cinema-managament/actions/workflows/maven.yml/badge.svg)
[![CodeFactor](https://www.codefactor.io/repository/github/frederik-hoeft/iis-2-cinema-managament/badge?s=2033dbd82a54803db81d899b448496bfabb36920)](https://www.codefactor.io/repository/github/frederik-hoeft/iis-2-cinema-managament)

## DB: 

 - MySQL

## Server:

- Spring [MVC + JSON](https://www.geeksforgeeks.org/spring-rest-json-response/) für REST API
- ~Hibernate als ORM (https://www.tutorialspoint.com/hibernate/hibernate_examples.htm, https://www.javatpoint.com/hibernate-configuration, https://www.javatpoint.com/hibernate-many-to-many-example-using-xml)~
$\Rightarrow$ er will MPS

## Client:

- [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html) (Java built-in) + [Jackson API](https://www.delftstack.com/howto/java/java-deserialize-json/#deserialize-json-using-jackson-api) for JSON deserialization ODER was anderes (C#)

## Fragen:

- Client als richtige CLI anwendung, oder nur mocken? C# ist fine, aber mit CLI parser.
- Ist Transaktionssicherheit mit Hibernate okay, oder *muss* das der MPS kram sein? MPS zumindest versuchen
- Schrifliche ausarbeitung (LaTeX, Deutsch oder Englisch)? LaTeX englisch ist fine.
- Java version vorgegeben oder uns überlassen? (genauso mit IDE)? Egal.

## Rest API endpoints:

- Reservieren
- Buchen
- Stornieren
- Einnahmen (admin)
- komplette Verwaltung!
