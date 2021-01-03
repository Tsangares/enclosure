package enclosure;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Polygon;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    //Given a list of edges find all the polygons
    public static ArrayList<Polygon> getCircuits(ArrayList<Line2D.Float> edges){
	Graph graph = new Graph(edges);
	
	return null;
    }
    public static ArrayList<Vertex> traverse(Vertex current, ArrayList<Vertex> visited){
	if(visited.contains(current)) return null; //Been here before
	
    }

    /*
      The follwing are print statements to help visualize the edges.
    */

    //Print a single edge
    public static void printEdge(Line2D.Float edge){
	printEdge(edge,"");
    }
    
    //Print a single edge with a prefix text as a label.
    public static void printEdge(Line2D.Float edge,String prefix){
	System.out.print(prefix);
	System.out.print(edge.getP1().toString() + ", " + edge.getP2().toString());
	System.out.print("\n");	    
    }

    //Prints a list of edges
    public static void printEdges(ArrayList<Line2D.Float> edges){
	printEdges(edges,"");
    }
    
    //Prints a list of edges wth a prefix text as a label.
    public static void printEdges(ArrayList<Line2D.Float> edges,String prefix){
	System.out.println("Displaying all Edges:"+prefix);
	for (Line2D.Float edge: edges){
	    printEdge(edge);
	}
	System.out.print("\n");
    }
}
