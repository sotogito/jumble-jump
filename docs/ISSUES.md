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