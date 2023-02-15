# iis-2-cinema-managament

## DB: 

 - MySQL

## Server:

- Spring [MVC + JSON](https://www.geeksforgeeks.org/spring-rest-json-response/) für REST API
- Hibernate als ORM (https://www.tutorialspoint.com/hibernate/hibernate_examples.htm, https://www.javatpoint.com/hibernate-configuration, https://www.javatpoint.com/hibernate-many-to-many-example-using-xml) 

## Client:

- [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html) (Java built-in) + [Jackson API](https://www.delftstack.com/howto/java/java-deserialize-json/#deserialize-json-using-jackson-api) for JSON deserialization 

## Fragen:

- Client als richtige CLI anwendung, oder nur mocken? (wäre .NET ggf hier auch okay)
- Ist Transaktionssicherheit mit Hibernate okay, oder *muss* das der MPS kram sein?
- Schrifliche ausarbeitung (LaTeX, Deutsch oder Englisch)?
- Java version vorgegeben oder uns überlassen? (genauso mit IDE)

## Rest API endpoints:

- Reservieren
- Buchen
- Stornieren
- Einnahmen (admin)