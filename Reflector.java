public class Reflector {
    private String pair1;
    private String pair2;
    public Reflector(String pair1,String pair2){
        this.pair1=pair1;
        this.pair2=pair2;
    }
    public char reflect(char letter, int leftRotorPos){
        int in =pair1.indexOf(letter);
        int out=(26+(in-leftRotorPos))%26;
        return pair2.charAt(out);
    }
}