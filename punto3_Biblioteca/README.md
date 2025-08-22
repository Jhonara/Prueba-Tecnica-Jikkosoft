# Sistema de Gestión de Bibliotecas - Prueba Técnica

Este módulo corresponde al **punto 3** de la prueba técnica. Se trata de un sistema backend sencillo para gestionar libros, miembros y préstamos, implementado en **Java con Spring Boot**, usando persistencia en memoria.

---

## Objetivo

Modelar un sistema orientado a objetos que permita:

- Registrar libros y miembros
- Prestar y devolver libros
- Consultar libros disponibles y libros prestados por miembro

---

## Tecnologías

- Java 21
- Spring Boot 3.5.5
- Maven
- Jakarta Validation
- Persistencia en memoria (`ConcurrentHashMap`, `AtomicLong`)
- Lombok

---

## Estructura del proyecto
Biblioteca/ 
├── controller/      
|    └──LibraryController
|
├── model/            
|    ├──Book
|    ├──Member
|    └──Library
|
└── service/          
      └──LibraryServicw



## Ejecución

1. Clonar o descargar este repositorio.
2. Abrir el proyecto en tu IDE (ejemplo: IntelliJ IDEA).
3. Ejecutar la clase **Main.java** mvn spring-boot:run

##  Accede a los endpoints en http://localhost:8080/api

## ----- Libros
| Método | Endpoint | Descripción | 
| POST | /books | Agregar libro | 
| GET | /books | Listar todos los libros | 
| GET | /books/available | Listar libros disponibles | 

## ----- Miembros
| Método | Endpoint | Descripción | 
| POST | /members | Registrar miembro | 
| GET | /members | Listar miembros | 
| GET | /members/{id}/books | Ver libros prestados por miembro | 

##  -----  Préstamos
| Método | Endpoint | Descripción | 
| POST | /loans/borrow?bookId=1&memberId=1 | Prestar libro | 
| POST | /loans/return?bookId=1&memberId=1 | Devolver libro | 

## Reglas de negocio
- Un libro solo puede estar prestado a un miembro a la vez
- Un miembro puede tener como máximo 3 libros prestados
- Validaciones con ResponseStatusException para errores comunes


## Notas técnicas
- No se utiliza base de datos ni repositorios (repository) ya que se simula persistencia en memoria
- Los DTOs para creación (CreateBookRequest, CreateMemberRequest) están definidos dentro del servicio para simplificar la estructur


## Extras
- Puedes consultar el estado completo de la biblioteca en:
GET /api/state


## Ejemplos para ejecutar en postman

## Libros

-- Crear un libro

POST http://localhost:8080/api/books
Content-Type: application/json

{
  "title": "Titulo del libro",
  "author": "Jhonatan Ramirez",
  "isbn": "123456789"
}


-- Listar todos los libros

GET http://localhost:8080/api/books


-- Listar libros disponibles

GET http://localhost:8080/api/books/available

## Miembros

-- Registrar un miembro

POST http://localhost:8080/api/members
Content-Type: application/json

{
  "name": "Jhonatan Ramirez"
}


-- Listar todos los miembros

GET http://localhost:8080/api/members


-- Listar libros prestados por un miembro

GET http://localhost:8080/api/members/1/books


(cambiar el 1 por el memberId real)

## Préstamos

-- Prestar un libro

POST http://localhost:8080/api/loans/borrow?bookId=1&memberId=1


-- Devolver un libro

POST http://localhost:8080/api/loans/return?bookId=1&memberId=1

## Estado general de la biblioteca 
GET http://localhost:8080/api/state


Te devolverá todo el estado (libros + miembros con préstamos).

## Autor

Proyecto creado por **Jhonatan Stiven Ramírez Useche**