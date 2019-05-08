public class SantoriniGame {
    public static String[][] board = new String[5][5];
    public static Worker[] workers = new Worker[4];

    public SantoriniGame(Worker w11, Worker w12, Worker w21, Worker w22) {
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 5; k++) {
                board[i][k] = "";
            }
        }
        //workers array of objects
        workers[0] = w11;
        workers[1] = w12;
        workers[2] = w21;
        workers[3] = w22;
    }

    
    public String toString(){
    	return ("\n"
    			+"\t 0 \t\t 1 \t\t 2 \t\t 3 \t\t 4"
    			+"\n  --------------------------------------------------------------------------------"
    			+"\n0|\t" + board[0][0] + "\t |\t" + board[0][1] + "\t |\t" + board[0][2] + "\t |\t" + board[0][3] + "\t |\t" + board[0][4] + "\t | 0"
    			+"\n  --------------------------------------------------------------------------------"
    			+"\n1|\t" + board[1][0] + "\t |\t" + board[1][1] + "\t |\t" + board[1][2] + "\t |\t" + board[1][3] + "\t |\t" + board[1][4] + "\t | 1"
    			+"\n  --------------------------------------------------------------------------------"
    			+"\n2|\t" + board[2][0] + "\t |\t" + board[2][1] + "\t |\t" + board[2][2] + "\t |\t" + board[2][3] + "\t |\t" + board[2][4] + "\t | 2"
    			+"\n  --------------------------------------------------------------------------------"
    			+"\n3|\t" + board[3][0] + "\t |\t" + board[3][1] + "\t |\t" + board[3][2] + "\t |\t" + board[3][3] + "\t |\t" + board[3][4] + "\t | 3"
    			+"\n  --------------------------------------------------------------------------------"
    			+"\n4|\t" + board[4][0] + "\t |\t" + board[4][1] + "\t |\t" + board[4][2] + "\t |\t" + board[4][3] + "\t |\t" + board[4][4] + "\t | 4"
    			+"\n  --------------------------------------------------------------------------------"
    			+"\n\t 0 \t\t 1 \t\t 2 \t\t 3 \t\t 4");
    			
    }
    public void print() { //our toString ..
    	System.out.println();
        System.out.println("\t 0 \t\t 1 \t\t 2 \t\t 3 \t\t 4");
        System.out.println("  --------------------------------------------------------------------------------");

        for (int i = 0; i < 5; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 5; j++) {
                System.out.print("\t" + board[i][j] + "\t |");
            }
            System.out.print(i);
            System.out.println();
            if (i < 4) {
                System.out
                        .println("  --------------------------------------------------------------------------------");
            }
        }
        System.out.println("  --------------------------------------------------------------------------------");

        System.out.println("\t 0 \t\t 1 \t\t 2 \t\t 3 \t\t 4");

    }

    public void fillBoard() {//used after the placing method to 
        for (int i = 0; i < 4; i++) {
            board[workers[i].getPositionX()][workers[i].getPositionY()] = workers[i].getName();
        }
    }

    public boolean build(int PositionX, int PositionY, String player) {
        int i = 0;
        switch (player) {
            case "w11":
                i = 0;
                break;
            case "w12":
                i = 1;
                break;
            case "w21":
                i = 2;
                break;
            case "w22":
                i = 3;
                break;
        }
        int r = 0;
        if (PositionX == workers[i].getPositionX() && PositionY == workers[i].getPositionY()) {
            System.out.println("You can't build in the same block - Try Again!");
            System.out.println();
            print();
            System.out.print("\nbuild's position < x,y > ");
            return false;
        } else if (Math.abs(workers[i].getPositionX() - PositionX) > 1
                || Math.abs(workers[i].getPositionY() - PositionY) > 1) {
            System.out.println("This block is out of your boundaries - Try Again!");
            System.out.println();
            print();
            System.out.print("\nbuild's position < x,y > ");
            return false;
        } else if (board[PositionX][PositionY].contains("w")) {
            System.out.println("You can't build above another worker - Try Again!");
            System.out.println();
            print();
            System.out.print("\nbuild's position < x,y > ");
            return false;
        } else if (board[PositionX][PositionY].equals("BBB")) {
            board[PositionX][PositionY] += "D";
            System.out.println();
            print();
            return true;
        } else if (board[PositionX][PositionY].equals("BBBD")) {
            System.out.println("You can't build above DOME - Try Again!");
            System.out.println();
            print();
            System.out.print("\nbuild's position < x,y > ");
            return false;
        } else {
            board[PositionX][PositionY] += "B";

            System.out.println();
            print();
            return true;
        }
    }

    public boolean isTrapped(String player) {
        int i = 0;
        switch (player) {
            case "w11":
                i = 0;
                break;
            case "w12":
                i = 1;
                break;
            case "w21":
                i = 2;
                break;
            case "w22":
                i = 3;
                break;
        }
        int g = 0, m = 0;
        int p = 0;
        for (m = -1, g = 0; m < 2; m++) {//from -1 to 1 to check the blocks before and after the worker
            for (int k = -1; k < 2; k++) {
                if (!(m == 0 && k == 0)) {
                    if (!((workers[i].getPositionY() + k) > 4 || (workers[i].getPositionY() + k) < 0)
                            && !((workers[i].getPositionX() + m) > 4 || (workers[i].getPositionX() + m) < 0)) {
                        if (board[workers[i].getPositionX() + m][workers[i].getPositionY() + k].contains("w"))
                            p = 1;
                        else if (board[workers[i].getPositionX() + m][workers[i].getPositionY() + k].contains("D"))
                            p = 1;
                        else if (board[workers[i].getPositionX() + m][workers[i].getPositionY() + k].equals("BBB")) {
                            if (board[workers[i].getPositionX()][workers[i].getPositionY()]
                                    .equals("B" + workers[i].getName()))
                                p = 1;
                            else if (board[workers[i].getPositionX()][workers[i].getPositionY()]
                                    .equals(workers[i].getName()))
                                p = 1;
                        } else if (board[workers[i].getPositionX() + m][workers[i].getPositionY() + k].equals("BB")) {
                            if (board[workers[i].getPositionX()][workers[i].getPositionY()]
                                    .equals(workers[i].getName()))
                                p = 1;
                        } else
                            p = 0;

                    } else
                        p = 1;

                    if (p > 0) {
                        g += 1;
                    }
                }
                p = 0;
            }
        }

        if (g == 8)//if it's 8 so he is trapped..
            return true;
        return false;
    }


    public boolean hasWon(String player) {
        int i = 0;
        switch (player) {
            case "w11":
                i = 0;
                break;
            case "w12":
                i = 1;
                break;
            case "w21":
                i = 2;
                break;
            case "w22":
                i = 3;
                break;
        }
        //if he on the 3rd level "BBB", he wins!!
        if (board[workers[i].getPositionX()][workers[i].getPositionY()].contains("BBB"))
            return true;

        return false;
    }

    public static boolean reset(String answer) {
        if (answer.equalsIgnoreCase("yes")) {
            for (int i = 0; i < 5; i++) {
                for (int k = 0; k < 5; k++) {
                    board[i][k] = "";
                }
            }
            return true;
        }
        return false;
    }
}
