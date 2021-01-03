# Summary

This is a basic piece of code to find polygons in an ArrayList of Line2D.Float. The way to do this is to build a graph from the ArrayList of lines then run a search algorthm to find circuits.

# How to Compile

Just install using maven

    mvn install
	
It will compile code and run tests.


# Contents

The algorithm is located at `src/main/java/enclosure/App.java`, the test files are located at, `src/test/java/enclosure/AttTest.java`.

In the `App.java` there are the print hepler functions,

    printEdge(Line2D.Float edge) //Prints the points in an edge
	printEdges(ArrayList<Line2D.Float edges) //Prints a list of lines
	
In the `AppTest.java` there are a bunch of functions that help generate segmented connected lines and boxes. There are a few tests and have some basic shapes described but nothing being asserted. 

# How to help

Please edit `src/main/java/enclosure/App.java`, and change the function `getCircuits()` to follow some sort of DFS path and return a list of polygon.
