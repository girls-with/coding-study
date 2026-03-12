n = int(input())

arr = []
count = 0

for i in range(n):
    txt = input()
    arr.append(txt)

arr.sort()

for i in range(n):
    if i == n-1:
        count += 1

    elif len(arr[i]) <= len(arr[i+1]) and arr[i] not in arr[i+1][:len(arr[i])]:
        count += 1

    elif len(arr[i]) > len(arr[i+1]):
        count += 1

print(count)
