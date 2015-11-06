package Search;

import ImageCompressor.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;


/** 
 * This class has to be extended to implement a search algorithm 
 * The only method to be implemented is the "search" method, which 
 * performs the whole search process.
 */

public abstract class SearchAlgorithm
{
     BufferedImage inputImage;     /* Image to achieve */ 
     ColorDictionary dictionary;   /* Color dictionary to use */
     int numberOfColors;           /* Number of different colors in the 
				      dictionary */
     int numberOfEllipses;         /* Number of ellipses in a solution */     
     int width;                    // width and height of 
     int height;                   // the input image 

     private List<ImageUpdatedListener> listeners = new ArrayList<ImageUpdatedListener>();
     public abstract Solution doSearch();


    /** Constructors. Parameters: Input image, Color dictionary and
	number of ellipses to use */
    
    public SearchAlgorithm(BufferedImage i, ColorDictionary cD, int n)
    {
	inputImage=i;
	dictionary=cD;
	numberOfEllipses=n;
	numberOfColors=dictionary.getNumberOfColors();
	width=inputImage.getWidth();
	height=inputImage.getHeight();
    }


    /** This method save the image codified by a Solution object to a file.
	Parmeters: Solution to render and name of the output image file */    

    void saveImage(Solution sol, String filename)
    {
	sol.getCompressedImage().renderAndSave(inputImage.getWidth(),inputImage.getHeight(), filename);	
    }



    
    /** This method generates an image (file) showing the colors
	contained in the dictionary. Parameter: output image file */

    void dumpDictionary(String filename)
    {
	int columns=(int) Math.sqrt(dictionary.getNumberOfColors());
	int index=0;
	int x=15;
	int y=15;
	int imageSize=800;
	ArrayList<Ellipse> c=new ArrayList<Ellipse>();
	for(int i=0;i<dictionary.getNumberOfColors();i++)
	{

	    c.add(new Ellipse(x,y,30,30,i,255));
	    x+=40;
   	    if(x>imageSize-15)
	    {
		y+=40;
		x=15;
	    }
	}
	Solution s=getNewSolution(c);
	s.getCompressedImage().renderAndSave(imageSize,imageSize, filename);	
    }


    /** This method computes the score of a Solution using the maximum 
        accuracy */
    
    int computeScore(Solution s)
    {
	return(s.computeScore(inputImage));
    }

    /** This method computes the score of a solution. The second
        parameter is the accuracy to use. The higher this value is the
        lower (but faster) the precision is. precision=1 -> Best accuracy. */
    
    int computeScore(Solution s, int precision)
    {
	return(s.computeScore(inputImage, precision));
    }


    /** This method is used to create a new solution for the 
	problem to be solved. The parameter is an ArrayList 
	of Ellipses that represent the input image */
    
    Solution getNewSolution(ArrayList<Ellipse> ellipses)
    {
	return new Solution(ellipses,dictionary);
    }

    /* This method renders an image codified in a Solution Object 
       and displays it on the application window. */

    public void showSolution(Solution s)
    {
	if(s.getRenderedImage()==null)       
	    s.render(inputImage.getWidth(),inputImage.getHeight());
	for (ImageUpdatedListener listener : listeners)
	{
            listener.onUpdated(s.getRenderedImage(),s.getScore());
	}
    }

    public void addListener(ImageUpdatedListener listener)
    {
        listeners.add(listener);
    }

}
	    
    
