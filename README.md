# 김동현 202130101
## 6월 5일 14주차 
<br>

## 독립 클래스로 Action 이벤트 리스너 만들기
~~~
import java.awt.*;
import.java.awt.event.*;
import.javax.swing.*;

public class IndepClassListener extends JFrame {
    public IndepClassListneer(){
        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout( new FlowLayout());
        JButton btn = new JButton("Action");
        btn.addActionListener(new ExMyActionListener());
        c.add(btn);
        setSize(width:250, height:120);
        setVisible(true);
    }
    public static void main(String[] args){
        new IndepClassListener();
    }
}
//독립된 클래스로 이벤트 리스너를 작성한다
class ExMyActionListener implements ActionListener{
    public void actionperformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Action"))
                b.setText("액션");
            else
                b.setText("Action");    
    }
}
~~~
## 익명 클래스로 이벤트 리스너 작성
- 익명 클래스(anonymous class) : 이름 없는 클래스
    - (클래스 선언 + 인스턴스 선언)을 한번에 달성
        ~~~
        new 익명클래스의 슈퍼클래스이름(생성자인자들){
            ........................
            익명클래스의 멤버구현
            ........................
        }
        ~~~
    - 간단한 리스너의 경우 익명클래스 사용 추천
        - 메소드 개수가 1,2,개인 리스너(ActionListener, ItemListener)에 대해 주로 사용
- ActionListener를 구현하는 익명의 이벤트 리스너작성 예
    - 이름을 가진 클래스를 작성하고 클래스 인스턴스를 생성하는 경우
    ~~~
    class MyActionListenr implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ............
        }
    }

    +
    b.addActionListener(new MyActionListener());
    ~~~
    - 액션 리스너를 상속받고 바로 메소드 작성. 동시에 new로 인스턴스를 생성하는 경우
    ~~~
    b.addActionListener(new ActionListener(){
        public void actionformed(ActionEvent e){
         ..............
        }
    });
    ~~~
    
## 마우스 이벤트 리스너 작성 연습 - 마우스로 문자열 이동시키기
- 프레임의 임의의 위치에 마우스버튼을 누르면 마우스 포인터가 있는 위치에 "Hello"문자열을 출력하는 프로그램
- 마우스 버튼을 누르면 마우스가 있는 위치로 "HeLLO"문자열을 이동시킨다
- 이벤트와 리스너 : MouserListener
- 이벤트 소스 : 컨텐트팬
- 커넨트팬의 배치관리자 : 배치관리자 삭제 
- 구현할 리스너의 메소드 : mousePressed()
- "Hello" 문자열 : JLabel 컴포넌트 이용
~~~
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Ex94MouseListener extends JFrame {
    private JLabel la = new JLabel("Hello"); //Hello 레이블

    public Ex94MouseListener(){
        setTitle("Mouse 이벤트 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.addMouseListener(new MyMouseListener());

        c.setLayout(null);
        la.setSize(50,29);
        la.setLocation(39,30);
        c.add(la);

        setSize(200,200);
        setVisible(true);
    }
    
    class MyMouseListener implements MouseListener {
        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            la.setLocation(x,y);
        }
        public void mouseReleased(MouseEvent e){}
        public void mouseClicked(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }
    public static void main(String[] args) {
        new Ex91MyActionListener();
    }
    
}
~~~
## 어탭터 클래스
- 이벤트 리스너 구현에 따른 부담
    - 리스너의 추상 메소드를 모두 구현해야하는 부담
    - 예 > 마우스 리스너에서 마우스가 눌러지는경우 (mouserPressed())만 처리하고자하는경우에도 나머지 4개 메소드를 모두 구현해야하는 부담
- 어댑터 클래스(Adapter)
    - 리스너의 모든 메소드를 단순 리턴하도록 만든 클래스(JDK에서 제공)
    - mouseAdapter 예시
        ~~~
        class MouseAdapter implements MouseListener,MouseMotionListener,MouseWheelListener{
            public void mousePressed (MouseEvent e){}
            public void mouseReleased (MouseEvent e){}
            public void mouseClicked (MouseEvent e){}
            public void mouseEntered (MouseEvent e){}
            public void mouseExited (MouseEvent e){}       
            public void mouseDragged (MouseEvent e){}
            public void mouseMoved (MouseEvent e){}
            public void mouseWheelMoved (MouseEvent e){}

        }
        ~~~
- 추상 메소드가 하나뿐인 리스너는 어댑터 없음
    - ActionAdapter, ItemAdapter 클래스는 존재하지 않음음
## key 이벤트와 포커스
- 키 입력시, 다음 세경우 각각 key 이벤트 발생
    - 키를 누른순간
    - 누른 키를 뗸 순간
    - 누른키를 뗴는 순간(Unicode키의 경우만)
- 키 이벤트를 받을수 있는 조건
    - 모든 컴포넌트
    - 현재 포커스를 가진 컴포넌트가 키 이벤트 독점
- 포커스
    - 컴포넌트나 응용프로그램이 키 이벤트를 독점하는 궡ㄴ한
    - 컴포넌트에 포커스 설정방법: 다음 2라인 코드 필요
        ~~~
        component.setFocusable(true); // 컴포넌트가 포커스를 받을수있도록 설정
        component.requestFocus(); // 컴포넌트에 포커스 강제 지정정
        ~~~
- 자바 플랫폼마다 실행환경의 초기화가 서로 다를수있기떄문에 다음코드가 추가로 필요함
    - component.setFocusable(true);

## KeyListener
- 응용 프로그램에서 KeyListener를 상속받아 키 리스너 구현
- KeyListener의 3개 메소드
- 컴포넌트에 키 이벤트 리스너 달기
~~~
component.addKeyListener(myKeyListener);
~~~
## 유니코드 키 
- 유니코드 키의 특징
    - 국제 산업 표준
    - 전 세계의 문자를 컴퓨터에서 일관되게 표현하기위한 코드 체계
    - 문자들에 대해서만 키 코드값 정의: A~Z, a~z, 0~9, !,@,& 등
- 문자가 아닌 키 경우에는 표준화된 키 코드값 없음
    - < Funtion > 키, < Home > 키,< Up > 키,< Delete >키 등은 플랫폼에따라 키 코드값이 다를수 있음
- 유니코드 키가 입력되는 경우
    - keyPressed(), keyTyped(), keyReleased()가 순서대로 호출
- 유니코드 키가 아닌경우
    - keyPressed(), keyReleased()만 호출됨

## 가상 키와 입력된 키 판별
- keyEvent 객체
    - 입력된 키 정보를 가진 이벤트 객체
    - KeyEvent 객체의 메소드로 입력된 키 판별
- KeyEvent 객체의 메소드로 입력된 키 판별
    - char KeyEvent.getKeyChar()
    - 키의 유니코드 문자값 리턴
    - 유니코드 문자 키인 경우에만 의미있음
    - 입력된 키를 판별하기위해 문자 값과 비교하면됨
    ~~~
    public void keyPressed(KeyEvent e ){
        if(e.getKeyChar()=='q')
        System.exit(0);
    }         // q 키가 눌리면 프로그램 종료료
    ~~~
- int KeyEvent.getKeyCode()
    - 유니코드 ㅣ 포함
    - 모든 키에대한 정수형 키 코드 리턴
    - 입력된 키를 판별하기위해 카상키 값과 비교하여야함
    - 가상키 값은 KeyEvent 클래스에 상수로 선언
    ~~~
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_F5)
            System.exit(0);
    }     //F5를 누르면 프로그램 종료료
    ~~~

가상키(Virtual Key)
- 가상 키는 KeyEvent 클래스에 상수로 선언 <br>
마우스 이벤트
- 마우스 이벤트 : 사용자의 마우스 조작에 따라 발생하는 이벤트
    - mouse + Clicked/Released/Dragged 등등..





# 김동현 202130101
## 5월 29 일 13주차
<br>

## 컨테이너와 컴포넌트
1. 컨테이너 
    - 다른 컴포넌트를 포함할 수 있는 GUI 컴포넌트 :java.awt.Container를 상속받음
    - 다른 컨테이너에 포함될 수 있음
    - AWT 컨테이너 : Panel, Frame, Applet, Dialog, Window
    - Swing 컨테이너 : JPanel JFrame, JApplet, JDialog, JWindow
2. 컴포넌트 
    - 컨테이너에 포함되어야 화면에 출력될 수 있는 GUI객체
    - 다른 컴포넌트를 포함할 수 없는 순수 컴포넌트
    - 모든 GUI 컴포넌트가 상속받는 클래스 : java.awt.Component
    - 스윙 컴포넌트가 상속받는 클래스 : javax.swing.Jcomponent
3. 최상위 컨테이너
    - 다른 컨테이너에 포함되지 않고도 화면에 출력되며, 독립적으로 존재가능한 컨테이너
    - 스스로 화면에 자신을 출력하는 컨테이너 : JFrame, Jdialog, JApplet

## Swing GUI 프로그램 만들기
- 스윙 GUI 프로그램을 만드는 과정
    1. 스윙 프로그램 만들기
    2. main() 메소드 작성
    3. 스윙 프레임에 스윙 컴포넌트 붙이기

- 스윙 프로그램 작성에 필요한 import 문
~~~
import java.awt.*;             //그래픽 처리를 위한 클래스들의 경로
import java.awt.event.*;          // AWT 이벤트 사용을 위한 경로명
import javax.swing.*;              // 스윙 컴포넌트 클래스들의 경로명
import javax.swing.event.*;     // 스윙 이벤트를 위한 경로명
~~~
## SWING 프레임
- 스윙 프레임 : 모든 스윙 컴포넌트를 담는 최상위 컨테이너
    - JFrame 을 상속받아 구현
    - 컴포넌트들은 화면에 보이려면 스윙 프레임에 부착되어야함
    - 프레임을 닫으면 프레임에 부착된 모든 컴포넌트가 보이지 않게됨
- 스윙 프레임(JFrame) 기본 구성
    - 프레임 : 스윙 프로그램의 기본 틀
    - 메뉴바 : 메뉴들이 부착되는 공간
    - 컨텐트 팬 : GUI 컴포넌트들이 부착되는 공간 

## 프레임 만들기 , JFrame 클래스 상속
- 스윙 프레임
    - JFrame 클래스를 상속받은 클래스 작성
    - 프레임의 크기 반드시 지정 : setSize() 호출
    - 프레임을 화면에 출력하는 코드 반드시 필요: serVisible(true) 호출
~~~
import javax.swing.*;

public class MyFrame extends JFrame {         // JFrame을 상속받은 MyFrame 작성
    public MyFrame(){
        setTitle("300x300 스윙 프레임 만들기");  // 타이틀 설정

        setSize(300,300);              // 프레임 크기 지정

        setVisible(true);         //프레임을 화면에 출력
    }

    public static void main(String[ ] args){
        MyFrame mf = new MyFrame();   // MyFrame 객체. 즉 스윙 프레임 생성
    }
}
~~~
## Swing 응용 프로그램에서 main()의 기능과 위치
- 스윙 응용 프로그램에서 main()의 기능 최소화 바람직
    - 스윙 응용프로그램이 실행되는 시작점으로서의 기능만
    - 스윙 프레임을 생성하는 정도의 코드로 최소화
~~~
public static void main(String [ ] args){
    MyFrame frame = new MyFrame();    // 스윙 프레임 생성
}
~~~
- frame 객체를 생성하고 사용하지 않기떄문에 바로 실행하면 warning이 발생.
    - 실무에서는 다음과 같이 코딩하는게 일반적
