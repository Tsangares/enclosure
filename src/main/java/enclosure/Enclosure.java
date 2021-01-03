package enclosure;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays; 
import java.awt.Polygon;
import java.awt.geom.Area;
public class Enclosure 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static boolean trapped(ArrayList<Line2D.Float> edges, Point2D.Float point){
	ArrayList<Polygon> polygons = getCircuits(edges);
	for(Polygon polygon: polygons){
	    if(polygon.contains(point)) return true;
	}
	return false;
    }
    //Given a list of edges find all the polygons
    public static ArrayList<Polygon> getCircuits(ArrayList<Line2D.Float> edges){
	Graph graph = new Graph(edges);
	ArrayList<ArrayList<Vertex>> allPaths = new ArrayList<>();
	while(graph.size()>0){
	    Vertex start = graph.vertices.get(0);
	    ArrayList<ArrayList<Vertex>> paths = traverse(start);
	    if(paths.size()>0){
		for(ArrayList<Vertex> path: paths){
		    graph.remove(path);
		}
		allPaths.addAll(paths);
	    }else{
		graph.remove(getConnected(start));
	    }
	}
	ArrayList<Polygon> polygons = getPolygons(allPaths);
	ArrayList<Polygon> uniquePolygons = new ArrayList<>();
	for(Polygon polygon: polygons){
	    boolean unique = true;
	    for(Polygon uniquePolygon: uniquePolygons){
		if((new Area(polygon)).equals(new Area(uniquePolygon)))
		   unique=false;
	    }
	    if (unique) uniquePolygons.add(polygon);
	}
	return uniquePolygons;
    }
    public static ArrayList<Polygon> getPolygons(ArrayList<ArrayList<Vertex>> paths){
	ArrayList<Polygon> polygons = new ArrayList<>();
	for(ArrayList<Vertex> path: paths)
	    polygons.add(getPolygon(path));
	return polygons;
    }
    public static Polygon getPolygon(ArrayList<Vertex> path){
	Polygon poly = new Polygon();
	for(Vertex vertex: path){
	    Point2D p = vertex.location;
	    poly.addPoint((int)(p.getX()),(int)(p.getY()));
	}
	return poly;
    }
    public static ArrayList<ArrayList<Vertex>> traverse(Vertex current){
	ArrayList<Vertex> criticals = new ArrayList<Vertex>();
	criticals.add(current);
	return traverse(current,current,new ArrayList<Vertex>(),new ArrayList<ArrayList<Vertex>>(),criticals);
    }
    public static ArrayList<ArrayList<Vertex>> traverse(Vertex current, Vertex critical, ArrayList<Vertex> visited, ArrayList<ArrayList<Vertex>> bin,ArrayList<Vertex> criticals){
	visited.add(current);
	Vertex start = visited.get(0);
	for(Vertex next: current.getConnections()){
	    if(visited.size()>2 && next.equals(critical)) {
		bin.add(visited);
		return bin;
	    }
	    if(visited.contains(next)) continue;
	    traverse(next,critical,new ArrayList<Vertex>(visited),bin,criticals);
	}
	if(current.getConnections().size() > 2 && !criticals.contains(current)){
	    criticals.add(current);
	    traverse(current,current,new ArrayList<Vertex>(),bin,criticals);
	}
	return bin;
    }
    
    public static ArrayList<Vertex> getConnected(Vertex current){
	return getConnected(current,new ArrayList<Vertex>());
    }
    public static ArrayList<Vertex> getConnected(Vertex current,ArrayList<Vertex> visited){
	visited.add(current);
	for(Vertex next: current.getConnections()){
	    if(visited.contains(next)) continue;
	    return getConnected(next,visited);
	}
	return visited;
    }
}
