package com.danke.engine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.awt.BorderLayout;


public class Window{

    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy buffstrat;
    private Graphics graphic;

    public Window(GameContainer gamecon){

        image = new BufferedImage(gamecon.getWidth(),gamecon.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas =  new Canvas();
        // size of the canvas (needs dimension class)
        Dimension dim = new Dimension((int)(gamecon.getWidth() * gamecon.getScale()),(int) (gamecon.getHeight() * gamecon.getScale()));
        canvas.setPreferredSize(dim);
        canvas.setMaximumSize(dim);
        canvas.setMinimumSize(dim);

        frame = new JFrame(gamecon.getTitle());
        // upon clicking x button close program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        // sets frame to size of canvas
        frame.pack();
        // frame starts in middle of screen otherwise starts in top left corner
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // 2 buffers to render to
        canvas.createBufferStrategy(2);
        buffstrat = canvas.getBufferStrategy();
        graphic = buffstrat.getDrawGraphics();



    }

    public void update(){

        // draws image to canvas (actually to buffstrat attached to canvas)
        graphic.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        buffstrat.show();






    }


    // getter
    public Canvas getCanvas(){
        return this.canvas;
    }

    public BufferedImage getImage(){
        return this.image;
    }
    
    public JFrame getFrame() {
    	return this.frame;
    }
        
}