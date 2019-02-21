#!/bin/bash

rm  *.class;
if [ -z "$1" ];then
    echo "Name not provided";
else javac $1.java && java $1;
fi;