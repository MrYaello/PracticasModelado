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


void filterRed(int value, cv::Mat img, int rows) {
    for (int j = 0; j < rows; j++) {
        img.at<cv::Vec3b>(value,j)[0] = 0; // B
        img.at<cv::Vec3b>(value,j)[1] = 0; // G
        img.at<cv::Vec3b>(value,j)[2]; // R
    }
}

void filterBlue(int value, cv::Mat img, int rows) {
    for (int j = 0; j < rows; j++) {
        img.at<cv::Vec3b>(value,j)[0]; // B
        img.at<cv::Vec3b>(value,j)[1] = 0; // G
        img.at<cv::Vec3b>(value,j)[2] = 0; // R
    }
}

void filterGrayScale(int value, cv::Mat img, int rows) {
    for (int j = 0; j < rows; j++) {
        int var = (img.at<cv::Vec3b>(value,j)[0] + img.at<cv::Vec3b>(value,j)[1] + img.at<cv::Vec3b>(value,j)[2]) / 3;
        img.at<cv::Vec3b>(value,j)[0] = var; // B
        img.at<cv::Vec3b>(value,j)[1] = var; // G
        img.at<cv::Vec3b>(value,j)[2] = var; // R
    }
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        std::cout << "Uso: Activity2 inFilePath outFilePath" << std::endl;
        return -1;
    }

    char *path = argv[1];
    char *outPath = argv[2];

    cv::Mat img = cv::imread(path, cv::IMREAD_UNCHANGED);
    cv::Mat color = img;

    if (img.empty()) {
        std::cerr << "Error al cargar la imagen." << std::endl;
        return -1;
    }

    std::vector<std::thread> threads;

    for (int i = 0; i < color.rows; ++i) {
        threads.emplace_back(filterRed, i, color, color.cols);
    }
 
    for (auto& thread : threads) {
        thread.join();
    }
    cv::namedWindow("Preview", cv::WINDOW_AUTOSIZE);
    cv::imshow("Preview", color);
    cv::waitKey(0);

    if (cv::imwrite(outPath, color)) {
        std::cout << "Imagen Guardada" << std::endl;
    }

    return 0;
}