~~~
public static void main(String [ ] args){
    new MyFrame();    //스윙 프레임 생성 
}
~~~
## 프레임에 컴포넌트 붙이기
- 타이틀 달기
    - super()나 setTitle()이용
~~~
MyFrame(){       // 생성자
    super("타이틀 문자열");
}
~~~
~~~
MyFrame(){      //생성자자
    setTitle("타이틀 문자열");
}
~~~
- 컨텐트팬에 컴포넌트 달기
    - 컨텐트팬이란? 스윙 컴포넌트들이 부착되는 공간
    - 컨텐트팬 알아내기 : 스윙 프레임에 붙은 디폴트 컨텐트팬 알아내기
 ~~~   
    public class MyFrame extends JFrame{
    MyFrame(){
        ...
        //프레임의 컨텐트팬을 알아낸다.
        Container contentPane = getContentPane();
    }
    ...
}
~~~
    - 컨텐트팬에 컴포넌트 붙이기
    ~~~
    JButton button = new JButton("Click");
    contentPane.add(button);     //컨텐트팬에 버튼 부착
    ~~~
    - 컨텐트팬 변경
~~~
class MyPanel extends JPanel{
    ...    // JPanel을 상속받은 패널을 구현
} 
// frame의 컨텐트팬을 MyPanel 객체로 변경
frame.setContentPane(new MyPanel());
~~~
## 컨텐트팬에 대한 JDK 1.5 이후의 추가사항
- JDK 1.5 이전
    - 프레임의 컨텐트팬을 알아내어 반드시 컨텐트팬에 컴포넌트 부착
    Container c = frame.getContnetPane();
~~~    
    c.add(new JButton("Click")); // 컨텐트팬에 직접 컴포넌트 부착 
~~~
- JDK 1.5 이후 추가된 사항
    - 프레임에 컴포넌트를 부착하면 프레임이 대신 컨텐트팬에 부착
~~~
frame.add(new JButton("Click"));
// 프레임이 버튼 컴포넌트를 컨텐트팬에 대신 부착
~~~
- 결론
    - JDK 1.5 이전 처럼 직접 컨텐트팬에 컴포넌트를 부착하는것이 바람직
    - 컨텐트팬 다루기 능력이 필요하기 떄문
    - 컴포넌트의 부모가 프레임이 아닌 컨텐트팬임을 알고 명확히 사용하여야 함
    - 1.5 이후 추가된 기능으로 사용하는게 가독성이 좋으며, 컨텐트팬을 다루는 능력이 반드시 필요한것은 아님

~~~
import javax.swing.*;
import java.awt.*;

public class ExContentPane extends JFrame {
    public ExContnetPane() {
        setTitle("ContentPane과 JFrame 예제");      // 프레임의 타이틀 달기 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentpane = getContentPane();       //컨텐트팬 알아내기
        contentPane.setBackground(Color.ORANGE);    //오렌지색 배경 설정
        contentPane.setLayout(new FlowLayout());    //컨텐트팬에 flowlayout
                                                  //              배치관리자 달기 
        contentPane.add(new JButton("OK"));     //ok 버튼 달기
        contentPane.add(new JButton("Cancel"));   // cancel 버튼달기
        contentPane.add(new JButton("Ignore"));    // ignore 버튼 달기

        setSize(width:300,height:150);    //프레임 크기 300x 150 설정
        setVisible(true);        //화면에 프레임 출력
    }
}
~~~
## Swing 프로그램의 종료
- 응용프로그램 내에서 스스로 종료하는 방법
    - 언제 어디서나 무조건 종료
    ~~~
    System.exit(0);
    ~~~
- 프레임의 오른쪽 상단의 종료버튼이 클릭되면 어떤일이 일어나는가?
    - 프레임 종료, 프레임 윈도우를 닫음 : 프레임이 화면에서 보이지 않게됨
- 프레임이 보이지 않게 되지만 응용프로그램이 종료된것은 아님
    - 키보드나 마우스 입력을 받지 못함
    - 다시 setVisible(true)를 호출하면 보이게되고 이전처럼 작동함
- 프레임 종료버튼이 클릭될때, 프레임과 함께 프로그램을 종료시키는 방법
    ~~~
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ~~~
## 컨테이너와 배치, 배치 관리자 개념
- 컨테이너의 배치관리자
    - 컨테이너마다 하나의 배치관리자 존재
    - 컨테이너에 부착되는 컴포넌트의 위치와 크기 결정
    - 컨테이너의 크기가 변경되면, 컴포넌트의 위치와 크기 재결정
## 배치 관리자 대표 유형 4가지
- FlowLayout 배치 관리자
    - 컴포넌트가 삽인되는 순서대로 왼쪽에서 오른쪽으로 배치
    - 배치할 공간이 없으면 아래로 내려와서 반복한다
- BorderLayout 배치관리자
    - 컨테이너의 공간을 동,서,남,북,중앙의 5개 영역으로 나눔
    - 5개 영역중 응용프로그램에서 지정한 영역에 컴포넌트 배치
- GridLayout 배치관리자
    - 컨테이너를 프로그램에서 설정한 동일크기의 2차원 격자로 나눔
    - 컴포넌트는 삽입 순서대로 좌에서 우로, 다시 위에서 아래로 배치
- CardLayout 
    - 컨테이너의 공간에 카드를 쌓아 놓은듯이 컴포넌트를 포개어 배칯

## 컨테이너와 디폴트 배치관리자
- 컨테이너의 디폴트 배치관리자 : 컨테이너 생성시 자동으로 생성되는 배치관리자

AWT와 스윙 컨테이너  | 디폴트트 배치관리자
---|---
Window,JWindow   | BorderLayout
Frame,Jframe   | BorderLayout
Dialog,JDialog | BorderLayout
Panel,JPanel   | FlowLayout
Applet,JApplet | FlowLayout

## 컨테이너에 새로운 배치관리자 설정
- 컨테이너에 새로운 배치관리자 설정
    - setLayout(LayoutManager lm) 메소드 호출: lm을 새로운 배치관리자로 설정
- 사례 
    - JPanel 컨테이너에 BorderLayout 배치관리자를 설정하는 예
    ~~~
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());    // JPanel에 BorderLayout 설정
    ~~~
    - 컨텐트팬의 배치관리자를 FlowLayout 배치관리자로 설정
    ~~~
    Container c = frame.getContentPane();   // 프레임의 컨텐트팬 알아내기
    c.setLayout(new FlowLayout());   //컨텐트팬에 FlowLayout 설정
    ~~~
    - 오류 
    ~~~
    c.setLayout(FlowLayout);    // new 를 빠뜨렸기에 오류 
    ~~~
## FloewLayout 배치관리자
- 배치방법 : 
    - 컴포넌트를 컨테이너 내에 왼쪽에서 오른쪽으로 배치
    - 다시 위에서 아래로 순서대로 배치
    ~~~
    container.setLayout(new FlowLayout());
    container.add(new JButton("add"));
    container.add(new JButton("suv"));
    container.add(new JButton("mul"));
    container.add(new JButton("div"));
    container.add(new JButton("Calculate"));
    ~~~

## FlowLayout의 생성자
- 셍성자 : 
    - FlowLayout()
    - FlowLayout(int align, int hGap, int vGap)
- align : 컴포넌트를 정렬하는 방법 지정. 왼쪽 정렬(FlowLayout.LEFT),오른쪽 정렬 (FlowLayout.RIGHT),중앙정렬 (FlowLayout.CENTER(디폴트))
- hGap : 좌우 두 컴포넌트 사이의 수평간격 , 픽셀단위. 디폴트는 5
- vGap : 상하 두 컴포넌트 사이의 수직 간격, 픽셀단위. 디폴트는 5
## BorderLayout 배치관리자
- 배치방법
    - 컨테이너 공간을 5 구역으로 분할,배치 : 동서남북 중앙
    - add(Component comp, int index) : comp를 index의 공간에 배치
    ~~~
    container.setLayout(new BorderLayout());
    container.add(new JButton("div"), BorderLayout.WEST);
    container.add(new JButton("Calculate"), BorderLayout.CENTER);

    ~~~
## GridLayout 배치관리자
- 배치방법
    - 컨테이너 공간을 동일한 사각형 격자(그리드)로 분할하고 각셀에 컴포넌트 하나씩 배치
        - 생성자에 행수와 열수 지정
        - 셀에 왼쪽에서 오른쪽으로, 다시 위에서 아래로 순서대로 배치
        ~~~
        container.setLayout(new GridLayout(4,3,5,5));   //4x3 분할로 컴포넌트 배치
        container.add(new JButton("1"));   //상단 왼쪽 첫번째 버튼 셀에 버튼배치
        container.add(new JButton("2"));   //그 옆 셀에 버튼배치
        ~~~
        - 4x3 그리드 레이아웃 설정
        - 총 11개의 버튼이 순서대로 add됨
        - 수직 간격 5 픽셀
        - 수평 간격 5 픽셀

## GridLayout 생성자
- 생성자
    - GridLayout()
    - GridLayout(int rows, int cols)
    - GridLayout(int rows,int cols, int hGap, intvGap)
        - rows : 격자의 행수 (디폴트 1)
        - cols : 격자의 열수 (디폴트 1)
        - hGap : 좌우 두 컴포넌트 사이의 수평간격, 픽셀단위 ( 디폴트 0 )
        - vGap : 상하 두 컴포넌트 사이의 수직 간격, 픽셀단위 ( 디폴트 0 )
        - row x cols 만큼의 셀을 가진 격자로 컨테이너 공간을 분할, 배치치

## 배치관리자 없는 컨테이너
- 배치관리자가 없는 컨테이너가 필요한 경우
    - 응용프로그램에서 직접 컴포넌트의 크기와 위치를 결정하고자 하는경우
        1. 컴포넌트의 크기나 위치를 개발자 임의로 결정하고자 하는경우
        2. 게임 프로그램과같이 시간이나 마우스/키보드의 입력에 따라 컴포넌트들의 위치와 크기가 수시로 변할수 있는 경우
        3. 여러 컴포넌트들이 서로 겹쳐 출력하고자 하는경우
- 컨테이너의 배치 관리자 제거 방법
    - container.setLayout(null);
    ~~~
    JPanel p = new JPanel();
    p.setLayout(null);    //JPanel의 배치관리자 삭제제
    ~~~
    - 컨테이너의 배치관리자가 없어지면, 컴포넌트에대한 어떤 배치도 없음
        - 추가된 컴포넌트의 크기가 0으로 설정, 위치는 예측할수 없게됨
        ~~~
        // 패널 p 에는 배치관리자가 없으면 아래 두 버튼은 배치되지 않는다
        p.add(new JButton("Click"));  // 폭과 높이가 0인상태로 화면에 보이지 않음
        p.add(new JButton("me"));  // 폭과 높이가 0인상태로 화면에 보이지 않음
        ~~~
## 컴포넌트의 절대 위치와 크기 설정
- 배치 관리자가 없는 컨테이너에 컴포넌트를 삽입할때
    - 프로그램에서 컴포넌트의 절대 크기와 위치 설정
    - 컴포넌트들이 서로 겹치게 할수 있음
