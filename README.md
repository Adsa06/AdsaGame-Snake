# Juego de Snake en Consola

Este es un proyecto de un juego de **Snake** en consola, desarrollado en **Java**. El juego permite al usuario jugar al clásico juego del Snake en un entorno de consola con controles simples y una representación visual en texto.

| Detalle              | Información       |
|----------------------|-------------------|
| **Autor**            | Aitor de Santos   |
| **Fecha de inicio**  | 17/10/2024        |
| **Última revisión**  | v7 25/04/2025     |

Enlace al repositorio de git: https://github.com/Adsa06/VideojuegoFundamentos.git
## Estructura del Proyecto

El proyecto está dividido en varios archivos Java organizados en:

```plaintext
VideojuegoFundamentos/
├── README.md                       # Este archivo
├── .vscode/                        # Configuraciones para Visual Studio Code
├── target/                         # Carpeta con el código compilado del src/
├── Player/                         # Carpeta con la informacion de los jugadores localmente
├── src/main/java/dev/adsa          # Carpeta con el código fuente
│   ├── clases/                     # Clases principales del programa
│   │   ├── modos/                  # Clases de los modos de juego
│   │   ├── JuegoBase.java          # Clase base para el juego
│   │   ├── Partida.java            # Clase para gestionar las partidas
│   │   └── Player.java             # Clase para gestionar los jugadores
│   ├── utils/                      # Utilidades y controladores
│   │   ├── ColoresConsola.java     # Gestión de colores en consola
│   │   ├── Configuracion.java      # Configuración del juego
│   │   ├── ControladorJuego.java   # Controlador principal del juego
│   │   └── Utilidades.java         # Funciones auxiliares
│   ├── bbdd/                       # Gestión de la base de datos
│   │   ├── ConexionDB.java         # Conexión a la base de datos
│   │   └── GestionDB.java          # Gestión de datos en la base de datos
│   ├── ControladorPrincipal.java   # Controlador para los comandos del jugador
│   └── Main.java                   # Archivo principal del programa
├── content.txt                     # Archivo utilizado para conectar los controladores
├── content.txt                     # Archivo utilizado para controlar la subida de archivos
└── pom.xml                         # Archivo de configuración de Maven
```

## Requisitos del Sistema

- **Java Development Kit (JDK)** versión 21 o superior.
- **Maven** para la gestión de dependencias.
- **MySQL** para la base de datos (con una base de datos llamada `playerssnake`).
- Una terminal en el sistema (CMD, PowerShell, etc.).

## Compilación y Ejecución

### Compilación con Maven

1. Abre una terminal y navega al directorio del proyecto.
2. Ejecuta el siguiente comando para compilar el proyecto:
   ```bash
   mvn clean compile
   ```
3. Esto generará los archivos `.class` en la carpeta `target/`.

### Ejecución del Juego

1. Abre dos terminales.
2. En la primera terminal, ejecuta la clase principal:
   ```bash
   mvn exec:java -Dexec.mainClass="dev.adsa.Main"
   ```
3. En la segunda terminal, ejecuta el controlador:
   ```bash
   mvn exec:java -Dexec.mainClass="dev.adsa.ControladorPrincipal"
   ```

### Creacion del javadoc con maven

1. Abre una terminal y navega al directorio del proyecto.
2. Ejecuta el siguiente comando para compilar el proyecto:
   ```bash
   mvn javadoc:javadoc
   ```
