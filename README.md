# 📱 TecnoStore - Sistema de Venta de Celulares

Sistema de gestión de ventas de celulares desarrollado en **Java 17** con arquitectura **MVC** (Modelo-Vista-Controlador). El proyecto está diseñado para ejecutarse desde la terminal, utilizando **MySQL** como base de datos y aplicando buenas prácticas de programación orientada a objetos y patrones de diseño.

## ✨ Características Principales

- 🔐 **Control de Acceso**: Roles diferenciados para Administradores y Vendedores.
- 📦 **Gestión de Inventario**: CRUD completo de celulares con validación de precios y stock.
- 👥 **Gestión de Clientes**: Registro con validación de formato de correo electrónico e identificación única.
- 🛒 **Proceso de Venta**: Carrito de compras en consola con cálculo automático de IVA (19%).
- 📊 **Reportes y Análisis**: 
  - Celulares con stock bajo (< 5 unidades).
  - Top 3 de celulares más vendidos (usando Java Stream API).
  - Ventas totales agrupadas por mes.
  - Exportación de reporte general a archivo `.txt`.
- 🛡️ **Seguridad y Auditoría**: Registro automático de modificaciones en la base de datos.

## 🏗️ Arquitectura y Patrones de Diseño

El proyecto aplica los siguientes patrones y principios de software:
- **MVC (Modelo-Vista-Controlador)**: Separación clara de responsabilidades.
- **Singleton**: Para la gestión de la conexión a la base de datos (`ConexionDB`).
- **Factory**: Para la creación de instancias de los DAOs (`DAOFactory`).
- **Strategy**: Para el cálculo de impuestos (`EstrategiaCalculoImpuesto`), permitiendo fácil escalabilidad.
- **DAO (Data Access Object)**: Abstracción completa del acceso a datos.
- **Excepciones Personalizadas**: Manejo robusto de errores de negocio (ej. `StockInsuficienteException`, `EmailInvalidoException`).

## 🗄️ Base de Datos Avanzada

El sistema delega la lógica pesada a la base de datos MySQL mediante:
- **Stored Procedures**: Para operaciones de inserción, actualización y consultas complejas.
- **Triggers**: 
  - Validación de stock en tiempo real antes de registrar un detalle de venta.
  - Desconto automático de inventario post-venta.
  - Auditoría automática de cambios en la tabla de celulares.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Gestor de Dependencias**: Apache Maven
- **Base de Datos**: MySQL 8.x
- **Conexión**: JDBC (MySQL Connector/J)
- **Control de Versiones**: Git & GitHub

## 🚀 Instalación y Ejecución

### 1. Prerrequisitos
- Tener instalado **Java 17** o superior.
- Tener instalado **Apache Maven**.
- Tener un servidor **MySQL** corriendo.

### 2. Configuración de la Base de Datos
1. Accede a tu consola de MySQL:
```bash
   mysql -u tu_usuario -p
```
2. Ejecuta el script SQL proporcionado en el proyecto (o disponible en la documentación) para crear la base de datos, tablas, procedimientos almacenados, triggers y datos de prueba.
   *(Nota: El script incluye `DROP DATABASE IF EXISTS`, lo que reiniciará la base de datos).*

### 3. Configuración del Proyecto
1. Clona este repositorio:
```bash
   git clone https://github.com/orduzdarney-dot/Proyecto_Venta_Celulares.git
   cd Proyecto_Venta_Celulares
```
2. Abre el archivo `src/main/java/com/mycompany/Tecnostore/persistencia/conexion/ConexionDB.java` y actualiza tus credenciales de MySQL:
```java
   private static final String USUARIO = "tu_usuario_mysql";
   private static final String PASSWORD = "tu_contraseña_mysql";
```

### 4. Compilar y Ejecutar
Desde la terminal, en la raíz del proyecto, ejecuta:

```bash
# 1. Limpiar y compilar el proyecto
mvn clean package

# 2. Ejecutar el archivo JAR generado
java -jar target/VentaCelulares-1.0-SNAPSHOT.jar
```

## 🔑 Credenciales de Prueba

El script de base de datos incluye los siguientes usuarios por defecto:

| Rol | Usuario | Contraseña |
| :--- | :--- | :--- |
| **Administrador** | `admin` | `1234` |
| **Vendedor** | `vendedor` | `1234` |

## 📂 Estructura del Proyecto

```text
src/main/java/com/mycompany/Tecnostore/
├── Main.java                  # Punto de entrada
├── controlador/               # Lógica de coordinación (MVC)
├── excepciones/               # Excepciones personalizadas
├── modelo/                    # Entidades y Enums
├── persistencia/              # Conexión (Singleton) y DAOs
├── reportes/                  # Lógica de generación de informes (Stream API)
├── servicio/                  # Reglas de negocio y Patrones (Strategy, Factory)
└── vista/                     # Interfaz de consola (Scanner, System.out)
```

## 👨‍💻 Autor

Desarrollado por **Darney Orduz**  
🔗 [GitHub Profile](https://github.com/orduzdarney-dot)




```


<img width="1494" height="1739" alt="Captura desde 2026-10-14 10-31-06" src="https://github.com/user-attachments/assets/232c92e6-86d9-4986-b110-4c2f033f9cb3" />
<img width="1494" height="1739" alt="Captura desde 2026-10-14 10-30-51" src="https://github.com/user-attachments/assets/734e3dba-3f7f-4be6-8509-3bb3a552a2bc" />
<img width="1494" height="1739" alt="Captura desde 2026-10-14 10-30-13" src="https://github.com/user-attachments/assets/4815b998-41b3-4e1c-b563-62b8518a625c" />
<img width="1494" height="1739" alt="Captura desde 2026-10-14 10-28-57" src="https://github.com/user-attachments/assets/5d53746f-8676-4b5d-ad1b-521f9dbf93de" />

