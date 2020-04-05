public abstract class Rotor {
    private String commutation1;
    private String commutation2;
    private int current_position;
    private int next_rotor_spin_position;
    public Rotor(String c1, String c2){
        this.commutation1=c1;
        this.commutation2=c2;
        this.next_rotor_spin_position=25;
    }
    public abstract char rightSignal(char letter,int nearRotor_pos);

    public abstract char leftSignal(char letter,int nearRotor_pos);

    public void spinRotor(){
        if (this.current_position!=25){
            this.current_position++;
        }
        else this.current_position=0;
    }
    public String getCommutation1(){
        return commutation1;
    }

    public String getCommutation2(){
        return commutation2;
    }

    public int getCurrent_position() {
        return current_position;
    }

    public int getNext_rotor_spin_position() {
        return next_rotor_spin_position;
    }

    public void setCurrent_position(int current_position) {
        this.current_position = current_position;
    }
}

class LeftRotor extends Rotor{
    public LeftRotor(String c1, String c2){
        super(c1, c2);
    }

    @Override
    public char leftSignal(char letter, int nnn) {
        int in= getCommutation1().indexOf(letter);
        int inside= Math.floorMod(in+getCurrent_position(),26);
        char ltr=getCommutation1().charAt(inside);
        int out=getCommutation2().indexOf(ltr);
        return getCommutation1().charAt(out);
    }

    @Override
    public char rightSignal(char letter, int centralRotor_pos) {
        int in=getCommutation1().indexOf(letter);
        int out=Math.floorMod(in+Math.floorMod(getCurrent_position()-centralRotor_pos,26),26);
        return getCommutation2().charAt(out);
    }

}

class CentralRotor extends Rotor{
    public CentralRotor(String c1,String c2){
        super(c1, c2);
    }

    @Override
    public char leftSignal(char letter, int leftRotor_pos) {
        int in=getCommutation1().indexOf(letter);
        int inside=Math.floorMod(in-Math.floorMod(leftRotor_pos-getCurrent_position(),26),26);
        char ltr=getCommutation1().charAt(inside);
        int out=getCommutation2().indexOf(ltr);
        return getCommutation1().charAt(out);
    }

    @Override
    public char rightSignal(char letter,int rightRotor_pos) {
        int in=getCommutation1().indexOf(letter);
        int out=Math.floorMod(in+Math.floorMod(getCurrent_position()-rightRotor_pos,26),26);
        return getCommutation2().charAt(out);
    }
}

class RightRotor extends Rotor{
    public RightRotor(String c1,String c2){
        super(c1, c2);
    }

    @Override
    public char leftSignal(char letter, int centralRotor_pos) {
        int in1= getCommutation1().indexOf(letter);
        int inside=Math.floorMod(in1-Math.floorMod(centralRotor_pos-getCurrent_position(),26),26);
        char ltr=getCommutation1().charAt(inside);
        int out1=getCommutation2().indexOf(ltr);
        int out2=Math.floorMod(out1-getCurrent_position(),26);
        return getCommutation1().charAt(out2);
    }

    @Override
    public char rightSignal(char letter,int nnn) {
        int in=getCommutation1().indexOf(letter);
        int out= Math.floorMod(in+getCurrent_position(),26);
        return getCommutation2().charAt(out);
    }
}