- 컴포넌트의 크기와 위치설정 메소드
    - void setSize(int width, int height)   // 컴포넌트 크기설정
    - void setLocation(int x, int y ) // 컴포넌트 위치 설정
    - void setBounds(int x, int y, int width, int height) //위치,크기 동시설정
    - 예시 버튼을 100x 40 크기로 하고, JPanel 의 50,50 위치에 배치
    ~~~
    JPanel p = new JPanel();
    p.setLayout(null);   //패널 p 의 배치관리자 제거

    JButton clickButton = new JButton("Click");
    clickButton.setSize(100,40);    // 버튼 크기를 100x 40으로 설정
    clickButton.setLocation(50,50);    // 버튼 위치를 50,50으로 설정
    p.add(clickButton);    //패널 내 50,50 에 100x40 크기의 버튼 출력력
    ~~~

# 8장 
## 이벤트 기반 프로그래밍
- 이벤트 기반 프로그래밍(Event Driven Programming)
    - 이벤트의 발생에 의해 프로그램 흐름이 결정되는 방식
        - 이벤트가 발생하면 이벤트를 처리하는 루틴(이벤트 리스너)실행
        - 실행될 코드는 이벤트의 발생에 의해 전적으로 결정
    - 반대되는 개념 : 배치 실행(batch programming)
        - 프로그램의 개발자가 프로그램의 흐름을 결정하는 방식
    - 이벤트 종류
        - 사용자의 입력: 마우스 드래그, 마우스 클릭, 키보드 누름등
        - 센서로부터의 입력, 네트워크로부터 데이터 송수신
        - 다른 응용프로그램이나 다른 스레드로부터의 메시지
- 이벤트 기반 응용 프로그램의 구조 
    - 각 이벤트마다 처리하는 리스너 코드 보유
- GUI 응용프로그램은 이벤트 기반 프로그래밍으로 작성됨
    - GUI 라이브러리 종류 : C++의 MFC , C# GUI , Visual Basic, X Window, Android등
    - 자바의 AWT와 SwING
## 자바 스윙 프로그램에서 이벤트 처리 과정
1. 이벤트 발생
    - 예 : 마우스의 움직임 혹은 키보드 입력
2. 이벤트 객체 생성
    - 현재 발생한 이벤트에 대한 정보를 가진 객체
3. 응용프로그램에 작성된 이벤트 리스너 찾기
4. 이벤트 리스너 실행
    - 리스너에 이벤트 객체 전달
    - 리스너 코드 실행
## 이벤트 객체
- 이벤트 객체
    - 발생한 이벤트에 관한 정보를 가진 객체
    - 이벤트 리스너에 전달됨 
        - : 이벤트 리스너 코드가 발생한 이벤트에 대한상황을 파악할수 있게함
- 이벤트 객체가 포함하는 정보
    - 이벤트 종류와 이벤트 소스
    - 이벤트가 발생한 화면좌표및 컴포넌트 내 좌표
    - 이벤트가 발생한 버튼이나 메뉴 아이템의 문자열
    - 클릭된 마우스 버튼 번호및 마우스의 클릭횟수
    - 키의 코드값과 문자값
    - 체크박스, 라디오 버튼등과같은 컴포넌트에 이벤트가 발생하였다면 체크상태
- 이벤트 소스를 알아내는 메소드 : Object getSource()
    - 발생한 이벤트의 소스 컴포넌트 리턴
    - Object 타입으로 리턴하므로 캐스팅하여 사용
    - 모든 이벤트 객체에 대해 적용
## 리스너 인터페이스 
- 이벤트 리스너 : 이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성
- 자바는 다양한 리스너 인터페이스 제공
    - EX ) ActionListener 인터페이스 - 버튼 클릭이벤트를 처리하기위한 인터페이스
    - EX ) MouseListner 인터페이스 - 마우스 조작에 따른 이벤트를 처리하기위한 인터페이스
- 사용자의 이벤트 리스너 작성
    - 자바의 리스너 인터페이스를 상속받아 구현
    - 리스너 인터페이스의 모드 추상 메소드 구현
## 이벤트 리스너 작성과정 사례
1. 이벤트와 이벤트 리스너 선택
    - 버튼 클릭을 처리하고자 하는경우 
        - 이벤트: action이벤트 , 이벤트 리스너 : ActionListener 
2. 이벤트 리스너 클래스 작성 : ActionListner 인터페이스 구현
    - 이벤트 리스너 클래스 작성 > 리스너 인터페이스를 상속받는 클래스를 작성하고 추상메소드 모두 구현현
3. 이벤트 리스너 등록
    - 이벤트를 받아 처리하고자 하는 컴포넌트에 이벤트 리스너 등록
    - component.addXXXListener(listener)
        - xxx : 이벤트 명, listener : 이벤트 리스너 객체
        ~~~
        MyActionListener listener = new MyActionListener(); //리스너 인스턴스 생성
        btn.addActionListener(listener);   // 리스너 등록록
        ~~~
## 이벤트 리스너 작성 방법
## 3가지 방법
- 독립 클래스로 작성
    - 이벤트 리스너를 완전한 클래스로 작성
    - 이벤트 리스너를 여러곳에서 사용할때 적합
- 내부 클래스(inner class)로 작성
    - 클래스 안에 멤버처럼 클래스 작성
    - 이벤트 리스너를 특정 클래스에서만 사용할때 적합
- 익명 클래스(anonymous class)로 작성
    - 클래스의 이름없이 간단한 리스너 작성
    - 클래스조차 만들필요없이 리스너 코드가 간단한 경우에 적합
    

# 김동현 202130101
## 5월 22일 12주차
<br>

## StringBuffer 클래스
1. 가변 스트링을 다루는 클래스 
2. StringBuffer 객체 생성
    - StringBuffer sb = new StringBuffer("java");<br>
3. StringBuffer 클래스와 달리 문자열 변경가능<br>
    - 가변 크기의 버퍼를 가지고 있어 문자열 수정가능<br>
    - 문자열의 수정이 많은 작업에 적합<br>
    - 스트링 조작 사례
~~~
StringBuffer sb = new StringBuffer("This");

sb.append(" is pencil");   // sb= "This is pencil"
sb.insert(7,"my");         //sb = "This is my pencil"
sb.replace(8,10,"your");  // sb= "This is your pencil"
System.out.println(sb);  // "This is your pencil." 출력
~~~

## StringTokenizer
1. 구분 문자를 기준으로 문자열을 분리하는 클래스
    - 구분문자(delimiter): 문자열을 구분할때 사용되는 문자
    - 토큰(Token):구분 문자로 분리된 문자열 <br>

생성자와 메소드 | 설명 
---|---
StringTokenizer(String str)|str 스트링으로 파싱한 스트링 토크나이저 발생
int countToken( )|스트링 토크나이저에 포함된 토큰의 개수 리턴
boolean hasMoreToken( )|스트링 토크나이저에 다음 토큰이 있으면 true 리턴
String nextInt( )|다음 토큰 리턴

## Math 클래스
1. 기본 산술 연산 메소드를 제공하는 클래스
2. 모든 메소드는 static 으로 선언
    - 클래스 이름으로 호출 가능
3. Math.Random( )메소드로 난수 발생
    - rando,( )은 0보다 크거나 같고 1.0보다 작은 실수 난수 발생
    - 1에서 100까지의 랜덤 정수 10개를 발생시키는 코드 사례
    * 매우 사용률 높은 메소드들이 많다
~~~
for(int x=0; x<10; x++){
    int n = (int)(Math.random( )*100+1);  //1~100까지의 랜덤 정수 발생
    System.out.println(n);
}
~~~
- java.util.Random 클래스를 이용하여 난수 발생 가능
~~~
Random r = new Random( );
int n = r.nextInt( );         // 음수, 양수, 0 포함, 자바의 정수 범위 난수 발생
int m = r.nextInt(100);;     // 0에서 99 사이의 정수(0,99포함) 난수 발생
~~~

<br>

메소드 | 설명 
---|---
static double abs (double a)|실수 a의 절대값 리턴
static double exp (double a)|e의 a승 값 리턴
static double sin (double a)|실수a의 sine 값 리턴
static double random (double a)|0.0보다 크거나 같고 1.0보다 작은 임의의 실수 리턴
static long round (double a)|실수 a를 소수 첫째 자리에서 반올림한 정수를 long타입으로 반환
static double sqrt (double a)|실수 a의 제곱근 리턴

# 7장 컬렉션과 제네릭
## 컬렉션(collection)의 개념
1. 요소(element)라고 불리는 가변 개수의 객체들의 저장소
    - 객체들의 컨테이너 라고 불림
    - 요소의 개수에 따라 크기 자동조절
    - 요소의 삽입,삭제에 따른 요소의 위치 자동 이동
2. 고정 크기의 배열을 다루는 어려움 해소
3. 다양한 객체들의 삽입, 삭제, 검색등의 관리 용이 

## 컬렉션의 특징
1. 컬렉션은 제네릭(generic) 기법으로 구현
2. 제네릭 
    - 특정 타입만 다루지않고 ,여러종류의 타입으로 변할수 있도록 클래스나 메소드를 일반화 시키는 기법
    - 클래스나 인터페이스 이름에 < E > < K > < V >등 타입 매개변수 포함

    - JDK 1.5 부터 도입(2004년 기점)
    - 모든 종류의 데이터 타입을 다룰수 있도록 일반화된 타입 매개 변수로 클래스(인터페이스)나 메소드를 작성하는 기법
    - c++의 템플릿과 동일    
3. 제네릭 컬렉션 사례 : 벡터 VECTOR < E >
    - < E >에서 E에 구체적인 타입을 주어 구체적인 타입만 다루는 벡터로 활용
    - 정수만 다루는 컬렉션 벡터 Vector < Interger >
    - 문자열만 다루는 컬렉션 Vector < String >
4. 컬렉션의 요소는 객체만 가능 
    - int char double 등의 기본타입으로 구체화 불가
    - 컬렉션 사례 
        ~~~
        Vector<int> v = new Vector<int>():             // 컴파일 오류 int 사용불가
        Vector<Integer> v = new Vector<Interger>();     //정상 코드
        ~~~


## Vector< E >의 특성
1. < E >에 사용할 요소의 특정 타입으로 구체화
2. 배열을 가변크기로 다룰수 있게 하는 컨테이너
    - 배열의 길이제한 극복
    - 요소의 개수가 넘치면 자동으로 길이 조절
3. 요소객체들을 삽입,삭제,검색하느 컨테이너
    - 삽입,삭제에 따라 자동으로 요소의 위치조정
4. Vector 에 삽입 가능한 것
    - 객체 , null
    - 기본 타입의 값은 Wrapper 객체로 만들어 저장
5. Vector에 객체삽입
    - 벡터의 맨 뒤, 중간에 객체삽입 가능
6. Vector에서 객체삭제
    - 입의의 위치에 있는 객체 삭제 가능
- 타입 매개 변수를 사용하지 않는 경우 경고 발생
    - Vector< Integer >나 Vector< String > 등 타입 매개 변수를 사용하여야함

## 컬렉션과 자동 박싱/언박싱
1. JDK 1.5 이전 
    - 기본 타입 데이터를 Wrapper 객체로 만들어 삽입
    Vector < Integer > v = new Vector< Integer >();
    v.add(Integer.valueOf(4));
    - 컬렉션으로부터 요소를 얻어올때, Wrapper 클래스로 캐스팅 필요
    Interger n = (Integer) v.get(0);
    int k = n.intValue();    // k = 4
