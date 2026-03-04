fun main(args: Array<String>) {
    if (args.isEmpty() || args[0].length != 81) {
        println("NOSOLUTION")
        return
    }

    val input = args[0]
    val tablero = Array(9) { IntArray(9) }

    for (i in 0 until 81) {
        val fila = i / 9
        val col = i % 9
        tablero[fila][col] = input[i] - '0'
    }

    if (resolverSudoku(tablero)) {
        var resultado = ""
        for (fila in 0 until 9) {
            for (col in 0 until 9) {
                resultado += tablero[fila][col]
            }
        }
        println(resultado)
    } else {
        println("NOSOLUTION") 
    }
}

fun resolverSudoku(tablero: Array<IntArray>): Boolean {
    for (fila in 0 until 9) {
        for (col in 0 until 9) {
            if (tablero[fila][col] == 0) {
                for (num in 1..9) {
                    if (esSeguro(tablero, fila, col, num)) {
                        tablero[fila][col] = num

                        if (resolverSudoku(tablero)) return true

                        tablero[fila][col] = 0
                    }
                }
                return false
            }
        }
    }
    return true
}

fun esSeguro(tablero: Array<IntArray>, fila: Int, col: Int, num: Int): Boolean {
    for (i in 0 until 9) {
        if (tablero[fila][i] == num) return false
        if (tablero[i][col] == num) return false
    }

    val inicioFila = fila - (fila % 3)
    val inicioCol = col - (col % 3)
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (tablero[inicioFila + i][inicioCol + j] == num) return false
        }
    }

    return true
}