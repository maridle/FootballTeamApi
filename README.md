"# FootballTeamApi" 

to get started:

This is a spring boot api, the easiest way to run is to import the project to Intellij and run directly from:
BookStorageApplication as spring boot application.

You can also start it using Maven from the commandline/terminal

Alternatively you can build the application using commands:
 * mvn clean install
 * mvn spring-boot:run


endpoints:

get a team by name -> http://localhost:8080/v1/football-teams/{teamname}

get all teams ordered by stadium capacity -> http://localhost:8080/v1/football-teams?sortByStadiumCapacity=true

get all teams in no specific order -> http://localhost:8080/v1/football-teams

post a new team -> http://localhost:8080/v1/football-teams
    body {  "name": "Team Name",
               "owner": "Owner Name",
               "competition": "Name of the competition",
               "city": "name of the city",
               "stadiumCapacity": value,
               "numberOfPlayers": value,
               "dateOfCreation": "date in format yyyy-mm-dd"
         }    
         

Assumptions:
Get a list of all teams in Json Format &&
Get a list of teams sorted by their stadium capacity:
means that both endpoints return all teams in Json format (one is ordered the other is not)

Technologies used
Spring Boot, java 8
Spring boot is my preferred Java Framework, it allows you to create elegant 
Rest APIs with full control over the behaviour. It is also very well documented
providing devs with the ability to easily pickup additional features.
I typically use Java 11 however not everyone does so to make things simpler I used java 8.

What I would do in the real world: 
* use a database to persist the teams
* use an in memory database h2 for the test scenarios
* use spring hateos 
* confirm the exact requirements for validation etc
* I would store the teams in capacity order removing the need to sort them all at once
* implement more concise logging 
* improve unit test to cover more of the codebase (ran out of time)

           