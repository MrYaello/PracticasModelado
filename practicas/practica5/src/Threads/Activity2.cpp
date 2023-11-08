/**
 * Práctica 5 - ACTIVIDAD 2 del curso de Modelado y Programación.
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

std::atomic<int> value(O);

void filterRed(cv::Mat img, int rows) {
    for (int i = 0; i < rows; ++i) {
        img.at<cv::Vec3b>(value,j)[0] = 0; // B
        img.at<cv::Vec3b>(value,j)[1] = 0; // G
        img.at<cv::Vec3b>(value,j)[2]; // R
    }
    value++;
}

void filterBlue(cv::Mat img, int rows) {
    for (int i = 0; i < rows; ++i) {
        img.at<cv::Vec3b>(value,j)[0]; // B
        img.at<cv::Vec3b>(value,j)[1] = 0; // G
        img.at<cv::Vec3b>(value,j)[2] = 0; // R
    }
    value++;
}

void filterGrayScale(cv::Mat img, int rows) {
    int var = (img.at<cv::Vec3b>(value,j)[0] + img.at<cv::Vec3b>(value,j)[1] + img.at<cv::Vec3b>(value,j)[2]) / 3
    for (int i = 0; i < rows; ++i) {
        img.at<cv::Vec3b>(value,j)[0] = var; // B
        img.at<cv::Vec3b>(value,j)[1] = var; // G
        img.at<cv::Vec3b>(value,j)[2] = var; // R
    }
    value++;
}


int main(int argc, char* argv[]) {
    if (argc != 2) {
        std::cout << "Uso: Activity2 inFilePath" << std::endl;
    }

    char *path  = argv[1];

    cv::Mat img = cv::imread(path, cv::IMREAD_UNCHANGED);
    cv::Mat color = img.clone;

    if (img.empty()) {
        std::cerr << "Error al cargar la imagen." << std::endl;
        return -1;
    }

    std::vector<std::thread> threads;

    for (int i = 0; i < color.cols; ++i) {
        threads.emplace_back(filterBlue, cv::ref(color), color.rows);
    }
 
    for (auto& thread : threads) {
        thread.join();
    }


    if (cv::imwrite("imagenes/color.jpg", color)) {
        std::cout << "Imagen Guardada" << std::endl;
    }

    return 0;
}

