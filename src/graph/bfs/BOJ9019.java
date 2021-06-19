package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ9019 {
    static final int MAX = 10000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder ans;
    static int a, b;

    static Queue<Integer> q;
    static int[] dist;
    static int[] from;
    static char[] how;
    static boolean[] check;

    static void bfs() {
        while(!q.isEmpty()) {
            int x = q.poll();

            if(x == b) {
                print(a, b);
            }

            int d = x * 2;
            int s = x - 1;
            int l = (x % 1000) * 10 + (x / 1000);
            int r = (x % 10) * 1000 + x / 10;

            if(d >= MAX) d %= MAX;
            if(s < 0) s = MAX - 1;

            if(!check[d]) {
                check[d] = true;
                dist[d] = dist[x] + 1;
                from[d] = x;
                how[d] = 'D';
                q.add(d);
            }
            if(!check[s]) {
                check[s] = true;
                dist[s] = dist[x] + 1;
                from[s] = x;
                how[s] = 'S';
                q.add(s);
            }
            if(!check[l]) {
                check[l] = true;
                dist[l] = dist[x] + 1;
                from[l] = x;
                how[l] = 'L';
                q.add(l);
            }
            if(!check[r]) {
                check[r] = true;
                dist[r] = dist[x] + 1;
                from[r] = x;
                how[r] = 'R';
                q.add(r);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");

            a = Integer.parseInt(in[0]);
            b = Integer.parseInt(in[1]);

            dist = new int[MAX];
            from = new int[MAX];
            how = new char[MAX];
            check = new boolean[MAX];

            q = new LinkedList<>();
            q.add(a);
            check[a] = true;
            from[a] = -1;
            ans = new StringBuilder();
            bfs();

            bw.write(ans.reverse().toString());
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void print(int a, int b) {
        if(a != b) {
            ans.append(how[b]);
            print(a, from[b]);
        }
    }
}
