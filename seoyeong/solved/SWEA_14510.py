T = int(input())

for tc in range(1, T+1):
    n = int(input())
    arr = list(map(int, input().split()))
    day1, day2 = 0, 0
    max_h = max(arr)

    for i in range(n):
        v = max_h - arr[i]

        day2 += v // 2
        day1 += v % 2

    if day2 > day1:
        while abs(day2 - day1) > 1:
            day2 -= 1
            day1 += 2
    
    if day2 > day1:
        print(f'#{tc} {day2 * 2}')
    elif day2 == day1:
        print(f'#{tc} {day2 * 2}')
    else:
        print(f'#{tc} {day1 * 2 - 1}')