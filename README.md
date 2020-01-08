# regex-workshop

Como argumentos de la ejecución se deberán pasar, primero la carpeta que contenga los archivos de texto, y luego la carpeta con los archivos de regex.


#!/bin/bash
  
if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    echo "Please enter the provide path for input file name as argument"
else
    javac -classpath .:jars/*.jar src/*.java
    /usr/bin/java  -classpath .:jars/*.jar -Xmx300m src.<MAIN_CLASS> $1 $2
fi


