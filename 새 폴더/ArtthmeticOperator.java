import java.util.Scanner;
public class ArtthmeticOperator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("정수를 입력해주세요");
        int time = scanner.nextInt();
        int second = time %60;
        int minute = (time/60)%60;
        int hour = (time/60)/60;

        System.out.print(time+"초는");
        System.out.print(hour+"시간,");
        System.out.print(minute+"분,");
        System.out.print(second+"초입니다");
        scanner.close();
    
    }
    
}

// 초단위의 정수를 입력받고 몇시간몇분몇초인지 출력하는 프로그램