import Lib.StdDraw;

import java.awt.*;

/**
 * Created by ImanH on 9/1/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class Bench {
    public static double fd(double[] values,int j,int n,double dx){
        if(n==1) return ((values[(j+1+N)%N]-values[(j-1+N)%N])/(2.0*dx));
        return (fd(values,j+1,n-1,dx)-fd(values,j-1,n-1,dx))/(2.0*dx);
    }
    static final double g=0.00001;
    static final double h=0.1;
    static final double eps=0.00000001;
    static final int N=1000;
    static final double dx=0.1;
    static final double dt=0.4;
    static double[] z=new double[N];
    static double[] zdot=new double[N];
    static double shiftval = -0.2;
    static double scaler = 1.2;
    static double fric = 0.6;
    static double maxZdot = 0.01;
    public static void main(String[] args) {
        initialize(0);
        double sumz2 = 0.0;
        for (int i = 0; i < N; i++) {
            sumz2 += (z[i] * z[i]);
        }
        sumz2 /= (double) N;
        //StdDraw.show(50);
        StdDraw.show(0);
        int t = 0;
        while (true) {
            for (int i = 0; i < N; i++) {
                double d1 = fd(z, i, 1, dx);
                double d2 = fd(z, i, 2, dx);
                double d4 = fd(z, i, 4, dx);
                zdot[i] += g * h * (100.0 * d2 + (6.0 * (Math.pow(d1, 2.0) + d2 * (0.5 * (z[(i + 1) % N] + z[(i + N - 1) % N]))) / h) + ((h * h / 3.0) * d4)) * dt;
                if (zdot[i] >= maxZdot) {
                    while (zdot[i] >= maxZdot) {
                        zdot[i] *= fric;
                    }
                }
            }
            double newsz2 = 0.0;
            for (int i = 0; i < N; i++) {
                z[i] += zdot[i] * dt;
                newsz2 += (z[i] * z[i]);
                if (Math.abs(z[i]) >= 0.8 && zdot[i] > 0.0) zdot[i] -= Math.abs(zdot[i]) * 0.2;
            }
            newsz2 /= (double) N;

                if ((newsz2 / sumz2) > (1.6)) {
                    for (int j = 0; j < N; j++) {
                        z[j] *= 0.99999;
                        //zdot[j] *=((sumz2 / newsz2)*0.1);
                    }
                } else if ((newsz2 / sumz2) < (0.4)) {
                    for (int j = 0; j < N; j++) {
                        z[j] *= 1.00001;
                        //zdot[j] *=((sumz2 / newsz2)*0.1);
                    }
                }




            if (t % 30 == 0) {
                StdDraw.clear();
                for (int i = 0; i < N - 1; i++) {
                    //StdDraw.line((double)i/(double)N,(z[i]-shiftval)*scaler,(double)(i+1)/(double)N,(z[i+1]-shiftval)*scaler);
                    double[] xz = {(double) i / (double) N, (double) (i + 1) / (double) N, (double) (i + 1) / (double) N, (double) i / (double) N};
                    double[] yz = {0.0, 0.0, (z[i + 1] - shiftval) * scaler, (z[i] - shiftval) * scaler};
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.filledPolygon(xz, yz);
                    //System.out.println(z[i]);
                }
                StdDraw.show(0);
            }
            t++;
        }
    }




    public static void initialize(int j){
        if(j==0){
            double k=0.006;
            double omeg=k*Math.sqrt(g*h)/dx;
            for(int i=0;i<N;i++){
                z[i] = 0.1*(Math.sin((double)i*k));
                //zdot[i] =0.001*(1.0+Math.sin((double)i/30));

                zdot[i]=0.1*omeg*Math.cos((double)i*k);
//                if(Math.abs(i)<20) {
//                    z[i] = 0.05*(Math.sin((double)i/10.0));
//                    zdot[i]=-0.000005*Math.cos((double)i/10.0);
//                }
            }
        }
    }
}