2. JDK 1.5 이후 
    - 자동 박싱/ 언박싱이 작동하여 기본타입 값 삽입가능
    -그러나 타입매개변수를 기본 타입으로구체화 할수는 없음
    Vector< Int >v= 

## 컬렉션 생성문의 진화: java 7 , 10 
1. java 7 이전
    ~~~
    Vector < Integer > v = new Vector < Integer >();       // java 7 이전
    ~~~
2. java 7 이후 
    - 컴파일러의 타입 추론 기능 추가
    - <> (다이아몬드 연산자)에 타입 매개변수 생략
        ~~~
        vector < Integer > v = new Vector <> ();   // java 7 부터 추가, 가능
        ~~~
3. java 10 이후 
    - var 키워드 도입, 컴파일러의 지역 변수 타입 추론 가능
        ~~~
        var v = new Vector< Integer >();    // java10부터 추가 , 가능
        ~~~


## ArrayList < E >
1. 가변 크기 배열을 구현한 클래스 
    - < E >에 요소로 사용할 특정 타입으로 구체화
2. 벡터와 거의 동일
    - 요소 삽입,삭제,검색등 벡터기능과 거의 동일
    - 벡터와 달리 스레드 동기화 기능 없음
    - 다수 스레드가 동시에 ArrayList에 접근할때 동기화 되지 않음
    - 개발자가 스레드 동기화 코드 작성
        ~~~
        ArrayList<String> = new ArrayList<String>();
        ~~~

