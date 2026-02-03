'''
0 1 1 2 3 5 8 13 21 34 55 89
lst =[0, 1]
앞에 두 수를 더하고, append를 함. n 번째 피보나치 수를 구함
'''
N = int(input())
lst = [0, 1]
for i in range(N-1):
    pibonachi = lst[i] + lst[i+1]
    lst.append(pibonachi)

print(lst[N])