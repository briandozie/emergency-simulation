[README
======

Description of program:
A program that simulates emergency and responses of responders towards the emergnecies.
This program implements object-oriented design patterns such as Observer Pattern and State Pattern
Source code can be found under Simulation/src/main/java/edu/curtin/emergencysim

To run the programme:

    Step 1: Navigate to Assignment2/Simulation
    Step 2: Type ./gradlew run --args="[filename]"

    If you encounter a permission denied message for gradlew,
    type this command to enable execute and read access:

        chmod 755 gradlew


Logs of the simulation can be found at:

    Assignment2/Simulation/EmergencySim0.log


A test input file can be found at:

    Assignment2/Simulation/input.txt
    
    The format of the input file is as follows:
        [time(seconds) of occurence of emergency] [type of emergency] [location of emergency]
