package graph.bfs;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ1697 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int n, k;
    static Queue<Integer> q;
    static boolean[] check = new boolean[100001];
    static int[] dist = new int[100001];
    static int[] from = new int[100001];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        q = new LinkedList<>();

        q.add(n);
        check[n] = true;

        while(!q.isEmpty()) {
            int x = q.poll();
            int x1 = x-1;
            int x2 = x+1;
            int x3 = x*2;

            if(x1 >= 0 && x1 <= 100000 && !check[x1]) {
                q.add(x1);
                check[x1] = true;
                dist[x1] = dist[x] + 1;
                from[x1] = x;
            }

            if(x2 >= 0 && x2 <= 100000 && !check[x2]) {
                q.add(x2);
                check[x2] = true;
                dist[x2] = dist[x] + 1;
                from[x2] = x;
            }

            if(x3 >= 0 && x3 <= 100000 && !check[x3]) {
                q.add(x3);
                check[x3] = true;
                dist[x3] = dist[x] + 1;
                from[x3] = x;
            }
        }

        bw.write(dist[k]+"\n");

        print(n, k);

        bw.flush();
        bw.close();

    }

    static void print(int n, int k) throws IOException {
        if(n != k) {
            print(n, from[k]);
        }
        bw.write(k+" ");
    }
}
