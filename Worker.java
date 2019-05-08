public class Worker {
    private String name;
    private static String name1;
    static String player;
    public int positionX = -1;
    public int positionY = -1;


    public Worker(String name, String player) {
        this.player = player;
        this.name = name;
        name1 = name;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public String getName() {
        return name;
    }

    public static String getName1() {
        return name1;
    }

    public boolean placeWorker(int newPositionX, int newPositionY, SantoriniGame g) {
        if ((newPositionX < 0 || newPositionX > 4) || (newPositionY < 0 || newPositionY > 4)) {
            return false;
        }
        for (int i = 0; i < g.workers.length; i++) {
            if (g.workers[i].name != this.name)
                if (newPositionX == g.workers[i].positionX && newPositionY == g.workers[i].positionY)
                    return false;

        }

        this.positionX = newPositionX;
        this.positionY = newPositionY;
        return true;
    }

    private String getPosition() {
        String s = positionX + "" + positionY;
        return s;
    }

    public boolean move(int newPositionX, int newPositionY, SantoriniGame game) {

        if (newPositionX == this.positionX && newPositionY == this.positionY) {
            System.out.println("You can't move to the same block - TRY AGAIN!!");
            System.out.println();
            game.print();
            return false;
        } else if (Math.abs(this.positionX - newPositionX) > 1 || Math.abs(this.positionY - newPositionY) > 1) {
            System.out.println("You can't move to a block out of your boundaries - TRY AGAIN!!");
            System.out.println();
            game.print();
            return false;
        } else if (game.board[newPositionX][newPositionY].contains("w")) {
            System.out.println("This block is busy with another worker - TRY AGAIN!!");
            System.out.println();
            game.print();
            return false;
        } else if (game.board[newPositionX][newPositionY].contains("D")) {
            System.out.println("This block has a DOME - TRY AGAIN!!");
            System.out.println();
            game.print();
            return false;
        } else if (game.board[newPositionX][newPositionY].equals("BBB")) {
            if (game.board[positionX][positionY].equals("B" + name) || game.board[positionX][positionY].equals(name)) {//the worker in zero level
                System.out.println("You can't move to a block in LEVEL 3 - TRY AGAIN!");
                System.out.println();
                game.print();
                return false;
            }
        } else if (game.board[newPositionX][newPositionY].equals("BB")) {
            if (game.board[positionX][positionY].equals(name)) {
                System.out.println("You can't move to a block in LEVEL 2 - TRY AGAIN!");
                System.out.println();
                game.print();
                return false;
            }
        }

        game.board[positionX][positionY] = game.board[positionX][positionY].substring(0, game.board[positionX][positionY].indexOf("w"));

        this.positionX = newPositionX;
        this.positionY = newPositionY;

        game.board[positionX][positionY] += name;
        System.out.println();
        game.print();
        return true;

    }

    
}
