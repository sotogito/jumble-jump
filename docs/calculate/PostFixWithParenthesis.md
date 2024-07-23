### 숫자와 연산자 그리고 괄호
괄호가 있을 때 후위 표기법으로 처리하는 과정을 똑같다.  
다만 괄호가 연산자보다 우선이 되어야한다.

#### 소괄호 하나
```text
3 + 4 * 2 + (2 - 1)
```

초기 설정:
output: []
operatorStack: []

토큰 3 처리:
output: [3]
operatorStack: []

토큰 + 처리:
output: [3]
operatorStack: [+]

토큰 4 처리:
output: [3, 4]
operatorStack: [+]

토큰 * 처리:
output: [3, 4]
operatorStack: [+, *]

토큰 2 처리:
output: [3, 4, 2]
operatorStack: [+, *]

토큰 + 처리:
output: [3, 4, 2, *, +]
operatorStack: [+(new)]

토큰 ( 처리:
output: [3, 4, 2, *, +]
operatorStack: [+,(]

토큰 2 처리:
output: [3, 4, 2, *, +,2]
operatorStack: [+,(]

토큰 - 처리:
output: [3, 4, 2, *, +,2]
operatorStack: [+,(, -]  
-**_괄호안에 있는 연산자기 때문에 앞에 (가 있으면 우선순위의 지킴은 괄호 안에서 지켜진다._**

토큰 1 처리:
output: [3, 4, 2, *, +,2,1]
operatorStack: [+,(, -] 

토큰 ) 처리:
output: [3, 4, 2, *, +,2,1]
operatorStack: [+,(, -]  
-**_스택에 저장해주었던 짝(을 만날때까지 pop해서 Output에 넣는다._**  
output: [3, 4, 2, *, +,2,1,-]
operatorStack: [+] 

마지막 단계:
output: [3, 4, 2, *, +,2,1,-,+]
operatorStack: []
---

#### 소괄호 + 중괄호 + 대괄호
이 문제도 그렇게 복잡하짆 않다. ㅏㅅ실 복잡하다  
중간 과정은 생략하겠다.
```text
3 + [4 * {2 + (2 - 1)} + 2]
```

토큰 1 처리 :
output : 3,4,2,2,1
operatorStack: +, [, *, {, +, (, -

토큰 ) 처리 :
output : 3,4,2,2,1,-
operatorStack: +, [, *, {, +, ~~(, -~~

토큰 } 처리 :
output : 3,4,2,2,1,-, +
operatorStack: +, [, *, ~~{, +,~~ 

토큰 + 처리 :
output : 3,4,2,2,1,-, + , *
operatorStack: +, [,+
- +보다 스택의 *이 더 우선순위가 높다.

토큰 2 처리 :
output : 3,4,2,2,1,-, + , * , 2
operatorStack: +, [,+

토큰 ] 처리 :
output : 3,4,2,2,1,-, + , * , 2, +
operatorStack: +, ~~[,+~~

마지막:
output:3, 4, 2, 2, 1, -, +, *, 2, +, +
operatorStack: 


---
- now보다 스택이 같거나 큰 연산자가 있으면 output에 넣는다.
- 연산자의 우선순위를 계산은 열린괄호 이상부터한다. 스택 : -,*,(-*
- 같은 종류의 닫힌 괄호가 나올때까지 스택를 pop해서 output에 저장한다.