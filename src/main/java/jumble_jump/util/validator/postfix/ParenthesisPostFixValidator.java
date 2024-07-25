package jumble_jump.util.validator.postfix;

import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.type.ParenthesisType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ParenthesisPostFixValidator {

    public static void validateBeforeParenthesisNull(ParenthesisToken nowParenthesis){
        if(!nowParenthesis.isOpenParenthesis()){
            throw new IllegalArgumentException("첫 괄호는 열림 괄호여야 합니다.");
        }
    }

    public static void validateWhenNowBeforeOpenState(ParenthesisToken nowParenthesis,ParenthesisToken beforeParenthesis, int rightParenthesisCount){
        ParenthesisType nowParenthesisType = nowParenthesis.getParenthesisType();

        if(!ParenthesisType.isSamePriority(beforeParenthesis.getParenthesisType(), nowParenthesisType)){
            throw new IllegalArgumentException("열리고, 닫힌 괄호의 종류가 달라요"); //fixme
        } else if (rightParenthesisCount <= 1) {
            if(nowParenthesis.getParenthesisPriority() != 1){
                throw new IllegalArgumentException("단독으로 사용하는 괄호는 소괄호를 사용하세요.");
            }
        }
    }

    //note {[(1+1)]}
    public static void validateNestedParentheses(Set<Integer> openParenthesisPriorityList){
        if (openParenthesisPriorityList.size() >= 2) { //note parenthesisStack에 들어있는 열린 괄호들을 우선순위로 정렬한다음 그 값이 isnectOpen인지
            List<Integer> sortedPriorityList = new ArrayList<>(openParenthesisPriorityList);
            Collections.sort(sortedPriorityList);

            for (int i = 0; i < sortedPriorityList.size() - 1; i++) {
                if(sortedPriorityList.get(0) != 1){
                    throw new IllegalArgumentException("중첩을 위해서는 소괄호가 반드시 사용되어야합니다.");
                }else if (sortedPriorityList.get(i + 1) - sortedPriorityList.get(i) != 1) {
                    throw new IllegalArgumentException("우선순위가 높은 괄호부터 차례대로 사용해주세요");
                }
            }
        }
    }

    public static void validateNestedParenthesesEnd(int rightParenthesisCount, int leftParenthesisCount){
        if(rightParenthesisCount != leftParenthesisCount){
            throw new IllegalArgumentException("괄호의 중첩이 끝나지 않았습니다.");
        }
    }

    public static boolean isResetParenthesesData(int rightParenthesisCount, int leftParenthesisCount){
        return rightParenthesisCount == leftParenthesisCount;
    }

}
