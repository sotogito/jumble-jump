befor 우선순위(1
nnow 우선순위)1
befor 우선순위(1
nnow 우선순위}2

if(!ParenthesisType.isSamePriority(beforeParenthesis.getParenthesisType(), nowParenthesisType)){
throw new IllegalArgumentException("열리고, 닫힌 괄호의 종류가 달라요");

열리고 닫치고 전체의 유효성을 알기 위해서는 하낳나 괄호를 따로 저장해얗나댜.,



       //if (!ParenthesisType.isNextOpen(beforeParenthesis.getParenthesisType(), nowParenthesisType)) {
                        //    throw new IllegalArgumentException("열린 괄호의 우선순위를 지켜주세요");
                        //}



이렇게되면 (1 + {2 * [3 + 4]}) - 5 이런 문제는 오류가 남 꼭 바깥에 있는 괄호가 우선순위가 낮은 괄호일 피룡는 없음



0.002-0.001
0.002-0.0010
0
0.002 0.001 SUBTRACT Intermediate step: [0.001]
0.0정
소수 둘째자리에서 반올림하려도했지만 저 경우에 0.0이 출력되었다


안약 음수의 숫자인 경우?
뒷자리는 연산자로 처리해도 무방하지만 첫 자연수가 음수일경우는 곤란하다
예를들어 -4+3을 계산한다고 했을 때
4,3,+,- 순으로 들어갈텐데 그럼 연산에 맞지 않다.
첫자리 음수는 따로 처리해주는게 맞다고 생각했다



아니 tokenizer에서 for문은 length까진데 왜 마지막 연산자 확인은 -1로하는데?



{2 * [3 * (4 - 1)]} - 6
