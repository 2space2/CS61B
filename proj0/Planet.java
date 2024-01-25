public class Planet {
    double xxPos, yyPos ,xxVel, yyVel, mass;
    static double G = 6.67e-11;
    String imgFileName;
    
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
                xxPos = xP;
                xxVel = xV;
                yyPos = yP;
                yyVel = yV;
                mass = m;
                imgFileName = img;

              }
    
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        return Math.pow(dx * dx + dy * dy, 0.5);
    }

    public double calcForceExertedBy(Planet p) {
        return G * p.mass * this.mass / Math.pow(calcDistance(p), 2);
    }
    public double calcForceExertedByX(Planet p) {
        return G * p.mass * this.mass / Math.pow(calcDistance(p), 3) * (p.xxPos - this.xxPos);
    }

    public double calcForceExertedByY(Planet p) {
        return G * p.mass * this.mass / Math.pow(calcDistance(p), 3) * (p.yyPos - this.yyPos);
    }

    public double calcNetForceExertedByX(Planet p[]) {
        double sum =0;
        for(Planet i:p) {
            if(!this.equals(i)) sum += this.calcForceExertedByX(i);
        }
        return sum;
    }
    
    public double calcNetForceExertedByY(Planet p[]) {
        double sum =0;
        for(Planet i:p) {
            if(!this.equals(i)) sum += this.calcForceExertedByY(i);
        }
        return sum;
    }

    public void update(double dt, double fx, double fy) {
        this.xxVel = this.xxVel + dt * fx / this.mass;
        this.yyVel = this.yyVel + dt * fy / this.mass;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}