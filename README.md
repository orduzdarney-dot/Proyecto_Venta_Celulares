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
