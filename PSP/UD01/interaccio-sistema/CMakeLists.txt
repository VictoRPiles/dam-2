cmake_minimum_required(VERSION 3.23)
project(interaccio_sistema C)

set(CMAKE_C_STANDARD 99)

add_executable(interaccio_sistema execl.c system.c get-pid.c)
