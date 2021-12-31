package graph.bfs;

import java.io.*;
import java.util.*;

/*
 * [BFS] 촌수계산  
 * https://www.acmicpc.net/problem/264
 */
public class BOJ2644 {
    static class Node {
        int from, to, dist;
        Node(int from , int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    static StringTokenizer stk = null;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static boolean[] visited = null;
    static List<Integer>[] list;
    static List<Node> q = new LinkedList<>();
    static List<Integer> ansList = new ArrayList<>();
    static int from, to, ans;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) list[i] = new ArrayList<>();
        stk = new StringTokenizer(br.readLine(), " ");
        from = Integer.parseInt(stk.nextToken());
        to = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(br.readLine());

        for(int i = 1; i <= m; i++) {
            stk = new StringTokenizer(br.readLine(),  " ");
            int x = Integer.parseInt(stk.nextToken());
            int y =  Integer.parseInt(stk.nextToken());

            // 양방향 인접리스트
            list[x].add(y);
            list[y].add(x);
        }

        q.add(new Node(from, to, 1));
        visited[from] = true;
        ans = 0;
        bfs(q);

        if(ansList.size() == 0) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(ansList.get(0)));
        }
        bw.flush();
        bw.close();
    }

    static void bfs(List<Node> q) {
        while(!q.isEmpty()) {
            Node nowNode = ((LinkedList<Node>)q).poll();
            int from = nowNode.from;

            for(int next : list[from]) {
                if(!visited[next]) {
                    if(next == nowNode.to) {
                        ansList.add(nowNode.dist);
                    }

                    visited[next] = true;
                    q.add(new Node(next, nowNode.to, nowNode.dist + 1));
                }
            }
        }
    }
}
