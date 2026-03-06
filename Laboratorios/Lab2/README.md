# Laboratorio 2: El juego del Sudoku

**Curso:** CI-2693 - Laboratorio de Algoritmos y Estructuras III
**Autor:** Victor Hernandez
**Carnet:** 20-10349

## Descripción

Este programa resuelve tableros de Sudoku de $9 \times 9$ representados como una cadena de 81 caracteres numéricos. La solución está implementada en Kotlin utilizando la técnica algorítmica de Backtracking.

## Lógica de Retroceso (Backtracking)

Para implementar la lógica de retroceso, se diseño la función recursiva `resolverSudoku`. El algoritmo se basa en la estrategia de "ensayo y error" para construir la solución paso a paso:

1. El código utiliza dos ciclos `for` anidados para recorrer la matriz buscando la primera celda vacía (identificada con un `0`).
2. Al encontrar un espacio, se abre otro ciclo para probar los números del 1 al 9.
3. Si el número pasa las validaciones de poda (explicadas abajo), se le asigna a la celda en la matriz (`tablero[fila][col] = num`) y se llama recursivamente a `resolverSudoku` para avanzar al siguiente nivel de profundidad.
4. **El retroceso (Backtrack):** Si la llamada recursiva devuelve `false` (lo que significa que la solución parcial actual no puede completarse con éxito), se deshace el movimiento devolviendo la celda a su estado original (`tablero[fila][col] = 0`). De esta forma, el algoritmo retrocede al paso anterior para intentar la siguiente alternativa en el ciclo
.

## Condiciones de Poda (Pruning)

Dado que estamos ante un problema con un espacio de búsqueda inmenso, se aplica poda para no explorar ramas del árbol que de antemano sé sabe que no contienen una solución válida.

Para lograr esto, se implementa la función auxiliar `esSeguro`. Antes de hacer la llamada recursiva y avanzar en el árbol, esta función verifica estrictamente tres condiciones vitales del Sudoku:

* Que el número candidato no exista ya en la misma **fila**.
* Que el número candidato no exista ya en la misma **columna**.
* Que el número candidato no esté presente en la **subcuadrícula de $3 \times 3$** correspondiente.

Si alguna de estas tres condiciones no se cumple, la función `esSeguro` retorna `false`. Esto descarta la rama de ejecución inmediatamente, evitando que el programa evalúe configuraciones inviables y garantizando que encuentre la solución (o determine que no la hay) en un tiempo razonable.

## Complejidad del Algoritmo

### Complejidad de Tiempo: $O(9^n)$

Donde $n$ es la cantidad de casillas vacías. El Backtracking explora un árbol de espacio de estados. En el peor de los casos, cada una de las $n$ casillas tiene 9 "acciones aplicables" (los números del 1 al 9). Aunque la función `esSeguro` realiza una poda (pruning) para descartar rápidamente las ramas inválidas y acelerar el tiempo de ejecución real, la cota superior teórica sigue siendo exponencial, lo cual es típico en problemas que no tienen una solución eficiente conocida.

### Complejidad de Espacio: $O(n)$

Donde $n$ es la cantidad de casillas vacías. El Backtracking es esencialmente un recorrido DFS (Búsqueda en Profundidad) sobre un grafo implícito. Al implementarlo de forma recursiva, utiliza la pila del sistema (LIFO). La profundidad máxima de esta pila será exactamente $n$ (el total de llamadas necesarias para alcanzar el estado meta). Por su parte, la matriz del tablero ocupa un espacio constante $O(1)$.

## Ejecución

Para compilar y ejecutar el programa:

```bash
kotlinc Sudoku.kt -include-runtime -d Sudoku.jar
java -jar Sudoku.jar <cadena_de_81_caracteres>
```
