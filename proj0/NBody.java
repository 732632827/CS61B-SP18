public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int first = in.readInt();
		double second = in.readDouble();
        return second;
    }
    
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int first = in.readInt();
		double second = in.readDouble();
        
        Planet[] Planets = new Planet[first];
        
        for(int i=0; i<first; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            Planets[i] = p;
        }
        
        return Planets;
    }
    
    private static void drawBg(String imageToDraw, double radius) {
		/** Sets up the universe so it goes from 
		  * -100, -100 up to 100, 100 */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		/* Stamps three copies of advice.png in a triangular pattern. */
        StdDraw.picture(0, 0, imageToDraw);
		//StdDraw.picture(0, 75, imageToDraw);
		//StdDraw.picture(-75, -75, imageToDraw);
		//StdDraw.picture(75, -75, imageToDraw);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		//StdDraw.pause(200);		
	}
    
    public static void main(String[] args){
        /** Collecting All Needed Input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        StdDraw.enableDoubleBuffering();
        
        /** Drawing the Background */
        String imageToDraw = "images/starfield.jpg";
        drawBg(imageToDraw, radius);
        
        /** Drawing All of the Planets */
        for(Planet p: planets){
            p.draw();
        }
        
        double t = 0.0;
        while(t<T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0; i<planets.length; i++){
                double pNetFX = planets[i].calcNetForceExertedByX(planets);
                double pNetFY = planets[i].calcNetForceExertedByY(planets);
                xForces[i] = pNetFX;
                yForces[i] = pNetFY;
            }
            
            for(int i=0; i<planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            
            drawBg(imageToDraw, radius);
            
            for(Planet p: planets){
                p.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(100);
            //StdDraw.pause(10);
            
            t += dt;
        }
        
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}






















