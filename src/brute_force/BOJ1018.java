package brute_force;

import java.io.*;
import java.util.StringTokenizer;

/*
 * [브루트포스] 체스판 다시 칠하기
 * https://www.acmicpc.net/problem/1018
 */
public class BOJ1018 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static char[][] arr;
    static int n, m;
    static int cnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new char[n][m];

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < m; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < n - 7; i++) {
            for(int j = 0; j < m - 7; j++) {
                int tempCnt = counting(i, j,'B');
                int tempCnt2 = counting(i, j, 'W');
                cnt = Math.min(tempCnt, cnt);
                cnt = Math.min(tempCnt2, cnt);
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }

    static int counting(int x, int y, char val) {
        int rs = 0;
        int w = x % 2;
        int z = y % 2;

        for(int i = x; i < x + 8; i++) {
            for(int j = y; j < y + 8; j++) {
                if( i % 2 == w && j % 2 == z ) {
                    if( val != arr[i][j] )
                        rs++;
                }
                else {
                    if( val == arr[i][j] )
                        rs++;
                }
            }
            w++;
            z++;
            w %= 2;
            z %= 2;
        }

        return rs;
    }
}
