package graph.bfs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1261
[BFS]알고스팟 deque
*/
public class BOJ1261 {
    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayDeque<Pair> q = new ArrayDeque<Pair>();
    static int r, c;
    static char[][] arr;
    static int[][] dist;

    static void bfs() {
        while(!q.isEmpty()) {
            Pair now = q.poll();
            int cost = dist[now.x][now.y];

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx >= 0  && nx < r && ny >= 0 && ny < c) {
                    if(dist[nx][ny] == -1) {
                        if(arr[nx][ny] == '0') {
                            dist[nx][ny] = cost;
                            q.addFirst(new Pair(nx, ny));
                        } else {
                            dist[nx][ny] = cost + 1;
                            q.addLast(new Pair(nx, ny));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        c = Integer.parseInt(stk.nextToken());
        r = Integer.parseInt(stk.nextToken());

        arr = new char[r][c];
        dist = new int[r][c];

        for(int i = 0; i < r; i++) {
            StringBuilder row = new StringBuilder(br.readLine());

            for(int j = 0; j < c; j++) {
                arr[i][j] = row.charAt(j);
                dist[i][j] = -1;
            }
        }

        dist[0][0] = 0;
        q.addFirst(new Pair(0, 0));
        bfs();

        bw.write(dist[r-1][c-1] + "\n");
        bw.flush();
        bw.close();
    }
}
