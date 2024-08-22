- lowerCamelCase - getUSerData
- 동사로 시작하라
- 약어를 지양하라

- 동사 + 명사 + 형용사 + (전치사 + 명사)
  - ex)Return categories by name
- 관사는 사용하지 마라
  - ex) findUser (올바른 예시), findTheUser (비추천).
- 시재는 현재 시재로 작성하라
- 동사 + 명사의 조합으로 이루어져야한다.
- 특정 동작을 수행하지 않음을 나타내야할 경우, isNot과 같은 부정형 동사의 사용을 지양하라
  - ex) isValid (비추천: isNotInvalid).
- 2인칭 대명사는 사용하지 않는다 - getYourData
  - your -> user로 변경 또는 아예삭제하기

### boolean 메서드는 어떡학지 - "is", "can", "has", "should"
사용자가 로그인 되어 있는지 확인하기 - isLoggedIn
파일이 존재하는지 확인하기 - fileExists
데이터가 유효한지 검사하기 - isValidData
사용자가 관리자인지 확인하기 - isAdmin - _Verify that the user is an admin_
저장 공간이 충분한지 확인하기 - hasEnoughStorage
이메일이 확인되었는지 검사하기 - isEmailVerified
결제가 완료되었는지 확인하기 - isPaymentCompleted
업데이트가 필요한지 판단하기 - needsUpdate
서비스가 활성 상태인지 확인하기 - isActiveService
사용자가 권한을 가지고 있는지 검사하기 - hasPermission

- be 동사 is로
- 만약 동사가 2개면 하나만 사용해야하는데 일단... 일반 동사를 우선으로, 만약 동사가 2개인데 is 가 있으면 is 앞으로
  - ex)Verify that the user is an admin 1. verifyUserAdmin, 2. isUserAdmin
- 
- 




---
- 복수형을 사용해도 된다.

--- 
#### 구체화
- 확인 : check


---