## ArrayList < E > 클래스의 주요 메소드
메소드 | 설명 
---|---
boolean add(E element)|ArrayList의 맨 뒤에 element 추가
void add(int index, E element)|인덱스 index에 지정된 element 삽입
boolean addAll(Collection<? extends E> c|컬렉션 c의 모든 요소를 ArrayList의 맨 뒤에 추가 
void clear()|ArrayList의 모든 요소 삭제
boolean contains(Object o)|ArrayList가 지정된 객체를 포함하고 있으면 true 리턴
E elementAt(int index)|index 인덱스의 요소 리턴
E get(int index)|index 인덱스의 요소 리턴
int indexOf(Object o)|o와 같은 첫 번째 요소의 인덱스 리턴. 없으면 -1 리턴
oolean isEmpty()|ArrayList가 비어 있으면 true 리턴
E remove(int index)|index 인덱스의 요소 삭제
boolean remove(Object o)|o와 같은 첫 번째 요소를 ArrayList에서 삭제
int size()|ArrayList가 포함하는 요소의 개수 리턴
Object[]toArray()|ArrayList의 모든 요소를 포함하는 배열 리턴

## ArrayList와 Vector 의 차이
1. ArrayList와 Vector는 모두 동적으로 크기가 늘어나는 배열 기반의 리스트 클래스 
## 비교요약

항목| arraylist |vector
---|---|---
동기화 여부 |비동기화 스레드 안전x |동기화 스레드 안전o 
성능 |빠름 싱글 스레드에 적합 |느림 동기화로 인한 오버헤드 발생 
기본 크기 증가 | 1.5배씩 증가(newCapacity = old+ old/2)| 2배씩 증가
도입 시기| java1.2 (Collection Framework)| java1.0 (초기부터 존재)
사용권장 여부| 현대개발에서 추천 |특별한 이유가 없다면 지양양
2. 요즘은 ArrayList가 기본 선택지
3. Vector는 이제 거의 사용하지 않고, 멀티 스레드가 필요하면 다른 방법을 사용함
    - synchronizedList , copyOnWriteArrayList 등 
## 컬렉션의 순차 검색을 위한 Iterator
1. Iterator < E > 인터페이스
    - 리스트 구조의 컬렉션에서 요소의 순차 검색을 위한 인터페이스
    - Vector e ArrayList e , LinkedList e 가 상속받는 인터페이스
2. Iterator 객체 얻어내기
    - 컬렉션의 Iterator()메소드 호출: 해당 컬렉션을 순차 검색할 수 있는 Iterator 객체 리턴 
    ~~~
    Vector< Integer > v = new Vector < Integer >{};
    Iterator< Integer > it = v.iterator();
    ~~~
    ~~~
    while(it.hasNext()){        // 모든 요소 방문
        int n = it.next();      // 다음 요소 리턴
        ...
    }
    ~~~
3. 컬렉션 검색 코드 

    메소드 | 설명
    ---|---
    boolean hasNext() |다음요소 반복에서 사용할 요소가 있으면 true 리턴
    E next() |다음요소 리턴
    void remove() |마지막으로 리턴된 요소 제거 

## HashMap < K,Y >
1. 키(Key)오 값(value)의 쌍으로 구성되는 요소를 다루는 컬렉션
    - k : 키로 사용할 요소의 타입
    - v : 값으로 사용할 요소의 타입
    - 키와 값이 한쌍으로 삽입
    - 값을 검색 하기 위해서는 반드시 키 이용
2. 삽입및 검색이 빠른 특징
    - 요소 삽입 : put()메소드
    - 요소 검색 : get()메소드
## ex) HashMap < String, String > 생성, 요소삽입,요소검색 
~~~
HashMap < String, String > h = new HashMap< String,String>();   //해시맵 객체 생성

h.put(:apple","사과");      //"apple"키와 "사과" 값의 쌍을 해시맵에삽입
String kor = h.get("apple");    //"apple" 키로 값 검색. kor = "사과"
~~~

## HashMap< K , y >클래스의 주요 메소드
메소드 | 설명
---|---
void clear()|HashMap의 모든 요소 삭제
boolean containskey(Object key)|지정된 키를 포함하고있으면 true 리턴
boolean containsValue(Object key)|하나 이상의 키를 지정된 값에 매핑시킬수 있으면 true 리턴
v get(Object key)|지정된 키에 매핑되는 값리턴, 키에매핑되는 어떤값도없으면 null리턴
boolean isEmpty()|HashMap이 비어있으면 true 리턴
set< k >keyset()|HashMap에 있는 모든 키를 담은 Set< K >컬렉션 리턴
V put (k key ,V value)|key와 value 를 매핑하여 HashMap에 지정
V remove(Object key)|지정된 key 와 이에 매핑된 값을 HashMap에서 삭제
int size()|HashMap에 포함된 요소의 개수 리턴턴


## 제네릭 만들기
1. 제네릭 클래스 작성: 클래스 이름 옆에 일반화된 타임 매개변수 추가
    ~~~
    public class MyClass< T >{        //제네릭 클래스 MyClass 선언 , 타입 매개변수 T
        T val;                        // val 의 타입은 T 
        void set(T a){
            val = a;                  //T 타입의 값a 를 val 에 저장장
        }
        T get(){
            return val;              // T 타입의 값 val 리턴턴
        }
    }
    ~~~
2. 제네릭 객체 생성및 활용
    - 제네릭 타입에 구체적인 타입을 지정하여 객체를 생성하는것을 구체화라고함 
        ~~~
        MyClass<String> s = new MyClass<String>();   // T를 String으로 구체화
        s.set("hello");
        System.out.println(s.get());   // hello 출력

        MyClass< Integer> n = new MyClass <Integer>();   // T를 Integer로 구체화
        n.set(5);
        System.out.println(n.get());     // 숫자 5 출력
        ~~~
## 제네릭 스택 만들기 예제 
~~~
class GStack<T>{   // 제네릭 스택 선언. 제네릭 타입 T
    int tos;
    Object [] stck;   //스택에 저장된 요소의 개수  / T[]stack; 으로 할수 없음
    public GStack(){ 
        tos = 0;
        stck = new Object [10];  // new T[10];로 할수 없음 제네릭 타입의 배열생성 불가
    }
    public void push(T item){
        if(tos == 10)   // 스택이 꽉차서 더이상 요소를 삽입 할수 없음
            return;
        stck[tos] = item;
        tos++;
    }
    public T pop(){
        if(tos == 0)   // 스택이 비어있어 꺼낼 요소가 없음
            return null;
        tos --;
        return (T)stck[tos];  // 타입 매개변수 타입으로 캐스팅팅
    }
}

public class MyStack{
    public static void main(String[] args) {
        GStack<String> stringStack = new GStack<String>(); // String 타입의 GS 생성
        stringStack.push("seoul");
        stringStack.push("busan");
        stringStack.push("LA");

        for( int n=0; n <3 ; n ++)
            System.out.println(stringStack.pop()); //stringStack 스택에 있는 
                                                    문자열 팝
        GStack<Integer> intStack = new GStack<Integer>();   // Integer 타입의 GStack 생성
        intStack.push(1);
        intStack.push(3);
        intStack.push(5);
        
        for(int n=0; n<3; n++)
            System.out.println(intStack.pop()); //intstack 스택에 있는 3개의 정수 팝

    }
}
~~~

# 8장 자바 GUI 스윙 기초 
## 자바의 GUI 
- GUI : 사용자가 편리하게 입출력 할 수 있도록 그래픽으로화면을 구성하고, 마우스나 키보드로 입력 받을 수 있도록 지원하는 사용자 인터페이스 
- 자바 언어에서 GUI 응용 프로그램 작성 : AWT와Swing 패키지에 강력한 GUI 컴포넌트 제공.

## AWT(Abstract Windwing Toolkit )패키지 
- 자바가 처음나왔을 때 부터 배포된 GUI 패키지, 최근에는 거의 사용하지 않음
- AWT 컴포넌트는 중량 컴포넌트 (heavy weight component)
- AWT 컴포넌트의 그리기는 운영체제에 의해 이루어지며, 운영체제에 자원을 많이 소모하고 부담을 줌
- 운영체제가 직접 그리기 때문에 속도는 빠르다

## Swing 패키지
- AWT 기술을 기반으로 작성된 자바 라이브러리
- 모든 AWT 기능+ 추가된 풍부하고 화려한 고급 컴포넌트
- AWT 컴포넌트를 모두 스윙으로 재작성
- AWT 컴포넌트 이름앞에 J 자를 덧붙임
- 순수 자바언어로 구현
- 스윙 컴포넌트는 경량 컴포넌트(light weight component)
- 스윙 컴포넌트는 운영체제의 도움을 받지 않고 , 직접 그리기 때문에 운영체제에 부담을 주지 않음
- 현재 자바의 GUI 표준으로 사용됨










# 김동현 202130101 
## 5월 15일 11주차 
<br>

## package의 운영 방법
1. 패키지 이름은 도메인 기반으로 시작 형식: com.회사이름.프로젝트명.기능명
    - 충돌 방지 전세계 어디서든 유일한 패키지명 확보가능 /모듈별 분리 가능
2. 기능 역할별로 하위패키지를 구분:utils.  controller , service등
3. 디렉토리 구조와 package 선언을 정확히 일치하게
4. import는 필요한 만큼만 *전체 import는 피하는게 좋다

## 모듈 개념
1. java9 에서 도입된 개념
2. 패키지와 이미지등의 리소스를 담은 컨테이너
3. 모듈파일(.jmod )로 저장

## 자바 플랫폼의 모듈화
1. 자바 플랫폼
    - 자바의 개발 환경(jdk)과 자바의 실행 환경(jre)를 지칭. java se(자바api)포함
    - 자바 api의 모든 클래스가 여러개의 모듈로 재구성됨
    - 모듈 파일은 jdk의 jmods 디렉터리에 저장하여 배포<br><br>


2. 모듈 파일로부터 모듈을 푸는 명령
    - jmod extract "c:\program files\java\jdk-17.0.3.+7\jmods\java.base.jmod"
    - 현재 디렉터리에 java.base 모듈이 패키지와 클래스들로 풀림림

## 자바 모듈화의 목적
1. 자바 컴포넌트들을 필요에 따라 조립하여 사용하기위함
2. 컴퓨터 시스템의 불필요한 부담 감소
3. 세밀한 모듈화를 통해 필요없는 모듈이 로드되지 않게함
    - 소형IOT장치에도 자바프로그램이 실행되고 성능을 유지하게함함

## JDK의 주요 패키지
- java.lang
- java.util
- java.io
- java.awt
- javax.swing

## object 클래스
- 모든 자바 클래스는 반드시 object를 상속받도록 자동 컴파일
- 모든 클래스의 수퍼 클래스 (부모가 됨)
- 모든 클래스가 상속받는 공통 메소드 포함

## toString()메소드 , 객체를 문자열로 변환
- 각 클래스는 toString()을 오버라이딩하여 자신만의 문자열 리턴 가능
- 객체를 문자열로 반환
- 원형: public String toString();

- 컴파일러에 의한 toString()자동 변환
- 객체 +문자열 > 객체.toString()+문자열 로 자동변환
- 객체를 단독으로 사용하는 경우 > 객체.toString()으로 자동변환

## 객체 비교와(==)와 equasls() 메소드
- ==연산자 : 객체 레퍼런스 비교

- boolean equals(object obj)
- 두 객체의 내용물 비교
- 객체의 내용물을 비교하기위해 클래스의 멤버로 작성

## wrapper 클래스 
이름이 wrapper 인 클래스는 없다 하지만 8개의 기본타입을 객체로 다루기 위해
- byte,short,int,long,char,float,double,boolean <br>
- 8개의 클래스를 통칭하여 wrapper 클래스라고 부른다<br>

## 박싱과 언박싱
- 박싱: 기본타입의 값을 wrapper 객체로 변환하는것
- 언박싱: wraaper 객체에 들어있는 기본타입의 값을 빼내는것.
- 자동박싱과 자동언박싱 : JDK 1.5부터 박싱과 언박싱은 자동으로 이루어지도록 컴파일됨

## String 과 StringBuffer 클래스
1. String 생성과 특징
    - string 클래스는 문자열을 나타냄
    - string 라터럴은 string객체로 처리됨
2. 대문자로 시작 클래스 클래스는 변수랑 같이보면 같은형태로 봄 String str1 = @@@ str1 이라고하는 객체를 String (wrapper)클래스로 생성해준다  
3. @@@ 를 str1 이라는 객체에 박싱을 해주는것 

## 스트링 리터럴과 new String()
1. 스트링 리터럴 
    - 자바 가상기계 내부에서 리터럴 테이블에 저장되고 관리됨
    - 응용프로그램에서 공유됨
    - 스트링 리터럴 사례) String s = "hello";

2. new String()으로 생성된 스트링
    - 스트링 객체는 힙에생성
    - 스트링은 공유되지않음 

# 김동현 202130101
## 5월 8일 10주차
<br>

## 추상 클래스
1. abstract method 추상 메소드<br>
2. 메소드의 코드는 없고 원형만 선언 <br><br>
3. abstract class 추상 클래스 <br>
4. 추상 메소드를 가지며 abstarct로 선언된 클래스<br>
5. 추상메소드 없이 abstact로 선언한 클래스 <br>
    - 추상클래스는 객체를 생성하는게 아니라 상속을 위한 슈퍼 클래스로 활용하기위해 만든다 <br>
    - 추상클래스는 추상 메소드를 통해서 서브클래스가 구현할 메소드의 원형을 알려주는 역할<br>
    - 상속을 위해서 만드는 클래스 

### 특징
- 추상 클래스는 온전한 클래스가 아니기 떄문에 인스턴스를 생성할수 없음<br>
- Java p;          > 오류없음 추상클래스의 레퍼런스만 선언<br>
- p= new Java();   > 오류 추상클래스의 인스턴스(객체) 생성 불가<br>
- Shape obj = new Shape();  > 오류 추상클래스의 인스턴스(객체) 생성 불가<br>

### 추상 클래스의 상속
- 추상클래스를 상속받으면 추상클래스가 됨<br>
- 서브클래스도 abstract로 선언 해주어야함<br>
### 추상 클래스 구현
- 서브 클래스에서 슈퍼클래스의 추상메소드 구현(오버라이딩)<br>
- 추상 클래스를 구현한 서브클래스는 추상클래스가 아님<br>
- 추상클래스를 구현한 서브클래스는 인스턴스를 생성할수 있다 > 정상 클래스가 된다<br>
### 추상 클래스의 목적
- 상속을 위한 슈퍼클래스로 활용하는것<br>
- 서브 클래스에서 추상메소드 구현<br>
- 다형성 실현<br>
### 예시<br>
한 집단에서 여러 사람이 한가지 프로그램을 만들때 여러 개발자가 만들어놓은 
메소드 이름과 클래스 이름이 같을수있는 불상사가 일어남
그래서 팀장이 한 슈퍼 클래스를 만들어 제공해 각 직원이 이 클래스에 오버라이딩 해서 사용하라 .
추상 클래스를 상속해서 사용해 이름이 겹치는일을 방지할수 있다 

## 인터페이스의 필요성
전원 플러그에도 나라별 규격이 다르듯 소프트웨어에서도 적용된다
### 자바 인터페이스
- 구성요소 <br>
상수/추상메소드/디폴트메소드/프라이빗메소드/static 메소드 

- 인터페이스의 객체는 추상메소드 처럼 객체를 생성할수없다
### 안터페이스 상속
인터페이스에서는 다른 인터페이스를 상속할수있다 <br>
다중 상속이 가능하다 <br>
### 인터페이스 구현
인터페이스 구현은 implements 키워드를 사용하여 인터페이스의 모든 추상 메소드를 구현한 클래스를 작성하는 것을 말한다.<br><br>
예제 5-6 을 보아 
samsungphone 클래스에는 phoneinterface 인터페이스의 모든 추상 메소드를 구현했으며<br>
flash()메소드를 추가 작성하였다<br>
그리고 phoneinterface 인터페이스에 이미 구현되어있는 디폴트 메소드 printlogo()는 그대로 상속받아 사용했다 
### 인터페이스 구현과 동시에 슈퍼클래스 상속
스마트폰클래스는 칼크를 상속받고 폰인터페이스 인터페이스의 추상메소드를 모두 구현했다
이후 추가작성 메소드 스케듈 

## 6장 패키지 
### 패키지 개념과 필요성
하나의 자바 응용프로그램을 개발하기 위해 여러 개발자가 작없을 분담한다.
개발자 a b c 가 각자 동일한 이름의 tools.class라는 파일을 만들었다 가정해보자 . 파일의 중복 문제가 발생한다 이를 어떻게 해결해야할까?
Project 라는 이름의 패키지를 만들어 project 하위에 a b c 파일을 따로 만들면된다 
그렇게 되면 project / a / tools.class
project/ b / tools.class 이렇게 다른 파일로 인식하여 중복문제를 해결할수 있다
### 자바 패키지와 모듈이란
패키지 
- 서로 관련된 클래스와 인터페이스를 컴파일한 클래스 파일들을 묶어 놓은 디렉터리
- 하나의 으용ㅇ프로그램은 한개 이상의 패키지로 작성
- 패키지는 jar 파일로 압축할수 이음

모듈 
- 여러 패키지와 이미지등의 자우너을 모아놓은 컨테이너
- 하나의 모듈을 하나의 .jmod 파일에 저장

자바9부터 모듈화 도입
- 플랫폼의 모듈화 : 자바 9 부터 자바api 의 모든 클래스들을 패키지기반에서 모듈들로 완전히 재구성
- 응용프로그램의 모듈화 : 클래스들은 패키지로 만들고 다시 패키지를 모듈로 만듦 모듈 프로그래밍은 어렵고 복잡.


모듈하의 목적
- 자바 컴포넌트들을 필요에 따라 조립하여 사용하기 위함이다. 
- 세밀한 모듈화를 통해 필요없는 모듈이 로드되지 않게 하여 컴퓨터 시스템에 불필요한 부담을 줄인다 
- 특히 하드웨어가 빈약한 소형 iot 장치에서도 자바 응용프로그램이 실행되고 성능을 유지하게 한다 .

- 모듈화 작업은 매우 중요한 개념이며 소규모 프로젝트부타 적용해야 대형프로젝트 쉽게 도입,활용할수있음.

파워 셸 
dir 입력 

### 패키지 사용하기 import 문 
다른 패키지에 작성된 클래스 사용 
- 소스에 클래스이름의 완전 경로명 사용

필요한 클래스만 임포트
- 소스 시작 부분에 클래스의 경로명 임포트
- 임포트 패키지.클래스 
- 소스에는 클래스명만 명시하면 됨

패키지 전체를 임포트
- 소스시작부분에 패키지의 경로명.*import
- import 패키지.*
- 소스에는클래스 명만 명시하면됨
- import java.util.*;
- java.util 패키지 내의 모든 클래스만을 지정, 하위 패키지의 클래스는 포함하지 않음

### 패키지 만들기 >교재 jscode / 

이클립스로 만들기 단락
클래스 파일이 저장되는 위치는?
- 클래스나 인터페이스가 컴파일되면 클래스파일 생성'
- 클래스 파일은 패키지로 선언된 디렉터리에 저장
패키지 선언 
- 소스파일의 맨 앞에 컴파일 후 저장될 패키지 지정 > package 패키지명;

디폴트 패키지 
package 선언문이 없는 자바소스 파일의 경우
- 컴파일러는 클래스나 인터페이스를 디폴트 패키지에 소속시킴
- 디폴트 패키지 > 현재 디렉터리



# 김동현 202130101
## 4월18일 9주차
<br>

## static 메소드의 제약조건
- static메소드는 오직 static멤버만 접근할수 있다<br>

static메소드는 객체가 생성되지 않은 상황에서도 사용이 가능하므로 객체에 속한 인스턴스 메소드, 인스턴스 변수등을 사용할수없고 static멤버들만 사용가능하다.

- static메소드는 this를 사용할수 없다<br>

static메소드는 객체없이도 존재하기때문에 static메소드에서 this를 사용할수없다
## final<br><br> 
### final 키워드의 3가지 용도
> 클래스 
- final이 클래스 앞에 사용되면 클래스를 상속받을수 없음을 지정한다
> 메소드
- final이 메소드 앞에 사용되면 이 메소드는 더이상 오버라이딩 할 수 없음을 지정한다
> 필드
- final로 피드를 선언하면 필드는 상수가 된다 
- 상수 필드는 한번 초기화 되면 더이상 값을 변경할수없다
- final 키워드를 public static 과 함께 선언하면 프로그램 전체에서 공유할수있는 상수가 된다.

# 5장 상속
<br>

## 상속의 필요성 
개발자가 공통적인 코드가 들어있느 여러개의 클래스를 작성할때 한 멤버를 수정하려할때<br> 그 멤버를 공통적으로 포함하고있는 다른 클래스의 멤버들도 함께 수정해주어야 한다 <br>
그때 상속을 이용하면 이문제는 간단히 해결된다 <br>여러 클래스의 공통된 코드를 추려 새로운 클래스를 작성해 묶고 <br>나머지 클래스를 상속 클래스로 선언하면 코드를 중복작성할필요없이 물려받기만 하면된다<br>
> 상속은 클래스사이에 코드중복을 제거하여 클래스를 간결하게 구현할수 있게 한다
## 상속의 장점 
- 클레스 사이의 멤버 중복선언 불필요
- 클래스의 계층적 분류로 클래스 관리 용이
- 클래스의 재사용과 확장을통한 소프트웨어 생산성 향상
## 클래스 상속과 객체
- 상속선언 extends 키워드 사용
- 부모클래스를 물려받아 자식클래스를 확장한다는의미
- 부모클래스 = 수퍼클래스(super class)
- 자식클래스 = 서브클래스(sub class) 
## 자바 상속의 특징
> 클래스 다중 상속 불허
- 하나의 클래스가 둘이상의 부모클래스를 동시에 상속받는 것을 말함.
- c++ 는 다중상속가능 
- c++ 는 다중상속으로인한 멤버가 중복생성되는 문제 있음.
- 부모 클래스간에 계층적 관계가 있을경우 중복된 멤버가 생성될수 있다.
- 모호성문제 :두 부모클래스에 동일한 이름의 멤버가 존재할경우 어떤부모의 멤버를 호출해야할지 모호해짐.
> 자바는 인터페이스의 다중상속허용 
- 다중상속과 유사한 기능을 제공함
> 모든 자바 클래스는 묵시적으로 Object클래스 상속받음
- java. lang.Object 는 모든 클래스의 부모 클래스 (슈퍼 클래스)

## 슈퍼 클래스의 멤버에 대한 서브클래스의 접근
- 슈퍼 클래스의 private 멤버 > 서브클래스에서 접근할수없음<br>
- 슈퍼 클래스의 디폴트 멤버 > 서브 클래스가 동일한 패키지에 있을때 접근가능<br>
- 슈퍼 클래스의 public멤버 > 서브 클래스는 항상 접근 가능<br>
- 슈퍼 클래스의 protect 멤버 같은 패키지 내의 모든 클래스 접근 허용 >>패키지 여부와 상관없이 서브 클래스는 접근가능

## 상속과 생성자
<br>

## 서브 클래스와 슈퍼클래스의생성자 호출및 실행
- 서브클래스의 객체가 생성될때 : 슈퍼 클래스 생성자와 서브클래스 생성자 모두실행
- 호출순서: 서브클래스의 생성자 먼저 호출 >슈퍼클래스 생성자 호출
- 실행순서: 슈퍼클래스의 생성자 먼저 실행 >서브클래스 생성자 실행

## 서브클래스에서 슈퍼클래스 생성자 선택
- 슈퍼 클래스와 서브 클래스 :각각 여러개의 생성자 작성가능
- 서브클래스의 객체가 생성될때 : 슈퍼 클래스 생성자 1개와 서브클래스 생성자 1개가 실행
- 서브 클래스의 생성자와 슈퍼 클래스의 생성자가 결정되는 방식
> 개발자의 명시적 선택
- 서브 클래스 개발자가 슈퍼클래스의 생성자 명시적 선택
- super()키워드를 이용하여 선택
> 컴파일러가 기본 생성자 선택
- 서브 클래스 개발자가 슈퍼 클래스의 생성자를 선택하지 않는 경우
- 컴파일러가 자동으로 슈퍼 클래스의 기본 생성자 선택
## super()를 이용하여 명시적으로 슈퍼클래스의 생성자 선택
- super() : 서브 클래스에서 명시적으로 슈퍼 클래스의 생성자 선택 호출
> 사용방식 
- super(parameter);
- 인자를 이용하여 슈퍼클래스의 적당한 생성자 호출
- 반드시 서브클래스 생성자코드의 제일 첫라인에 와야함 
- super()를 활용한 예제 SuperEX.java

<br>
****** 5-2 예제를 실행할때 오류 발생 이유 5-1 때 동일한 이름의 클래스가 존재하기때문에 5-1이나 5-2 클래스 이름 둘중하나를 바꿔줘야 정상적으로 실행됨.

## 업캐스팅 
> 하위 클래스의 레퍼런스는 상위 클래스를 가리킬수 없지만 상위클래스의 레퍼런스는 하위 클래스를 가리킬수 있다.
- 생물이 들어가는 박스에 사람이나 코끼리를 넣어도 무방
- 사람이나 코끼리 모두 생물을 상속받았기 때문
#### 업캐스팅이란? 
- 서브클래스의 레퍼런스를 슈퍼클래스레퍼런스에 대입
- 슈퍼클래스 러퍼런스로 서브클래스 객체를 가리키게 되는현상
<br>

class Person{ } //슈퍼 클래스 <br>
class Student extends Person{ }<br>
<br>
>Person p;<br>
Student s = new Student( );<br>
p = s; //업캐스팅<br><br>

업 캐스팅 예시
>person[] people = new person[3]<br>
people[0] = new student ("홍길동")<br>
people[1] = new student ("김영희") <br>
people[2] = new person ("이순신")<br>

- 여러 자식 클래스를 하나의 부모타입으로 다루기위해 사용한다
- 이렇게하면 공통된 타입으로 여러자식클래스를 한배열에 담을수있다
- 대신 접근은 person 수준에서만 가능하다

## 다운캐스팅
- 슈퍼 클래스 레퍼런스를 서브클래스 레퍼런스에 대입
- 업캐스팅된것을 다시 원래대로 되돌리는것 
- 반드시 명시적 타입변환 지정이 필요함
## 업캐스팅 레퍼런스로 객체 구별?
- 업캐스팅된 레퍼런스로는 객체의 실제타입을 구분하기 어려움
- 슈퍼 클래스는 여러 서브 클래스에 상속되기 때문
- p 가 person인지 student인지 professor 인지 구분하기 어려움
## instance of 연산자
### 객체 레퍼런스 instanceof 클래스 타입 
-레퍼런스가 가리키는 객체의 타입 식별 : 연산의 결과는 true/false의 불린 값으로 변환
## 메소드 오버라이딩 
- 서브 클래스에서 슈퍼클래스의 메소드 중복작성
- 슈퍼 클래스의 메소드 무력화, 항상 서버 클래스에 오버라이딩한 메소드가 실행되도록 보장됨
- 메소드 무시하기로 변역되기도함
- 오버라이딩 조건 > 슈퍼 클래스의 메소드의 원형(메소드 이름, 인자타입 및 개수 , 리턴 타입 )과 동일하게 작성

## 오버라이딩의 목적
- 오버라이딩으로 다형성 실현
- 하나의 인터페이스(같은이름)에 서로다른 구현
- 슈퍼클래스의 메소드를 서브 클래스에서 각각 목적에 맞게 다르게 구현
- 사례5-18 shape의 draw() 메소드를 line rect cicle 에서 오버라이딩하여 다르게 구현
## 동적 바인딩
- 실행할 메소드를 컴파일시에 결정하지않고 실행시에 결정하는것
- 자바에서는 동적바인딩을 통해 오버라이딩된 메소드가 항상 실행되도록 보장한다.
## 오버로딩과 오버라이딩
| 비교요소 | 오버로딩 | 오버라이딩 |
|---|---|---|
| 선언 | 같은 클래스나 상속관계에서 동일한 이름의 메소드 중복작성 | 서브클래스에서 슈퍼클래스에 있는 메소드와 동일한 이름의 메소드 재작성 |
| 관계 | 동일한 클래스 내 혹은 상속 관계 | 상속관계 |
| 목적 |이름이 같은 여러개의 메소드를 중복선언하여 사용의 편리성 향상 | 슈퍼클래스에 구현된 메소드를 무시하고 서브클래스에서 새로운기능의 메소드를 재정의하고자함 |
| 조건 |메소드이름은 반드시 동일함 메소드의 인자의 개수나 인자의 타입이 달라야 성림 | 메소드의 이름 인자의 타입 인자의 개수 리턴타입등이 모두동일하여야성림 |
| 바인딩 |정적바인딩 컴파일시에 중복된 메소드 중호출되는 메소드 결정 | 동적바인딩 실행시간에 오버라이딩된 메소드 찾아 호출 |
<br><br><br>

# 김동현 202130101
## 4월 17일 7주차 
<br>

## 생성자
생성자는 객체가 생성될때 초기화를 위해 실행되는 메소드<br>
## 생성자의 특징
- 생성자의 이름은 클래스 이름과 동일하게 지어야함<br>
- 생성자는 몇개가 있어도 상관없다<br>
- 매개변수의 개수 혹은 매개변수의 데이터 타입이 다르면 된다<br>
## 기본 생성자<br>
기본생성자란 매개변수가 없고 또한 실행코드가 없어 아무일도하지않고 단순 리턴하는 생성자 이다 디폴트 생성자라고도 부른다.<br>
## 기본생성자가 자동으로 생성되는 경우 
생성자가 없는 클래스는 있을수 없으며, 클래스에 생성자가 하나도 선언되어 있지 않은경우에는 컬파일러는 기본생성자를 자동으로 생성한다
## 기본생성자가 자동으로 생성되지 않은경우 
생성자가 하나라도 존재하는 클래스 에는 컴파일러가 기본생성자를 자동으로 생성해주지않는다 
## this 레퍼런스 
객체 자신에 대한 레퍼런스, 컴파일러에 의해 자동으로 관리되므로 개발자는 사용하기만 하면됨
## this 레퍼런스의 활용
- this ()로 다른생성자 호출
- 같은 클래스의 다른 생성자 호출
- 생성자 내에서만 사용가능
- 생성자 코드의 제일 앞에 잇어야함
## 객체배열
객체에 대한 레퍼런스 배열
## 자바의 객체배열만들기3단계
- 1.배열 레퍼런스 변수선언
- 2.레퍼런스 배열생성
- 3.배열의 각원소 객체생성
## 배열의 원소 객체 접근
배열c 의 i 번째 객체에 접근하기위해서 c[i]레퍼런스를 사용한다
- circle 배열만들기 예제 CircleArray.java
## 메소드
자바의 메소드는 클래스의 멤버 함수로서 그림4-18 의 형식을 가지며 접근지정자를 선언한다는점외에 c/c++함수와 동일하다 
> 메소드는 클래스안에 존재해야한다
## 접근지정자 
접근지정자: 다른 클래스에서 메소드를 접근할수있는지 여부선언<br>
public/private/protected/디폴트(접근지정자 생략)<br>
## 리턴타입
리턴타입: 메소드가 리턴하는값의 데이터타입<br>
int/double/void 등
## 인자 전달 
## 기본타입의 값이 전달되는 경우 
매개 변수가 byte int char double 등 기본타입으로 선언되는 경우 호출기(caller)가 건네는 값이 메소드의 매개변수에 복사되어 잔달한다 실제 인자값은 변경되지않는다 
## 객체가 전달되는 경우 
메소드의 매개 변수가 클래스타입인경우 객체가 아니라 객체의 레퍼런스값이 전달된다
메소드 호출시 객체가 전달되는경우 객체에 대한 레퍼런스만 전달되지 객체가 통채로 복사되지않는다 
## 배열이 전달되는경우
배열이 전달되는경우도 객체가 전달되는경우와 동일하게 배열이 통채로 전달되는것이아니라 배열에대한 레퍼런스만 전달된다 
## 메소드 오버로딩 
자바에서는 클래스내에 이름이같지만 매개변수의 타입이나 개수가 서로다른 여러개의 메소드를 작성할수있다.<br>
 메소드 오버로딩=메소드 중복. <br>
 메소드오버로딩은 다형성의 한종류이다 
## 메소드 오버로딩의 조건
- 메소드이름이 동일하여야한다
- 메소드매개변수의 개수나 타입이서로달라야한다
- 메소드의 리턴타입과 접근지정자역시 메소드 오버로딩과 관계없다.

### 객체치환시 주의할점 
> 객체의 치환은 객체를 복사하는것이아니다 

## 객체의 소멸
- new로 할당받은 객체와 메모리를 jvm으로 되돌려주는 행위
- 자바는 객체소멸연산자 없음
- 객체소멸은 jvm의 고유한 역할
- c/c++에서는 할당받은 객체를 개발자가 프로그램내에서 삭제해야함
- c/c++의 프로그램작성을 어렵게 만드는 요안 
- 자바에서는 사용하지않는 객체나 배열을 돌려주는 코딩 책임으로부터 개발자 해방됨
## 가비지
할당받은 객체나 베열 메모리중에서 더 이상 사영하지않게된 메모리를 가비지라고부른다.<br>
자바플랫폼은 참조하는 레퍼런스가 하나도없는 객체나 배열을 가비지로 판단한다<br>
왜냐하면 이객체는 프로그램에서 더이상 접근할수없게 되았기 떄문이다 
## 가비지 컬렉션
가비지가 많아지면 응용프로그램에서 할당해줄수있는 가용메모리의 양이 줄어들기떄문에 가용 메모리공간이 일정수준 이하로 줄어들면 자바 가상기계는 가비지를 회수하여 가용메모리 공간을 늘린다 
이것을 가비지컬렉션이라고 부른다 
## 가비지컬렉션 강제수행 
system 또는 runtime 객체의 gc()메소드를 호출하여 자바플랫폼에 가비지 컬렉션을 요청할수있다 <br>
그러나 이문장을 호출한다고해서 즉시 가비지컬렉션이 작동되는것은 아니고 가비지컬렉션이 필요하다는 강력한 제안을 하는것에 불과하다 자바 가상기계가 전적으로 판단하여 적절한 시점에 작동시킨다
## 자바의 패키지개념 
자바에서는 상호관련있는 클래스파일을 패키지에 저장하여 관리한다
패키지는 디렉토리 혹은 폴더와 같은개념이며 자바응용프로그램은 하나이상의 패키지로 구성된다

## 접근지정자
자바의 접근 지정자4가지 private protected public 디폴트(접근지정자 생략)
## 접근 지정자의 목적 
- 클래스나 일부 멤버를 공개하여 다른 클래스에서 접근 하도록허용
- 객체지향언어의 캡슐화 정책은 멤버를 보호하는것> 접근지정은 캡슐화에 묶인 보호를 일부해제할 목적으로 사용
- 접근지정자에따른클래스나 멤버의 공개범위

| private | 디폴트 | protected | public 
|---|---|---|---|
| 외부로부터 완벽차단 | 동일패키지에 허용 | 동일패키지와 자식클래스에 허용 | 모든클래스에 허용 |


## static 과 non-static

## non-static
- 멤버는 객체마다 별도 존재 -인스턴스 멤버
- 객체 생성시에 멤버 생성됨
- 객체생길때 멤버도 생성
- 객체생성후 멤버사용가능
- 객체가 사라지면 멤버도 사라짐
- 공유되지않음 - 멤버는 객체내에 각각공간유지 
## static
- 멤버는 클래스당하나 생성
- 멤버는 객체내부가 아닌 별도의 공간 생성
- 클래스 멤버라고 부름
- 클래스 로딩시에 멤버생성
- 객체가생기기전에 이미생성
- 객체가 생기기전에도 사용가능
- 객체가사라져도 멤버는사라지지않음
- 멤버는 프로그램이 종료될때사라짐
- 동일한클래스의 모든객체들에의해 공유됨

### non-static 멤버가 객체이름으로만 활용할수있는것과는 달리 static멤버는 객체이름이나 클래스이름으로 모두활용할수있다

중간고사 > 오류잡는 문제 ,코딩

173p 그림 참조

## static 활용
- 전역변수와 전역함수를 만들때 사용
- 공유멤버를만들때  > staric 으로선언한멤버는 클래스의 객체들사이에 공유
# 김동현 202130101
## 4월 10일 6주차

## 예외
실행중 오작동이나 예상치못한 상황 발생을 예외라고한다.

실행중 예외가 발생하면 자바 플랫폼이 가장먼저 알게되어 자바 응용프로그램에게 

예외를 전달하게하여 예외에 대응하게한다 

정수를 0으로 나눈경우

배열의 크기보다 큰 인덱스로 배열의 원소에 접근한 경우

정수를 읽는 코드를 실행하고있을떄 사용자가 문자를 입력한경우 등

## 예외 처리 
TRY - CATCH - FINALLY문

try { 예외가 발생할 가능성이 있는 실행문  try 블록 }
    
catvh{ 처리할 예외 타입선언}{ 예외처리문(catvh 블록)}

finally{ 예외 발생여부와 관계없이 무조건 실행되는 문장(finally 블록 생략가능) }

- 예외클래스 자주발생하는 예외 117p 표3-1 참조 


## 클래스와 객체 
클래스 >> 객체의 모양을 선언한 틀 

클래스 모양 그대로 생성된 실체 >> 객체

붕어빵 기계 >클래스 

붕어빵      >객체 

## 객체의 특징

### 캡슐화 
안을 들여다 볼수없게하는것 . 객체를 보호하는목적, 하지만 외부의 정당한 접속을 위해서 몇몇 부분만을 공개 노출시킨다 
- ex) tv ,리모콘의 버튼등 

