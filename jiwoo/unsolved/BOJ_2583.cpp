#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int M, N, K;
int grid[100][100];
bool visited[100][100];
vector<int> res;

int dr[] = { -1, 1, 0, 0 };
int dc[] = { 0, 0, -1, 1 };

void fill(int sc, int sr, int ec, int er) {
	for (int r = sr; r < er; r++) {
		for (int c = sc; c < ec; c++) {
			visited[r][c] = true;
		}
	}
}

void bfs(int r, int c) {
	queue<pair<int, int>> q;  // AI 활용: 좌표 저장하기 위한 queue 선언 방법
	q.push({ r, c });
	visited[r][c] = true;
	int cnt = 1;

	while (!q.empty()) {
		int curR = q.front().first;  // AI 활용: queue에서 pair<int, int> 꺼내는 방법
		int curC = q.front().second;
		q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int nr = curR + dr[dir];
			int nc = curC + dc[dir];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if (visited[nr][nc]) continue;

			q.push({ nr, nc });
			visited[nr][nc] = true;
			cnt++;
		}
	}

	res.push_back(cnt);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> M >> N >> K;

	for (int i = 0; i < K; i++) {
		int sc, sr, ec, er;
		cin >> sc >> sr >> ec >> er;

		fill(sc, sr, ec, er);
	}

	int cnt = 0;
	for (int r = 0; r < N; r++) {
		for (int c = 0; c < M; c++) {
			if (visited[r][c]) continue;

			bfs(r, c);
			cnt++;
		}
	}

	cout << cnt << '\n';
	for (int i = 0; i < res.size(); i++) {
		cout << res[i] << ' ';
	}
}