package graph.bfs;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/1525
[BFS]퍼즐 1 2 3 4 5 6 7 8 9 순서로 만드는 최소횟수
*/
public class BOJ1525 {
    static final int n = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Map<String, Integer> map = new HashMap<>();
    static Queue<String> q = new LinkedList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Integer ans = null;

    static void bfs() throws IOException {
        while(!q.isEmpty()) {
            String nowStr = q.poll();

            if(map.containsKey("123456789")) {
                ans = map.get("123456789");
                break;
            }

            int pos = nowStr.indexOf("9");
            int x = pos / 3;
            int y = pos % 3;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 체크
                if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    StringBuilder nextStr = new StringBuilder(nowStr);
                    char tmp = nextStr.charAt(x * 3 + y);
                    nextStr.setCharAt(x * 3 + y, nextStr.charAt(nx * 3 + ny));
                    nextStr.setCharAt(nx * 3 + ny, tmp);

                    // 맵에 없으면 큐에 넣고 BFS 계속진행
                    if(!map.containsKey(nextStr.toString())) {
                        map.put(nextStr.toString(), map.get(nowStr) + 1);
                        q.add(nextStr.toString());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder startStr = new StringBuilder();

        for(int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                String x = stk.nextToken();

                if("0".equals(x)) {
                    x = "9";
                }

                startStr.append(x);
            }
        }
        map.put(startStr.toString(), 0);
        q.add(startStr.toString());
        bfs();

        if(ans == null) {
            bw.write(-1+"\n");
        } else {
            bw.write(ans.toString()+"\n");
        }
        bw.flush();
        bw.close();
    }
}