### 상속
상속은 상위 개체의 속성이 하위개체에 물려져서 하위개체가 상위 개체의 속성을 모두가지는 관계 
- ex) 134p 그림 4-4 참조

### 다형성 
같은 이름의 메소드가 클래스 혹은 객체에 따라 다르게 구현되는것 

메소드 오버로딩: 한 클래스 내에서 같은 이름이지만 다르게 작동하는 여러메소드

메소드 오버라이딩: 슈퍼 클래스의 메소드를 동일한 이름으로 서브클래스마다 다르게 구현


## 객체 지향 언어의 목적
### 소프트웨어의 생산성 향상 
컴퓨터 산업 발전에 따라 소프트웨어의 생명주기 단축

소프트웨어를 빠른속도로 생산할 필요성 증대

### 실세계에 대한 쉬운 모델링
초기 프로그래밍 - 수학 계산/통계 처리등 처리과정,계산절차 중요

반면 

현대 프로그래밍 - 컴퓨터가 산업전반에 활용

실세계에서 발생하는 일을 프로그래밍

실세계에서는 절차나 과정보다 물체(객체)들의 상호작요응로 묘사하는것이 용이

### 객체지향언어

- 실세계의 일을 보다 쉽게 프로그래밍하기 위한 객체 중심적 언어

