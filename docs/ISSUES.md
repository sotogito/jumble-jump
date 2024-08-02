문제를 이해하기 위해서는 현재 발생하고 있는 문제를 구체적으로 파악하는 것이 중요합니다. 다음은 문제의 설명과 해결 방법을 차근차근 설명한 것입니다.

문제 설명
현재의 문제는 웹 애플리케이션에서 계산을 수행할 때 SolvingRepository의 상태가 이전 계산 결과를 유지하는 문제입니다. 즉, 이전 계산의 풀이 결과가 다음 계산에 영향을 미쳐서, 같은 CalculatorService와 SolvingRepository 인스턴스가 재사용되기 때문입니다.

초기화 문제:

사용자가 /calculate 페이지에서 계산을 수행하면 CalculatorService와 SolvingRepository의 인스턴스가 사용됩니다.
이후 홈 페이지로 돌아가서 다른 문제를 입력하면, 이전 계산의 상태가 여전히 유지되므로, 풀이 횟수나 중간식이 이전 결과를 반영하여 잘못된 결과를 보여주는 문제 발생.
상태 관리:

CalculatorService와 SolvingRepository는 스프링의 빈으로 관리되기 때문에 상태가 지속됩니다. 즉, 같은 인스턴스가 요청 사이에 유지됩니다.
새로운 요청이 오더라도 상태가 초기화되지 않으면, 이전 상태가 그대로 남아있어 문제가 발생합니다.
해결 방법
문제 해결을 위해 다음과 같은 방법을 고려할 수 있습니다.

1. SolvingRepository의 스코프 변경
   SolvingRepository를 매번 새로운 인스턴스가 생성되도록 하여 상태가 초기화되도록 설정합니다. 이는 @Scope("prototype") 어노테이션을 사용하여 해결할 수 있습니다.

SolvingRepositoryImpl.java:

java
코드 복사
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype") // Prototype scope 설정
public class SolvingRepositoryImpl implements SolvingRepository {
// 기존 코드
}
Prototype Scope: 이 설정을 통해 스프링 컨테이너는 매번 새로운 인스턴스를 생성하여 반환합니다. 따라서 매 요청마다 새로운 SolvingRepository 인스턴스가 생성되어 상태가 유지되지 않습니다.
2. 컨트롤러에서 새 인스턴스 생성
   만약 SolvingRepository를 @Prototype으로 설정할 수 없다면, 각 요청에서 SolvingRepository의 새로운 인스턴스를 생성하는 방법도 있습니다. 그러나 스프링의 의존성 주입과는 맞지 않으므로 이 방법은 권장되지 않습니다.

3. 요청 사이 상태 공유 문제 확인
   홈 페이지에서 초기화: 홈 페이지로 이동하고 /calculate 페이지로 돌아가는 과정에서 상태가 초기화되지 않는 이유는, 서버 측에서는 동일한 CalculatorService와 SolvingRepository 인스턴스를 계속 사용하고 있기 때문입니다.
   브라우저에서 페이지 이동: 브라우저에서 페이지를 이동하면 서버 측에서는 새로 요청이 들어오지만, 스프링 컨테이너에서 빈의 상태가 초기화되지 않으면 이전 상태가 그대로 유지됩니다.
   결론
   문제: SolvingRepository의 상태가 유지되면서 이전 결과가 다음 계산에 영향을 미침.
   해결 방법:
   SolvingRepository의 빈 스코프를 prototype으로 변경하여 매 요청마다 새로운 인스턴스가 생성되도록 설정합니다.
   컨트롤러와 서비스 클래스에서는 변경된 빈 스코프에 맞춰서 적절히 상태를 관리합니다.
   이러한 방법으로 웹 애플리케이션의 상태 문제를 해결하여, 매번 새로운 계산 요청이 올 때마다 상태가 초기화되도록 할 수 있습니다.

### 문제1
- 처음에 1+1
```text
풀이 횟수 : 1
중간식 : 2
3토큰사이즈
총 풀이 횟수 : 1
결과 : 2
```
- 두번째 1+2
```text
풀이 횟수 : 1
중간식 : 1+2
풀이 횟수 : 2
중간식 : 3
3토큰사이즈
총 풀이 횟수 : 2
결과 : 3
```
문제는 식을 후이ㅜ표기법으로 변환할때 그것을 저장하는 PostfixExpressionManager의 스택과 토큰 리스트를 초기화해주지 않아 생긴 문제이다.  
그래서 1+1에 새로운 1+2가 누적으로 들어가면 calculator에서는 우선적으로 1+1을 계산하고 그 다음 식인 1+2를 계산한거였다.