n = int(input())

arr = [list(map(int, input().split())) for i in range(n)]
arr2 = []
min_v = float('inf')

def func(level, start):
    global min_v
    if level == n//2:
        v = 0
        arr3 = []
        for i in range(n):
            if i not in arr2:
                arr3.append(i)
        for i in range(len(arr2)-1):
            for j in range(i+1, len(arr2)):
                v += arr[arr2[i]][arr2[j]] + arr[arr2[j]][arr2[i]]

        for i in range(len(arr2)-1):
            for j in range(i+1, len(arr2)):
                v -= arr[arr3[i]][arr3[j]] + arr[arr3[j]][arr3[i]]
        min_v = min(min_v, abs(v))
        return

    for i in range(start, n):
        arr2.append(i)
        func(level+1, i+1)
        arr2.pop()

func(0, 0)
print(min_v)

