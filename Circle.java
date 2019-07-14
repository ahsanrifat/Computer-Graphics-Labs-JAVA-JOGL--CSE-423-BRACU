/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circle;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.lang.Math;
import java.security.Signature;
import java.util.Scanner;

import javax.swing.JFrame;

import jogamp.nativewindow.x11.awt.X11AWTGraphicsConfigurationFactory;

public class Circle
{
	public static int radius;
        public static int centerX;
        public static int centerY;
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter radius of circle");
		radius = scanner.nextInt();
                System.out.println("Enter center of circle");
		centerX = scanner.nextInt();
                centerY = scanner.nextInt();
		
		
		//getting the capabilities object of GL2 profile
		final GLProfile profile=GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities=new GLCapabilities(profile);
		// The canvas
		final GLCanvas glcanvas=new GLCanvas(capabilities);
		ThirdGLEventListener b=new ThirdGLEventListener(radius,centerX,centerY);
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
	int rad;
        int centerX;
        int centerY;
	ThirdGLEventListener(int radius,int centerX,int centerY) {
		rad = radius;
                this.centerX=centerX;
                this.centerY=centerY;
	}
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
		drawCircle(gl,rad,centerX,centerY);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
	}

	public void displayChanged(GLAutoDrawable drawable,
			boolean modeChanged, boolean deviceChanged) {
	}
	
	
	
	private void drawForAllCoOrdinates(GL2 gl, int x, int y,int centerX,int centerY) {
	    gl.glPointSize(1.0f);
	    gl.glBegin(GL.GL_POINTS);
	    gl.glVertex2f(x+centerX, y+centerY);
	    gl.glVertex2f(-x+centerX, -y+centerY);
            gl.glVertex2f(-y+centerX, -x+centerY);
	    gl.glVertex2f(y+centerX, x+centerY);
	    gl.glVertex2f(-y+centerX, x+centerY);
            gl.glVertex2f(-x+centerX, y+centerY);
	    gl.glVertex2f(x+centerX, -y+centerY);
	    gl.glVertex2f(y+centerX, -x+centerY);
	    
	    
	    gl.glEnd();
	};

	private void drawCircle(GL2 gl, int rad,int centerX,int centerY) {
		int d = 5-(4*rad);
	    int x = rad, y = 0;
	    drawForAllCoOrdinates(gl,x,y,centerX,centerY);
	    while(y<x) {
	    	if(d<0) {
	    		d+= 4*((2*y)+3);
	    		y++;
	    	}
	    	else {
	    		d+=4*((2*y)-(2*x)+5);
	    		x--;y++;
	    	}
	    	drawForAllCoOrdinates(gl,x,y,centerX,centerY);
	    }
	}
	
	public void dispose(GLAutoDrawable arg0)
		{

		}
}