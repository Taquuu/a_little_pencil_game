package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final Scanner sc;

    public static void main(String[] args) {
        initGame();
    }

    static void initGame() {
        System.out.println("How many pencils would you like to use:");
        int amountOfPencils = 0;
        int input=0;

        while (true) {
            String inputString = sc.nextLine().trim();
            if (inputString.isBlank()) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            try {
                input = Integer.parseInt(inputString);
            }
            catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            if (input == 0) {
                System.out.println("The number of pencils should be positive");
                continue;
            }
            if (input < 0) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            amountOfPencils = input;
            break;
        }

        System.out.printf("Who will be the first (%s, %s )", "Henry", "James");
        String whoGoesFirst = "";
        String player2="";
        String stringInput="";
        while (sc.hasNext()) {
            stringInput = sc.nextLine();
            if ("Henry".equalsIgnoreCase(stringInput)) {
                whoGoesFirst = stringInput;
                player2 = "James";
                break;
            }
            else if ("James".equalsIgnoreCase(stringInput)) {
                whoGoesFirst = stringInput;
                player2 = "Henry";
                break;
            }
            else {
                System.out.println("Choose between Henry and James");
                continue;
            }
        }
        playGame(amountOfPencils, whoGoesFirst, player2);
    }

    static void playGame(int amountOfPencils, String player1, String player2) {
        String playersTurn = player1;
        int input = 0;
        int maxTake=3;

        while(amountOfPencils > 0) {
            StringBuilder currentPencils = new StringBuilder();
            int pencilsTaken = 0;

            for(int i = 0; i < amountOfPencils; ++i) {
                currentPencils.append("|");
            }

            System.out.println(currentPencils);
            System.out.println(playersTurn+"'s turn!");
            if ("James".equalsIgnoreCase(playersTurn)) {
                if(Main.winning(amountOfPencils,maxTake)) {
                    pencilsTaken= botMove(amountOfPencils,maxTake);
                    System.out.println(pencilsTaken);
                }
                else {
                    Random random = new Random();
                    if (amountOfPencils>=3) {
                        pencilsTaken=random.nextInt(1,4);
                    } else pencilsTaken= random.nextInt(1,amountOfPencils+1);
                    System.out.println(pencilsTaken);
                }
            }
            else {

                while (true) {
                    if (!sc.hasNextInt()) {
                        System.out.println("Possible values: '1', '2', '3'");
                        sc.nextLine();
                        continue;
                    }
                    String inputString = sc.nextLine();
                    try {
                        input = Integer.parseInt(inputString);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Invalid Integer.");
                    }
                    if (input > amountOfPencils && input <= 3) {
                        System.out.println("Too many pencils were taken");
                        continue;
                    }
                    if (input == 1 || input == 2 || input == 3) {
                        pencilsTaken = input;
                        break;
                    }
                    else {
                        System.out.println("Possible values: '1', '2', '3'");
                        continue;
                    }
                }
            }

            amountOfPencils-= pencilsTaken;

            if (playersTurn.equalsIgnoreCase(player1)) {
                playersTurn = player2;
            } else {
                playersTurn = player1;
            }
        }
        System.out.println(playersTurn+" won!");
    }
    static public boolean winning(int amountOfPencils, int maxTake) {

        return amountOfPencils % (maxTake + 1) != 1;
    }
    static public int botMove(int amountOfPencils, int maxTake) {
        int move = (amountOfPencils-1)%(maxTake+1);
        if (move != 0) {
            return move;
        }
        else {
            return amountOfPencils;
        }
    }

    static {
        sc = new Scanner(System.in);
    }
}
