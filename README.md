# urlparser

This includes two implementations of URL parsing. I completed this as a take-home programming challenge (with a time limit of 2 hours) in October 2014.

## Usage

Build the uber jar:

    ./gradlew shadowJar

Then run the uber jar:

    java -jar build/libs/urlparser-1.0.0-all.jar urls.txt

## Input

The input to the program is a file that contains one URL to parse per line. An example file is included as `urls.txt`.

