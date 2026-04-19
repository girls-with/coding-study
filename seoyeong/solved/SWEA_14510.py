T = int(input())

for tc in range(1, T+1):
    n = int(input())
    arr = list(map(int, input().split()))
    day1, day2 = 0, 0 #소요될 첫날, 둘째날을 저장할 변수
    max_h = max(arr)

    for i in range(n):
        v = max_h - arr[i]

        day2 += v // 2 #day2에 최대로 가질 수 있는 짝수날 수 누적
        day1 += v % 2 #day1에 최소로 가질 수 있는 홀수날 수 누적

    if day2 > day1: #만약 day2가 day1보다 크다면
        while abs(day2 - day1) > 1: #둘의 절댓값이 1이하가 될 때까지
            day2 -= 1 #day2는 1씩 빼고
            day1 += 2 #day1에 2씩 더하기
    
    if day2 > day1: #만약 day2 + 1 == day1이라면
        print(f'#{tc} {day2 * 2}') #day2가 마지막으로 끝나므로 day2 * 2출력
    elif day2 == day1: #만약 둘이 같다면?
        print(f'#{tc} {day2 * 2}') #day2가 마지막으로 끝나므로 day2 * 2출력
    else: #만약 day1 + 1 == day2라면
        print(f'#{tc} {day1 * 2 - 1}') #day1이 마지막으로 끝나므로(홀수날로 끝나므로) day1 * 2 - 1출력