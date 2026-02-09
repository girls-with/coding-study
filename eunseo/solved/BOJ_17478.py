N = int(input())

conversation = '' # 전체 대화를 빈 문자열로 초기 설정
conversation += '어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n' # 대화시작

question = '"재귀함수가 뭔가요?"\n' # 질문


body = [
    '"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n',
    '마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n',
    '그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."\n'
] # 본론

conclusion = '"재귀함수는 자기 자신을 호출하는 함수라네"\n' # 결론

answer = '라고 답변하였지.\n' # 답변

for cnt in range(N+1): # cnt는 언더바 개수 범위 0~N까지
    spaces = '____' * cnt
    conversation += (spaces + question) # 언더바에 질문 더해서 대화에 추가
    
    if cnt == N: # 언더바가 N개면 결론 도출
        conversation += (spaces + conclusion) # 언더바에 결론 더해서 대화에 추가
    
    else:
        for dialogue in body: # 본론에 있는 원소 하나하나를 언더바에 합해서 대화에 추가
            conversation += (spaces + dialogue)

for cnt in range(N, -1, -1): # 답변은 N부터 0까지 내림차순이니까 
    spaces = '____' * cnt
    conversation += (spaces + answer) # 언더바 내림차순 개수에 답변 합해서 대화에 추가

print(conversation) # 전체 대화 출력!
        
