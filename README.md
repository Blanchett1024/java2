# 김동현 202130101 
## 3월 27일 4주차 
### 다른 언어 들은 자체적 으로 멀티스레드를 지원 하지않는 것 들이 많아 운영체제의 도움을 받는데에 반해 자바는 운영체제 없이 자체적 으로 멀티스레드 프로그래밍이 가능하다. 장점
### 자바는 번거로운 메모리 반환과정을 자동으로 변환 해주는 가비지 컬렉션 이라는 기능이 있음. 장점
### 하지만 가비지 컬렉션이 실행도중 예상할 수 없는 시점에 알아서 실행되므로 프로그램 실행이 일시적으로 중단되는 현상이있어 실시간 응용시스템에 이용하기엔 적합하지않다. 단점 
### 클래스 파일은 엄밀히 따지면 기계어가 아니다 . 그러므로 c나 c++ 보다는 실행 속도가 느리다. 최근 jtt컴파일링 기법등 의 방식으로 실행성능이 개선되긴했다. 

### 소스코드 > 우리가사용하는 java 코드 / 사람이 읽을수있는 고수준 언어 
### 바이트코드 javac가 소스코드를 변환한 중간코드 /cpu 가 직접 실행할수는 없음 jvm (java virtual machine )이 실행 해줘야함 / 기계어와 다르게 플랫폼 독립적  여러 플랫폼에서 동일하게 실행가능 
### /어디에서 실행되느냐에 따라서 jvm이 해석하고 코드를 변환해서 알아서 기계어로 변환해서 보여줌 
### 기계어 >cpu가 직접 실행할수있는 0과1로 구성된 이진코드 


### 2장 자바프로그램의 기본구조 
### 식벽자의 명명규칙  특수문자 
### 공백 사용 xx (_ &)예외 
### 한글 x if while class 등 자바언어의 키워드는 식별자로 x
###  첫글자 숫자 x 
### 대소문자 구별함 
### 길이제한 x 
### 불린 리터럴 -true false 널 리터럴 -null 은 사용 x 
### 등등...

### 파일이름이 같은 파일이 존재할수있는 이유 > 파일 이름은 경로상에 있는 모든 파일들을 포함하는것이기 때문에 경로가 한개라도 다르다면 최종 파일이름이 같더라도 다른 파일임 . 
### 위에서 말했듯이 파일명은 경로상에 있는 모든 파일들이 이어져 포함되는것이기 떄문에 경로상에있는 모든 파일들에 식별자 명명규칙이 적용된다 한개의 파일경로에도 한글이 있으면 안된다 등. 
### $ > 임시파일에 사용함/ 첫글자에 _이 오면 보통 숨기는 파일에 사용   >>>> 잘 사용하지않음 

### 자바의 데이터 타입 8개 > 4가지  
### 논리타입 boolean 
### 문자타입 char 
### 정수타입 byte short int long
### 실수타입 float double

### 리터럴 .. 프로그램에 직접 표현한 값  int n = 15;   >여기에서는 15를 의미 
### 자바는 왜 참조 자료형을 (reference)쓸까?
### 안정성 
### 보안강화 포인트를 사용하면 메모리주소를 직접 조작할수 있기떄문에 오버플로우가 일어날수있다 > 보안취약점이 생길수있음  
### 다중플랫폼지원 자바는 jvm에서 실행되기때문에 os 에따라 메모리관리방식이 달라져도 영향받지않음 레퍼런스를 사용하면 jvm이 메모리관리를 해주므로 os영향받지않고 같은코드를 실행할수있다 

### 메모리의 구조 힙 /스택 
### 힙 - 선입선출 /스택- 쌓는 방식 나중에 들어온게 먼저나감 
### 힙이 스택을 침법하면 힙 오버 플로우  스택이 힙을 침범하면 스택 오버 플로우

### 변수>프로그램 실행중 값을임시로 저장하기위한 메모리공간/변경될수있음   
### 변수의 선언 >데이터타입에서 정한 크기의 메모리를 할당 

### 상수선언 final 키워드 사용.선언할때 초기값 지정 실행중 값의 변경 x
### fianl double PI = 3.141592; 
### 상수선언/데이터타입/상수이름/초기화  상수선언은 항상 초기화 해줘야함
## 원의 면적을 구하는 프로그램 
## CircleArea.java

### VAR 키워드 
### 데이터 타입을 생략하고 변수선언을 할수있다.
### 컴파일러가 알아서 추론하여 변수타입을 결정 
### 변수선언할떄 초기값을 지정하지않으면 컴파일 오류가 일어남 
### 메소드 내부에 선언되는 지역변수에만 사용가능 하고 
### 클래스 내부에 선언되는 변수.객체가생성될때 함께 만들어지는 변수.   >>클래스필드에서는 사용될수 없다 
### 명시형 자료형 INT STRING DOUBLE 등과 함께 사용하는것이 좋다
### 가독성이 유지될수 있게 사용하여야 좋다
### 상수를 적극적으로 사용해서 코드의 안정성을 높히는 편이 좋다 


