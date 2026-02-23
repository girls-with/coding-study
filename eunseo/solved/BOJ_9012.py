T = int(input())

for tc in range(1, T+1):
    code = input()

    # 빈 스택 선언
    stack = []
    # 딕셔너리로 짝 구성
    pair = {'(': ')'}
    # 먼저 답은 짝이 있다고 가정
    answer = 'YES'

    # 답이 없음을 출력하는 과정
    for  c in code:
        # 코드가 열린괄호면
        if c in '(': 
            # 스택에 추가하기
            stack.append(c)
        
        # 코드가 닫힌 괄호인데
        if c in ')':
            # 스택이 비어있으면
            if not stack:
                # 답이 없음
                answer = 'NO'
                break

            left = stack.pop()
            # 딕셔너리에 왼쪽 키의 값이 코드랑 다르면
            if pair[left] != c:
                # 답이 없음
                answer = 'NO'
                break
    
    # 다 끝났는데도 스택이 비어있지 않으면 짝이 안맞는다는 거니까
    if stack:
        # 답이 없음 
        answer = 'NO'
    
    print(f'{answer}')