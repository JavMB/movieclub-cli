# MovieClub CLI

Este proyecto es un ejercicio práctico tutorizado por [Kalimordkalume](https://github.com/Kalimordkalume), pensado para
aprender y afianzar conceptos de persistencia en Java usando JDBC y SQL sin frameworks. Surge porque en 1º DAM no se
pudo ver JDBC en profundidad, así que aquí se pone en práctica desde cero, primero guardando datos en memoria y luego
migrando a una base de datos PostgreSQL para entender el proceso y los retos reales.

## ¿Qué hace la aplicación?

Permite gestionar una colección de películas desde consola: crear, consultar, actualizar y borrar. Incluye filtros para
evitar géneros, directores o palabras malsonantes no deseadas, y validaciones como títulos en minúscula y control de
stock. La estructura está dividida en capas (presentación, lógica y persistencia), todo implementado manualmente para
entender bien cada parte. También se han creado utilidades propias para la entrada de datos y manejo de fechas.

## Cosas mejorables

El código tiene detalles que se pueden pulir: las credenciales de la base de datos están escritas directamente, no hay
pool de conexiones, todo se instancia manualmente y no hay tests. Son cosas que se podrían mejorar, pero la idea era
aprender y ver los límites de hacerlo "a pelo" antes de entrar en frameworks.

## Cómo ejecutarlo

Necesitas tener PostgreSQL funcionando. La aplicación espera una base de datos llamada `movies` con un usuario `root` y
contraseña `1234`. Una vez configurado, solo tienes que compilar y ejecutar:

```bash
mvn clean install
java -cp target/movieclub-cli-1.0.jar Main
```

---