### println >> 그냥 print 랑 기능적으로 같은데 ln 의 역할이 \n 임 줄바꿈을 한번 해줌 

### print 하트 
### print 하트 
### 실행시 
### 하트하트 

### print 하트 
### println 하트 
### 실행시 
### 하트 
### 하트
### printf 형식을 지정하여 문자열을 출력할때 사용 
 

### 타입변환 >특정 데이터 타입의 값을 다른데이터타입의 값으로 변환 
### 자동 타입변환 >컴파일러에의해 원래의 타입보다 큰타입으로 자동변환 
### 강제 타입변환 > 개발자가 직접 변환 .데이터 손실 발생가능


### system.in 
### 가장 기본 표준입력 스트림객체 
### 입력되는 키를 바이트(문자 x)로 리턴하는 저수준 스트림
### 바이트를 문자나 숫자로 변환하는 어려움이있음 

### scanner 
### 읽은 바이트를 문자 정수 실수 불린 문자열등 다양한 타입으로 변환하여 리턴 
### 객체를 생성해서 사용 
### 키보드에 연결된 system.in에게 키를 읽게하여 원하는 타입으로 변환하여 리턴 
### 입력되는 키 값을 공백으로 구분되는 토큰단위로 읽음 
### 공백문자 "\tab \n \r 등 (페이지 나누기 폼 피드 프린트 등에서 사용)

## 정수를 입력받아 몇시간 몇분 몇초인지 출력하는 프로그램 
## ArthmeticOperator.java
### 연산자 
### 주어진 식을 계산하여 결과를얻어내는 과정 >연산
### 하나의 식에는 피연산자 연산자 피연산자가 존재한다 
### 연산종류 증감/산술/시프트/비교/비트/논리/조건/대입 

## 두수의 차이를 구하는 프로그램 
## TermaryOperator.java

 ### 조건문 
 ### 단순if문 if-else문 
 ### 조건식이 참이면 if 내부의 실행문장이 실행 거짓이면 if 문을 벗어남

 ### if-else 문 조건식이 참이면 실행문장1 을 실행 거짓이면 실행문장 2를 실행후 문장을 벗어남 

 ### 다중 if-else 문 if-else 가 연속되는것 
 ### 여러개의 조건문을 가지고 ,조건식이 참인경우 해당하는실행문장을 실행한 후 다중if-else 문을 벗어남 . 앞의 조건문이 모두 거짓인경우 else의 실행문장을 실행후 다중if-else 문을 벗어남 
 ### * 조건문이 너무많은 경우엔 후술할 switch문을 사용하기를 권장함

 ### 중첩 if-else 문 
 ### if문이나 if-else 문혹은 ekse 문의 실행문장 안에 또다시 if 문,if-else 문등을 내포할수있다 .
 
 ### switch문 
 ### 식을먼저 계산하고 그결과값과 일치하는 case문으로 분기하여 실행문장을 실행한후 break를만나면 switch문을 벗어난다 만일 어떤case문의 값과도 같지않은경우가 나올경우 default문으로 분기하여 실행문장을 실행한다 . default문은 생략할수있다. 

 ### swotch문에서 break문의 역할과 중요성..

 다음시간에 ....





# 김동현 202130101
## 3월 20일 3주차
### 프로젝트 생성하는 방법 위에 검색창에 >create java project 선택 no build tool 선택하고 폴더 선택 해야함 >>> 워킹 디렉토리를 선택 하면 그안에 디렉토리를 생성함.
### src 안에 새로운 클래스 생성 하기  
### 새로 깃허브에 연동하는 방법 

### 자바 묘듈화의 필요성 
### 자바 플랫폼 독립성 ,객체지향 , 자바의 모든변수나 함수는 클래스 내에서 선언된다 ..   클래스 내부에 클래스를 만들었다면 컴파일 후에 바이트 코드가 생성이 된다
 ###  클래스가 여러개 있다고 한들 배포해야하는데 사용하는 이용자는 여러개의 클래스 파일을 일일이 다운로드 받지않게하기위해 한개의 파일로 압축을 해서 배포함 >> 그 파일을 jar이라고함 
 ### 여러개의 클래스 파일이 각각 main()을 포함하는 것은 상관없으나 한개의 클래스 파일안에 2개이상의 main()은 존재할수없음 

# 김동현 202130101
## 3월13일 2주차 

### 깃허브 에 파일 올리는 방법 .커밋해서 파일 올리는 방법 
# h1 tag 
## h2 
### h3 
#### h4
##### h5
###### h6

---

* 가가가
- 나나나
1. 가가가
2. 나나나
3. 다다다

```
라라라
```
