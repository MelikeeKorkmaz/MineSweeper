import java.util.Scanner;

public class Main {
    static boolean isTrue(int i, int j){
        if(i < 2 || j < 2){
            System.out.println("Boyut minimum, 2 sütun ve 2 satır şeklinde olmalıdır!");
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int i, j;
        Scanner input = new Scanner(System.in);

        do{
            System.out.print("Hangi boyutlarda oynamak istediğinizi giriniz.\nSatır: ");
            i = input.nextInt();
            System.out.print("Sütun: ");
            j = input.nextInt();
        }while(!isTrue(i, j));

        MineSweeper m= new MineSweeper(i, j);
        //Görebilmek için yazdığım kod
        m.printMatrix();
        System.out.println();
        m.game();
        m.printGame();

        System.out.println("Açmak istediğiniz satır ve sütun numaralarını giriniz.");
        do{
            System.out.print("Satır: ");
            int k = input.nextInt();
            System.out.print("Sütun: ");
            int l = input.nextInt();
            m.play(k, l);
        }while(!m.isGameEnd);

    }
}
