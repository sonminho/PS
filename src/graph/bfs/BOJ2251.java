package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2251 {
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Queue<Pair> q;
    static int[] from = {0, 0, 1, 1, 2, 2};
    static int[] to = {1, 2, 0, 2, 0, 1};
    static boolean[][] check = new boolean[201][201];
    static boolean[] ans = new boolean[201];
    static int[] cap = new int[3];

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");

        cap[0] = Integer.parseInt(in[0]);
        cap[1] = Integer.parseInt(in[1]);
        cap[2] = Integer.parseInt(in[2]);
        int sum = cap[2]; // 물의 총합

        q = new LinkedList<>();
        check[0][0] = true;
        ans[cap[2]] = true;
        q.add(new Pair(0, 0));

        while(!q.isEmpty()) {
            Pair p = q.poll();
            int[] now = {p.x, p.y, sum - p.x - p.y};

            for(int i = 0; i < 6; i++) {
                int[] next = {now[0], now[1], now[2]};
                next[to[i]] += next[from[i]];
                next[from[i]] = 0;

                // 다른 한 물통이 가득차면
                if(next[to[i]] >= cap[to[i]]) {
                    next[from[i]] = next[to[i]] - cap[to[i]];
                    next[to[i]] = cap[to[i]];
                }

                if(!check[next[0]][next[1]]) {
                    check[next[0]][next[1]] = true;
                    q.add(new Pair(next[0], next[1]));
                    if(next[0] == 0) {
                        ans[next[2]] = true;
                    }
                }
            }
        }

        for(int i = 0; i <= cap[2]; i++) {
            if(ans[i]) {
                bw.write(i + " ");
            }
        }

        bw.write("\n");
        bw.flush();
        bw.close();
    }

}
