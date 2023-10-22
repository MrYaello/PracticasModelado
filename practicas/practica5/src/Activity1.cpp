/**
 * Práctica 5 - ACTIVIDAD 1 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

#include <iostream>
#include <thread>
#include <vector>
#include <random> // Incluir la librería para números aleatorios

/**
* N representa el tamaño de las matrices.
*/
const int N = 3; 

/**
Metodo para generar una matriz con números aleatorios.
*/
void generateRandomMatrix(std::vector<std::vector<int>>& matrix) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<int> dist(1, 10); // Rango de números aleatorios

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            matrix[i][j] = dist(gen); // Generar número aleatorio y asignar a la matriz
        }
    }
}

/**
* Función para multiplicar una fila de la primera matriz por una columna de la segunda matriz.
    Recibe: 
    - matrix1: Matriz 1 para obtener la fila.
    - matrix2: Matriz 2 para obtener la columna.
    - result: Matriz donde se almacena el resultado.
    - row: Fila actual.
    - col: Columna actual.
*/
void multiplicacionMatrix(const std::vector<std::vector<int>>& matrix1, const std::vector<std::vector<int>>& matrix2, std::vector<std::vector<int>>& result, int row, int col) {
    int sum = 0;
    for (int i = 0; i < N; ++i) {
        sum += matrix1[row][i] * matrix2[i][col];
    }
    result[row][col] = sum;
}

/*
* Metodo Main. Metódo que ejecuta el programa.
*/

int main() {
    std::vector<std::vector<int>> matrix1(N, std::vector<int>(N));
    std::vector<std::vector<int>> matrix2(N, std::vector<int>(N));
    std::vector<std::vector<int>> result(N, std::vector<int>(N));

    /**
    Genera matrices aleatorias.
    */
    generateRandomMatrix(matrix1);
    generateRandomMatrix(matrix2);

    std::vector<std::thread> threads;

    /*
    *  Crear threads para realizar la multiplicación
    */    
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            threads.emplace_back(multiplicacionMatrix, std::ref(matrix1), std::ref(matrix2), std::ref(result), i, j);
        }
    }

    /*
    * Espera a que todos los threads terminen para que no pase que todos quieran entrar al mismo espacio.
    */    
    for (auto& thread : threads) {
        thread.join();
    }

    /*
    *Imprimir la matriz de resultado
    */
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            std::cout << result[i][j] << " ";
        }
        std::cout << std::endl;
    }

    return 0;
}
