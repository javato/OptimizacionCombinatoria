package ImageCompressor;

import java.util.ArrayList;
import java.lang.Math;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;


/** 
 * This class implements a static method that compares two images */

public class ImageComparator
{

    /** Compares two images pixel by pixel. The parameter specifies 
	the accuracy of the result. A value of 1 means that all 
	the pixels in the images will be considered in the comparison. 
	A value of 2 means that 1 out of 2 pixels will be compared, 3 
	means 1 out of 3 and so on */

    
    public static int compare(BufferedImage i1, BufferedImage i2,int skip)
    {
      byte [] i1Pixels = ((DataBufferByte) i1.getRaster().getDataBuffer()).getData();
      byte [] i2Pixels = ((DataBufferByte) i2.getRaster().getDataBuffer()).getData();

      if(i1Pixels.length!=i2Pixels.length)
      {
	  System.err.println("ERROR. Trying to compare images of different size. size1 = " +i1Pixels.length + " size2 = " + i2Pixels.length);
	  
	  return -1;
      }
	       
      int res=0;
      if(skip<1) skip=1;
      
      for(int i=0;i<i1Pixels.length;i+=skip)
      {
	  res+=Math.abs((i1Pixels[i]&0xff)-(i2Pixels[i]&0xff));
      }
      return res;
    }
}

