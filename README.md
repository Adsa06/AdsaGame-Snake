# Proyecto de Consola en Java

## Descripción General

Este proyecto en Java permite la interacción con el usuario a través de la consola, ofreciendo opciones para jugar o salir. La interfaz de usuario se maneja a través de líneas de texto organizadas y coloreadas, utilizando funciones para crear un diseño visual atractivo. El programa está estructurado en dos archivos principales: `Main.java` y `ColoresConsola.java`.

## Estructura del Proyecto

- `Main.java`: Contiene la lógica principal del programa, incluyendo la interacción con el usuario.
- `src/ColoresConsola.java`: Proporciona funciones para cambiar los colores del texto en la consola.

## Funciones de Main.java

### `public static void main(String[] args) throws IOException`

Función principal del programa. Aquí se gestiona la entrada y salida de datos desde la consola. Se inicializa un `BufferedReader` para leer el nombre del usuario y opciones de juego, y se establece un bucle para mantener la interacción hasta que el usuario decida salir.

### `public static void sueloTecho(int longitud)`

Imprime una línea horizontal en la consola. La longitud de la línea se determina por el parámetro `longitud`. Se utiliza para crear un diseño estructurado.

### `public static void paredes(int anchura, int longitud, int fila, int espaciado, String frase)`

Dibuja un marco vertical con una frase centrada en la fila especificada. Acepta parámetros para la anchura y longitud del marco, la fila donde se mostrará la frase, el espaciado y la frase a imprimir. También maneja el centrado y el espaciado de la frase.

### `public static void paredesMultiplesFrases(int anchura, int longitud, int fila, String[] frases)`

Similar a la función `paredes`, pero permite imprimir múltiples frases en la fila especificada. Cada frase se centra dentro del marco, y se ajusta según la longitud y anchura proporcionadas.

## Descripción de src/ColoresConsola.java

### `public class ColoresConsola`

Contiene métodos estáticos que devuelven códigos ANSI para cambiar el color del texto en la consola. Esto permite personalizar la salida de texto, mejorando la visibilidad y la estética del programa. Los métodos incluyen:

- `ANSI_RESET()`: Restablece el color del texto a su valor por defecto.
- `ANSI_RED()`: Establece el texto en color rojo.
- `ANSI_GREEN()`: Establece el texto en color verde.
- `ANSI_YELLOW()`: Establece el texto en color amarillo.
- `ANSI_BLUE()`: Establece el texto en color azul.
- `ANSI_PURPLE()`: Establece el texto en color púrpura.
- `ANSI_CYAN()`: Establece el texto en color cian.
- `ANSI_WHITE()`: Establece el texto en color blanco.
- `ANSI_BOLD()`: Establece el texto en negrita.
- `ANSI_UNDERLINE()`: Establece el texto subrayado.
- `ANSI_INVERT()`: Invierte los colores de fondo y texto.

## Notas

- El código incluye comentarios que sugieren mejoras futuras, como unir funciones y agregar más colores.
- Asegúrate de tener configurado un entorno que soporte ANSI para ver correctamente los colores en la consola.

