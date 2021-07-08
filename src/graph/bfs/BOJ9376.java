package graph.bfs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

/*
https://www.acmicpc.net/problem/9376
[BFS 탈출]
*/
public class BOJ9376 {
    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int h, w;
    static String[] arr;
    static int[][] d0, d1, d2;
    static Pair[] prisoners;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static void bfs(int[][] a, int x, int y) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        for(int i = 0; i <= h+1; i++) {
            Arrays.fill(a[i], -1);
        }
        a[x][y] = 0;
        q.add(new Pair(x, y));

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 0 && nx <= h+1 && ny >= 0 && ny <= w+1) {
                    if(a[nx][ny] != -1) continue; // 방문
                    char ch = arr[nx].charAt(ny);
                    if(ch == '*') continue; // 벽
                    if(ch == '#') {
                        q.addLast(new Pair(nx, ny));
                        a[nx][ny] = a[cur.x][cur.y] + 1;
                    }
                    if(ch == '.' || ch == '$') {
                        q.addFirst(new Pair(nx, ny));
                        a[nx][ny] = a[cur.x][cur.y];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0 ) {
            String[] in = br.readLine().split(" ");

            h = Integer.parseInt(in[0]);
            w = Integer.parseInt(in[1]);

            arr = new String[h+2];
            prisoners = new Pair[2];
            int pCnt = 0;

            for(int i = 1; i <= h; i++) {
                StringBuilder line = new StringBuilder();
                line.append(".");
                String tmp = br.readLine();
                for(int j = 1; j <= w; j++) {
                    if(tmp.charAt(j-1) =='$') { // 수감자
                        prisoners[pCnt++] = new Pair(i, j);
                    }
                }
                line.append(tmp);
                line.append(".");
                arr[i] = line.toString();
            }

            StringBuilder tmp = new StringBuilder();

            for(int j = 0; j <= w+1; j++) {
                tmp.append(".");
            }

            arr[0] = tmp.toString();
            arr[h+1] = tmp.toString();

            d0 = new int[h+2][w+2];
            d1 = new int[h+2][w+2];
            d2 = new int[h+2][w+2];

            bfs(d0, 0, 0);
            bfs(d1, prisoners[0].x, prisoners[0].y);
            bfs(d2, prisoners[1].x, prisoners[1].y);

            int ans = h*w;

            for(int i = 1; i <= h; i++) {
                for(int j = 1; j <= w; j++) {
                    char ch = arr[i].charAt(j);
                    if(ch == '*') continue;
                    if(d0[i][j] == -1 || d1[i][j] == -1 || d2[i][j] == -1) continue;
                    int sum = d0[i][j] + d1[i][j] + d2[i][j];
                    if(ch == '#') {
                        sum -= 2;
                    }
                    ans = Math.min(sum, ans);
                }
            }
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
    }
}
