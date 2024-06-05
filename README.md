# PokeChallenge Springboot API

This Backend application developed in Spring Boot 3 was created in the context of a challenge for a Software Engineer interview.<br/>
[See deploy](https://pokechallenge.matiabossio.com.ar/api/v1/doc/swagger-ui/index.html)

## Instructions

For the backend, I was asked to build a REST API using Java Spring Boot or Node.js to expose information to the Pokédex.

This API was supposed to consume the external service PokeApi https://pokeapi.co/ to obtain the information. The documentation can be found at https://pokeapi.co/docs/v2.

The necessary endpoints had to be exposed in order to set up a frontend (which is not part of the deliverable), which was a page listing the pokémons initially showing the following information (Basic Information):
* Photo (url)
* Type
* Weight
* Abilities List

When the user clicked on any of the pokemons, a screen with more details of the selected pokemon had to be shown:
* Basic Information (returned in the list of pokemons)
* Description
* List of Moves (name field in moves)
* Swagger had to be included in the API to test the endpoints
* Optionally, I could choose to add different languages

The application had to be deployed on AWS, Azure, GCP, or Heroku, of choice. The application code had to be hosted in a Git repository.

Any extra functionality or optimization was welcome: I added the option to get a Cached List for the List of Pokemons endpoint in order save time, resources and external API consumption. 🚀

With this challenge, they evaluated me to see if I was able to:
* Integrate an external API.
* Expose an API using Java Spring Boot.
* Implement Swagger.
* Deploy in the cloud.
* Handle errors.
* Maintain an ordered and quality code.
