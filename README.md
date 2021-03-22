# Kardex Backend
Esta app realiza gestiona inventarios por método de promedio.

## Información técnica
- Gestor: Gradle
- Lenguaje de programación: Java 8
- Framework: Spring Boot
- Base de datos: H2
- Lombok: Generar setter, getter, constructores, builder.

## Ejecución del proyecto
```
./gradlew bootRun
```

## Endpoints
- auth/register (post): Registro de usuario
- auth/login (post): Login de usuario
- buys (post): Guardar compra de productos
- products (get): Obtener productos
- products/{id} (get): Obtener producto con kardex
- products (post): Guardar producto
- sales (post): Guardar venta
- shopping_cart/add: Agregar al carrito de compra
- shopping_cart/get: Obtener carrito de compras
