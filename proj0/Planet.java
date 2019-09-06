import java.lang.Math;
public class Planet {

	public double xxPos; 
	//Its current x position
	public double yyPos; 
	//Its \\current y position
	public double xxVel; 
	//Its current velocity in the x direction
	public double yyVel; 
	//Its current velocity in the y direction
	public double mass; 
	//Its mass
	public String imgFileName; 
	//The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif)
	public Planet(double xP, double yP, double xV,double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet b) {
		double dis = Math.sqrt((Math.pow((b.xxPos-this.xxPos),2)+Math.pow((b.yyPos-this.yyPos),2)));
		return dis;
	}
	
	public double calcForceExertedBy(Planet b){
		double force = 6.67*Math.pow(10, -11)*b.mass*this.mass/Math.pow(this.calcDistance(b), 2);
		return force;
	}
	
	public double calcForceExertedByX(Planet b){
		double fX = this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)/this.calcDistance(b);	
		return fX;
	}
	
	public double calcForceExertedByY(Planet b){
		double fY = this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)/this.calcDistance(b);	
		return fY;
	}
	
	public double calcNetForceExertedByX(Planet [] pList){
		double netF = 0;
		for(int i = 0; i < pList.length; i++) {
			if(pList[i].equals(this)) {
				continue;
			}else {
				netF += this.calcForceExertedByX(pList[i]);
			}
		}
		return netF;
	}
	
	public double calcNetForceExertedByY(Planet [] pList){
		double netF = 0;
		for(int i = 0; i < pList.length; i++) {
			if(pList[i].equals(this)) {
				continue;
			}else {
				netF += this.calcForceExertedByY(pList[i]);
			}
		}
		return netF;
	}
	
	public void update(double dt, double fX, double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel =  this.xxVel + aX * dt;
		this.yyVel =  this.yyVel + aY * dt;
		this.xxPos = this.xxPos+ dt * xxVel;
		this.yyPos = this.yyPos+ dt * yyVel;
	}
	
	public void draw() {
		String pName = "images/"+this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, pName);	
	}
	
	

}
