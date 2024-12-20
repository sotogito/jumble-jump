# 사칙 연산 계산기
사칙 연산 문제를 계산하여 답을 출력한다.

### 사건의 발단
영민이는 어느날 인터넷에 떠도는 간단한 사칙연산 문제를 틀리게된다.
```text
7+7/7+7*7-7
```
오랜만에 쉬운 문제를 풀다보니 곱하기, 나누기를 먼저 풀지 않고 순서대로 풀어나간 것이다.
수키피는 영민이를 위해 사칙연산의 기본을 다질 수 있는 프로그램을 선물하려한다.
영민이가 각박한 세상을 살아가느라 잊어버린 사칙연산의 기본을 다시 새길 수 있을까!

### 문제 설명
- 사칙연산의 법칙에 맞게 계산한다.
- 더하기, 빼기, 곱하기, 나누기의 연산자가 있다.
- 괄호(), 중괄호{}, 대괄호[]의 괄호를 사용할 수 있다.
- 한번의 연산이 수행될 때마다 중간 식을 출력한다.
- 연산이 수행되는 횟수를 출력한다.
- 결과에는 문제, 최종 횟수, 정답을 출력한다.
- 정답 소수점 둘째 자리까지 출력한다.

### 출력 예시
```text
안녕하세요. 영민씨.
제 이름은 FourChick이에요.

문제를 입력해 주세요.
1+2*{3+(4-1)}-1

---계산중---

계산 횟수 : 1회
1+2*{3+3}-1

계산 횟수 : 2회
1+2*6-1

계산 횟수 : 3회
1+12-1

계산 횟수 : 4회
13-1

계산 횟수 : 5회
12

---결과를 출력합니다.---
문제 : 1+2*{3+(4-1)}-1
총 풀이 횟수 : 5회

정답 : 12
```
