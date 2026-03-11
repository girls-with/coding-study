# 스위치 8개의 상태가 표시되어  있음
# 스위치 ON = 1, OFF = 0
# 학생 몇 명을 뽑아서 학생들에게 1 이상이고, 스위치 개수 이하인 자연수를 하나 씨 ㄱ나누어줌

# N: 스위치의 개수 
N = int(input())
# switch: 스위치의 상태
# on = 1, off = 0
switch = list(map(int, input().split()))
# people: 학생 수 
people = int(input())
# students: 성별과 받은 수를 나타내는 2차원 배열
# 남자 = 1, 여자 = 2
# 학생이 받은 수는 스위치 개수 이하인 양의 정수이다
students = [list(map(int, input().split())) for _ in range(N)]

# 남학생은 스위치 번호가 자기가 받은 수의 배수이면 그 스위치의 상태를 바꿈
# on -> off, off -> on으로
# 남학생 3 => 3, 6번 스위치 변경

# 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서
# 그 구간에 속한 스위치의 상태를 모두 바꾼다.
# 이때 구간에 속한 스위치 개수는 항상 홀수가 된다
# 여학생 3 => 3을 중심으로 2,4가 같고, 1,5가 같으면 5개 모두 변경
# 여학생 4 => 4를 중심으로 3,5가 다르므로 4만 변경

# 스위치 상태 바꾸는 함수 
def change(idx):
    switch[idx] = 1 - switch[idx]
    
for gender, num in students:
    # 남학생의 경우 
    if gender == 1:
        # num의 배수 번호 스위치 변경 
        for i in range(num - 1, N, num):
            change(i)
    
    # 여학생의 경우
    elif gender == 2:
        # 기준 스위치 먼저 변경
        center = num - 1
        change(center)

        # 좌우 대칭 확인하면서 확장
        left = center - 1
        right = center + 1
        # 받은 스위치 개수가 처음과 끝 즉, 1과 N개일때는 1과 N만 바뀜

        # 받은 스위치의 개수가 2~(N-1)개일때는 조건에 따라 변경하는 개수가 달라짐
        # 받은 개수 중심으로 좌우가 같으면, 좌우와 받은 개수의 번호 포함해서 모두 변경하고
        
        # 받은 개수 중심으로 좌우가 다르면, 받은 개수의 번호만 변경 

        while left >= 0 and right < N and switch[left] == switch[right]:
            change(left)
            change(right)
            left -= 1
            right += 1

# 출력: 한 줄에 20개씩
for i in range(N):
    print(switch[i], end=' ')
    if (i+1)%20 == 0:
        print()

