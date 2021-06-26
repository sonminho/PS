package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
https://www.acmicpc.net/problem/12851
[BFS]숨바꼭질2
*/
public class BOJ12851 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, k;
    static final int MAX = 1000000;
    static boolean[] check = new boolean[MAX + 1];
    static int[] cnt = new int[MAX + 1];
    static int[] cost = new int[MAX + 1];
    static Queue<Integer> q = new LinkedList<>();

    static void bfs() {
        while(!q.isEmpty()) {
            int now = q.poll();
            int[] nextArr = {now-1, now+1, now*2};

            for(int next : nextArr) {
                if(next >= 0 && next <= MAX) {
                    if(!check[next]) { // 첫방문
                        check[next] = true;
                        cnt[next] = cnt[now]; // 이동 경우의수 변동없음
                        cost[next]= cost[now] + 1;
                        q.add(next);
                    } else if(cost[next] == cost[now]+1) { // 최소 비용으로 방문할수 있는지
                        cnt[next] += cnt[now]; // 현재 지점의 이동경우의 수 추가
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");

        n = Integer.parseInt(in[0]);
        k = Integer.parseInt(in[1]);

        check[n] = true;
        cnt[n] = 1;
        q.add(n);

        bfs();

        bw.write(cost[k] + "\n");
        bw.write(cnt[k] + "\n");
        bw.flush();
        bw.close();
    }
}
