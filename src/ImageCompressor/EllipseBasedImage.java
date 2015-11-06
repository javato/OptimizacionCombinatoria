package ImageCompressor;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;




/** 
 * This class implements a image defined by a set of ellipses (elements of
 * Ellipse class). Each ellipse has a position and a color. The final image 
 * is built by combining all the ellipses the final image
 */

public class EllipseBasedImage 
{
  
  ArrayList<Ellipse> ellipses;   // Ellipses that define the image
  ColorDictionary dictionary;    // Dictionary having the colors to be used

  /** Constructor. Receives an ArrayList of the Ellipses to be rendered and 
      a ColorDictionary object (palette) */
    
  public EllipseBasedImage(ArrayList<Ellipse> pellipses, ColorDictionary dict)
  {
      ellipses=pellipses;
      dictionary=dict;
  }


  /** Returns the ArrayList of Ellipses */

  public ArrayList<Ellipse> getEllipses()
  {
      return ellipses;
  }

  /** Returns the Color Dictionary */

  public ColorDictionary getColorDictionary()
  {
      return dictionary;
  }    
    
  /** Renders the image. The parameters are the height and width of 
   *  the rendered image */
    
  public BufferedImage render(int width, int height)
  {
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);     
      Graphics2D g2 = image.createGraphics();
      for(Ellipse c : ellipses)
      {
	  Color col=dictionary.getColor(c.color);      
	  g2.setColor(new Color(col.getRed(),col.getBlue(),col.getGreen(),c.transparency));
	  g2.fillOval(c.x,c.y,c.xradius,c.yradius);
      }
      return image;
  }

  /** Renders and save the rendered image to a file */
    
  public void renderAndSave(int width, int height, String filename)
  {
	BufferedImage image=render(width, height);	

	File outfile = new File(filename);
	try
	{
	   ImageIO.write(image, "jpg", outfile);
	}

	catch(IOException e)
	{
	    System.err.println("Can't write image to file " + filename);
	    System.err.println(e);
	}
    }


    /** Clone method. It returns a new Object with the same attributes */
	  
    public EllipseBasedImage clone()
    {
	ArrayList<Ellipse> c=new ArrayList<Ellipse>();
	for(int i=0;i<ellipses.size();i++)
	{
	    c.add(ellipses.get(i).clone());
	}
	return(new EllipseBasedImage(c,this.dictionary));
    }
    
	    

}

