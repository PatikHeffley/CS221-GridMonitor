****************
* Warmup
* CS 221
* 9/2/2025
* Patik Heffley
**************** 

OVERVIEW:

GridMonitor is a class I created to test an input grid file for danger. The Class takes in a file that has a first line that specifies the dimensions,
and then reads the numbers and stores them in a double[][]. This array can be tested for the sums of each grid's surrounding tiles, 
the average of the surrounding tiles, the deltas, and finally a true or false if that tile is considered safe.

INCLUDED FILES:

GridMonitorInterface.java - source file
GridMonitor.java - file I created for this project
GridMonitorTest.java - source file
negatives.txt - source file
oneByOne.txt - source file
sample.txt - source file
sample4x5.txt - source file
sampleDoubles.txt - source file
wideRange.txt - source file
README.txt - this file

BUILDING AND RUNNING

Run GridMonitorTest.java, GridMonitor also includes a main method for testing, however I have commented it out.

PROGRAM DESIGN

GridMonitor implements the GridMonitorInterface interface and includes a definition for each of the methods included in GridMonitorInterface.
GridMonitor also includes a method of its own, toString, which neatly returns information about the currently selected grid including safety information.

The size of the grid is read from the first two integers in the input file, everything is dependent on these numbers being correct. 
Currently, the program does not handle multiple input files, to input a new grid to test you must create a new class, as the constructor for
the scanner is defined in there.

Noteably, each other method besides the toString, and constructor method relies on each other to be run in order in order to read proper inputs.
For example:
In order for getDangerGrid to return the value needed it must first run getDeltaGrid > getSurroundingAvgGrid > getSurroundingSumGrid > getBaseGrid in that order.
This is because the delta grid is determined by the average grid and so on.
The code accounts for this, but another issue arises if a second input file is given, luckily that isn't an issue for this project.

TESTING:

GridMonitorTest was given as part of the assignment, making testing easy. At first I passed only 8 of the tests, but slowly I modified my code
to pass all 33 tests. One test to note was the toString test which determined if the default toString method inherited from object was present.
My first toString method was bad and so I changed it to provide much more useful data in a nicer format.

DISCUSSION
Reintroducing myself to java after 18 months of absence was difficult but I had forgotten how fun it can be. While I am aware that my solutions
are not perfect or even close to being, I am proud of my project and I can't wait to learn how I can improve myself for next time.
I have had a lot going on recently so I couldn't dedicate myself to this assignment as much as I had hoped to, but I look forward to next time.