3. Esto generará los archivos necesarios en la carpeta `target\reports\apidocs\`.

### Creacion del .jar con VSCode

En el IDE, en el apartado de JAVA PROJECTS haay un boton para exprotar automaticamente como .jar
Tendras que hacerlo 2 veces, una para el Controlador principal y el siguiente para Main.
Advertencia: como los 2 se te guardaran con el mismo nombre tendras que cambiar el nombre al primero que crees
Estos se te crearan en la raiz del proyecto, para ejecutarlos abra 2 terminales y ejecute este comando para los 2 archivos:
   ```bash
   java -jar nombredelarchivo.jar
   ```

## Funcionalidades Principales

- **Tablero dinámico**: Se muestra un tablero actualizado continuamente.
- **Condiciones de victoria**: Verifica automáticamente si un jugador ha ganado completando todo el cuadrado.
- **Condicion de derrota**: Detecta si el jugador se ha chocado con sigo misma o con las paredes.
- **Tablero dinámico**: Se muestra un tablero actualizado continuamente.
- **Condiciones de victoria**: Verifica automáticamente si un jugador ha ganado completando todo el tablero.
- **Condiciones de derrota**: Detecta si el jugador se ha chocado consigo mismo o con las paredes.
- **Colores personalizables**: Diferencia los símbolos de la fruta y la serpiente mediante colores (opcional).
- **Modos de juego**:
  - **Modo Normal**: El jugador no puede atravesar las paredes.
  - **Modo Atravesar Paredes**: El jugador puede atravesar las paredes y aparecer en el lado opuesto.

## Descripción de Funcionamiento

### Fase 1: Menú Principal

1. **Inicio del Juego**:
   Antes de iniciar el juego se necesita crear una base de datos llamada `playerssnake` antes de ejecutar el programa. Mas informacion en el punto [Base de Datos](#base-de-datos)

   Al iniciar el programa, el usuario es recibido con un mensaje que solicita su nombre y luego presenta un menú con las siguientes opciones:
   - **1**: Jugar al juego de Snake.
   - **2**: Configuración.
   - **3**: Mostrar perfil del jugador.
   - **4**: Cerrar sesión.
   - **5**: Eliminar perfil.
   - **6**: Salir del programa.

2. **Elección del Jugador**:
   El usuario selecciona una opción del menú principal. Dependiendo de la opción, se inicia el juego, se configura el tablero, o se realizan otras acciones.

### Fase 2: Juego de Snake

1. **Tablero de Juego**:
   El tablero de juego se genera como una matriz de dimensiones personalizables en la seccion de configuracion (por defecto es 10x20), donde la serpiente se representa inicialmente en la parte superior izquierda.

2. **Movimiento de la Serpiente**:
   El jugador puede moverse utilizando las teclas **W**, **A**, **S**, **D** para moverla arriba, izquierda, abajo y derecha, respectivamente.

3. **Representación en Consola**:
   - La serpiente se representa con:
     - `#` para el cuerpo.
     - `*` para la cola.
     - `O` para la cabeza.
   - Los espacios vacíos están representados con un espacio.
   - La fruta está representada con `@`.

### Fase 3: Almacenamiento de Comandos

El archivo `ControladorPrincipal.java` permite al jugador escribir instrucciones que se guardan en un archivo de texto (`./content.txt`). Este archivo es luego utilizado para controlar los movimientos de la serpiente, lo que simula un control manual del juego basado en los comandos ingresados.

### Colores en Consola

El archivo `ColoresConsola.java` se utiliza para aplicar colores al texto impreso en consola, con el fin de mejorar la visibilidad del juego.

## Base de Datos

El proyecto utiliza una base de datos MySQL para almacenar información sobre los jugadores y sus partidas. Asegúrate de crear una base de datos llamada `playerssnake` antes de ejecutar el programa.

### Configuración de la Base de Datos

1. Crea una base de datos llamada `playerssnake`.
2. Configura las credenciales en el archivo `ConexionDB.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/playerssnake";
   private static final String USER = "root";
   private static final String PASSWORD = "Password1234";
   ```

## Notas Adicionales

Este proyecto está diseñado para aprender y mejorar en:

- **Modularización**: Descomposición del programa en métodos.
- **Documentación**: Creación de un análisis técnico del software.
- **Uso de herramientas modernas**: Familiarización con Visual Studio Code, JDK 21, Maven y MySQL.