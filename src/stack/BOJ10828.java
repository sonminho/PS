package stack;

import java.io.*;
import java.util.Stack;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

public class BOJ10828 {
    static int n;
    static BufferedReader br;
    static BufferedWriter bw;
    static Stack<Integer> stack;
    static StringBuilder ans;

    public static void main(String[] args) throws IOException, EmptyStackException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ans = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            String cmd = stk.nextToken();

            switch(cmd) {
                case "push":
                    int x = Integer.parseInt(stk.nextToken());
                    stack.push(x);
                    break;
                case "top":
                    if(!stack.isEmpty()) ans.append(stack.peek()).append("\n");
                    else ans.append(-1).append("\n");
                    break;
                case "size":
                    ans.append(stack.size()).append("\n");
                    break;
                case "pop":
                    if(!stack.isEmpty()) ans.append(stack.pop()).append("\n");
                    else ans.append(-1).append("\n");
                    break;
                case "empty":
                    if (stack.isEmpty()) ans.append(1);
                    else ans.append(0);
                    ans.append("\n");
                    break;
            }
        }

        bw.write(ans.toString());
        bw.flush();
        bw.close();
    }
}
