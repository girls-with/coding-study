import sys
sys.stdin = open('input.txt', 'r')
'''
이건 쉽더라 ㅋ
'''

T=int(input())

for test_case in range(1, T+1):
    word = input()

    stack = [0] * 50  # 최대길이 50 까지라고 문제에서 명시 해둠
    top = -1
    result = "YES"  #다 통과해서 나오면 가능! 이라고 출력

    for c in word:
        if c in "(":
            top += 1
            stack[top] = c

        if c in ")":   # 닫는게 왔는데 그전 스택에 쌓인값이 여는게 아니면 뽀감 여는게 맞으면 top-1
            if stack[top] == "(":
                top -= 1

            else:
                result = "NO"
                break

    if top != -1:
        result = "NO"   # 탑이 -1 -> 스택에 남아있는 데이터가 없다는 뜻~~ 이건 데이터가 있다는 거니까 불가능 외침!

    print(result)