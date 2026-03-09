t = int(input())
 
for tc in range(1, t+1):
    n, p = map(int, input().split())
 
    A_biryo = list(map(int, input().split()))
    B_biryo = list(map(int, input().split()))
 
    plant = [[0]*2 for i in range(n)]
 
    plant[0][0] = A_biryo[0]
    plant[0][1] = B_biryo[0]
 
    for i in range(1, n):
        plant[i][0] = max(plant[i-1][0] + A_biryo[i] - p, plant[i-1][1] + A_biryo[i])
        plant[i][1] = max(plant[i-1][0] + B_biryo[i], plant[i-1][1] + B_biryo[i] - p)
 
    print(f'#{tc} {max(plant[n-1][0], plant[n-1][1])}')