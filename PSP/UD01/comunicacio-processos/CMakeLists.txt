cmake_minimum_required(VERSION 3.23)
project(comunicacio_processos C)

set(CMAKE_C_STANDARD 99)

add_executable(comunicacio_processos fifolee.c fifoescribe.c)
