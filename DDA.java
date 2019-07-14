/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda;

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

public class DDA{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        final GLProfile profile=GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities=new GLCapabilities(profile);
        final GLCanvas glcanvas=new GLCanvas(capabilities);
        Triangle b=new Triangle();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(800, 800);
        final JFrame frame=new JFrame("Frame");
        frame.add(glcanvas);
        frame.setSize(800,800);
        frame.setVisible(true);
    }
    
}
class Triangle implements GLEventListener {
    private GLU glu;
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        System.out.println("Give Inputs");
        Scanner sc=new Scanner(System.in);
        Drawline(150,150,0,0,gl);
        Drawline(0,0,150,0,gl);
        Drawline(0,0,0,150,gl);
    }
    public void drawAxes(GL2 gl){
        gl.glBegin(GL_LINES);
        gl.glVertex2i(-200, 0);
        gl.glVertex2i(200, 0);
        gl.glVertex2i(0, -200);
        gl.glVertex2i(0, 200);
        gl.glEnd();
        gl.glFlush();
    }
    public void Drawline (int x1, int y1, int x2, int y2,GL2 gl){
        int dx= x2-x1;
        int dy=y2-y1;
        double m=(dy*1.0)/(dx*1.0);
        double m_1=1/m;
        gl.glPointSize(1.0f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glColor3f(0f,1.0f,0);
        if(dx > 0 && dy>0){
            if (m >= 0 && m <= 1) {
                int y = y1;
                double yTemp = y1;
                for (int x = x1; x <= x2; x++) {
                    gl.glVertex2i(x,y);
                    yTemp = yTemp + m;
                    y=(int)Math.round(yTemp);
                }
            } else if (m > 1) {
                int x = x1;
                double xTemp = x1;
                for (int y = y1; y <= y2; y++) {
                    gl.glVertex2i(x, y);
                    xTemp = xTemp+m_1;
                    x = (int)Math.round(xTemp);
                }
            }
        }else if(dx<0 && dy < 0){
            if (m >= 0 && m <= 1) {

                int y = y2;
                double yTemp = y2;

                for (int x = x1; x >= x2; x--) {
                    gl.glVertex2i(x, y);
                    yTemp = yTemp + m;
                    y=(int)Math.round(yTemp);
                }
            } else if (m > 1) {
                int x = x2;
                double xTemp = x2;
                for (int y = y1; y >= y2; y--) {

                    gl.glVertex2i(x, y);
                    xTemp = xTemp+m_1;
                    x = (int)Math.round(xTemp);
                }
            }
        }else if(dx>0 && dy < 0){         
            if (m < 0 && m >= -1) {
                int y= y1;
                double yTemp = y1;

                for(int x = x1; x <= x2; x++) {
                    gl.glVertex2i(x, y);
                    yTemp = yTemp + m;
                    y=(int)Math.round(yTemp);
                }
            } else if (m < 1) {
                int x = x1;
                double xTemp = x1;
                for (int y = y1; y >= y2; y--) {
                    gl.glVertex2i(x, y);
                    xTemp = xTemp+m_1;
                    x = (int)Math.round(xTemp);
                }
            }
        }else if(dx<0 && dy > 0){
            if (m < 0 && m >= -1) {
                int y = y2;
                double yTemp = y2;

                for(int x = x2; x <= x1; x++) {
                    gl.glVertex2i(x, y);
                    yTemp = yTemp + m;
                    y=(int)Math.round(yTemp);
                }
            } else if (m <1) {
                int x = x1;
                double xTemp = x1;
                for (int y = y1; y <= y2; y++) {

                    gl.glVertex2i(x, y);
                    xTemp = xTemp+m_1;
                    x = (int)Math.round(xTemp);
                }
            }
        }else if(dx == 0 || dy == 0){
            if(dx == 0){
                
                for (int y = Math.min(y2, y1); y <= Math.max(y2, y1); y++) {
                    gl.glVertex2i(0, y);

                }
            }else if(dy == 0){
                for(int x = Math.min(x2, x1); x <= Math.max(x2, x1); x++) {
                    gl.glVertex2i(x, 0);
                }
            }
        }
        gl.glEnd();
        gl.glFlush();

    }



    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }

    public void dispose(GLAutoDrawable arg0)
    {

    }
}
