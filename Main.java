import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Enigma enigma=new Enigma();
        enigma.setting("settings.txt");
        Scanner scanner=new Scanner(System.in);
        int k=0;
        String filename;
        while(k!=3) {
            System.out.println("1-расшифровать/зашифровать файл файл.\n2-изменить настроойки Энигмы.\n3-выход.");
            k=scanner.nextInt();
            scanner.nextLine();
            switch (k) {
                case 1:
                    System.out.println("Введите название файла, который надо расшифровать/зашифровать.");
                    filename=scanner.nextLine();
                    enigma.code(filename);
                    break;
                case 2:
                    System.out.println("Введите название файла с настройками.");
                    filename=scanner.nextLine();
                    enigma.setting(filename);
                    break;
                default:
                    break;
            }
        }
    }
}
