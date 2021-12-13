package recursion;

import java.io.*;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/2630
[RECURSION]색종이 만들기
*/
public class BOJ2630 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[][] arr;
    private static int[] ans = {0,0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());  // 2^n

        arr = new int[n][n]; // n*n map

        for(int i = 0; i < n; i++) {
            String row = br.readLine();
            StringTokenizer stk = new StringTokenizer(row, " ");
            int j = 0;
            while(stk.hasMoreTokens()) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
                j++;
            }
        }

        if(check(0, 0, n)) {
            if(arr[0][0] == 0) ans[0] += 1;
            else ans[1] += 1;
        } else {
            divide(0, 0, n);
        }

        bw.write(ans[0] + "\n");
        bw.write(ans[1] + "\n");
        bw.flush();
    }

    static void divide(int x, int y, int size) {
        if(size%2 != 0) return;

        int halfSize = size / 2;

        if(check(x, y, halfSize)) {
            if(arr[x][y] == 0) ans[0]++;
            else ans[1]++;
        } else {
            divide(x, y, halfSize);
        }

        if(check(x, y + halfSize, halfSize)) {
            if(arr[x][y + halfSize] == 0) ans[0]++;
            else ans[1]++;
        } else {
            divide(x, y + halfSize, halfSize);
        }

        if(check(x + halfSize, y, halfSize)) {
            if(arr[x + halfSize][y] == 0) ans[0]++;
            else ans[1]++;
        } else {
            divide(x + halfSize, y, halfSize);
        }

        if(check(x + halfSize, y + halfSize, halfSize)) {
            if(arr[x + halfSize][y + halfSize] == 0) ans[0]++;
            else ans[1]++;
        } else {
            divide(x + halfSize, y + halfSize, halfSize);
        }
    }

    static boolean check(int x, int y, int size) {
        int val = arr[x][y];
        boolean result = true;
        int nx = x + size;
        int ny = y + size;

        for(int i = x; i < nx; i++) {
            for(int j = y; j < ny; j++) {
                if(arr[i][j] != val) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
