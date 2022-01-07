package graph.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* [DFS, Backtracking] 암호 만들기
* https://www.acmicpc.net/problem/1759
* */
public class BOJ1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int l, c;
    static char[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        l = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        arr = new char[c];
        visited = new boolean[c];

        stk = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < c; i++) {
            arr[i] = stk.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(arr, 0, 0);
        bw.flush();
        bw.close();
    }

    static void dfs(char[] arr, int idx, int cnt) throws IOException {
        if(cnt == l) {
            int a = 0;
            int b = 0;
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < c; i++) {
                if(visited[i]) {
                    sb.append(arr[i]);
                    switch(arr[i]) {
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                            a++;
                            break;
                        default:
                            b++;
                            break;
                    }
                }
            }
            if(a >= 1 && b >= 2) {
                bw.write(sb.toString());
                bw.write("\n");
            }
        }

        for(int i = idx; i < c; i++) {
            visited[i] = true;
            dfs(arr, i + 1, cnt + 1);
            visited[i] = false;
        }
    }

}
