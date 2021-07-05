package graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
https://www.acmicpc.net/problem/3055
[BFS] 탈출
*/
public class BOJ3055 {
    static class Pair {
        int x, y;
        Pair(int x, int y ){
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int x, y;
    static int[][] water;
    static int[][] d;
    static char[][] map;
    static Queue<Pair> q;
    static int wx, wy; // 물의 시작위치
    static int sx, sy; // 비버의 굴 위치
    static int ex, ey; // 고슴도치 위치
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0 ,0};

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        x = Integer.parseInt(in[0]);
        y = Integer.parseInt(in[1]);

        water = new int[x][y];
        map = new char[x][y];
        d = new int[x][y];

        q = new LinkedList<>();
        for(int i = 0; i < x; i++) {
            String line = br.readLine();

            for(int j = 0; j < y; j++) {
                char ch = line.charAt(j);
                map[i][j] = ch;
                water[i][j] = -1;
                d[i][j] = -1;

                switch(ch) {
                    case '*':
                        wx = i;
                        wy = j;

                        water[wx][wy] = 0;
                        q.add(new Pair(wx, wy));

                        break;
                    case 'S':
                        sx = i;
                        sy = j;
                        break;
                    case 'D':
                        ex = i;
                        ey = j;
                        break;
                    default:
                        break;
                }
            }
        }

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 0 && nx < x && ny >= 0 && ny < y) {
                    if(water[nx][ny] == -1 && map[nx][ny] != 'X' && map[nx][ny] != 'D') {
                        water[nx][ny] = water[cur.x][cur.y] + 1;
                        q.add(new Pair(nx, ny));
                    }
                }
            };
        }

        q.add(new Pair(sx, sy));
        d[sx][sy] = 0;

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx >= 0 && nx < x && ny >= 0 && ny < y) {
                    if (d[nx][ny] != -1) continue;
                    if (map[nx][ny] == 'X') continue; // 돌
                    if (water[nx][ny] != -1 && d[cur.x][cur.y]+1 >= water[nx][ny]) continue;

                    d[nx][ny] = d[cur.x][cur.y] + 1;
                    q.offer(new Pair(nx, ny));
                }
            }
        }

        if(d[ex][ey] == -1) {
            bw.write("KAKTUS\n");
        } else {
            bw.write(d[ex][ey]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
