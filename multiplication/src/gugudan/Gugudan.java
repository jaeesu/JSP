package gugudan;

import java.util.Random;
import java.util.Scanner;

public class Gugudan {
    private static int a;
    private static int b;
    
    private String difficulty;
    
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String Gugudan_make() {
        Random random = new Random();
        switch(this.difficulty){
            case "easy":
                a = random.nextInt(9)+1;
                b = random.nextInt(9)+1;
                System.out.println("case 1");
                break;
            case "hard":
                a = random.nextInt(90)+10;
                b = random.nextInt(90)+10;
                System.out.println("case 2");
                break;
        }
        
        String ques = a + "*" + b;
        return ques;
    }

    public int get_result() {
        return a*b;
    }

    public int check_result(int _result) {
        if(_result == a*b) {
            System.out.println("correct!!");
            return 1;
        }else {
        	return 0;
        }
    }


    public static void main(String[] args) {
        Gugudan gugu = new Gugudan();
        gugu.setDifficulty("easy");

        System.out.println(gugu.Gugudan_make());
        Scanner sc = new Scanner(System.in);
        
        System.out.print("입력 : ");
        int result = sc.nextInt();
        
        gugu.check_result(result);
        sc.close();
        
    }
}
