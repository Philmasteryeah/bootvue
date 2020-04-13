
# bootvue
- Spring Boot 2
- Vue.js
- CoreUI with AdminLTE
- axios for REST
- Java, Maven, Node

# info
- simple prototype approach for modern web applications
- two seperated projects managed with maven
- monolitic generic user backend as auth and resource gateway
	 - should work as base for other projects
- vue as simple and modern frontend
	- can be replaced later

# why
- vue vs angular vs react
	- vue is small, modern, simple and easy to use
	- no typescript
	- no complex Facebook bloat
- spring boot vs javaEE
	- versatile bloat integrations in spring
	- less code with more functionality

# TODO
- complete backend archetype with users and auth
- single sign on with JWT with redis
- elasticsearch integration
- testing other frontend primeVue https://www.primefaces.org/primevue/
- usage of netty instead of tomcat
- swagger UI for api documentation
- image of the architecture design

# How to start
- start both

```
$ git clone https://github.com/Philmasteryeah/bootvue.git
$ cd bootvue
$
$ mvn clean install
$ mvn --projects backend spring-boot:run
$
```
- open http://localhost:8098/

- or only the frontend

```
$
$ cd bootvue\frontend\
$
$ npm start serve
$
```
- http://localhost:8081/

# Screenshots

![alt text](https://abload.de/img/bootvuektjmt.png)

![alt text](https://abload.de/img/bootvue1jj8a.png)

# Axios

![alt text](https://bezkoder.com/wp-content/uploads/2019/12/spring-boot-vue-js-crud-example-architecture.png)


# THX
- great project: https://github.com/jonashackt/spring-boot-vuejs
- nice example: https://bezkoder.com/spring-boot-vue-js-crud-example/
