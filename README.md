# hexagon

construct a consulting platform which provides people with business advice

Current core requirements：

implement the module which allows user to publish requirement
establish experts's information with corresponding label
implement SMS registration module
call WeChat API to allow users to register the platform through WeChat Account
order module
implement user payment module thoguth alipay API or WeChat Pay API
Program framework brief： I established this project with Spring Boot framework, and integrated MyBatis to operate MySQL database more conveniently including CRUD(Create, Read, Update and Delete) and other complex operations.

I configured Druid as the data connection pool which is access, and Log4j2 as the tool to handle server logs.

Since in this project, frontend is separate with backend, I are going to implement frontend with Vue, and they can transfer data through AJAX.

Project Structure:

I used maven to build this project. pom.xml records all kinds of dependencies I need to use, resource/application.yml records all configurations of the project.

main/java/com.jojoliu.hexagon/+++ model: stores entities which are one-to-one corresponding with the table in database. mapper: stores all mappers. controller: stores controller corresponding to each entity, maps http request to corresponding function service: implements business functions