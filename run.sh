#!/bin/bash

# Compile the Java program
javac -d out/production/java-chess-system/ src/application/Program.java

# Run the Java program
java -cp out/production/java-chess-system src.application.Program
