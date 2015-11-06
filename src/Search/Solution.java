package Search;

import ImageCompressor.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

/** 
 * This class implements a solution to the problem
   (configuration */


public class Solution
{
    EllipseBasedImage compressedImage;
    BufferedImage renderedImage;
    int score;

    /** Constructor: Receives an ArrayList of Ellipses and a ColorDictionary */

    Solution(ArrayList<Ellipse> c, ColorDictionary cD)
    {
	compressedImage=new EllipseBasedImage(c,cD);
	renderedImage=null; 
	score=-1;
    }


    /** Returns the Image represende as an ArrayList of Ellipses */

    public EllipseBasedImage getCompressedImage()
    {
	return compressedImage;
    }

    /** Returns the rendered image */
    
    BufferedImage getRenderedImage()
    {
	return renderedImage;
    }

    /** Render the image */
    
    void render(int width, int height)
    {	
        renderedImage=compressedImage.render(width, height);	
    }

    /** Computes the score for the solution. The second parameter
        specifies the precision used in the score computation */
    
    int computeScore(BufferedImage referenceImage,int precision)
    {
	if(precision<1) precision=1;
	/* If the image was not previously rendered, it is rendered to compute the score */
	if(renderedImage==null)	 
	    renderedImage=compressedImage.render(referenceImage.getWidth(), referenceImage.getHeight());

	score=ImageComparator.compare(referenceImage,renderedImage,precision);
	return score;						  
    }

    /** Computes the score for the solution using the maximum precision */     
    
    int computeScore(BufferedImage referenceImage)	
    {
	return this.computeScore(referenceImage,1);
    }

    /** Get the score of the solution (the method computeScore should
        have been called previously) */
    
    int getScore()
    {
	return score;
    }


    
    /** Returns the set of ellipses stored in the solution */
    
    ArrayList<Ellipse> getEllipses()
    {
	return(compressedImage.getEllipses());
    }

    /** Returns the size of a solution */

    int getSize()
    {
	return(compressedImage.getEllipses().size());
    }

    /** Gets the ellipse stored in a specific position in the solution vector */
	
    Ellipse getEllipseAt(int i)
    {
	if(i<compressedImage.getEllipses().size()&&i>=0)
	    return compressedImage.getEllipses().get(i);
	else
	{
	    System.out.println("WARNING. Accessing to an ellipse index not permitted");
	    return null;
	}
    }    

    
    /** Sets a specific position of the solution vector with a new ellipse */

    void setEllipseAt(int i, Ellipse c)
    {
	if(i<compressedImage.getEllipses().size()&&i>=0)
	   compressedImage.getEllipses().set(i,c);
	else	
	    System.err.println("WARNING. Accessing to an ellipse index not permitted");
    }    

    
    /** Clone the current solution, returing a new one with the same ellipses */
    
    public Solution clone()
    {
	EllipseBasedImage tmp=compressedImage.clone();
	Solution s=new Solution(tmp.getEllipses(),compressedImage.getColorDictionary());
	s.score=this.score;
	return(s);       
    }

    /** Returns a string representation of the solution */

    public String toString()
    {
	String res=("[ ");
	for(int i=0;i<compressedImage.getEllipses().size()-1;i++)       
	    res=res+getEllipseAt(i).toString()+(" , ");

	res=res+getEllipseAt(compressedImage.getEllipses().size()-1).toString();
	res=res+" ]. score = " + new Integer(score).toString();
	return res;
    }
       
}
