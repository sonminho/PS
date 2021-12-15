package  recursion;

import java.io.*;

/*
https://www.acmicpc.net/problem/1992
[RECURSION]쿼드트리
*/
public class BOJ1992 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringBuilder sb;

    private static int n;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for(int i = 0; i < n; i++) {
            String row = br.readLine();

            for(int j = 0; j < n; j++) {
                arr[i][j] = row.charAt(j) - '0';
            }
        }

        if(check(0, 0, n)) {
            sb.append(arr[0][0]);
        } else {
            divide(0, 0, n);
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void divide(int x, int y, int size) throws IOException {
        int halfSize = size / 2;

        sb.append("(");

        if(check(x, y, halfSize)) {
            sb.append(arr[x][y]);
        } else {
            divide(x, y, halfSize);
        }

        if(check(x, y + halfSize, halfSize)) {
            sb.append(arr[x][y + halfSize]);
        } else {
            divide(x, y + halfSize, halfSize);
        }

        if(check(x + halfSize, y, halfSize)) {
            sb.append(arr[x + halfSize][y]);
        } else {
            divide(x + halfSize, y, halfSize);
        }

        if(check(x + halfSize, y + halfSize, halfSize)) {
            sb.append(arr[x + halfSize][y + halfSize]);
        } else {
            divide(x + halfSize, y + halfSize, halfSize);
        }

        sb.append(")");
    }

    static boolean check(int x, int y, int size) {
        int val = arr[x][y];

        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(arr[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }
}
