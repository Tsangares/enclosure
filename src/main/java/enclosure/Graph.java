package enclosure;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Polygon;
public class Graph
{
    ArrayList<Vertex> vertices = new ArrayList<>();

    public Graph(ArrayList<Line2D.Float> edges){
	for(Line2D.Float edge: edges){
	    addEdge(edge);
	}
    }
    public void addEdge(Line2D.Float edge){
	Vertex alpha = new Vertex(edge.getP1());
	Vertex beta = new Vertex(edge.getP2());
	for(Vertex vertex: vertices){
	    if(vertex.at(alpha)){
		alpha = vertex;
	    }else if(vertex.at(beta)){
		beta = vertex;
	    }
	}
	if(alpha.size() == 0) vertices.add(alpha);
	if(beta.size() == 0) vertices.add(beta);
	alpha.addConnection(beta);
	beta.addConnection(alpha);
    }

    public final ArrayList<Vertex> getVertices(){
	return vertices;
    }
}
