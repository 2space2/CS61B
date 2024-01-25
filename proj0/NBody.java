public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int n = in.readInt();
        Planet [] p = new Planet[n];

        in.readDouble();
        for(int i = 0;i < n;i++) {
            p[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
             in.readDouble(), in.readDouble(), in.readString());
        }
        return p;
    }

    public static void main(String[] args) {
        
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet [] p = readPlanets(filename);
        int N = p.length;
   
        StdDraw.setScale(-radius, radius);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.enableDoubleBuffering();
        for(int j=0;j<N;j++) p[j].draw();   
        StdDraw.show();

        for(int i = 0;i <= T;i+=dt) {
            double [] xFroce = new double[N];
            double [] yFroce = new double[N];

            for(int j=0;j<N;j++) xFroce[j] = p[j].calcNetForceExertedByX(p);
            for(int j=0;j<N;j++) yFroce[j] = p[j].calcNetForceExertedByY(p);

            for(int j=0;j<N;j++) p[j].update(dt, xFroce[j], yFroce[j]);

           StdDraw.picture(0, 0, "images/starfield.jpg");
           //StdDraw.clear(StdDraw.BLACK);
            for(int j=0;j<N;j++) p[j].draw();

            StdDraw.show();
            StdDraw.pause(10);
        }



        


    }
}
