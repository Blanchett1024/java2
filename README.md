# 김동현 202130101 
## 3월 27일 4주차 
### 다른 언어 들은 자체적 으로 멀티스레드를 지원 하지않는 것 들이 많아 운영체제의 도움을 받는데에 반해 자바는 운영체제 없이 자체적 으로 멀티스레드 프로그래밍이 가능하다. 장점
### 자바는 번거로운 메모리 반환과정을 자동으로 변환 해주는 가비지 컬렉션 이라는 기능이 있음. 장점
### 하지만 가비지 컬렉션이 실행도중 예상할 수 없는 시점에 알아서 실행 되므로 프로그램 실행이 일시적으로 중단되는 현상이있어 실시간 응용시스템에 이용하기엔 적합하지않다. 단점 
### 클래스 파일은 엄연히 따지면 기계어가 아니다 . 그러므로 c나 c++ 보다는 실행 속도가 느리다. 최근 jtt컴파일링 기법등 의 방식으로 실행성능이 개선되긴 했다. 

### 소스코드 > 우리가사용하는 java 코드 / 사람이 읽을수있는 고수준 언어 
### 바이트코드 javac가 소스코드를 변환한 중간코드 /cpu 가 직접 실행할수는 없음 jvm (java virtual machine )이 실행 해줘야함 / 기계어와 다르게 플랫폼 독립적  여러 플랫폼에서 동일하게 실행가능 
### /어디에서 실행되느냐에 따라서 jvm이 해석하고 코드를 변환해서 알아서 기계어로 변환해서 보여줌 
### 기계어 >cpu가 직접 실행할수있는 0과1로 구성된 이진코드 







# 김동현 202130101
## 3월 20일 3주차
### 프로젝트 생성하는 방법 위에 검색창에 >create java project 선택 no build tool 선택하고 폴더 선택 해야함 >>> 워킹 디렉토리를 선택 하면 그안에 디렉토리를 생성함.
### src 안에 새로운 클래스 생성 하기  
### 새로 깃허브에 연동하는 방법 

### 자바 묘듈화의 필요성 
### 자바 플랫폼 독립성 ,객체지향 , 자바의 모든변수나 함수는 클래스 내에서 선언된다 ..   클래스 내부에 클래스를 만들었다면 컴파일 후에 바이트 코드가 생성이 된다
 ###  클래스가 여러개 있다고 한들 배포해야 하는데 사용하는 이용자는 여러개의 클래스 파일을 일일이 다운로드 받지않게하기위해 한개의 파일로 압축을 해서 배포함 >> 그 파일을 jar이라고함 
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
