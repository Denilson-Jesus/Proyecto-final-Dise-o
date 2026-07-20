#  Sistema de Gestión de Biblioteca

##  Descripción

El Sistema de Gestión de Biblioteca es una aplicación de escritorio desarrollada en Java utilizando NetBeans y SQLite como base de datos.

El sistema permite administrar usuarios, libros y préstamos de una biblioteca mediante una interfaz gráfica desarrollada con Java Swing, aplicando principios SOLID, patrones de diseño GOF y principios GRASP para lograr un software organizado, mantenible y escalable.

---

#  Objetivos

- Gestionar usuarios.
- Gestionar libros.
- Registrar préstamos.
- Registrar devoluciones.
- Actualizar automáticamente el stock de libros.
- Aplicar buenas prácticas de programación orientada a objetos.

---

#  Tecnologías utilizadas

- Java
- Java Swing
- SQLite
- JDBC
- NetBeans IDE

---

#  Estructura del proyecto

```
SistemaBiblioteca
│
├── conexion
├── controlador
├── dao
├── modelo
├── observer
├── strategy
├── adapter
├── factory
├── servicio
├── util
├── vista
└── main
```

---

#  Funcionalidades

## Usuarios

- Registrar usuarios
- Actualizar usuarios
- Eliminar usuarios
- Listar usuarios

---

## Libros

- Registrar libros
- Actualizar libros
- Eliminar libros
- Buscar libros
- Listar libros

---

## Préstamos

- Registrar préstamo
- Devolver préstamo
- Actualizar stock automáticamente
- Listar préstamos

---

#  Base de datos

Se utiliza SQLite con tres tablas principales:

- Usuarios
- Libros
- Préstamos

---

#  Usuario administrador

Usuario:

```
admin
```

Contraseña:

```
1234
```

---

#  Patrones de diseño implementados

## GOF

### Creacionales

### Singleton

Implementado en:

- ConexionBD
- BibliotecaFacade

Permite que exista una única instancia para acceder a la base de datos y a los servicios principales del sistema.

---

### Factory Method

Implementado en:

- UsuarioFactory

Permite crear objetos Alumno o Docente sin depender directamente de sus constructores.

---

### Estructurales

### Facade

Implementado en:

- BibliotecaFacade

Centraliza todas las operaciones relacionadas con usuarios, libros y préstamos.

---

### Adapter

Implementado en:

- UsuarioAdapter

Permite adaptar un objeto Usuario para mostrar su información en un formato diferente sin modificar la clase original.

---

### Comportamiento

### Observer

Implementado mediante:

- Observer
- BibliotecaSubject

Permite actualizar automáticamente las ventanas cuando ocurre un préstamo o devolución.

---

### Strategy

Implementado mediante:

- BusquedaStrategy
- BusquedaContext
- BusquedaPorTitulo
- BusquedaPorAutor
- BusquedaPorCategoria

Permite cambiar dinámicamente la estrategia utilizada para buscar libros.

---

#  Principios SOLID

## S - Single Responsibility Principle

Cada clase tiene una única responsabilidad.

Ejemplo:

- LibroDAO
- UsuarioDAO
- PrestamoDAO

---

## O - Open/Closed Principle

Las estrategias de búsqueda permiten extender funcionalidades sin modificar código existente.

---

## L - Liskov Substitution Principle

Alumno y Docente heredan correctamente de Usuario.

---

## I - Interface Segregation Principle

Las interfaces Observer y BusquedaStrategy contienen únicamente los métodos necesarios.

---

## D - Dependency Inversion Principle

El sistema utiliza abstracciones mediante Factory, Observer y Strategy para reducir el acoplamiento entre componentes.

---

#  Principios GRASP

## Controller

Implementado mediante:

- UsuarioController
- LibroController
- PrestamoController

Se encargan de coordinar la lógica entre la interfaz y la base de datos.

---

## Creator

Implementado mediante:

- UsuarioFactory
- BibliotecaFacade

Responsables de crear los objetos necesarios dentro del sistema.

---

#  Cómo ejecutar el proyecto

1. Abrir el proyecto en NetBeans.
2. Agregar la librería SQLite JDBC.
3. Ejecutar la clase Principal.
4. Iniciar sesión con:

Usuario:

admin

Contraseña:

1234

---

# ‍ Autor

Proyecto desarrollado para el curso de Patrones de Diseño.
INTEGRANTES:

- Medina Medina Denilson Jesus
- Palomino Huamani Jimmy Cristian
- Prado Lozada Alison Dayana