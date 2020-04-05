import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Enigma {
    private LeftRotor leftRotor;
    private CentralRotor centralRotor;
    private RightRotor rightRotor;
    private Reflector reflector;
    public Enigma(){
        leftRotor=new LeftRotor("abcdefghijklmnopqrstuvwxyz","bdfhjlcprtxvznyeiwgakmusqo");
        centralRotor=new CentralRotor("abcdefghijklmnopqrstuvwxyz","ajdksiruxblhwtmcqgznpyfvoe");
        rightRotor=new RightRotor("abcdefghijklmnopqrstuvwxyz","ekmflgdqvzntowyhxuspaibrcj");
    }

    public void setting(String filename){
        String pairs1="";
        String pairs2="";
        String leftRotor_start_letter="";
        String centralRotor_start_letter="";
        String rightRotor_start_letter="";
        try {
            FileInputStream fis = new FileInputStream(filename);
            Scanner scan = new Scanner(fis);
            pairs1=scan.nextLine();
            pairs2=scan.nextLine();
            leftRotor_start_letter=scan.nextLine();
            centralRotor_start_letter=scan.nextLine();
            rightRotor_start_letter=scan.nextLine();
            fis.close();
        } catch (IOException e) {
            e.getMessage();
        }
        reflector=new Reflector(pairs1,pairs2);
        for (int i=0; i<26;i++) {
            if (leftRotor.getCommutation1().charAt(i)==leftRotor_start_letter.charAt(0)) {
                leftRotor.setCurrent_position(i);
            }
        }
        for (int i=0; i<26;i++) {
            if (centralRotor.getCommutation1().charAt(i)==centralRotor_start_letter.charAt(0)) {
                centralRotor.setCurrent_position(i);
            }
        }
        for (int i=0; i<26;i++) {
            if (rightRotor.getCommutation1().charAt(i)==rightRotor_start_letter.charAt(0)) {
                rightRotor.setCurrent_position(i);
            }
        }
        System.out.println("Энигма успешно настроена.");
    }

    public void code(String filename){
        try {
            FileWriter cleaner=new FileWriter("result.txt");
            cleaner.write("");
            cleaner.close();

            FileReader fr =new FileReader(filename);
            FileWriter fw= new FileWriter("result.txt",true);

            char letter;
            char l1;
            HashSet<Object> symbols=new HashSet<>();
            symbols.add('.');
            symbols.add(',');
            symbols.add(' ');
            symbols.add('-');
            symbols.add('!');
            symbols.add('?');
            symbols.add(':');
            symbols.add(';');
            symbols.add('\'');
            symbols.add('\"');
            symbols.add('*');
            symbols.add('_');
            symbols.add('(');
            symbols.add(')');
            symbols.add('+');
            symbols.add('=');
            symbols.add('<');
            symbols.add('>');
            symbols.add('\n');
            while (fr.ready()) {
                letter= Character.toLowerCase((char) fr.read());
                if(!symbols.contains(letter)) {
                    rightRotor.spinRotor();
                    if (rightRotor.getCurrent_position() == rightRotor.getNext_rotor_spin_position()) {
                        centralRotor.spinRotor();
                        if (centralRotor.getCurrent_position() == centralRotor.getNext_rotor_spin_position()) {
                            leftRotor.spinRotor();
                        }
                    }
                    l1 = rightRotor.rightSignal(letter, 0);
                    l1 = centralRotor.rightSignal(l1, rightRotor.getCurrent_position());
                    l1 = leftRotor.rightSignal(l1, centralRotor.getCurrent_position());
                    l1 = reflector.reflect(l1, leftRotor.getCurrent_position());
                    l1 = leftRotor.leftSignal(l1, 0);
                    l1 = centralRotor.leftSignal(l1, leftRotor.getCurrent_position());
                    l1 = rightRotor.leftSignal(l1, centralRotor.getCurrent_position());
                    fw.append(l1);
                }
                else fw.append(letter);
            }
            fr.close();
            fw.close();
            System.out.println("Шифровка прошла успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
