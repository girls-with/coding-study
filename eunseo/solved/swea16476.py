T = int(input())
for tc in range(1, T+1):
    code = input()

    # 짝을 이룰 자료구조 생성: 딕셔너리로
    pair = {
        '(': ')',
        '{': '}',
    }

    # 스택 생성
    stack = [0] * 9999
    top = -1

    # 짝이 이루어졌음을 기본값 설정하고
    # 안되는 경우를 조건문으로
    ans = 1

    for i in code:
        # i가 열린 괄호라면 삽입
        if i in '{(':
            top += 1
            stack[top] = i

        # i가 닫힌 괄호라면
        if i in ')}':
            # 스택이 비어있으면 삭제 불가 0
            if top == -1:
                ans = 0
                break

            # 스택에 남아있는 괄호가 닫힌 괄호랑 짝이 안맞으면 0
            if pair[stack[top]] != i:
                ans = 0
                break

            # 삭제 가능하면 삭제
            top -= 1
    
    # 다 끝났는데도 스택에 원소가 남아있으면
    # 짝을 못 이룬 것이므로 0
    if top > -1:
        ans = 0

    print(f'#{tc} {ans}')