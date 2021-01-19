package enclosure;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Polygon;
public class Vertex
{
    Point2D location;
    ArrayList<Vertex> connections = new ArrayList<>();
    public Vertex(Point2D point){
	location = point;
    }
    public Vertex(int x, int y){
	location = new Point2D.Float(x,y);
    }

    public boolean addConnection(Vertex point){
	if(contains(point)) return false; //Already got this point
	connections.add(point);
	return true;
    }

    public final int size(){
	return connections.size();
    }
    //Just retuns the connection list as a constant
    public final ArrayList<Vertex> getConnections(){
	return connections;
    }

    //Checks if the point is at the location of this vertex.
    public boolean at(Point2D.Float point){
	return point.equals(location);
    }
    public boolean at(Vertex point){
	return point.location.equals(location);
    }

    //Looks to see if the given point is in the connections.
    public boolean contains(Vertex point){
	for(Vertex connection: connections){
	    if(connection.at(point)) return true;
	}
	return false;
    }
}
