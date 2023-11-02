### BABEL ###

### Descripción del Problema:
Juan y María son hermanos y les encanta aprender otros idiomas. Cada de ellos está tomando varios cursos de idiomas. Cuando llegan a casa, comentan sobre la gramática, el vocabulario, y la cultura de
otros países.

En una de esas conversaciones se dieron cuenta que algunas palabras son comunes a más de un idioma, a pesar de que las palabras pueden tener diferentes significados en esos idiomas. Por ejemplo, la palabra
"amigo" que existe en portugues y español y tiene el mismo significado, mientras que "date" es una palabra que existe en Inglés y francés, y puede tener diferentes significados, ya que "date" además ser una fecha en el calendario, es también es una fruta. Por otro lado, "red" es un tejido de malla (o un grafo) en español, mientras que en Inglés es un color.

Emocionados por estos hallazgos, los hermanos decidieron escribir en una libreta todas las palabras que se escribieran igual en dos idiomas. Observador e inteligente, Juan le propuso un desafío a María: Dado un
idioma de inicio y uno de llegada, escribir una secuencia de palabras, de manera que la primera palabra esté en el idioma de inicio y la  ultima palabra en el idioma de llegada. Dos palabras adyacentes en la secuencia
pueden estar en el mismo lenguaje. Por ejemplo, si el lenguaje de inicio es Portugués y el de llegada en Francés, María puede escribir la secuencia "amigo actual date" (Portugues/Español, Español/Inglés,
Inglés/Francés).

Para la sorpresa de Juan, María pudo resolver el problema fácilmente. Enojado por el éxito de su hermana, decidió complicar el problema: María debe hallar la solución en la que la secuencia tenga el menor número de letras en total (sin contar los espacios en blanco), y con la restricción de que dos palabras consecutivas no tengan la misma letra inicial.

Note que la solucion previa es inválida, ya que "amigo" y "actual" comparten la misma letra de inicio. Sin embargo, es posible encontrar otra solución "amigo red date", con una longitud total de 12.

Juan realizó una búsqueda intensiva en internet, recopiló una lista enorme de palabras, y retó a María para que resolviera el problema. Como puede haber más de una solucion, el le pidió que respondiera si hay una solución, y de haberla el número de letras en la mejor solución. Pueden ayudar a María?


### Especificación de la entrada:

La entrada al programa **Babel.java**  será un archivo de texto.

La entrada consiste en varios casos de prueba. 

La primera línea de un caso de prueba contiene un entero
<tt>M</tt> (1 <= <tt>M</tt> <= 2000), que representa el número total de palabras recopiladas por Juan. La segunda línea contiene dos strings *<tt>O</tt>* y *<tt>D</tt>*, separados por un espacio, indicado los idiomas de origen y destino respectivamente. 

Cada una de las próximas M líneas contiene tres strings <tt>I1</tt>, <tt>I2</tt> y <tt>P</tt>, separados por un espacio, representando respectivamente dos idiomas y una palabra en común entre ambos idiomas. (<tt>I1</tt>, <tt>I2</tt>
siempre son diferentes). Todos los strings tendrán una longitud de al menos 1 y a lo sumo de 50, y sólo tendrán letras minúsculas. 

El mismo par de idiomas puede tener varias palabras asociadas a ellos, pero
una palabra <tt>P</tt> no se repetir a en un caso de prueba. El  final de la entrada se indica con una línea que solo contiene un cero.


### Especificación de la salida:
Para cada caso de prueba el programa imprime un entero, la longitud de la secuencia más corta que satisface las restricciones de Juan seguido de la secuencia entre comillas, o la palabra `imposible` (en minúsculas) si no hay una secuencia del idioma de origen al destino del caso.


### Ejemplo ###

#### Entrada:
```
4
portugues frances
ingles espanhol red
espanhol portugues amigo
frances ingles date
espanhol ingles actual
4
portugues aleman
ingles espanhol red
espanhol portugues amigo
frances ingles date
espanhol ingles actual
6
portugues frances
ingles espanhol red
espanhol portugues amigo
frances ingles date
frances espanhol la
portugues ingles a
espanhol ingles actual
0
```

#### Salida:
```
12 "amigo red date"
imposible
5 "a date"
```

### Observaciones: 

- La carpeta tad_grafo, tiene dos sub carpetas: 
	
	- general: Incluye todas las implementaciones de Grafos, tanto para grafos no dirigidos, como para grafos dirigidos(Digrafos).
	
	- extendido: Se extienden los objetos Arista y Vertice, ademas de GrafoNoDirigido, con la finalidad de crear un Multigrafo no dirigido y posteriormente implementar el algoritmo aEstrella (Algoritmo utilizado en IA, ya que utiliza una heuristica, con la que encuentra algun camino de manera mas rapida y optima).

- Para ejecutar el programa en VSC al dar run, se ejecutara con el archivo "test.txt" como entrada, si se quiere probar otros datos, se puede modificar dicho archivo o configurar para que lea otro archivo como entrada. 


- Para ejecutar en linux se debe llamar al programa de la siguiente manera:
 
Para compilar: javac ./Babel.java
Para ejecutar: \> java Babel \<archivoEntrada\>

donde, \<archivoEntrada\> es el nombre de un archivo con la información de los casos de prueba como se describe en la sección **Especificación de la entrada**
