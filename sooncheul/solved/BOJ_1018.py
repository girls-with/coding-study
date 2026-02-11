import sys
sys.stdin = open('input.txt', 'r')

'''
ai 사용 했다.
난 아직도 밑에 있는 놈이 돌아가지 않는 이유를 파악하지 못하겠다ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ
난 빡대가리야.... ㅎ.....
비난 맞을 만 했네 문제 꼬라지가

다시 칠해야하는 정사각형의 최소 개수를 구하는 프로그램
순회하면서, 2가지 경우를 구함 첫 줄에 W인 경우, 첫 줄에 B인 경우
다시 칠할때마다! 카운트를 해서 카운트가 제일 낮은 값을 출력
'''

N, M = map(int, input().split())
a = [list(input().strip()) for _ in range(N)]

cnt_1 = [[0]*M for _ in range(N)]  # W 시작일 때 다시칠해야 하면 1
cnt_2 = [[0]*M for _ in range(N)]  # B 시작일 때 다시칠해야 하면 1

for i in range(N):
    for j in range(M):
        if (i + j) % 2 == 0:
            if a[i][j] == 'W':
                cnt_1[i][j] = 1
            if a[i][j] == 'B':
                cnt_2[i][j] = 1
        else:
            if a[i][j] == 'B':
                cnt_1[i][j] = 1
            if a[i][j] == 'W':
                cnt_2[i][j] = 1

min_v = N * M
for x in range(N - 7):
    for y in range(M - 7):
        v1 = v2 = 0
        for i in range(8):
            for j in range(8):
                v1 += cnt_1[x+i][y+j]
                v2 += cnt_2[x+i][y+j]
        min_v = min(min_v, v1, v2)

print(min_v)

# N, M = map(int, input().split())
# matrix_origin = [list(input()) for _ in range(N)]    # 가변이 용이한 리스트로 받음
#
#
# cnt_1 = [[0] * M for _ in range(N)]
# cnt_2 = [[0] * M for _ in range(N)]
# min_v = N * M
#
#
# for tc in range(2):
#     matrix = [[0] * M for _ in range(N)]
#     for idx in range(N):
#         for jdx in range(M):
#             matrix[idx][jdx] = matrix_origin[idx][jdx]
#
#     if tc == 1:
#
#         if matrix[0][0] == "W":
#             matrix[0][0] = "B"
#             cnt_2[0][0] = 1
#
#         elif matrix[0][0] =="B":
#             matrix[0][0] = "W"
#             cnt_2[0][0] = 1
#
#     for i in range(N):    # 세로부터 싹 바꾸고,
#         if i != 0 and matrix[i][0] == matrix[i-1][0]:
#             if matrix[i-1][0] == "W":
#                 matrix[i][0] = "B"
#                 if tc == 0:
#                     cnt_1[i][0]= 1
#                 if tc == 1:
#                     cnt_2[i][0] = 1
#
#             if matrix[i-1][0] == "B":
#                 matrix[i][0] = "W"
#                 if tc == 0:
#                     cnt_1[i][0] = 1
#                 if tc == 1:
#                     cnt_2[i][0] = 1
#         else:
#             pass
#
#         for j in range(1, M):   # 가로를 바꾸고
#             if matrix[i][j] == matrix[i][j-1]:
#                 if matrix[i][j-1] == "W":
#                     matrix[i][j] = "B"
#                     if tc == 0:
#                         cnt_1[i][j] = 1
#                     if tc == 1:
#                         cnt_2[i][j] = 1
#
#                 if matrix[i][j-1] == "B":
#                     matrix[i][j] = "W"
#                     if tc == 0:
#                         cnt_1[i][j] = 1
#                     if tc == 1:
#                         cnt_2[i][j] = 1
#             else:
#                 pass
#
#     for idx in range(N - 7):
#         for jdx in range(M - 7):
#             v1 = 0
#             v2 = 0
#             for di in range(8):
#                 for dj in range(8):
#                     v1 += cnt_1[idx + di][jdx + dj]
#                     v2 += cnt_2[idx + di][jdx + dj]
#
#             min_v = min(v1, v2)
#
#
# print(min_v)
#
