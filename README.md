# computationally-intense-cucumber
A spot of practice for some reasons. You will probably not be interested.

# What's in the box?
A spring boot application :)

Start up, visit one of (or both!)
- http://localhost:8080/swagger-ui.html
- http://localhost:8080

Hopefully from there it will be self explanatory!

## This is not self explanatory at all!
### http://localhost:8080/swagger-ui.html
Here we have a [swagger UI](http://springfox.github.io/springfox/) demonstrating the rest end points exactly as specified.
Go ahead, hit the "Try it" buttons :)

#### Note
Please ignore the current error relating to "Could not resolve pointer: /definitions/Calendar" - it does NOT affect the functioning of the API in anyway.

### http://localhost:8080
#### Reading
- https://martinfowler.com/articles/richardsonMaturityModel.html
- https://damienfremont.com/2017/11/23/rest-api-maturity-levels-from-0-to-5/
- http://timelessrepo.com/haters-gonna-hateoas
- http://javasampleapproach.com/spring-framework/spring-data/use-hal-browser-spring-data-rest-springboot-mysql

So HATE(oas) it or love it, there is a movement towards "REST nirvana".

So, at the application root, you will find a HAL browser, which you can use to explore the
API.

Note that the endpoints here are, by necessity NOT exactly as specified.


# Building
I assume Java 8 and maven 3.5 is installed and working.

If so, fire up 

`mvn clean package`

## Docker
If you would like to build a docker image instead, you can run 

`mvn -Ddocker.image.name=demaniak/cic -P build-docker clean deploy`

This of course assume Docker is alive and well on your system.

It will build the image, AND then attempt to push it to docker central.
Unless you somehow have MY credentials, that won't work.

Please adjust *demaniak/cic* in the above command to an image name that YOU have `write` access to.

# Running
Execute the jar! 

`java -jar target/cic-0.0.1-SNAPSHOT.jar`

## Docker 
Or you could run the docker image living on line!

`docker run -it --rm -p 8080:8080 demaniak/cic:0.0.1-SNAPSHOT`

# To exit running
Ctrl+C should kill it nice and dead.

# Hacking
If you would like to work on the source code, your IDE will seem upset until
you introduce it to [lombok](https://projectlombok.org/).

Installing it in your IDE will change your life.

