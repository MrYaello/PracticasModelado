/**
 * Práctica 5 - ACTIVIDAD 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

#include<iostream>
#include <cmath>
#include <opencv2/opencv.hpp>
#include <opencv2/core/matx.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/imgproc.hpp>
#include <vector>
#include <future>
#include <thread>

void blend(cv::Mat img, cv::Mat result, int start, int end) {
    for (int i = 0; i < img.cols; ++i) {
        for (int j = start; j <= end; j++) {
            result.at<cv::Vec3b>(value,j)[0] = img.at<cv::Vec3b>(value,j)[0]; // B
            result.at<cv::Vec3b>(value,j)[1] = img.at<cv::Vec3b>(value,j)[1]; // G
            result.at<cv::Vec3b>(value,j)[2] = img.at<cv::Vec3b>(value,j)[2]; // R
        }
    }
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        std::cout << "Uso: Activity2 img1FilePath img2FilePath" << std::endl;
    }

    char *path1  = argv[1];
    char *path2 = argv[2];

    cv::Mat img1 = cv::imread(path1, cv::IMREAD_UNCHANGED);
    cv::Mat img2 = cv::imread(path2, cv::IMREAD_UNCHANGED);
    cv::Mat result = img1;

    if (img1.empty() || img2.empty()) {
        std::cerr << "Error al cargar las imágenes." << std::endl;
        return -1;
    }

    if (img1.size() != img2.size()) {
        std::cerr << "Las imágenes deben tener el mismo tamaño." << std::endl;
        return -1;
    }

    std::thread thread1;
    std::thread thread2;

    thread1.emplace_back(blend, cv::ref(img1), cv::ref(result), 0, img1.rows / 2);
    thread1.emplace_back(blend, cv::ref(img2), cv::ref(result), img1.rows / 2, img1.rows);


    if (cv::imwrite("imagenes/blend.jpg", result)) {
        std::cout << "Imagen Guardada" << std::endl;
    }

    return 0;
}