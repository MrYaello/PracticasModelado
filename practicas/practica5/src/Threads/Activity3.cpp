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
    for (int i = 0; i < img.cols; i++) {
        for (int j = start; j <= end; j++) {
            result.at<cv::Vec3b>(i,j)[0] = img.at<cv::Vec3b>(i,j)[0]; // B
            result.at<cv::Vec3b>(i,j)[1] = img.at<cv::Vec3b>(i,j)[1]; // G
            result.at<cv::Vec3b>(i,j)[2] = img.at<cv::Vec3b>(i,j)[2]; // R
        }
    }
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        std::cout << "Uso: Activity3 img1FilePath img2FilePath" << std::endl;
        return -1;
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

    std::thread t1(blend, img1, result, 0, img1.rows / 2);
    std::thread t2(blend, img2, result, img1.rows / 2, img1.rows);
 
    t1.join();
    t2.join();

    cv::namedWindow("Imagen", cv::WINDOW_AUTOSIZE);
    cv::imshow("Imagen", result);
    cv::waitKey(0);

    cv::imwrite("./imagenes/blend.jpg", result);
    if (cv::imwrite("./imagenes/blend.jpg", result)) {
        std::cout << "Imagen Guardada" << std::endl;
    }

    return 0;
}