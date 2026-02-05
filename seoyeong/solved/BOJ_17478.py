def recursion(n, i):
    underbar = '____'*i #i가 1 증가할 때마다 언더바 수 4개씩 증가
    if i != n:
        # i가 n과 다를 시엔(재귀가 도는 동안) 아래 문장을 프린트
        print(f'{underbar}"재귀 함수가 뭔가요?"')
        print(f'{underbar}"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.')
        print(f'{underbar}마을 사람들 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.')
        print(f'{underbar}그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어." ')

        recursion(n, i+1) # i를 1씩 늘리면서 재귀 돌림

    else: # i가 n과 같으면(즉, 재귀의 마지막) 아래 문장을 프린트
        print(f'{underbar}"재귀 함수가 뭔가요?')
        print(f'{underbar}"재귀함수는 자기 자신을 호출하는 함수라네"')
    
    print(f'{underbar}라고 답변하였지.') #이거 위치때문에 고전함... 이거때문에 5분을 넘게 썼다ㅠ


print("어느 날 한 컴퓨터공학 학생이 유명한 교수님을 찾아가 물었다.")
recursion(2, 0)