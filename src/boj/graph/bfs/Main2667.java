package boj.graph.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main2667 {
    static BufferedReader br;
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] check;
    static List<Integer> ansList;
    static int cnt;

    static void dfs(int x, int y) {
        cnt++;

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 지도 범위 안에 들어오는지 확인
            if(nx >= 0 && nx < n && ny >=0 && ny < n) {
                // 방문하지 않았으면
                if(arr[nx][ny] == 1 && !check[nx][ny]) {
                    check[nx][ny] = true;
                    dfs(nx, ny);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
	    br = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(br.readLine());
	    arr = new int[n][n];
        check = new boolean[n][n];
        ansList = new ArrayList<>();

	    // 지도 입력
	    for(int i = 0; i < n; i++) {
	        StringTokenizer stk = new StringTokenizer(br.readLine());
	        String line = stk.nextToken();

	        for(int j = 0; j < n; j++) {
	            int x = line.charAt(j) - '0';
	            arr[i][j] = x;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] == 1 && !check[i][j]) {
                    cnt = 0;
                    check[i][j] = true;
                    dfs(i, j);
                    ansList.add(cnt);
                }
            }
        }

        ansList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if( o1 < o2) {
                    return -1;
                } else if( o1 == o2 ) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        System.out.println(ansList.size());

        for(Integer i : ansList) {
            System.out.println(i);
        }
    }
}
