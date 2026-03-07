package kyungrin.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 실행 시간: 104ms
 * 
 * 암호의 후보들은 전부 순차적 증가하는 형태, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.
 * 	=> 같은 구성이더라도 순서는 동일하게 가져간다. 조합 힌트.
 * 
 * 모음과 자음 구별
 * 주어지는 데이터는 알파벳 뿐이기 때문에 모음이 아니면 자음.
 * **/
public class BOJ_1759 {
	  static int L, C; // 뽑는 수, 주어진 수
	  static char[] alpha;
	  //
	  static ArrayList<Character> mo = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
	  static char[] password;
	  //
	  static StringTokenizer st;
	  static StringBuilder sb;
	  public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    st = new StringTokenizer(br.readLine());
	    L = Integer.parseInt(st.nextToken());
	    C = Integer.parseInt(st.nextToken());
	    alpha = new char[C];
	    st = new StringTokenizer(br.readLine());
	    for(int i =0; i < C; i++){
	      alpha[i] = st.nextToken().charAt(0);
	    }
	    Arrays.sort(alpha); // 미리 정렬, 만들어지는 조합의 형태는 작은 알파벳부터가 된다.
	    password = new char[L];
	    
	    sb = new StringBuilder();
	    //
	    combination(0, 0);
	    //    
	    System.out.print(sb);
	}
	  
	private static void combination(int idx, int start) {
		if(idx == L) {
			int mcnt = 0;
			int jcnt = 0;
			for(char p : password) {
				if(mo.contains(p)) {
					mcnt++;
				} else {
					jcnt++;
				}
			}
			if(mcnt < 1 || jcnt < 2) return;
			
			for(char p : password) {
				sb.append(p);
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i < C; i++) {
			password[idx] = alpha[i];
			combination(idx+1, i+1);
		}
	}
}
