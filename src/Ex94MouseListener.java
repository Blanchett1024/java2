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
