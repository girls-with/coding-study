t = int(input())

for tc in range(1, t+1):
    text = input()

    stack = [] # 스택 생성
    result = 'YES' # 디폴트 = YES

    for i in text:
        if i == '(': 
            stack.append(i) # '('를 만나면 스택에 어펜드
        else:
            if len(stack) == 0:
                result = 'NO' #')'를 만났는데 스택에 남아있는게 없으면 결과 = 'NO'
                break # 더 볼 필요가 없으니 break
            else:
                stack.pop() # 만약 스택에 남아있는게 있으면 팝
    if len(stack) != 0: 
        result = 'NO' # 다 돌았는데 스택에 남아있는게 있으면 짝이 맞지 않다는 것. 결과 = 'NO'

    print(result)