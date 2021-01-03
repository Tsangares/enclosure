package enclosure;

import static org.junit.Assert.*;  
import org.junit.Test;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays; 
import java.awt.Polygon;

public class EnclosureTest 
{

    /* 
       The follwing is a bunch of helper functions to build test enclosures.
       Mainly the point is that is generates shapes on a grid,
        jumbled into one big list of edges.
       The whole point of this code,
        is the unscramble the list of edges into polygons.
     */
    //Easier function to type for getLine
    public ArrayList<Line2D.Float> getLine(int x1, int y1, int x2, int y2){
	return getLine(new Point2D.Float(x1,y1), new Point2D.Float(x2,y2));
    }
    //Given two points, returns a list of connected edges aling path with length of 1.
    public ArrayList<Line2D.Float> getLine(Point2D.Float start, Point2D.Float end){
	ArrayList<Line2D.Float> edges = new ArrayList<>();
	int length = (int)(start.distance(end));
	int dx = (int)(end.x - start.x)/length;
	int dy = (int)(end.y - start.y)/length;
	for(int i=0; i < length; ++i){
	    Point2D.Float a = new Point2D.Float(start.x+dx*i,start.y+dy*i);
	    Point2D.Float b = new Point2D.Float(start.x+dx*(i+1),start.y+dy*(i+1));
	    Line2D.Float edge = new Line2D.Float(a,b);
	    edges.add(edge);
	}
	return edges;
    }

    //x,y defines bottom left point
    //w,h defines width and height of rect.
    //Returns a list of edges with length 1 along a rectangle.
    public ArrayList<Line2D.Float> getRect(int x, int y, int w, int h){
	ArrayList<Line2D.Float> edges = new ArrayList<>();
	edges.addAll(getLine(  x,  y, x+w,  y));
	edges.addAll(getLine(x+w,  y, x+w,y+h));
	edges.addAll(getLine(x+w,y+h,   x,y+h));
	edges.addAll(getLine(  x,y+h,   x,  y));
	return edges;
    }

    /*
      The following are a series of tests which are applicable situations.
     */

    //One small box
    @Test
    public void basic_enclosure()
    {
	ArrayList<Line2D.Float> edges = getRect(0,0,3,3);
	assertTrue("Inside Basic Box", Enclosure.trapped(edges,new Point2D.Float(1,1)));
	assertFalse("Outside Basic Box", Enclosure.trapped(edges,new Point2D.Float(-1,1)));
    }

    //Two seperate boxes
    @Test
    public void multiple_seperate_enclosures()
    {
	ArrayList<Line2D.Float> edges = getRect(0,0,3,3);
	edges.addAll(getRect(5,5,3,3));
	assertTrue("Inside Basic Box", Enclosure.trapped(edges,new Point2D.Float(1,1)));
	assertFalse("Outside Boxes", Enclosure.trapped(edges,new Point2D.Float(-1,1)));
	assertTrue("Inside Distant Box", Enclosure.trapped(edges,new Point2D.Float(6,6)));
    }

    //Two connected boxes
    @Test
    public void multiple_connected_enclosures()
    {
	ArrayList<Line2D.Float> edges = getRect(0,0,3,3);
	edges.addAll(getLine(3,3,6,3));
	edges.addAll(getLine(6,3,6,0));
	edges.addAll(getLine(6,0,3,0));
	Enclosure.getCircuits(edges);
	assertTrue("Inside Basic Box", Enclosure.trapped(edges,new Point2D.Float(1,1)));
	assertFalse("Outside Boxes", Enclosure.trapped(edges,new Point2D.Float(-1,1)));
	assertTrue("Inside Extra Box", Enclosure.trapped(edges,new Point2D.Float(5,1)));
    }

    //Two connected boxes, two sprawling lines, and one semi-disconnected box.
    @Test
    public void multiple_connected_enclosures_with_false_edges()
    {
	ArrayList<Line2D.Float> edges = getRect(0,0,3,3);
	edges.addAll(getLine(3,3,6,3));
	edges.addAll(getLine(6,3,6,0));
	edges.addAll(getLine(6,0,3,0));
	edges.addAll(getLine(3,3,3,6));
	edges.addAll(getLine(0,0,-3,0));
	edges.addAll(getRect(3,5,4,4));
	assertTrue("Inside Basic Box", Enclosure.trapped(edges,new Point2D.Float(1,1)));
	assertFalse("Outside Boxes", Enclosure.trapped(edges,new Point2D.Float(-1,1)));
	assertTrue("Inside Extra Box", Enclosure.trapped(edges,new Point2D.Float(5,1)));

    }
}
