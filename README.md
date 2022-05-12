## __Team Player Task__

### Project Requirements

* JDK 17(openjdk-17)
* Spring Boot 2.6.7
* Maven 3.6.3 or newer
* Docker
* Mockito and JUnit5
* GraphQL

### 1.Build Project

    $ git clone https://github.com/yunusbagriyanik/team-player.git
    $ cd team-player
    $ mvn clean install
    $ docker build -t team-player .

### Run Unit Test

    mvn -Dtest=PlayerServiceTest test

### 2.Run Project

* The application works in mysql by default. Therefore, the mysql container must be run before the application run.

#### Run With Docker

    $ docker network create teamplayer
    $ docker create -v /var/lib/mysql --name team-player-dbserver mysql/mysql-server:latest
    $ docker container run --volumes-from team-player-dbserver --name teamplayerdb --network teamplayer -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=teamplayerdevdb -d mysql:8.0.28
    $ docker run --name teamplayer-dashboard --network teamplayer -p 8000:80 --link teamplayerdb:db -d phpmyadmin/phpmyadmin
    $ mvn spring-boot:run

#### After the application starts up, run the script [team-player.sql](https://github.com/yunusbagriyanik/team-player/blob/master/src/main/resources/database/team-player.sql).

* Database Dashboard url: http://localhost:8000/

* Swagger url: http://localhost:8090/swagger-ui/index.html#/



<h3 align="left">Tech Stack</h3>
<p align="left"> 
<a href="https://www.spring.io" target="_blank"> <img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" alt="spring" width="100" height="50"/>
<a href="https://www.java.com/en/" target="_blank"> <img src="https://upload.wikimedia.org/wikipedia/tr/2/2e/Java_Logo.svg" alt="java" width="50" height="50"/>
<a href="https://hibernate.org/orm/" target="_blank"> <img src="https://in.relation.to/images/hibernate_icon_whitebkg.svg" alt="hibernate" width="50" height="50"/>
<a href="https://graphql.org/" target="_blank"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/GraphQL_Logo.svg/800px-GraphQL_Logo.svg.png" alt="graphql" width="50" height="50"/>
<a href="https://swagger.io/" target="_blank"> <img src="https://help.apiary.io/images/swagger-logo.png" alt="swagger" width="" height="50"/>
<a href="https://www.docker.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="50" height="50"/>
<a href="https://git-scm.com/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="50" height="50"/>
<a href="https://www.mysql.com/" target="_blank"> <img src="https://www.onurbabur.com/wp-content/uploads/2020/09/MySQL-Logo.wine_.png" alt="mysql" width="50" height="50"/>
</p>