- **_난이도 : ⭐️⭐️️️️️️⭐️️️️️️_**
- **_엉망친창 완성 시간 : 약 4시간_**
- **_커밋 : 35회_**

### 공부가 된 내용
- 이중 리스트
- 이중리스트에서 방향을 고려한 이동
- 알고리즘
- 문제이해
- DTO

### 이동까지의 point 처리 과정
1. getLeftPoint() 로 방향을 고려해 새로운 newPoint 객체를 받는다.
2. point좌표의 유효성을 검사한다.
3. 이동이 확정되면 현제 currentPoint(newPoint아님)을 moved에 저장한다.
4. newPoint를 currentPoint로 저장한다. 이떄! 새로운 인스턴스여야한다.

여기서 핵심은!  
currentPosition는 항상 getLeftPoint()에서 반환한 새로운 객체를 받아 업데이트하고,  
List<Point> moved에는 이 currentPosition는를 저장하도록 했다. 그럼 결국 moved에 들어간 객체가 다 다른 인스턴스가 된다.  


### 첫! DTO의 사용
```java
    public UserMoveManager createUserMoveManager(Map map) {
        while (true) {
            try {
                List<Integer> startDataToken = UserStartLocalDataParser.parse(
                        Input.inputUserStartPointAndDirection(), map);
                Point startPoint = new Point(startDataToken.get(0), startDataToken.get(1));
                Direction startDirection = Direction.fromNumber(startDataToken.get(2));

                return new UserMoveManager(createTurnLeftCalculator(), startPoint, startDirection);
            } catch (IllegalArgumentException e) {
                Output.printError(e.getMessage());
            }
        }
    }
```
위에는 사용자의 x,y위치와 방향을 입력받고 " " 기준으로 파싱한 후 객체를 생성시키는 코드이다.  
start position을 입력받는 상황은 x,y와 방향이라는 각각 다른 객체로 생성시켜야하는 특수한 상황이다.  
그래서 나는 이를 띄어쓰기 기분으로 파싱한 후 List<Intger>에 한번에 담아서 get해와 사용했다.  
여기서 한가지 의문점이 생겼다.  
도대체 이게 맞냐 이말이다.  
코드가 영 마음에 안들었다.  
controller에서 startDataToken만 보고
어느 index에 position과 direction의 데이터가 들어가있는지 전혀 파악이 되지 않는다.  
이걸 GPT에게 물어봤더니 DTO를 사용하라는 추천을 받았다.  
DTO 많이 들어봤지만 이럴때 사용하는구나를 알 수 있었다.  

#### UserMoveManager 와 StartPostionDTO의 차이점?
근데... 둘 다 어차피 startPositon과 direction을 담는다면 DTO와 UserMoveManager이 뭐가 다른 역할일까?  
DTO는 말그대로 데이터의 징검다리라고 보면 된다.  
UserMoveManager이 훨씬 더 복잡한 로직을 담당한다. DTO는 전달하는 역할을 해줄 뿐이다.

```java
    public UserMoveManager createUserMoveManager(Map map) {
        while (true) {
            try {
                StartPositionDTO dto = getStartPositionDTO(map);
                return new UserMoveManager(createTurnLeftCalculator(), dto.getStartPosition(), dto.getDirection());
            } catch (IllegalArgumentException e) {
                Output.printError(e.getMessage());
            }
        }
    }

    public StartPositionDTO getStartPositionDTO(Map map){
        return UserInputStartPositionParser.parse(Input.inputUserStartPointAndDirection(), map);
    }
```
그래서 위와같이 코드를 고칠 수 있었다. UserMoveManager로 완전한 데이터가 생성하기 전에 꼬리표가 붙으면서 좀 더 명확해졌다.