- 상속,다향성,객체,캡슐화 등 소프트웨어 재사용을 위한 여러 장치 내장

- 소프트웨어 재사용과 부분 수정 빠름

- 소프트웨어를 다시 만드는 부담 대폭 줄임

- 소프트웨어 생산성 향상

## 절차 지향 프로그래밍과 객체 지향 프로그래밍
### 절차 지향 프로그래밍 
- 작업 순서를 표현하는 컴퓨터 명령 집합
- 함수들의 집합으로 프로그램 작성
### 객체 지향 프로그래밍
- 컴퓨터가 수행하는 작업을 객체들간의 상호 작용으로 표현
- 클래스 혹은 객체들의 집합으로 프로그램 작성

- 136P 그림4-7 참조 


## 클래스와 객체
클래스 : 객체의 속성과 행위 선언 객체의 설계도혹은틀

객체: 클래스의 틀로 찍어낸 실체
- 프로그램 실행중에 생성되는 실체
- 메모리 공간을 갖는 구체적인 실체
- 인스턴스라고도 부름

### 사례

클래스 :소나타 자동차           객체 : 출고된 소나타 100대

클래스 : 벽시계                 객체 : 우리집벽에걸린 벽시계

클래스 : 책상                   객체 : 우리가 사용중인 실제 책상

## 클래스 
CLASS 키워드로 선언

멤버 : 클래스 구성요소. 필드와(멤버변수) 메소드(멤버함수)

클래스에 대한 public 접근 지정: 다른 모든 클래스에서 클래스 사용허락

멤버에 대한 public 접근 지정 : 다른 모든 클래스에게 멤버 접근 허용 
- 139p 그림4-10 참조

객체 생성과 활용 
- 객체 생성과 활용 예제 Circle.java
1.레퍼런스 변수선언

2.new 연산자로 객체 생성

3.객체 멤버 접근

- 클래스 만들기 예제2 143p 예제 4-2 참조

## 생성자 
객체가 생성될때 초기화목적으로 실행되는 메소드

객체가 생성되는 순간에 자동 호출
### 생성자의 특징
- 생성자의 이름은 클래스의 이름과 동일하게 작성되어야한다
- 생성자를 여러개 생성할수있다
- 생성자는 객체를 생성할때 한번만 호출된다
- 생성자에 리턴 타입을 지정할수 없다
- 생성자의 목적은 객체가 생성될때 필요한 초기작업을 위함이다














# 김동현 202130101
## 4월 3일 5주차

## 반복문 for 문,while 문,do-while 문
## for 문 
초기문, 조건식, 반복후 작업 ,작업문 등을 기술해 조건식이 충족될때 까지 반복 작업문을 실행함 

- for문 예제 ForSample.java

## while문 
조건식과 작업문으로 이어져있으며 조건식이 참인동안 작업문을 반복한다 
- while문 예제  WhileSample.java 

## 교재에는 나오지않는 for문과 while문의 차이??
for문은 반복횟수가 명확히 정해져있을떄 ex 1에서 100까지 더해라 , 3단 구구단을 다 더해라 등

while문은 반복횟수가 정해져있지않는 연산을 해야할떄 >> 입력을 받아 연산을 행하는경우 //사용자의 숫자 입력을 받고 enter 를 받았을떄 반복을 시작하는 연산에 유리

## do-while문 
while문 과 동일한데 조건식이 반드시 필요하며 적어도 한번은 반드시 작업문이 실행되어야한다.
일단 작업문을 한번 실행하고 , 조건식을 따지기 시작한다 while 문과 순서가 다르다 
- do-while 문 예제      DoWhileSample.java  

문자열인 a 에 +1 을 해서 아스키값을 올려 a+1 =b 와 같은 연산을 이용함 
그렇게 a 가 가진 아스키값이 z 가 될떄까지 반복하여 a부터 z까지 출력하는 프로그램이 된다 

## 중첩 반복문.  
반복문안에 다른 반복문을 만들수 있다.

반복은 몇번이고 중첩할수있지만 너무많은 중첩반복은 프로그램 구조를 복잡하게 하므로 2중 ,3중정도가 적절하다 

- 중첩반복문을 이용해 구구단 출력하는 프로그램  NestedLoop.java

for문안에 for문을 넣는 중첩for문에서 정수 i를 많이 넣는데 여러정수를 쓸경우 i 다음 알파벳인 j ,k 등을 이용하는것이 보편적 

## continue문 
반복문을 빠져나가지않으면서 즉시 다음반복으로 넘어가려 할때 사용된다 
- continue문 예제     ContinueSample.java

