/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab01;

/**
 *
 * @author Syed Rifat Ahsan
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math;
import javax.swing.JFrame;
import java.util.Scanner;

import static com.jogamp.opengl.GL.GL_LINES;

public class Lab01  {

    /**
     * @param args the command line arguments
     */
        public static int x1;
	public static int y1;
	public static int x2;
	public static int y2;
	public static int x3;
	public static int y3;
	public static int x4;
	public static int y4;
	public static int x5;
	public static int y5;
	
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter first point (x1 and y1)");
		x1 = scanner.nextInt();
		y1 = scanner.nextInt();

		System.out.println("Enter second point (x2 and y2)");
		x2 = scanner.nextInt();
		y2 = scanner.nextInt();
		
		System.out.println("Enter third point (x3 and y3)");
		x3 = scanner.nextInt();
		y3 = scanner.nextInt();
		
		System.out.println("Enter third point (x4 and y4)");
		x4 = scanner.nextInt();
		y4 = scanner.nextInt();
		
		System.out.println("Enter third point (x5 and y5)");
		x5 = scanner.nextInt();
		y5 = scanner.nextInt();
		
		//getting the capabilities object of GL2 profile
		final GLProfile profile=GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities=new GLCapabilities(profile);
		// The canvas
		final GLCanvas glcanvas=new GLCanvas(capabilities);
		ThirdGLEventListener b=new ThirdGLEventListener();
		glcanvas.addGLEventListener(b);
		glcanvas.setSize(400, 400);
		//creating frame
		final JFrame frame=new JFrame("Basic frame");
		//adding canvas to frame
		frame.add(glcanvas);
		frame.setSize(400,400);
		frame.setVisible(true);
	}
}

class ThirdGLEventListener implements GLEventListener {
	/**
	 * Interface to the GLU library.
	 */
	private GLU glu;

	/**
	 * Take care of initialization here.
	 */
	public void init(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		glu = new GLU();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glViewport(-250, -150, 250, 150);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-250.0, 250.0, -250.0, 250.0);
	}

	/**
	 * Take care of drawing here.
	 */
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		/*
		 * put your code here
		 */
		draw8Way(gl, Lab01.x1, Lab01.y1, Lab01.x3, Lab01.y3);
		draw8Way(gl, Lab01.x3, Lab01.y3, Lab01.x5, Lab01.y5);
		draw8Way(gl, Lab01.x5, Lab01.y5, Lab01.x2, Lab01.y2);
		draw8Way(gl, Lab01.x2, Lab01.y2, Lab01.x4, Lab01.y4);
		draw8Way(gl, Lab01.x4, Lab01.y4, Lab01.x1, Lab01.y1);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
	}

	public void displayChanged(GLAutoDrawable drawable,
			boolean modeChanged, boolean deviceChanged) {
	}
	
	private void draw8Way(GL2 gl, int x1, int y1, int x2, int y2) {
		if((x2-x1)>=0&&(y2-y1)>=0) { //quadrant 1
			if(Math.abs(y2-y1)>Math.abs(x2-x1)) { //zone 1
				midpointLine(gl, y1, x1, y2, x2, 1);
			}
			else { //zone 0
				midpointLine(gl, x1, y1, x2, y2, 0);
			}
		}
		else if((x2-x1)<0&&(y2-y1)>=0) { //quadrant 2
			if(Math.abs(y2-y1)>Math.abs(x2-x1)) { //zone 2
				midpointLine(gl, y1, -x1, y2, -x2, 2);
			}
			else { //zone 3
				midpointLine(gl, -x1, y1, -x2, y2, 3);
			}
		}
		else if((x2-x1)<0&&(y2-y1)<0) { //quadrant 3
			if(Math.abs(y2-y1)<Math.abs(x2-x1)) { //zone 4
				midpointLine(gl, -x1, -y1, -x2, -y2, 4);
			}
			else { //zone 5
				midpointLine(gl, -y1, -x1, -y2, -x2, 5);
			}
		}
		else if((x2-x1)>=0&&(y2-y1)<0) { //quadrant 4
			if(Math.abs(y2-y1)>Math.abs(x2-x1)) { //zone 6
				midpointLine(gl, -y1, x1, -y2, x2, 6);
			}
			else { //zone 7
				midpointLine(gl, x1, -y1, x2, -y2, 7);
			}
		}
	}
	
	private void pixel8Way(GL2 gl, int x, int y, int zone) {
		gl.glPointSize(1.0f);
                gl.glBegin(GL2.GL_POINTS);
	    
	    switch(zone) { 
	    	case 0: gl.glVertex2f(x, y); break;
	    	case 1: gl.glVertex2f(y, x); break;
	    	case 2: gl.glVertex2f(-y, x); break;
	    	case 3: gl.glVertex2f(-x, y); break;
	    	case 4: gl.glVertex2f(-x, -y); break;
	    	case 5: gl.glVertex2f(-y, -x); break;
	    	case 6: gl.glVertex2f(y, -x); break;
	    	case 7: gl.glVertex2f(x, -y); break;
	    }
	    gl.glEnd();
	};

	private void midpointLine(GL2 gl, int x1, int y1, int x2, int y2, int zone) {
		
	    
	    int dx, dy, d, incE, incNE, x, y;
	    dx = x2 - x1;
	    dy = y2 - y1;
	    d   = 2*dy - dx;
	    incE   = 2*dy;
	    incNE = 2*(dy - dx);
	    y = y1;
	    for (x=x1; x<=x2; x++) 
	    {
	    	pixel8Way(gl, x, y, zone);
	        if (d>0) {
	            d = d + incNE;
	            y = y + 1;
	        } 
	        else {
	            d = d + incE;
	        } 
	    }

	    
	}
	
	public void dispose(GLAutoDrawable arg0)
		{

		}

}