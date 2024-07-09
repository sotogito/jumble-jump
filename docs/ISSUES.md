### useJUnitPlatform()이 갑자기 인식되지 않는다..
지금까지 문제 없었던 build.gradle.kits에 빨간줄이 뜬다.  
확인해보니까 useJUnitPlatform()에 Unresolved reference: useJUnitPlatform 라고 뜬다,

#### tasks.test
Gradle의 빌드 자동화 도구로 여러가지 작업을 정의할 수 있다. test는 프로젝트의 테스트를 실행하는 기본 작업이다.
#### useJUnitPlatform()
Junit5의 새로운 테스트 플랫폼으로 Junit 4와는 다른 실행 발법을 사용한다.  
`useJUnitPlatform()`은 Gradle에게 JUnit5 폴랫폼을 사용해 테스트를 실행하고 지시하는 멸형어이다.

이것을 각주 처리한다면 테스트는 돌아가지 않는다.
```text
Test events were not received
```

#### 해결 시도
1. Gradle 프로젝트 동기화 하기
   오른쪽 Gradle에 들어가서 동기화해도 BUILD SUCCESSFUL만 뜨지 문제는 해결되지 않는다.
2. Find Action에서 동기화하기
구글링을 하니 나와 비슷한 사례를 가진 사람들이 많이 보였다. 
Help -> Find Action... -> Reload All Gradle Projects로 들어가서해보니...여전히 해결이 안된다.

근데 지금까지 했던 프로젝트 모두 다 useJUnitPlatform을 인식하지 못한 거 같다.  
인텔리제이 IDE에서 해당 메서드를 인ㅅ기하지 못하는 경우도 있다고 한다.  
새로 생성한 Spring에서는 이과 같은 문제가 발생하지 않는다.