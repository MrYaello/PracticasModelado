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

void blend(int i, cv::Mat img, cv::Mat result, int start, int end) {
    for (int j = start; j <= end; j++) {
        result.at<cv::Vec3b>(i,j)[0] = img.at<cv::Vec3b>(i,j)[0]; // B
        result.at<cv::Vec3b>(i,j)[1] = img.at<cv::Vec3b>(i,j)[1]; // G
        result.at<cv::Vec3b>(i,j)[2] = img.at<cv::Vec3b>(i,j)[2]; // R
    }
}

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cout << "Uso: Activity3 img1FilePath img2FilePath outFilePath" << std::endl;
        return -1;
    }

    char *path1  = argv[1];
    char *path2 = argv[2];
    char *outPath = argv[3];

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

    std::vector<std::thread> threads;

    for (int i = 0; i < img1.rows; i++) {
        threads.emplace_back(blend, i, img2, result, img1.cols / 2, img1.cols);
    }
 
    for (auto& thread : threads) {
        thread.join();
    }

    cv::namedWindow("Preview", cv::WINDOW_AUTOSIZE);
    cv::imshow("Preview", result);
    cv::waitKey(0);

    if (cv::imwrite(outPath, result)) {
        std::cout << "Imagen Guardada" << std::endl;
    }

    return 0;
}