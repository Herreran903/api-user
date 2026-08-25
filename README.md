# api-user

Microservicio de autenticación y gestión de usuarios construido con Java 17 y Spring
Boot, siguiendo arquitectura hexagonal. Emite los JWT que consumen los demás servicios.

Forma parte del reto **Emazon**, una tienda virtual dividida en microservicios
independientes, desarrollado durante el **Bootcamp Power Up de Pragma** (2024).

## Arquitectura

Puertos y adaptadores, con el dominio aislado de la infraestructura:

```
domain/     modelos, reglas de negocio, casos de uso y puertos
            (user, auth, role, error)
app/        handlers de aplicación, DTOs y mappers de MapStruct
infra/      adaptadores de entrada (controladores REST) y de salida
            (persistencia JPA), seguridad, manejo de excepciones y OpenAPI
```

## Endpoints

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `POST` | `/auth/login` | público | Autenticación por correo y clave, devuelve JWT |
| `POST` | `/user/client/register` | público | Registro de cliente |
| `POST` | `/user/warehouse-assistant/register` | `ADMIN` | Alta de auxiliar de bodega |

El controlador de usuarios declara `@PreAuthorize("denyAll()")` a nivel de clase: **todo
está denegado por defecto** y cada endpoint habilita explícitamente quién puede llamarlo.
Es lo contrario a abrir todo y cerrar excepciones, y evita que un método nuevo quede
accesible por olvido.

Documentación interactiva en `/swagger-ui.html` una vez levantado el servicio.

## Reglas de negocio implementadas

- Contraseñas cifradas con **BCrypt** al almacenarlas, nunca en tránsito hacia el dominio
- Validación de estructura de correo electrónico
- Teléfono de máximo 13 caracteres, admite el prefijo `+`
- Documento de identidad exclusivamente numérico
- Verificación de mayoría de edad a partir de la fecha de nacimiento
- Tres roles: `ADMIN`, `WAREHOUSE_ASSISTANT` y `CLIENT`
- Manejo centralizado de excepciones con respuestas de error consistentes

## Stack

- **Java 17**, **Spring Boot 3.3**
- Spring Web, Spring Data JPA, Spring Security, Spring Validation
- **MySQL** como motor de persistencia
- **MapStruct 1.5.5** para el mapeo entre entidades y DTOs
- **JJWT 0.12.6** para la emisión y firma de tokens
- **springdoc-openapi 2.6.0** para la documentación
- **JUnit 5** y **Mockito** para pruebas

## Pruebas

16 clases de prueba que cubren modelos de dominio, casos de uso, handlers de aplicación
y controladores.

```bash
./gradlew test
```

## Ejecución local

Requiere Java 17 y una instancia de MySQL.

```bash
git clone https://github.com/Herreran903/api-user.git
cd api-user
./gradlew bootRun
```

Configura la conexión a la base de datos y la clave de firma del JWT en
`src/main/resources/application.properties` antes de arrancar.

## Servicios relacionados

- [`api-stock`](https://github.com/Herreran903/api-stock) — catálogo e inventario
