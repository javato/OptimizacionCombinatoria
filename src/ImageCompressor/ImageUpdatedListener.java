package ImageCompressor;

import java.awt.image.BufferedImage;

/* This interface will implement image 
   updating events */

public interface ImageUpdatedListener
{
    public void onUpdated(BufferedImage img, int score);
}

