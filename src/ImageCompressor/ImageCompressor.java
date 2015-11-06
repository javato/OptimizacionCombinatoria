package ImageCompressor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import Search.*;
import java.lang.reflect.Constructor;        
import java.util.ArrayList;   
import java.lang.reflect.Field;


/** 
 * Panel to show a BufferedImage  */


class ImagePanel extends JPanel
{
    private BufferedImage image;
    private JLabel JLTitle;
    
    public ImagePanel(BufferedImage img,String title) 
    {
	image=img;
	JLTitle=new JLabel(title);
	JLTitle.setForeground(Color.green);
	add(JLTitle);
    }
    public void changeImage(BufferedImage img)
    {
	image=img;
	repaint();
    }

    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);  
    }

}

/** 
 * Main class. It implements the GUI  */


public class ImageCompressor extends JFrame implements ImageUpdatedListener
{
  BufferedImage compressedImage;
  BufferedImage originalImage;  
  ImagePanel JPCI; 
  ImagePanel JPOI; 
  JSplitPane JPM;
  JLabel JLScore;
  SearchAlgorithm search;
  ColorDictionary dictionary;


  ImageCompressor(String inputFile, String algorithmName, int numberOfColors,int numberOfEllipses, ArrayList<String> attributesName, ArrayList<Float> attributesValue)
   {

	this.setLayout(new BorderLayout());
	try 
	{
	    originalImage = ImageIO.read(new File(inputFile));
	}
	catch (IOException e) 
	{
	    System.err.println("ERROR! Can't read image from file " + inputFile);
	    System.exit(-1);
	}


   
	JPOI=new ImagePanel(originalImage, "Original Image");
	JPCI=new ImagePanel(compressedImage, "Compressed Image");
	JPM= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JPCI,JPOI);
	JPM.setDividerLocation(originalImage.getWidth()+10);
	add(JPM);

	JLScore=new JLabel("");

	add(JLScore,BorderLayout.PAGE_END);
	dictionary=new ColorDictionary(numberOfColors);
	Class parsType[]=new Class[3];
	parsType[0]=BufferedImage.class;
	parsType[1]=ColorDictionary.class;
	parsType[2]=int.class;
	Object pars[]=new Object[3];
	pars[0]=originalImage;
	pars[1]=dictionary;
	pars[2]=numberOfEllipses;
	search=(SearchAlgorithm) getNewObjectByName(algorithmName, parsType, pars,attributesName,attributesValue);	
	search.addListener(this);
        setSize(originalImage.getWidth()*2+50,originalImage.getHeight()+100);	
	setVisible(true);
  }

  public void compressImage()      
  {

        System.err.print("Searching .....");
        EllipseBasedImage searchResult=search.doSearch().getCompressedImage();
        System.err.println(" Done !!!");			   
	compressedImage=searchResult.render(originalImage.getWidth(),originalImage.getHeight());
	JPCI.changeImage(compressedImage);
  }
    
  public static Object getNewObjectByName(String className, Class[] classes, Object[] args, ArrayList<String> attrNames, ArrayList<Float> attrValues) 
  {
        Class classDefinition=null;
	Object o = null;
	try 
	{
	    classDefinition = Class.forName(className);
	    Constructor constructor = classDefinition.getConstructor(classes);
	    o = constructor.newInstance(args);
	} 
	catch (Exception e) 
	{
	    System.err.println("Clase " + className + " no encontrada");
	    System.err.println(e);
	    
	    System.exit(-1);
	    
	}    

	for(int i=0;i<attrNames.size();i++)
	{
	    try
	    {
		Field field = classDefinition.getDeclaredField(attrNames.get(i));
		String type=field.getType().getName();
		if(type.equals("int"))
		    field.set(o, attrValues.get(i).intValue());
		else if (type.equals("float"))
	     	   field.set(o, attrValues.get(i).floatValue());
		else if (type.equals("double"))
	     	   field.set(o, attrValues.get(i).doubleValue());
			 
	    }
	    catch(Exception e)
	    {
		System.err.println("ERROR . Parameter " + attrNames.get(i) + " not found in class " + className);
		System.exit(-1);
	    }
	}
	return o;
  }



  public void onUpdated(BufferedImage img, int score)
  {     
     JPCI.changeImage(img);
     JLScore.setText("SCORE = " + new Integer(score).toString());
     repaint();
  }

  public static void printUsageAndExit()
  {
      System.err.println(" Usage: java ImageCompressor -algorithm class_implementing_the_search_algorith  -inputImage image_file_to_be_compressed -numberOfColors number_of_colors_in_the_dictionary -numberOfEllipses number_of_ellipses_to_use_in_the_compression -searchParameters parameters_to_send_to_the_search_algorithm");
      System.exit(-1);
  }


  public static void main(String[] args)
  {
     /* Default parameters */
      int numberOfColors=16;
      int numberOfEllipses=20;
      String inputImage=null;
      String className="";
      ArrayList<String> parametersName=new ArrayList<String>();
      ArrayList<Float> parametersValue=new ArrayList<Float>();

      if(args.length<2) printUsageAndExit();
      for(int i=0;i<args.length;i++)
      {
	  switch(args[i])
	  {
	      case "-algorithm": if(i<args.length-1)
		                   className=args[++i];		     
                                 else printUsageAndExit();
	                         break;
             case "-inputImage": if(i<args.length-1)
 	                            inputImage=args[++i];
                                 else printUsageAndExit();
                                 break;

            case "-numberOfColors": if(i<args.length-1)
 			  	       numberOfColors=new Integer(args[++i]).intValue();
                                    else printUsageAndExit();
                                    break;
            case "-numberOfEllipses": if(i<args.length-1)
 			  	       numberOfEllipses=new Integer(args[++i]).intValue();
                                    else printUsageAndExit();
                                    break;

 	      default:             if(args[i].indexOf('-')!=0||i==args.length-1)
					printUsageAndExit();
		                    else
				    {

					parametersName.add(args[i++].substring(1));
					parametersValue.add(new Float(args[i]));
				    }		  				  
			      
       }
      }
    
      ImageCompressor ic=new ImageCompressor(inputImage,className,numberOfColors,numberOfEllipses,parametersName,parametersValue);
      ic.compressImage();
	  
  }
}
