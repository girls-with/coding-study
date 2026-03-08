n = int(input())

arr = [list(map(int, input().split())) for i in range(n)]

arr2 = []
min_v = float('inf')
visited = [False] * n 

def func(level):
    global min_v
    
    if level == n:
        v = 0
        idx = True
        for i in range(n-1):
            if arr[arr2[i]-1][arr2[i+1]-1] == 0:
                idx = False
            v += arr[arr2[i]-1][arr2[i+1]-1]
        if idx:
            return_cost = arr[arr2[-1]-1][arr2[0]-1]
            if return_cost != 0:
                v += return_cost
                min_v = min(min_v, v)
        return
    
    for j in range(1, n+1):
        if not visited[j-1]:
            visited[j-1] = True
            arr2.append(j)
            func(level+1)
            arr2.pop()
            visited[j-1] = False 

func(0)
print(min_v)