package graph.bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://www.acmicpc.net/problem/14226
이모티콘(BFS)
S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값을 구하는 프로그램
*/
public class BOJ14226 {
    static class Pair {
        int s, c;

        Pair(int s, int c) {
            this.s = s;
            this.c = c;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int x;
    static int[][] d;
    static Queue<Pair> q = new LinkedList<>();

    static void bfs() throws IOException {
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            int s = cur.s;
            int c = cur.c;

            if ( s == x ) {
                bw.write(d[s][c]+"\n");
                break;
            }

            // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            if( d[s][s] == -1 ) {
                d[s][s] = d[s][c] + 1;
                q.add(new Pair(s, s));
            }
            // 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            if( s+c <= 1000 && d[s+c][c] == -1 ) {
                d[s+c][c] = d[s][c] + 1;
                q.add(new Pair(s+c, c));
            }
            // 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
            if( s-1 >= 0 && d[s-1][c] == -1 ) {
                d[s-1][c] = d[s][c] + 1;
                q.add(new Pair(s-1, c));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        x = Integer.parseInt(br.readLine());
        d = new int[1001][1001];

        for(int i = 0; i <= x; i++) {
            Arrays.fill(d[i], -1);
        }

        // 영선이는 이미 화면에 이모티콘 1개를 입력했다..
        // 초기클립보드는 비어있는상태(0초)
        d[1][0] = 0;
        q.add(new Pair(1, 0));
        bfs();
        bw.flush();
        bw.close();
    }
}
