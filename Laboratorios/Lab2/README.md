# Laboratorio 2: El juego del Sudoku

**Curso:** CI-2693 - Laboratorio de Algoritmos y Estructuras III
**Autor:** Victor Hernandez
**Carnet:** 20-10349

## Descripción

Este programa resuelve tableros de Sudoku de $9 \times 9$ representados como una cadena de 81 caracteres numéricos, donde los ceros ('0') indican las celdas vacías[cite: 278, 280, 281]. La solución se implementó en Kotlin (`Sudoku.kt`) utilizando la técnica algorítmica de Backtracking.

## Lógica de Retroceso (Backtracking)

El algoritmo recorre el tablero intentando construir una solución paso a paso mediante ensayo y error, lo que equivale a un recorrido DFS sobre un árbol de estados implícito. La implementación sigue esta lógica:

1. **Búsqueda de espacios:** El programa escanea la matriz bidimensional buscando la primera celda vacía (valor `0`).
2. **Acciones aplicables:** Una vez hallada la celda, se iteran los números del 1 al 9 para intentar ubicarlos.
3. **Avance en profundidad:** Si un número es válido, se asigna a la celda y se realiza una llamada recursiva para continuar con la siguiente celda vacía.
4. **Retroceso (Backtrack):** Si en algún punto se determina que la solución parcial actual no puede completarse con éxito (ningún número del 1 al 9 es válido para una celda posterior), la función retorna `false`. Esto dispara el retroceso al paso anterior, restaurando el valor de la celda actual a `0` para intentar la siguiente alternativa disponible.

## Condiciones de Poda (Pruning)

Para asegurar que el programa termine en un tiempo razonable y no sufra de una explosión combinatoria (dado que el problema tiene un espacio de búsqueda exponencial), se implementó una técnica de poda.

La poda se realiza mediante la función auxiliar `esSeguro`, la cual evita explorar ramas del árbol que sabemos de antemano que no contienen una solución válida[cite: 44]. Antes de realizar la llamada recursiva y avanzar al siguiente estado, se verifica estrictamente que la inserción del número cumpla con las tres reglas del Sudoku:

* **Validación de Fila:** El número no debe existir previamente en la fila actual.
* **Validación de Columna:** El número no debe existir previamente en la columna actual.
* **Validación de Subcuadrícula:** El número no debe estar presente en la subcuadrícula de $3 \times 3$ a la que pertenece la celda.

Si alguna de estas condiciones falla, el estado es descartado inmediatamente, ahorrando al programa la necesidad de evaluar millones de configuraciones inviables.

## Ejecución

Para compilar y ejecutar el programa, utilice los siguientes comandos en la terminal:

```bash
kotlinc Sudoku.kt -include-runtime -d Sudoku.jar
java -jar Sudoku.jar <cadena_de_81_caracteres>
```
