### 무한루프?
서비스 로직은 잘 작성한거 같은데... 동작이 멈추지 않는다...
다시.. 디버깅을 돌려가며 살펴보았다.
문제는 3가지였다.
1. 이미 지나왔던 자리 확인 이슈
2. blockMoveCounter ==4 시 현재 point를 이미 지나왔던 자리 추가하지 않음
3. blockMoveCounter 초기화해주지 않음

### 1. point의 동등성 - 같은 필드값, 다른 메모리주소
#### 이미 지나간 자리를 저장하는 List<Point>에 포함되어있는지 확인하기
```java
    public boolean isMovedPoint(Point point) {
        return moved.contains(point);
    }
```
위의 코드는 왼쪽의 좌표로 이동할 때 이미 지나온 point인지 확인하는 코드이다.  
계속 무한루프가 일어나서 디버깅을 해보니 이동 가능한 위치가 (1,1) -> (1,2) ->(2,2)라면
(1,2)에서 (1,1)로 다시 돌아가는 문제가 있었다.
List<Point> moved에 point를 넣는 로직을 다시 살펴보니


1. getLeftPoint() 로 새로운 왼쪽 Point 객체를 가져온다.
2. 이동이 가능하다면 현재 point를 moved에 저장한다. - moved.add(currentPoint);
3. 1번에 가져왔던 pint를 현재 point로 set한다.

여기서 주의해야할 점은 위의 로직에 2개의 참조 객체가 사용된다는거다. 데이터의 흐름을 잘 확인해야한다
- 만약 현재 point를 moved에 저장하는 것을 set으로 x,y를 하나하나 변경해주면  
현재 point객체가 동일하며 Moved에 들어간 객체도 동일하게 참조하고 있기 때문에 x,y값이 업데이트가 된다.  
그래서 반드시 새로운객체를 받아서 바꿔줘야한다.

#### point != point ?
근데 여기서 내가 간과한 점은 isMovedPoint에서 두 point의 동등성이다.

인수로 받는 point는 getLeftPoint()에서 받아온 새로운 참조 변수이다. moved에 들어간 point는 이와 다른 변수이다.  
moved의 (1,1)과 인수의 (1,1)이 다르다는 것이다.

때문에 x,y가 같다고 하더라도 저 로직대로라면 절대로 true를 반환할 수 없다.  
그래서!!! 같게 해줘야한다.


그래서 처음에는 x와 y를 get해와서 하나하나 비교해줬다.
```java
    public boolean isMovedPoint(Point point) {
    for (Point movedPoint : moved) {
        if (movedPoint.getX() == point.getX() && movedPoint.getY() == point.getY()) {
             return true;
        }
    }
    return false;
}
```

하지만 이 프로그램 구현에서는 x,y가 같다면 같은 객체로 처리하는게 맞다고 생각했다. 
Point 클래스에 메모리 주소가 달라도 x,y같이 동일하면 같은 것으로 재정의 해줘야한다.

#### 동등성의 재정의
```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  
        if (o == null || getClass() != o.getClass()) return false;  

        Point point = (Point) o; 

        return x == point.x && y == point.y;  
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);  
    }
```
위와같이 새롭게 정의해주었다. 이제 x,y값만 같으면 동일시 처리할 수 있당

