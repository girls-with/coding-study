'''
문자를 정렬하고
while 구문으로 모두 순회하면서 모든 순열 만들고
안에 모음이 있는지 확인해서 있다면 리스트에 담아서
리스트를 다 꺼내서 출력
'''

L, C = map(int, input().split())
arr = list(input().split())

arr.sort()
werds = []

for spalling in range(C):
    passwerd = ""
    while True:
        if len(passwerd) == L:
            if "a" in passwerd or "e" in passwerd or "i" in passwerd or "o" in passwerd or "u" in passwerd:
                werds.append(passwerd)
            break


print(arr)