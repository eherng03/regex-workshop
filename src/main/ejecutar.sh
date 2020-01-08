#!/bin/bash

USAGE="./ejecutar.sh <src/main/resources/inputFolder/> <src/main/resources/regexPath/>"

if [ -z $1 ]; then
    echo "Como argumentos de la ejecución se deberán pasar, primero la carpeta que contenga los archivos de texto, y luego la carpeta con los archivos de regex."
    echo $USAGE
    exit
else
    javac regex/*.java
    /usr/bin/java regex.Main $1 $2
fi
