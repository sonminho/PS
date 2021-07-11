package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
https://www.acmicpc.net/problem/9328
[BFS] 열쇠
*/
public class BOJ9328 {
    static class Pair{
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static char[][] map;
    static boolean[][] check;
    static boolean[] key;

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            String[] in = br.readLine().split(" ");
            n = Integer.parseInt(in[0]);
            m = Integer.parseInt(in[1]);
            map = new char[n+2][m+2];
            check = new boolean[111][111];
            key = new boolean[111];

            for(int i = 1; i <= n; i++) {
                String tmp = br.readLine();
                for(int j = 1; j <= m; j++) {
                    map[i][j] = tmp.charAt(j-1);
                }
            }

            for(int i = 0; i <= n+1; i++) {
                map[i][0] = '.';
                map[i][m+1] = '.';
            }

            for(int j = 0; j <= m + 1; j++) {
                map[0][j] = '.';
                map[n+1][j] = '.';
            }

            String line = br.readLine();
            int length = line.length();
            if(!"0".equals(line)) {
                for(int i = 0; i < length; i++) {
                    char ch = line.charAt(i);
                    key[ch-'a'] = true;
                }
            }

            Queue<Pair> q = new LinkedList<>();
            Queue<Pair>[] door = new LinkedList[26];

            for(int i = 0; i < 26; i++) {
                door[i] = new LinkedList<>();
            }

            q.add(new Pair(0,0));
            check[0][0] = true;
            int ans = 0;
            while(!q.isEmpty()) {
                Pair cur = q.poll();

                for(int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || nx > n+1 || ny < 0 || ny > m+1) continue;
                    if(check[nx][ny]) continue;
                    char ch = map[nx][ny];

                    if(ch == '*') { // 벽
                        continue;
                    }
                    check[nx][ny] = true;

                    if(ch == '.') {
                        q.add(new Pair(nx, ny));
                    } else if(ch == '$') { // 문
                        ans++;
                        q.add(new Pair(nx, ny));
                    } else if(ch >= 'A' && ch <= 'Z') { // 열쇠가 있는지 확인
                        if(key[ch-'A']) { // 열쇠가 있으면
                            q.add(new Pair(nx, ny));
                        } else {
                            door[ch-'A'].add(new Pair(nx, ny));
                        }
                    } else if(ch >= 'a' && ch <= 'z') { // 열쇠 획득
                        q.add(new Pair(nx, ny));

                        if(!key[ch-'a']) { // 열쇠 얻은적 없으면
                            key[ch-'a'] = true;

                            while(!door[ch-'a'].isEmpty()) {
                                q.add(door[ch-'a'].poll());
                            }
                        }
                    }
                }
            }
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
    }
}
