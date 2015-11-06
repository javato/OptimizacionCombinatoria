package Search;


import ImageCompressor.Ellipse;
import ImageCompressor.ColorDictionary;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;


/** Example of search algorithm. This implements a random search 
    where a solution is modified randomly. If a better solution is found
    the new solution is taken. Otherwise, it is ruled out. This is 
    repeated until a maximum number of generations is achieved */

public class RandomSearch extends SearchAlgorithm
{

    public int maxIterations=100;  /* Maximum number of iterations for the
				     algorithm. This parameter can be 
				     set in the command line by 
				     typing "-maxInterations number" */

	
    public RandomSearch(BufferedImage i, ColorDictionary cD,int n)
    {
    	super(i,cD,n);
    }    


    public Solution doSearch()
    {
	ArrayList<Ellipse> c=new ArrayList<Ellipse>();
	Random rnd = new Random();

	/* We generate random ellipses and add them to the solution.
	   width and height store the dimensions of the input image */
	
	for(int i=0;i<numberOfEllipses;i++)
	    c.add(new Ellipse(rnd.nextInt(width),   // x position
			      rnd.nextInt(height),  // y position
			      rnd.nextInt(width),   // x radius 
			      rnd.nextInt(height),  // y radius
			      rnd.nextInt(dictionary.getNumberOfColors()), // color
			      rnd.nextInt(255))); // transparency;


	/* We get an initial solution based on this randomly generated 
	   solution */
		
	Solution currentSolution = getNewSolution(c);
	for(int i=0;i<maxIterations;i++)
	{

	    for(int j=0;j<numberOfEllipses;j++)
	    {
		Solution newSolution=currentSolution.clone();
		
		/* change randomly the position of the ellipse */
		newSolution.getEllipseAt(j).setX(rnd.nextInt(width));
		newSolution.getEllipseAt(j).setY(rnd.nextInt(height));

		/* If the score of the new solution is better, we update
		   the current solution */
		
		if(computeScore(newSolution)<computeScore(currentSolution))
		    currentSolution=newSolution;
		
		/* change randomly the radius of the ellipse */
		newSolution=currentSolution.clone();
		newSolution.getEllipseAt(j).setXRadius(rnd.nextInt(width));
		newSolution.getEllipseAt(j).setYRadius(rnd.nextInt(height));

		/* If the score of the new solution is better, we update
		   the current solution */
		if(computeScore(newSolution)<computeScore(currentSolution))
		    currentSolution=newSolution;


		/* change randomly the color of the ellipse */
		newSolution=currentSolution.clone();
		newSolution.getEllipseAt(j).setColor(rnd.nextInt(numberOfColors));

		if(computeScore(newSolution)<computeScore(currentSolution))
		    currentSolution=newSolution;

		/* Show the current solution on the main window */
		showSolution(currentSolution);
		
	    }
	}
	return currentSolution;

    }
}
