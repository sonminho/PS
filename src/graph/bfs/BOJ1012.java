package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * [BFS] 유기농 배추
 * https://www.acmicpc.net/problem/1012
 */
public class BOJ1012 {
    static class Pair{
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer stk;
    static int n, m, k;
    static int[][] arr;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visit;
    static LinkedList<Pair> q;
    static int minCount;


    public static void main(String[] args) throws IOException {
        stk = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(stk.nextToken());

        while(tc-- > 0) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());
            k = Integer.parseInt(stk.nextToken());

            arr = new int[n][m];
            visit = new boolean[n][m];
            q = new LinkedList<>();
            minCount = 0;

            // 배추 입력
            for(int i = 0; i < k; i++) {
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());
                arr[x][y] = 1;
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(arr[i][j] == 1 && !visit[i][j]) {
                        visit[i][j] = true;
                        q.push(new Pair(i, j));
                        minCount++;
                        visit();
                    }
                }
            }
            bw.write(String.valueOf(minCount));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static void visit() {
        while(!q.isEmpty()) {
            Pair pair = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if( nx >= 0 && nx < n && ny >= 0 && ny < m ) {
                    if(!visit[nx][ny] && arr[nx][ny] == 1) { // 방문 안했으면
                        visit[nx][ny] = true;
                        q.push(new Pair(nx, ny));
                    }
                }
            }
        }
    }
}
