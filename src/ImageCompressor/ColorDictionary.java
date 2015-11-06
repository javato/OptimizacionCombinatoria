package ImageCompressor;

import java.awt.Color;


/** 
 * This class implements a color dictionary (palette). Each color is indexed by an 
 * integer and the number of colors is chosen at the beginning */


public class ColorDictionary
{
    int numberOfColors; /* Number of colors in the dictionary */
    Color dictionary[];


    /** Constructor. Parameter: number of colors */
    
    ColorDictionary(int nC)
    {
	numberOfColors=nC+1;
	generateDictionary();
    }


    /* Method that generate the dictionary by
       performing a sampling in the HSB space
       and converting the colors to RGB. */       

    void generateDictionary()
    {
	dictionary=new Color[numberOfColors];
	/* The first element is always the white color */
	dictionary[0]=new Color(255,255,255);
	
	for(int i = 1; i < numberOfColors; i+=2)
	{
	    /* For the same color, three versions are produced: dark, normal, bright */
		dictionary[i] = Color.getHSBColor((float) i / (float) numberOfColors, 0.85f, 0.3f);
		if(i+1<numberOfColors) dictionary[i+1] = Color.getHSBColor((float) i / (float) numberOfColors, 0.85f, 0.8f);		
	}
	
    }

   /** Returns the number of colors in the dictionary */
    
   public int getNumberOfColors()
   {
       return numberOfColors;
   }

   /** Returns the RGB color corresponding to a specific index */
    
   public Color getColor(int i)
   {
	if(i<0) i=0;
	if(i>=numberOfColors) i=numberOfColors-1;
	return dictionary[i];
   }
	
	
    
}



    
