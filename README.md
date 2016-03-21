Smaller, reproducible test case for https://youtrack.jetbrains.com/issue/IDEA-153248

Requires Java8 and Maven3.

Simply import into IntelliJ 2016.1 (145.258) and go to Bug.java to see the two wrong error highlights.
The error goes away when using the type casts with wildcard type bounds (as indicated in Bug.java).

The second error about the type bounds also changes once there is a (working) class file present, indicating some type
information is missing during highlighting. Cleaning the project reverts the error back to the original one.