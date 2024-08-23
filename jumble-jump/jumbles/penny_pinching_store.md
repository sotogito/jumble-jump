# 빈털털이 구멍 가게
사용자가 입력한 금액이 더 이상 구매할 수 없는 가격이 될 때까지 구매한다.
### 사건의 발단
키피는 구멍가게 창업을 시작하려한다. 최고의 순수익을 내기 위해 주변에 허름한 물건들을 판매하기로 결심한다. 나름 자신에게 소중한 물건은 높은 가격으로 판매한다.
고객이 지불한 금액에 더이상 구매할 수 없는 금액이 될 때까지, 빈털털이가 될 까지 판매할 예정이다. 판매한 물건을 관리하고 수익을 내보자.

### 문제 설명
- CSV 파일에 있는 물건들을 읽어와서 물품을 생성시킨다.
- 상품은 이름-가격-수량 순으로 저장한다.
- 메뉴판을 출력한다
- 사용자의 투입 금액을 입력받는다.
- 구매할물품과 구매할 상품 수량 순으로 , 으로 구분해서 입력받는다.
- 사용자의 금액은 10원 단위로 나누어 떨어져야하며 최소 금액은 1000원이다.
- 더이상 구매가 불가능하면 입력을 중단한다.
- 영수증을 출력한다
    - 구매한 상품 - 가격
    - 총 구매 가격떄

### 출력 예시
```
빈털털이 구멍가게에 오신걸 환영합니다!

구매 가능 상품 목록
- 구멍 뚫린 종이컵 : 3,000원
- 잉크가 다 닳은 만연필 : 2,500원
- 액정이 깨진 아이폰 : 150,000,000원
- 씹던 껌 : 500원
- 피라냐가 물어 뜯은 물고기 잠옷 : 50,400원
- 아빠가 끓인 야심작 찌개 : 20,310원
- 꼬막이의 첫 유치 : 1,500원
- 할머니의 앉은뱅이 의자 : 7,908,000,000원

얼마를 투입하시겠어요?
50000

[잔액은 50,000원이에요.]
구매하고 싶은 상품과 수량을 입력해 주세요.
ex) 상품,2
*씹던 껌,50*

장바구니에 성공적으로 추가했습니다!
[잔액은 25,000원이에요.]
구매하고 싶은 상품과 수량을 입력해 주세요.
***아빠가 끓은 야심작 찌개,2***

<이런!> 아빠가 끓인 야심작 찌개는 수량이 1개 뿐이에요!
[잔액은 25,000원이에요.]
구매하고 싶은 상품과 수량을 입력해 주세요.
***아빠가 끓은 야심작 찌개,1***

장바구니에 성공적으로 추가했습니다!
[잔액은 4,690원이에요.]
구매하고 싶은 상품과 수량을 입력해 주세요.
***잉크가 다 닳은 만연필,2***

<이런!> 310원이 부족해요.
<이런!> 아잉크가 다 닳은 만연필은 수량이 1개 뿐이에요!
[잔액은 4,690원이에요.]
구매하고 싶은 상품과 수량을 입력해 주세요.
***구멍 뚫린 종이컵,1***

장바구니에 성공적으로 추가했습니다!
[잔액은 1,690원이에요.]
구매하고 싶은 상품과 수량을 입력해 주세요.
***꼬막이의 첫 유치,1***

빈털털이가 되셨군요!
영수증을 출력할게요.

<영수증>
[잔액은 190원원이에요.]
------
씹던 껌, 50개, 25,00원
아빠가 끓인 야심작, 1개, 20,310원
구멍 뚫린 종이컵, 1개, 3,000원
꼬막이의 첫 유치, 1개, 1,500원
------
총 가격 : 49,810원

<잔돈>
100원 - 1개
50원 - 1개
10원 - 4개
```