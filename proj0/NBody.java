public class NBody {
private static String starField="./images/starfield.jpg";
    public static double readRadius(String path){
        In in= new In(path);
        int numPlanets = in.readInt();
        double  radius =in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String path){
        In in= new In(path);
        int universesize=in.readInt();
        Planet[] planets=new Planet[universesize];
        for(int i =0;i<universesize;i++){
            planets[i]=new Planet ();
        }
        in.readDouble();
        int j=0;
        while(!in.isEmpty()){
            if(j==universesize) break;
            planets[j].xxPos=in.readDouble();
            planets[j].yyPos=in.readDouble();
            planets[j].xxVel=in.readDouble();
            planets[j].yyVel=in.readDouble();
            planets[j].mass=in.readDouble();
            planets[j].imgFileName=in.readString();
            j+=1;
        }
        return planets;
    }

    public static void main(String[] args){
    double T= Double.parseDouble(args[0]);
    double dt= Double.parseDouble(args[1]);
    String filename=args[2];
    double radius = readRadius(filename);
    Planet[] planets=readPlanets(filename);

        StdDraw.setScale(-radius, radius);

        double timer = 0.0;

        while(timer < T){
            double[] forceX = new double[planets.length];
            double[] forceY = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                forceX[i] = planets[i].calcNetForceExertedByX(planets);
                forceY[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, forceX[i], forceY[i]);
            }

            StdDraw.picture(0, 0, starField);

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(40);



            timer = timer + dt;
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

