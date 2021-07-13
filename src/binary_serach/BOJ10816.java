package binary_serach;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/10816
[이분탐색]숫자카드2
lowwer bound, upper bound
*/
public class BOJ10816 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr;

    static int lower_bound(int x) {
        int ans = -1;
        int left = 0;
        int right = arr.length-1;

        while(left <= right) {
            int mid = (left+right) / 2;

            if(arr[mid] == x) {
                ans = mid;
                right = mid - 1;
            } else if(arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    static int upper_bound(int x) {
        int ans = -1;
        int left = 0;
        int right = arr.length-1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] == x) {
                ans = mid;
                left = mid + 1;
            } else if(arr[mid]  < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
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
            int x = Integer.parseInt(stk.nextToken());
            int l = lower_bound(x);
            int r = upper_bound(x);

            if(l == -1) { // 정답없음!
                bw.write(0 + " ");
            } else {
                bw.write((r-l+1) +  " ");
            }
        }

        bw.flush();
        bw.close();
    }
}
