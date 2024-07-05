### 구현 순서
2. item
1. CSV 파일 생성 및 읽어오기
3. UserCashier
4. Order
5. Store
6. ChangeCalculator
7.  PurchasePolicy(i)
8. view , 컨트롤러




- Items : List<Item> : 최저 금액 찾기, 입력 상품 찾기
  - Item : 이름, 가격, 수량 반환하기
    - Name
    - Price
    - Stock

- UserCashier : 사용자의 총 금액, 구매 가능 유효 가능성 확인
- Order : Map<구매상품,수량>, 총가격을 업데이트
  - - ChangeCalculator : 잔돈 계산기
- Store : 입력된 상품을 찾고, 사용자, 상품의 상태를 업데이트
  - PurchasePolicy(i) : 구매 가능한지 확인
---
### 인터페이스
- Receiving Purchase Item Printer
  - 잔액
  - 입력 받기
- ReceiptsPrinter
  - <영수증>
  - 잔액
  - 목록
  - 총가격
  - 잔돈






- 구매하려는 상품이 품절일 경우 품절이라는 안내 띄우기

- 더이상 구매를 하지 못할때
  - 구매한 다음 사용자가 가지고 있는 금액이 최소 금액 상품보다 적을때
  - 최소 금액은 품절이 될 최소 금액 상품을 대비하여 계속 갠신해야된
    - 어느 상품이 상용자가 구매해서 품절이 된다면 상품의 최소 금액을 다시 받아와야함
  - 최소금액 갱신 -> 사용자금액이 최소 금액보다 적은지 확인

- Order : 사용자가 구매한 상품을 총 관리한다.
  - List<Item,Integer> items : 구매한 아이템과 수량

- printer은 인터페이스로?