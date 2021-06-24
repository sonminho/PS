package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
https://www.acmicpc.net/problem/13549
[BFS] 숨바꼭질3
*/
public class BOJ13549 {
    static final int MAX = 100000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Queue<Integer> q = new LinkedList<>();
    static Queue<Integer> nq = new LinkedList<>();
    static boolean[] check = new boolean[MAX+1];
    static int[] dist = new int[MAX+1];

    static void bfs() {
        while(!q.isEmpty()) {
            int now = q.poll();

            int[] nextArr = {now*2, now-1, now+1};

            for(int next : nextArr) {
                if(next >= 0 && next <= MAX) {
                    if(!check[next]) {
                        check[next] = true;

                        if(now * 2 == next) {
                            dist[next] = dist[now];
                            q.add(next);
                        } else {
                            dist[next] = dist[now] + 1;
                            nq.add(next);
                        }
                    }
                }

                if(q.isEmpty()) {
                    q = nq;
                    nq = new LinkedList<>();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");

        int n = Integer.parseInt(in[0]);
        int m = Integer.parseInt(in[1]);

        dist[n] = 0;
        check[n] = true;
        q.add(n);

        bfs();

        bw.write(dist[m] + "\n");
        bw.flush();;
        bw.close();
    }

}
