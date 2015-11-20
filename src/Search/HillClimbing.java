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
        int scoreActual, mejorScore, score;
        Solution mejorVecino;
        ArrayList<Solution> vecinos = new ArrayList<Solution>();
        boolean fin = false;
        Solution currentSolution = getNewSolution(c);
        Solution newSolution=currentSolution.clone();
        scoreActual = computeScore(currentSolution);
        
        while(!fin){
            mejorScore = -999999999;
            mejorVecino = null;
            vecinos = generarVecindad(currentSolution);
            for (int i = 0; i < vecinos.size(); i++) {
                score=vecinos.get(i).getScore();
                if(score<mejorScore){
                    mejorScore=score;
                    mejorVecino=vecinos.get(i);
                }
            }
            if(mejorScore<scoreActual){
                currentSolution=mejorVecino;
                scoreActual = mejorScore;
                showSolution(currentSolution);
            }
            else
                fin=true;
        }
        return currentSolution;
    }

    public ArrayList<Solution> generarVecindad(Solution currentSolution){
        ArrayList<Solution> vecinos = new ArrayList<Solution>();
        ArrayList<Ellipse> elipses;
        elipses = currentSolution.compressedImage.getEllipses();
        Ellipse elipseBackup = elipses.get(0);
        elipses.remove(0);
        elipses.add(0,new Ellipse(elipseBackup.getX() + 10,   // x position
			      elipseBackup.getY(),  // y position
			      elipseBackup.getXRadius(),   // x radius 
			      elipseBackup.getYRadius(),  // y radius
			      elipseBackup.getColor(),
                              elipseBackup.getTransparency()) );
        Solution vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
        vecinos.add(vecino);
        
        
        
        elipses.remove(1);
        elipses.add(1,new Ellipse(elipseBackup.getX() - 10,   // x position
			      elipseBackup.getY(),  // y position
			      elipseBackup.getXRadius(),   // x radius 
			      elipseBackup.getYRadius(),  // y radius
			      elipseBackup.getColor(),
                              elipseBackup.getTransparency()) );
        vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
        vecinos.add(vecino);
        
        
        elipses.remove(2);
        elipses.add(2,new Ellipse(elipseBackup.getX() ,   // x position
			      elipseBackup.getY() + 10,  // y position
			      elipseBackup.getXRadius(),   // x radius 
			      elipseBackup.getYRadius(),  // y radius
			      elipseBackup.getColor(),
                              elipseBackup.getTransparency()) );
        vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
        vecinos.add(vecino);
        
        elipses.remove(3);
        elipses.add(3,new Ellipse(elipseBackup.getX() ,   // x position
			      elipseBackup.getY() - 10,  // y position
			      elipseBackup.getXRadius(),   // x radius 
			      elipseBackup.getYRadius(),  // y radius
			      elipseBackup.getColor(),
                              elipseBackup.getTransparency()) );
        vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
        vecinos.add(vecino);
        
        elipses.remove(4);
        elipses.add(4,new Ellipse(elipseBackup.getX() ,   // x position
			      elipseBackup.getY() ,  // y position
			      elipseBackup.getXRadius() + 10,   // x radius 
			      elipseBackup.getYRadius(),  // y radius
			      elipseBackup.getColor(),
                              elipseBackup.getTransparency()) );
        vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
        vecinos.add(vecino);
        
        elipses.remove(5);
        elipses.add(5,new Ellipse(elipseBackup.getX() ,   // x position
			      elipseBackup.getY() ,  // y position
			      elipseBackup.getXRadius() - 10,   // x radius 
			      elipseBackup.getYRadius(),  // y radius
			      elipseBackup.getColor(),
                              elipseBackup.getTransparency()) );
        vecino = new Solution(elipses,currentSolution.compressedImage.getColorDictionary()); 
        vecinos.add(vecino);
        
        
        return vecinos;
    }
    
    
}



