import java.util.Scanner;
public class ArraySample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int intArray[];
        intArray = new int[5];
        // int intArray[] = new int[5]  
        //이렇게 선언과 생성 동시에 가능하다
        int max =0;
        System.out.println("양수 5개를 입력하시오");
        for(int i=0; i<5; i++){
            intArray[i] = scanner.nextInt();
            if(intArray[i]>max)
            max = intArray[i];
        }
        System.out.println("가장 큰 수는 "+max+"입니다");

        scanner.close();
    }
}
