
/******************************************************************************
 *  Compilation:  javac Mandelbrot.java
 *  Execution:    java Mandelbrot xc yc size
 *  Dependencies: StdDraw.java
 *
 *  Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 *  % java Mandelbrot -0.5 0 2
 *
 ******************************************************************************/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.swing.JFrame;
 
public class MandelbrotN extends JFrame {
 
    private final int MAX_ITER = 570;
    private final double ZOOM = 200;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
    Scanner teclado = new Scanner(System.in);
    
    
 
    public MandelbrotN() {
        
        super("Mandelbrot Set");
        System.out.println("Introdusca valor para N");
        double n = teclado.nextInt();
        System.out.println("Creando");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = (double)Math.pow((zx*zx+zy*zy),(n/2.0)) * (double)Math.cos(n*(double)Math.atan2(zy,zx)) + cX;
                    //xtmp=(x*x+y*y)^(n/2)*cos(n*atan2(y,x)) + a
                    //y=(x*x+y*y)^(n/2)*sin(n*atan2(y,x)) + b
                    //x=xtmp
                    zy = (double)Math.pow((zx*zx+zy*zy),(n/2.0)) * (double)Math.sin(n*(double)Math.atan2(zy,zx)) + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 8));
            }
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
 
    public static void main(String[] args) {
        new MandelbrotN().setVisible(true);
    }
}