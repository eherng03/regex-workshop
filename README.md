# regex-workshop

Proyecto:
java
carpeta input y carpeta regex pasadas como argumentos de la ejecución


#!/bin/bash
  
if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    echo "Please enter the provide path for input file name as argument"
else
    javac -classpath .:jars/*.jar src/*.java
    /usr/bin/java  -classpath .:jars/*.jar -Xmx300m src.<MAIN_CLASS> $1 $2
fi

output: Tu texto contiene bloques sensibles!!
todos los hits de las regex:...
