public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double gravity = 6.67e-11;
    
    public Planet(double xxPos, double yyPos, double xxVel,double yyVel, double mass, String imgFileName){
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }
    
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
    }
    
    public double calcForceExertedBy(Planet p){
        return gravity*p.mass*this.mass/(calcDistance(p)*calcDistance(p));
    }
    
    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/calcDistance(p);
    }
    
    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-this.yyPos)/calcDistance(p);
    }
    
    public double calcNetForceExertedByX(Planet[] p){
        double netFX = 0;
        for(Planet planet: p){
            if(equals(planet)==false){
                netFX+=calcForceExertedByX(planet);
            }
        }
        return netFX;
    }
    
    public double calcNetForceExertedByY(Planet[] p){
        double netFY = 0;
        for(Planet planet: p){
            if(equals(planet)==false){
                netFY+=calcForceExertedByY(planet);
            }
        }
        return netFY;
    }
    
    public void update(double dt, double xForce, double yForce){
        double ax = xForce / this.mass;
        double ay = yForce / this.mass;
        this.xxVel += ax*dt;
        this.yyVel += ay*dt;
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
    }
    
    /** Drawing One Planet */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+imgFileName);
		StdDraw.show();
        
    }
}















