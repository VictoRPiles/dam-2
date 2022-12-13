cmake_minimum_required(VERSION 3.23)
project(fifo C)

set(CMAKE_C_STANDARD 99)

add_executable(fifo vocals/frase.c vocals/compta.c)
