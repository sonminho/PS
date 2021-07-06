package greedy;

import java.io.*;
import java.util.Arrays;

public class BOJ11399 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int MAX = 1000;
    static int[] p;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");
        p = new int[n];

        for(int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(in[i]);
        };

        Arrays.sort(p);

        ans += p[0];

        for(int i = 1; i < n; i++) {
            p[i] += p[i-1];
            ans += p[i];
        }

        bw.write(new Integer(ans).toString());
        bw.flush();
        bw.close();
    }
}
