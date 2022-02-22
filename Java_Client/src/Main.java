import java.util.Scanner;

public class Main {

    static Frame frame;
    static Client client;
    static Scanner readinput = new Scanner(System.in);
    static String userinput = "xx";

    public static void run() {
        client = new Client(6666);
        client.start();

        while((userinput.length() > 0)){
            userinput = readinput.next();
            frame = client.getCurrentFrame();
            System.out.println(frame == null);
            if(frame!= null && frame.getHands().size() != 0){

            }

        }
        System.out.println("here");

    }

    public static void main(String[] args) {
        run();


    }



    }
