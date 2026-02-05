# def jeggi_hamsu(time, sp, result): # time 감소 sp 는 증가 -> time 감소하는걸로 재귀 끝을 정하고, sp 증가로 언더바 증가
#     space = '___'
#     a1 = '"재귀함수는 자기 자신을 호출하는 함수라네"'


#     if time == result:
#         print('어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.')    # 한번만 나와야 함 T가 감소하기 때문에 처음만 나오게... 함,...


#     if time >=0:    # 횟수가 거듭할수록 언더바는 상승
#         print(f'{space*sp}"재귀함수가 뭔가요?"')
#         print(f'{space*sp}"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.')
#         print(f'{space*sp}마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.')
#         print(f'{space*sp}그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."')
        

#         if time == 0:    # 마지막에 호출..하는 함수
#             print(f'{space*(result+1)}"재귀함수가 뭔가요?"')
#             print(space*(result+1),a1,sep="")
#             for i in range(result, -1, -1):
#                 print(f'{space*i}라고 답변하였지.')
                
#         jeggi_hamsu(time-1, sp+1, result)


# T = int(input())
# jeggi_hamsu(T, 0, T)

'''
    어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다. 첫번째 한번
    "재귀함수가 뭔가요?" 위에랑 같이 첫번째 한번

    "재귀함수가 뭔가요?" 두번째부터

    "잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.
    마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.
    그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어." 계속 호출

    "재귀함수는 자기 자신을 호출하는 함수라네" 마지막부터 감소하면서
    라고 답변하였지.
'''
def jeggi_hamsu(time, sp, result):
    space = "____"

    if time == result:
        print(f'{space*sp}어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.')
        print(f'{space*sp}"재귀함수가 뭔가요?"')

    elif time > 0:
        print(f'{space*sp}"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.')
        print(f'{space*sp}마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.')
        print(f'{space*sp}그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.')
    
    elif time == 0:
        print(f'{space*(sp)}"재귀함수가 뭔가요?"')
        print(f'{space*(sp)}"재귀함수는 자기 자신을 호출하는 함수라네"')
        for i in range(sp,-1,-1):
            print(f'{space*i}라고 답변하였지.')
        return


    jeggi_hamsu(time-1, sp+1, result)
    
T = int(input())
jeggi_hamsu(T, 0, T)