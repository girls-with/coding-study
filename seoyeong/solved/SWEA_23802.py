t = int(input())

for tc in range(1, t+1):
    n, p = map(int, input().split())

    A_biryo = list(map(int, input().split()))
    B_biryo = list(map(int, input().split()))

    plant = [[0, 0] for i in range(n)] #화분의 성장을 기록할 리스트
    # n*2형태의 리스트. 
    plant[0][0] = A_biryo[0] #0열은 A비료
    plant[0][1] = B_biryo[0] #1열은 B비료

    for i in range(1, n):
        plant[i][0] = max(plant[i-1][0] + A_biryo[i] - p, plant[i-1][1] + A_biryo[i])
        plant[i][1] = max(plant[i-1][0] + B_biryo[i], plant[i-1][1] + B_biryo[i] - p)
        #A비료를 줄 때와 B비료를 줄 때를 각각 기록
        #만약 p만큼 덜 자라는 것을 감수하고도 똑같은 비료를 주는 것이 좋은지, 아니면 다른 비료를 주는 것이 좋은지 비교
    result = max(plant[-1][0], plant[-1][1]) #최댓값 기록

    print(f'#{tc} {result}')