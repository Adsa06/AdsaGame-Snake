# Juego de Snake en Consola

Este es un proyecto de un juego de **Snake** en consola, desarrollado en **Java**. El juego permite al usuario jugar al clásico juego del Snake en un entorno de consola con controles simples y una representación visual en texto.

| Detalle              | Información       |
|----------------------|-------------------|
| **Autor**            | Aitor de Santos   |
| **Fecha de inicio**  | 23/01/2025        |
| **Última revisión**  | v1 23/01/2025     |

## Estructura del Proyecto

El proyecto está dividido en varios archivos Java organizados en:

```plaintext
VideojuegoFundamentos_project/
   VideojuegoFundamentos/
      README.md                  # Este archivo
      .vscode/                   # Configuraciones para Visual Studio Code
         settings.json
      lib/                       # Carpeta para bibliotecas externas (vacía)
      bin/                       # Carpeta con el codigo compilado del src/
      src/                       # Carpeta con el código fuente
         clases/                 # Carpeta donde guardo todas las clases del programa
         utils/                  # Carpeta donde se encuentra la mayor parte del contenido del programa
         PruebasAlocadas/        # Carpeta donde se guardan archivos no implementados al proyecto para hacer pruebas
      Main.java                  # Archivo principal del programa
      ControladorPrincipal.java  # Archivo para controlar la serpiente
      content.txt                # Archivo utilizado para conectar los 2 archivos anteriores
```

## Requisitos del Sistema

- **Java Development Kit (JDK)** versión 21 o superior.
- Una terminal en el sistema (CMD)

## Compilacion y ejecucion con ejecutable

1. Abre una terminal y escriba los siguientes comandos
2. cd ruta al proyecto en el src
3. `javac Main.java utils/Snake.java utils/ColoresConsola.java utils/Configuracion.java clases/Player.java`
4. `jar cfe MiProgramaSnake.jar Main Main.class utils/Snake.class utils/ColoresConsola.class utils/Configuracion.class clases/Player.class`
5. `javac ControladorPrincipal.java utils/ColoresConsola.java`
6. `jar cfe MiProgramaControlador.jar ControladorPrincipal ControladorPrincipal.class utils/ColoresConsola.class`
7. Ten abierta 2 terminales y ejecute un comando en cada una
8. `java -jar MiProgramaSnake.jar` y `java -jar MiProgramaControlador.jar`

## Funcionalidades Principales

- **Tablero dinámico**: Se muestra un tablero actualizado continuamente.
- **Condiciones de victoria**: Verifica automáticamente si un jugador ha ganado completando todo el cuadrado.
- **Condicion de derrota**: Detecta si el jugador se ha chocado con sigo misma o con las paredes.
- **Colores**: Diferencia los símbolos de la fruta y la serpiente mediante colores (Estos se pueden activar y desactivar).

## Descripción de Funcionamiento

### Fase 1: Menú Principal

1. **Inicio del Juego**:
   Al iniciar el programa, el usuario es recibido con un mensaje que solicita su nombre y luego presenta un menú con las siguientes opciones:
   - **1**: Jugar al juego de Snake.
   - **2**: Configuracion.
   - **3**: Salir del programa.

2. **Elección del Jugador**:
   El usuario selecciona una opción del menú principal. 
   - Si elige 1, el programa lo lleva directamente a la fase de juego de Snake. 
   - Si elige 2, el sistema muestra las opciones de configuración disponibles, como la velocidad o el tamaño. 
   - Si elige 3, el programa termina. 
   
   En caso de seleccionar una opción no válida, se muestra un mensaje de error y se vuelve a presentar el menú principal.

### Fase 2: Juego de Snake

1. **Tablero de Juego**:
   El tablero de juego se genera como una matriz de dimensiones personalizables en la seccion de configuracion (por defecto es 10x20), donde la serpiente se representa inicialmente en la parte superior izquierda.

2. **Movimiento de la Serpiente**:
   El jugador puede moverse utilizando las teclas **W**, **A**, **S**, **D** para moverla arriba, izquierda, abajo y derecha, respectivamente.

3. **Representación en Consola**:
   El tablero de juego se imprime en la consola en cada ciclo de movimiento. 
   - La serpiente se representa con el carácter `#` para el cuerpo principal, `*` para la cola y `O` para la cabeeza, todo ellos de tonos verdes.
   - Los espacios vacíos en el tablero está representado con un espacio.
   - La fruta esta representada con `@` de color rojo

### Fase 3: Almacenamiento de Comandos

El archivo `ControladorPrincipal.java` permite al jugador escribir instrucciones que se guardan en un archivo de texto (`./content.txt`). Este archivo es luego utilizado para controlar los movimientos de la serpiente, lo que simula un control manual del juego basado en los comandos ingresados.

### Colores en Consola

El archivo `ColoresConsola.java` se utiliza para aplicar colores al texto impreso en consola, con el fin de mejorar la visibilidad del juego.

## Notas Adicionales

Este proyecto está diseñado para aprender y mejorar en:

- **Modularización**: Descomposición del programa en métodos.
- **Documentación**: Creación de un análisis técnico del software.
- **Uso de herramientas modernas**: Familiarización con Visual Studio Code y JDK 21.