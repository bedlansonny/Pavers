import java.io.*;
import java.util.*;
public class Main
{
    public static void main(String args[]) throws IOException
    {
        Scanner in = new Scanner(new File("input.txt"));
        int t = in.nextInt();
        for(int ti = 0; ti < t; ti++)
        {
            int p = in.nextInt();
            int n = in.nextInt();
            int m = in.nextInt();
            Graph city = new Graph();

            for(int mi = 0; mi < m; mi++)
            {
                int a = in.nextInt();
                int b = in.nextInt();
                int c = in.nextInt();
                city.addEdge(a, b, c);
            }

            System.out.println(city.minSpanningTreeWeight()*p);
        }
    }
}
