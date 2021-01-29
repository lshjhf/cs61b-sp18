public class Planet {
    double xxPos;double yyPos;double xxVel;double yyVel;double mass;String imgFileName;
    Planet p;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
xxPos=xP;yyPos=yP;xxVel=xV;yyVel=yV;mass=m;imgFileName=img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
public double calcDistance(Planet p){
double c= (p.xxPos-this.xxPos)* (p.xxPos-this.xxPos)+(p.yyPos-this.yyPos)*(p.yyPos-this.yyPos);
return Math.sqrt(c);
}
    public double calcForceExertedBy(Planet target){
        double d = G* target.mass*this.mass/(calcDistance(target)*calcDistance(target));
        return d;
    }
    public double calcForceExertedByX(Planet target){
        double forceX = calcForceExertedBy(target) * (target.xxPos - this.xxPos) / calcDistance(target);
        return forceX;
    }

    public double calcForceExertedByY(Planet target){
        double forceY = calcForceExertedBy(target) * (target.yyPos - this.yyPos) / calcDistance(target);
        return forceY;
    }
    public double calcNetForceExertedByX(Planet planets[]){
    double netForceX=0.0;
    for (Planet p:planets){
        if (p.equals(this)) continue;
        netForceX=netForceX+this.calcForceExertedByX(p);
    }
    return netForceX;
    }

    public double calcNetForceExertedByY(Planet planets[]){
        double netForceY=0.0;
        for (Planet p:planets){
            if (p.equals(this)) continue;
            netForceY=netForceY+this.calcForceExertedByY(p);
        }
        return netForceY;
    }
    public void update(double time, double forceX,double forceY){
        double ax=forceX/this.mass;
        double ay=forceY/this.mass;
        this.xxVel=this.xxVel+ax*time;
        this.yyVel=this.yyVel+ay*time;
        this.xxPos=this.xxPos+this.xxVel*time;
        this.yyPos=this.yyPos+this.yyVel*time;
    }
    public Planet(){
        this.xxPos = 0;
        this.yyPos = 0;
        this.xxVel = 0;
        this.yyVel = 0;
        this.mass = 0;
        this.imgFileName = "";
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
