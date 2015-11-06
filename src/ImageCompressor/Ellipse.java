/**
 *  This class represents an ellipse
 */


package ImageCompressor;
    
import java.awt.Color;


public class Ellipse    
{
    
    public int x;              /* x coordinate of ellipse center */
    public int y;              /* y coordinate of the ellipse center */
    public int xradius;        /* ellipse xradius */
    public int yradius;        /* ellipse yradius */
    public int color;          /* ellipse color codified as an index to 
			          a dictionary (palette) */
    public int transparency;   /* ellipse transparency 
                                  This parameter can take 256
				  values so that the value 0 means completely at the
				  background while 255 indicates completely at the foreground */


    /** Constructor. Parameters: all the atributes that define an ellipse: x and y coordinates,
	x and y radius (width and height) color and transparency  */

    public Ellipse(int px, int py, int pxradius, int pyradius, int pColor, int t)
    {
	x=px;
	y=py;
	xradius=pxradius;
	yradius=pyradius;
	color=pColor;
	transparency=t;
    }

    /** Copy Constructor */

    public Ellipse(Ellipse c)
    {
	this(c.x,c.y,c.xradius,c.yradius,c.color,c.transparency);
    }

    /** Returns the x coordinate */
    
    public int getX()
    {
	return x;
    }

    /** Returns the y coordinate */
    
    public int getY()
    {
	return y;
    }

    /** Returns the x radius */

    public int getXRadius()
    {
	return xradius;
    }

    /** Returns the y radius */
    
    public int getYRadius()
    {
	return yradius;
    }


    /** Returns the color */
    
    public int getColor()
    {
	return color;
    }


    /** Returns the transparency */

    public int getTransparency()
    {
	return transparency;
    }

    /** Sets the x coordinate */
    
    public void setX(int px)
    {
       x=px;
    }

    /** Sets the y coordinate */
    
    public void setY(int py)
    {
	y=py;
    }

    /** Sets the x radius */
    
    public void setXRadius(int xr)
    {
       xradius=xr;
    }


    /** Sets the y radius */
    
    public void setYRadius(int yr)
    {
       yradius=yr;
    }

    /** Sets the color */

    public void setColor(int c)
    {
	color=c;
    }

    /** Sets the transparency */

    public void setTransparency(int t)
    {
	if(t>255) t=255;
	if(t<0) t=0;
       
	transparency=t;;
    }

    /** Clone method. Similar to copy constructor */

    public Ellipse clone()
    {
	return new Ellipse(this.x,this.y,this.xradius,this.yradius,this.color,this.transparency);
    }

    /** Prints the ellipse parameters */


    public String toString()
    {
	return("("+x+","+y+"). radius = (" + xradius + "," +yradius+")  color = " + color + " transparency = " + transparency);
    }

}



    
