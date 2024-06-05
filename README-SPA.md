# PokeChallenge Springboot API

Esta aplicación Backend desarrollada en Springboot 3 fue desarrollada en el contexto de un challenge para una entrevista de Software Engineer.<br/>
[Ver deploy](https://pokechallenge.matiabossio.com.ar/api/v1/doc/swagger-ui/index.html)

## Instrucciones
Para el back-end se pide construir una API REST usando Java Spring Boot o Node Js que exponga la información al Pokedex.

Esta API debe consumir el servicio externo PokeApi https://pokeapi.co/ para obtener la información. La documentación la puedes encontrar en https://pokeapi.co/docs/v2.

Se deben exponer los endpoints necesarios para poder montar un frontend (no es parte del entregable) que es una página donde se listan los pokémons inicialmente mostrando la siguiente información (información básica):
* Foto (url)
* Tipo (type)
* Peso (weight)
* Listado de Habilidades (ability)

Cuando el usuario haga click en alguno de los pokemons, se muestra una pantalla con más detalles del pokemon seleccionado:
* Información Básica (devuelta en la lista de pokemons)
* Descripción (en español)
* Lista de Movimientos (campo name en moves)

* Debes incluir Swagger en tu API para poder probar los endpoints. Pero si deseas (opcional) puedes hacer un front-end que haga las llamadas mencionadas arriba, y puedes elegir el lenguaje de tu preferencia.

La aplicación debe ser desplegada en AWS, Azure, GCP o Heroku, a elección. El código de la aplicación debe estar alojado en algún repositorio Git al que debes darnos acceso.

Cualquier funcionalidad extra u optimización es bienvenida.

Lo que evaluaremos con este desafío, es que seas capaz de:
* Integrar una API externa.
* Exponer un API usando Java Spring Boot.
* Implementar Swagger o consumir API desde front-end
* Desplegar en la nube.
* Manejar errores.
* Mantener un código ordenado y de calidad.
