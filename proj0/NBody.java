import java.io.*;
import java.util.*;


public class NBody {

	
	public static double readRadius(String path) {
		In readin = new In(path);
		int a = readin.readInt();
		return readin.readDouble();
	}
	

	public static Planet[] readPlanets(String path) {
		In readin = new In(path);
		int a = readin.readInt();
		double b = readin.readDouble();
		String c = readin.readLine();
		List<Planet> pList = new ArrayList<Planet>();
		while(readin.hasNextLine()) {
			String read = readin.readLine().trim();
			String[] readarray = read.split("\\s+");
			try{
                double test = Double.parseDouble(readarray[0]);
            } catch (java.lang.NumberFormatException e) {
            	break;
            }
			Planet p = new Planet(Double.parseDouble(readarray[0]), Double.parseDouble(readarray[1]),
					Double.parseDouble(readarray[2]),Double.parseDouble(readarray[3]), 
					Double.parseDouble(readarray[4]),readarray[5]);
			pList.add(p);
		}
		Planet[] pArray = new Planet[pList.size()];
		pArray = pList.toArray(pArray);
		return pArray;
		
	}
	
	
	
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet [] pList = NBody.readPlanets(filename);
		double R = NBody.readRadius(filename);
		StdDraw.setScale(-R, R);
		
		String bPath = "images/starfield.jpg";
		
		
		double t = 0.0;
		while(t <= T) {
			double[] xForces = new double[pList.length];
			double[] yForces = new double[pList.length];
			for(int i = 0; i < pList.length; i++) {
				xForces[i] = pList[i].calcNetForceExertedByX(pList);
				yForces[i] = pList[i].calcNetForceExertedByY(pList);
				pList[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.enableDoubleBuffering();
			StdDraw.picture(0, 0, bPath);
			for(int i = 0; i < pList.length;i++) {
				pList[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", pList.length);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < pList.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		    			  pList[i].xxPos, pList[i].yyPos, pList[i].xxVel,
		    			  pList[i].yyVel, pList[i].mass, pList[i].imgFileName);   
		}
		
		
		
	}

}
