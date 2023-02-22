# iis-2-cinema-managament

![dotnet-client](https://github.com/frederik-hoeft/iis-2-cinema-managament/actions/workflows/dotnet.yml/badge.svg)
![java-service](https://github.com/frederik-hoeft/iis-2-cinema-managament/actions/workflows/maven.yml/badge.svg)
[![CodeFactor](https://www.codefactor.io/repository/github/frederik-hoeft/iis-2-cinema-managament/badge?s=2033dbd82a54803db81d899b448496bfabb36920)](https://www.codefactor.io/repository/github/frederik-hoeft/iis-2-cinema-managament)

## DB: 

 - MySQL

## Server:

- Spring [MVC + JSON](https://www.geeksforgeeks.org/spring-rest-json-response/) für REST API
- MPS for persistence

## Client:

- C#/.NET 7 CLI application (`System.CommandLine`)

## Fragen:

- Client als richtige CLI anwendung, oder nur mocken? C# ist fine, aber mit CLI parser.
- Ist Transaktionssicherheit mit Hibernate okay, oder *muss* das der MPS kram sein? MPS zumindest versuchen
- Schrifliche ausarbeitung (LaTeX, Deutsch oder Englisch)? LaTeX englisch ist fine.
- Java version vorgegeben oder uns überlassen? (genauso mit IDE)? Egal.

## Rest API endpoints:

/management/{all the classes lol}/[create/update/delete/list]
/admin/[revenue]
/user/account/create (create new Customer)
/user/booking/[book/reserve/upgrade?/cancel/...]
/user/info/... (stuff für booking)
