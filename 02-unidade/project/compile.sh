#!/bin/bash

SOURCE_DIR="src/main/java"

OUTPUT_DIR="out"

mkdir -p $OUTPUT_DIR

find $SOURCE_DIR -name "*.java" > sources.txt
javac -d $OUTPUT_DIR @sources.txt

rm sources.txt

echo "Compile successfully."
