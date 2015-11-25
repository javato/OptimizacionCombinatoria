/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import ImageCompressor.ColorDictionary;
import ImageCompressor.Ellipse;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class HillClimbing extends SearchAlgorithm{

    
    public HillClimbing(BufferedImage i, ColorDictionary cD, int n){
        super(i, cD, n);
}

    @Override
    public Solution doSearch() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Ellipse> c=new ArrayList<Ellipse>();
        Random rnd = new Random();
        
        for(int i=0;i<numberOfEllipses;i++)
	    c.add(new Ellipse(rnd.nextInt(width),   // x position
			      rnd.nextInt(height),  // y position
			      rnd.nextInt(width),   // x radius 
			      rnd.nextInt(height),  // y radius
			      rnd.nextInt(dictionary.getNumberOfColors()), // color
			      rnd.nextInt(255))); // transparency;
        
        int scoreActual, mejorScore, score;
        Solution mejorVecino;
        ArrayList<Solution> vecinos = new ArrayList<Solution>();
        boolean fin = false;
        Solution currentSolution = getNewSolution(c);
        Solution newSolution=currentSolution.clone();
        //scoreActual = computeScore(currentSolution);
        
        while(!fin){
            System.out.println("WHILEEE!");
            scoreActual = computeScore(currentSolution);
            System.out.println("SCORE: " + scoreActual);
            System.out.println("CURRENT SOLUTION: " + currentSolution);
            scoreActual = currentSolution.getScore();
            mejorScore = 999999999;
            mejorVecino = null;
            vecinos = generarVecindad(currentSolution);
            System.out.println("TAMAÑO DE LOS VECINOS: " + vecinos.size());
            System.out.println("vecinos :" + vecinos);
            for (int i = 0; i < vecinos.size(); i++) {
                
                score=computeScore(vecinos.get(i));
                //score=vecinos.get(i).getScore();
                System.out.println("SCORE MIERDERO: " + score);
                System.out.println("SCORE ACTUAL:" + scoreActual);
                System.out.println("mejor score: " + mejorScore);
                if(score<mejorScore){
                    mejorScore=score;
                    mejorVecino=vecinos.get(i);
                    System.out.println(currentSolution.toString());
                }
            }
            if(mejorScore<scoreActual){
                System.out.println(mejorVecino);
                currentSolution=mejorVecino.clone();
                scoreActual = mejorScore;
                
                showSolution(currentSolution);
            }
            else
                fin=true;
        }
        return currentSolution;
    }

    public ArrayList<Solution> generarVecindad(Solution currentSolution){
        System.out.println("GENERANDO VECINDAD");
        ArrayList<Solution> vecinos = new ArrayList<Solution>();
        ArrayList<Ellipse> elipses;
        elipses = currentSolution.compressedImage.getEllipses();
        //System.out.println(currentSolution.compressedImage.getEllipses().toString());
        int a = 0;

        for (int i = 0; i < numberOfEllipses; i++) {
            Ellipse elipseBackup = elipses.get(i);
            
            //elipses.remove(i);
            //for (int j = 0; j < numberOfEllipses; j++) {
                //Ellipse elipseBackup = elipses.get(i);
            
            elipses.add(new Ellipse(elipseBackup.getX() + 10,   // x position
                                  elipseBackup.getY(),  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            //Solution vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
            //vecinos.add(vecino);
//
//
            //elipseBackup = elipses.get(1);
            //elipses.remove(1);
            elipses.add(new Ellipse(elipseBackup.getX() - 10,   // x position
                                  elipseBackup.getY(),  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            //vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
            //vecinos.add(vecino);

            //elipseBackup = elipses.get(2);
            //elipses.remove(2);
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() + 10,  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            //vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
            //vecinos.add(vecino);
            //elipseBackup = elipses.get(3);
            //elipses.remove(3);
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() - 10,  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            //vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
            //vecinos.add(vecino);

            //elipseBackup = elipses.get(4);
            //elipses.remove(4);
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() ,  // y position
                                  elipseBackup.getXRadius() + 10,   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            //vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
            //vecinos.add(vecino);

            //elipseBackup = elipses.get(5);
            //elipses.remove(5);
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() ,  // y position
                                  elipseBackup.getXRadius() - 10,   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() ,  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius() + 10,  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() ,  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius() - 10,  // y radius
                                  elipseBackup.getColor(),
                                  elipseBackup.getTransparency()) );
            
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() ,  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor() + 1,
                                  elipseBackup.getTransparency()) );
            
            elipses.add(new Ellipse(elipseBackup.getX() ,   // x position
                                  elipseBackup.getY() ,  // y position
                                  elipseBackup.getXRadius(),   // x radius 
                                  elipseBackup.getYRadius(),  // y radius
                                  elipseBackup.getColor() - 1,
                                  elipseBackup.getTransparency()) );
            
            Solution vecino = new Solution(elipses, currentSolution.compressedImage.getColorDictionary()); 
            vecinos.add(vecino);
            System.out.println("Num vecinos: " + vecinos.size() + " || Añadiendo vecino: " + vecino + " a -> VECINOS: " + vecinos);
            
        }
            
        //}
        
        return vecinos;
    }
        
    
    
}



