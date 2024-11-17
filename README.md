# Juego de Snake en Consola

Este es un proyecto de un juego de **Snake** en consola, desarrollado en **Java**. El juego permite al usuario jugar al clásico juego del Snake en un entorno de consola con controles simples y una representación visual en texto.

## Estructura del Proyecto

El proyecto está dividido en varios archivos Java organizados en dos carpetas principales:

1. **Archivos Principales**:
   - `Main.java`: Archivo principal donde se gestiona el inicio del juego y las opciones del menú.
   - `ControladorPrincipal.java`: Gestiona la entrada del usuario para escribir comandos y almacenar la información en un archivo de texto.

2. **Carpeta `src`**:
   - `Snake.java`: Contiene la lógica del juego de Snake, incluyendo el movimiento de la serpiente, la actualización de su posición y la detección de colisiones.
   - `ColoresConsola.java`: Define métodos para aplicar colores en la consola, permitiendo que el juego se vea más atractivo visualmente.

## Descripción de Funcionamiento

### Fase 1: Menú Principal

1. **Inicio del Juego**:
   Al iniciar el programa, el usuario es recibido con un mensaje que solicita su nombre y luego presenta un menú con las siguientes opciones:
   - **1**: Jugar al juego de Snake.
   - **2**: Opción vacía (actualmente no implementada).
   - **3**: Salir del programa.

2. **Elección del Jugador**:
   El usuario elige la opción de jugar, lo que da paso a la fase de juego de Snake. Si elige salir, el programa termina.

### Fase 2: Juego de Snake

1. **Tablero de Juego**:
   El tablero de juego se genera como una matriz de dimensiones predefinidas (en este caso, 10x20), donde la serpiente se representa inicialmente en la parte superior izquierda.

2. **Movimiento de la Serpiente**:
   El jugador puede moverse utilizando las teclas **W**, **A**, **S**, **D** para moverla arriba, izquierda, abajo y derecha, respectivamente.

3. **Representación en Consola**:
   El tablero de juego se imprime en la consola en cada ciclo de movimiento. La serpiente se representa con el carácter `#` de color verde, mientras que el espacio vacío en el tablero está representado por el carácter `" "`.

### Fase 3: Almacenamiento de Comandos

El archivo `ControladorPrincipal.java` permite al jugador escribir instrucciones que se guardan en un archivo de texto (`./content.txt`). Este archivo es luego utilizado para controlar los movimientos de la serpiente, lo que simula un control manual del juego basado en los comandos ingresados.

### Colores en Consola

El archivo `ColoresConsola.java` se utiliza para aplicar colores al texto impreso en consola, con el fin de mejorar la visibilidad del juego. Actualmente, la serpiente se dibuja en color verde (`#`), y los mensajes importantes se muestran en rojo.

## Compilacion y ejecucion con ejecutable
1. Abre una terminal y escriba los siguientes comandos
2. cd ruta al proyecto
3. `javac Main.java utils/Snake.java utils/ColoresConsola.java`
4. `jar cvfe MiProgramaSnake.jar Main Main.class utils/Snake.class utils/ColoresConsola.class`
5. `javac ControladorPrincipal.java utils/ColoresConsola.java`
6. `jar cvfe MiProgramaControlador.jar ControladorPrincipal ControladorPrincipal.class utils/ColoresConsola.class`
7. Ten abierta 2 terminales y ejecute un comando en cada una
8. `java -jar MiProgramaSnake.jar` y `java -jar MiProgramaControlador.jar`


## Estructura de carpetas

- `src`: Esta carpeta tiene los archivos del programa
- `lib`: Esta carpeta tiene las dependencias del programa

Mientras tanto, los archivos de salida compilados se generarán en la carpeta `bin` de forma predeterminada.

> Si desea personalizar la estructura de carpetas, abra `.vscode/settings.json` y actualice las configuraciones relacionadas allí.