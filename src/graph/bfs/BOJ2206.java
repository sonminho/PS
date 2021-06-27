package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
https://www.acmicpc.net/problem/2206
[BFS] 벽 부수고 이동하기
2차원 맵에서 벽을 최대 한번만 깨서 (N,M)으로 최소비용 구하기
*/
public class BOJ2206 {
    static class Pair {
        int x, y, z;
        Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[][] a;
    static int[][][] d;
    static Queue<Pair> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs() {
        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx > 0 && nx <= n && ny > 0 && ny <= m) {
                    if(a[nx][ny] == 0 && d[nx][ny][cur.z] == 0) {
                        d[nx][ny][cur.z] = d[cur.x][cur.y][cur.z] + 1;
                        q.add(new Pair(nx, ny, cur.z));
                    }

                    if(cur.z == 0 && d[nx][ny][1] == 0) {
                        d[nx][ny][1] = d[cur.x][cur.y][cur.z] + 1;
                        q.add(new Pair(nx, ny, 1)); // 벽을 한번 부숨
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");

        n = Integer.parseInt(in[0]);
        m = Integer.parseInt(in[1]);
        a = new int[n+1][m+1];
        d = new int[n+1][m+1][2];

        for(int i = 1; i <= n; i++) {
            StringBuilder row = new StringBuilder(br.readLine());

            for(int j = 1; j <= m; j++) {
                a[i][j] = row.charAt(j-1) - '0';
            }
        }

        d[1][1][0] = 1;
        q.add(new Pair(1,1,0));

        bfs();

        if(d[n][m][0] > 0 && d[n][m][1] > 0) {
            bw.write(Math.min(d[n][m][0], d[n][m][1])+ "\n");
        } else if(d[n][m][0] > 0){
            bw.write(d[n][m][0] + "\n");
        } else if(d[n][m][1] > 0) {
            bw.write(d[n][m][1] + "\n");
        } else {
            bw.write(-1 + "\n");
        }

        bw.flush();
        bw.close();
    }

}