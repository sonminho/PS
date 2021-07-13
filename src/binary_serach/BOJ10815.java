package binary_serach;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/10815
[이분탐색]숫자카드
*/
public class BOJ10815 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;

    static int bSearch(int x, int left, int right) {
        int mid;
        int t = 0;

        while(left <= right) {
            mid = (left+right)/2;

            if(arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid-1;
                t = mid;
            }
        }

        if(arr[t] == x) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(arr);

        m = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < m; i++) {
            bw.write(bSearch(Integer.parseInt(stk.nextToken()), 0, n-1) + " ");
        }

        bw.flush();
        bw.close();
    }
}