## break문 
하나의 반복문을 즉시 벗어날떄 사용한다

break문이 실행되면 현재의 반복문을 벗어나 다음코드로 실행이 이어진다 break문은 단하나의 반복문만 벗어나므로 중첩반복문의 경우엔 break문을 여러번 사용해주어야한다
- break문 예제    BreakSample.java

 문자열을 입력받아 Stringequals()를 이용하여 true가 나올경우 break로 반복문을 벗어나는 프로그램 

## java 배열 
연속적은 자료구조 . 배열에는 같은종류의 데이터들이 순차적으로 저장된다. 그냥 정수 10개를 선언하는 경우와 배열을 이용해 "순차적으로" 10개의 정수로 구성된 배열을 선언하는것은 확연히 다르다

 int i[] = new int[10] > 10개의 정수로 이루어진 배열 선언

 int i0,i1,i2,i3,i4,i5... ; 10개의 정수를 따로 선언하기 
## 배열 생성
배열을 생성하는 방법은 레퍼런스 변수선언,배열 생성의 두단계로 이루어진다 

### 레퍼런스 변수선언 
int intArray [];

int >배열타입  

intArray > 배열에대한 레퍼런스 변수 

[] > 배열선언 

### 배열생성 
intArray = new int [5];

intArray > 배열에 대한 레퍼런스 변수 

new > 배열생성 

int >타입 

[5] >원소의 개수 

- 배열 예제 Array.java

## 배열 초기화 
int intArray[]= {1,2,3,4};

{} 를 이용해 초기화된 배열을 만들수있으며 배열의 크기는 {} 안에있는 값의 개수로 정해진다

## 레퍼런스 치환과 공유 
int intArray[]=new int[5];

int myArray[]= intArray;

이처럼 intArray의 배열을 myArray가 공유하게 된다  myArray의 값을 변환하면 intArray 의 값도 변경된다 


## 배열의 크기 length 
- 배열의 크기를 출력하는 예제 ArrayLengthSample.java 

## 배열과 for-each문
배열의 크기만큼 루프를 돌며 반복한다 

int [] n={1,2,3,4,5};

for(int k:n ){ 반복문 }

## 2차원 배열 
앞서배운 1차원 배열과 동일하게 레퍼런스 변수 선언후 배열 생성의 과정을 가진다

int intArray [][]; 

2행 5열의 2차원배열

intArray = new int [2][5];

1차원 배열과 동일하게 배열 선언과 생성을 동시에 할수 있다 

int intArray[][] = new int[2][5];

## 2차원배열의 초기화
2차원 배열을 선언할떄 각원소를 초기화할수있다 이떄 ,자동으로 초기화된 배열이 생성된다 

int intArray[][] = { {0,1,2},{3,4,5},{6,7,8}}  >> 3x3 크기의 배열생성 

배열 리턴....


# 김동현 202130101 
## 3월 27일 4주차 
## java
### 장점 
다른 언어 들은 자체적 으로 멀티스레드를 지원 하지않는 것 들이 많아 운영체제의 도움을 받는데에 반해 자바는 운영체제 없이 자체적 으로 멀티스레드 프로그래밍이 가능하다.

자바는 번거로운 메모리 반환과정을 자동으로 변환 해주는 가비지 컬렉션 이라는 기능이 있음. 
### 단점 
하지만 가비지 컬렉션이 실행도중 예상할 수 없는 시점에 알아서 실행되므로 프로그램 실행이 일시적으로 중단되는 현상이있어 실시간 응용시스템에 이용하기엔 적합하지않다.  

클래스 파일은 엄밀히 따지면 기계어가 아니다 . 그러므로 c나 c++ 보다는 실행 속도가 느리다. 최근 jtt컴파일링 기법등 의 방식으로 실행성능이 개선되긴했다. 


소스코드 > 우리가사용하는 java 코드 / 사람이 읽을수있는 고수준 언어 

바이트코드 javac가 소스코드를 변환한 중간코드 /cpu 가 직접 실행할수는 없음 jvm (java virtual machine )이 실행 해줘야함 / 기계어와 다르게 플랫폼 독립적  여러 플랫폼에서 동일하게 실행가능 

어디에서 실행되느냐에 따라서 jvm이 해석하고 코드를 변환해서 알아서 기계어로 변환해서 보여줌 

기계어 >cpu가 직접 실행할수있는 0과1로 구성된 이진코드 

# 2장 자바프로그램의 기본구조
## 식별자의 명명규칙
공백 사용 xx 

특수문자 사용 x (_ &)예외 

한글 x if while class 등 자바언어의 키워드는 식별자로 x

첫글자 숫자 x 

대소문자 구별함 

길이제한 x 

불린 리터럴 -true false 널 리터럴 -null 은 사용 x 


파일이름이 같은 파일이 존재할수있는 이유 > 파일 이름은 경로상에 있는 모든 파일들을 포함하는것이기 때문에 경로가 한개라도 다르다면 최종 파일이름이 같더라도 다른 파일임 . 

위에서 말했듯이 파일명은 경로상에 있는 모든 파일들이 이어져 포함되는것이기 떄문에 경로상에있는 모든 파일들에 식별자 명명규칙이 적용된다 한개의 파일경로에도 한글이 있으면 안된다 등.

$ > 임시파일에 사용함/ 첫글자에 _이 오면 보통 숨기는 파일에 사용   >>>> 잘 사용하지않음 

## 자바의 데이터 타입 8개 > 4가지  
논리타입 boolean 

문자타입 char 

정수타입 byte short int long

실수타입 float double

리터럴 .. 프로그램에 직접 표현한 값  int n = 15;   >여기에서는 15를 의미 

자바는 왜 참조 자료형을 (reference)쓸까?

안정성 

보안강화 포인트를 사용하면 메모리주소를 직접 조작할수 있기떄문에 오버플로우가 일어날수있다 > 보안취약점이 생길수있음  

다중플랫폼지원 자바는 jvm에서 실행되기때문에 os 에따라 메모리관리방식이 달라져도 영향받지않음 레퍼런스를 사용하면 jvm이 메모리관리를 해주므로 os영향받지않고 같은코드를 실행할수있다 

## 메모리의 구조 힙 /스택 
힙 - 선입선출 /스택- 쌓는 방식 나중에 들어온게 먼저나감 

힙이 스택을 침법하면 힙 오버 플로우  스택이 힙을 침범하면 스택 오버 플로우

변수>프로그램 실행중 값을임시로 저장하기위한 메모리공간/변경될수있음   

변수의 선언 >데이터타입에서 정한 크기의 메모리를 할당 

상수선언 final 키워드 사용.선언할때 초기값 지정 실행중 값의 변경 x

fianl double PI = 3.141592; 

상수선언/데이터타입/상수이름/초기화  상수선언은 항상 초기화 해줘야함

- 원의 면적을 구하는 프로그램  CircleArea.java

## VAR 키워드 
데이터 타입을 생략하고 변수선언을 할수있다.

컴파일러가 알아서 추론하여 변수타입을 결정 

변수선언할떄 초기값을 지정하지않으면 컴파일 오류가 일어남 

메소드 내부에 선언되는 지역변수에만 사용가능 하고 

클래스 내부에 선언되는 변수.객체가생성될때 함께 만들어지는 변수.   >>클래스필드에서는 사용될수 없다 

명시형 자료형 INT STRING DOUBLE 등과 함께 사용하는것이 좋다

가독성이 유지될수 있게 사용하여야 좋다

상수를 적극적으로 사용해서 코드의 안정성을 높히는 편이 좋다 

println >> 그냥 print 랑 기능적으로 같은데 ln 의 역할이 \n 임 줄바꿈을 한번 해줌 
 
## 타입변환 >특정 데이터 타입의 값을 다른데이터타입의 값으로 변환 

자동 타입변환 >컴파일러에의해 원래의 타입보다 큰타입으로 자동변환 

강제 타입변환 > 개발자가 직접 변환 .데이터 손실 발생가능


## system.in 
가장 기본 표준입력 스트림객체 

입력되는 키를 바이트(문자 x)로 리턴하는 저수준 스트림

바이트를 문자나 숫자로 변환하는 어려움이있음 

## scanner 
읽은 바이트를 문자 정수 실수 불린 문자열등 다양한 타입으로 변환하여 리턴 

객체를 생성해서 사용 

키보드에 연결된 system.in에게 키를 읽게하여 원하는 타입으로 변환하여 리턴 

입력되는 키 값을 공백으로 구분되는 토큰단위로 읽음 

공백문자 "\tab \n \r 등 (페이지 나누기 폼 피드 프린트 등에서 사용)

- 정수를 입력받아 몇시간 몇분 몇초인지 출력하는 프로그램  ArthmeticOperator.java
## 연산자 
주어진 식을 계산하여 결과를얻어내는 과정 >연산

하나의 식에는 피연산자 연산자 피연산자가 존재한다 

연산종류 증감/산술/시프트/비교/비트/논리/조건/대입 

- 두수의 차이를 구하는 프로그램  TermaryOperator.java
## 조건문 
 ## 단순if문 if-else문 

조건식이 참이면 if 내부의 실행문장이 실행 ,거짓이면 if 문을 벗어남

## if-else 문 

조건식이 참이면 실행문장1 을 실행, 거짓이면 실행문장 2를 실행후 문장을 벗어남 

## 다중 if-else 문 if-else 가 연속되는것 

여러개의 조건문을 가지고 ,조건식이 참인경우 해당하는실행문장을 실행한 후 다중if-else 문을 벗어남 .

앞의 조건문이 모두 거짓인경우 else의 실행문장을 실행후 다중if-else 문을 벗어남 

* 조건문이 너무많은 경우엔 후술할 switch문을 사용하기를 권장함

## 중첩 if-else 문 

if문이나 if-else 문혹은 ekse 문의 실행문장 안에 또다시 if 문,if-else 문등을 내포할수있다 .
 
## switch문 

식을먼저 계산하고 그결과값과 일치하는 case문으로 분기하여 실행문장을 실행한후 break를만나면 switch문을 벗어난다 

만일 어떤case문의 값과도 같지않은경우가 나올경우 default문으로 분기하여 실행문장을 실행한다 . default문은 생략할수있다. 

switch문에서 break문의 역할과 중요성..


# 김동현 202130101
## 3월 20일 3주차

프로젝트 생성하는 방법 위에 검색창에 >create java project 선택 no build tool 선택하고 폴더 선택 해야함 >>> 워킹 디렉토리를 선택 하면 그안에 디렉토리를 생성함.

src  안에 새로운 클래스 생성 하기  

새로 깃허브에 연동하는 방법 

## 자바 묘듈화의 필요성 

자바 플랫폼 독립성 ,객체지향 , 자바의 모든변수나 함수는 클래스 내에서 선언된다 ..   클래스 내부에 클래스를 만들었다면 컴파일 후에 바이트 코드가 생성이 된다

클래스가 여러개 있다고 한들 배포해야하는데 사용하는 이용자는 여러개의 클래스 파일을 일일이 다운로드 받지않게하기위해 한개의 파일로 압축을 해서 배포함 >> 그 파일을 jar이라고함 

여러개의 클래스 파일이 각각 main()을 포함하는 것은 상관없으나 한개의 클래스 파일안에 2개이상의 main()은 존재할수없음 

# 김동현 202130101
## 3월13일 2주차 

 깃허브 에 파일 올리는 방법 .커밋해서 파일 올리는 방법 
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
