
/*Team: 09
1. Fozan Alkhalawi	201727090
2. Ali Alnimer		201780990
3. Khaled Khamis	201734550
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Santorini {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Scanner input = null;
        int scanner = 0;

//choosing the scanner..

        System.out.print("How do you prefer to play? enter 1 for < keyboard > or 2 for < file > :");
        do {
            scanner = kb.nextInt();
            if (scanner == 1) {
                input = new Scanner(System.in);
            } else if (scanner == 2) {
                System.out.print("\nPlease Enter your file name <e.g. file.txt> : ");
                String fileName = kb.next();
                try {
                    input = new Scanner(new FileInputStream(fileName));
                } catch (FileNotFoundException e) {
                    System.out.println("\nError : File not found, terminating the program...");
                    System.exit(0);
                }
            } else
                System.out.println("Invalid number, please enter < 1 > for keyboard or < 2 > for file D:");
        } while (scanner != 1 && scanner != 2);


        String answer = "";
        String winner = null;
        
//Get names and initializing the worker objects  ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
        
        do {
            
            System.out.println("\n\n######################### SantoriniGame #########################\n");
            
            System.out.print("Enter the First name : ");
            String[] names = new String[2];
            names[0] = input.next();
            if(scanner == 2)System.out.println(names[0]);//To print the names if it was from file
            System.out.print("Enter the Second name : ");
            names[1] = input.next();
            if(scanner == 2)System.out.println(names[1]);//To print the names if it was from file
            
            Worker w11 = new Worker("w11", names[0]);
            Worker w12 = new Worker("w12", names[0]);
            Worker w21 = new Worker("w21", names[1]);
            Worker w22 = new Worker("w22", names[1]);
            SantoriniGame game1 = new SantoriniGame(w11, w12, w21, w22);
            
            System.out.println("\n");
            
//defining variables-----------------------------------------------------------------------------------------------------------------------------------------------------------------------            
            
            int x, y, a, b;
            String worker = "";
            boolean workerName = true; //to handle if the user enter wrong worker name
            boolean workerPosition = true; //to handle if the user enter invalid position

//Placing Workers ------------------------------------------------------------------------------------------------------------------------------------------------------------
            
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= 2; j++) {
                    System.out.print("[ "+names[i] + " ] Put the worker #" + j + " name <e.g. w11> : ");
                    worker = input.next();
                    System.out.print("Its position < x , y > ");
                    x = input.nextInt();
                    y = input.nextInt();
                    if (i == 0) {
                        do {
                            workerName = workerPosition = true;
                            if (worker.equals("w11"))
                                workerPosition = w11.placeWorker(x, y, game1);// we are passing game1 to use the array of object instead of passing all the objects
                            else if (worker.equals("w12"))
                                workerPosition = w12.placeWorker(x, y, game1);
                            else {
                                workerName = false;
                                System.out.println("Error : Invalid worker name ! Try again ");
                                System.out.print("[ "+names[i] + " ] Put the worker #" + j + " name <e.g. w11> : ");
                                worker = input.next();
                                System.out.print("Its position < x , y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                            }
                            if (!workerPosition) {
                                System.out.println("Error : Invalid worker position ! Try Again ");
                                System.out.println("Enter the #" + j + " worker  position < x , y > : ");
                                x = input.nextInt();
                                y = input.nextInt();
                            }
                        } while (!workerName || !workerPosition);
                    }
                    if (i == 1) {
                        do {
                            workerName = workerPosition = true;
                            if (worker.equals("w21"))
                                workerPosition = w21.placeWorker(x, y, game1);
                            else if (worker.equals("w22"))
                                workerPosition = w22.placeWorker(x, y, game1);
                            else {
                                workerName = false;
                                System.out.println("Error : Invalid worker name ! Try again ");
                                System.out.print("[ "+names[i] + " ] Put the worker #" + j + " name <e.g. w11> : ");
                                worker = input.next();
                                System.out.print("Its position < x , y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                            }
                            if (!workerPosition) {
                                System.out.println("Error : Invalid worker position ! Try Again ");
                                System.out.println("Enter the #" + j + " worker  position < x , y > : ");
                                x = input.nextInt();
                                y = input.nextInt();
                            }
                        } while (!workerName || !workerPosition);
                    }
                }

            }

            game1.fillBoard();//filling the board with the workers
            System.out.println();//to get space :)
            game1.print();
            //System.out.println(game1);
//defining variables-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
            
            boolean MovingFlag = false; //to handle the moving errors

//Moving and building -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
            

            do {
                if (Worker.player.equals(names[0])) {
                    Worker.player = names[1];
                } else {
                    Worker.player = names[0];
                }

                System.out.println("\nThe turn is for player : " + Worker.player);
                System.out.print("Which worker you want to move : ");
                worker = input.next();

//these steps to handle the moving positions and players workers ..

                if (Worker.player.equals(names[0])) {
                    if (worker.equals("w11")) {
                        do {
                            if (game1.isTrapped("w11")) {//check if the worker is trapped player will move the other worker
                                System.out.println("\nThis worker can't do any move try your other worker");
                                worker = "w12";
                                System.out.print("\nIts new position < x,y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                                MovingFlag = w12.move(x, y, game1);
                                
                            }else {
                            System.out.print("Its new position < x,y > ");
                            x = input.nextInt();
                            y = input.nextInt();
                            MovingFlag = w11.move(x, y, game1);
                            }
                            System.out.println("\nMoving worker : " + worker + " to " + x + ", " + y);
                        } while (!MovingFlag);MovingFlag = false;
                        
                    } else if (worker.equals("w12")) {
                        do {
                            if (game1.isTrapped("w12")) {//check if the worker is trapped player will move the other worker
                                System.out.println("\nThis worker can't do any move try your other worker");
                                worker = "w11";
                                System.out.print("\nIts new position < x,y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                                MovingFlag = w11.move(x, y, game1);
                                
                            }else {
	                            System.out.print("Its new position < x,y > ");
	                            x = input.nextInt();
	                            y = input.nextInt();
	                            MovingFlag = w12.move(x, y, game1);
                            }
                            System.out.println("\nMoving worker : " + worker + " to " + x + ", " + y);
                        } while (!MovingFlag);MovingFlag = false;
                    } else {//this else will deal with the situation if the player entered a invalid worker name  
                        do {
                            System.out.println("Invalid Worker Name : Please choose one of your workers < w11 / w12 >");
                            worker = input.next();
                            if (game1.isTrapped(worker)) {//check if the worker is trapped player will move the other worker
                                System.out.println("\nThis worker can't do any move try your other worker");
                                System.out.print("\nIts new position < x,y > ");
                                x = input.nextInt();
                                y = input.nextInt();

                                if (worker.equals("w11")) {
                                    worker = "w12";
                                    MovingFlag = w12.move(x, y, game1);
                                } else {
                                    worker = "w11";
                                    MovingFlag = w11.move(x, y, game1);
                                }
                                
                            }else {
                            System.out.print("Put its new position < x,y > ");
                            x = input.nextInt();
                            y = input.nextInt();
                            if (worker.equals("w11")) 
                                MovingFlag = w11.move(x, y, game1);
                            else
                                MovingFlag = w12.move(x, y, game1);
                            }
                            System.out.println("\nMoving worker : " + worker + " to " + x + ", " + y);
                            
                        } while(!MovingFlag);MovingFlag = false;
                    }
                }
                if (Worker.player.equals(names[1])) {
                    if (worker.equals("w21")) {
                        do {
                            if (game1.isTrapped("w21")) {//check if the worker is trapped player will move the other worker
                                System.out.println("\nThis worker can't do any move try your other worker");
                                worker = "w22";
                                System.out.print("\nIts new position < x,y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                                MovingFlag = w22.move(x, y, game1);
                                
                            }else {
	                            System.out.print("Its new position < x,y > ");
	                            x = input.nextInt();
	                            y = input.nextInt();
	                            MovingFlag = w21.move(x, y, game1);
                            }
                            System.out.println("\nMoving worker : " + worker + " to " + x + ", " + y);

                        } while (!MovingFlag);MovingFlag = false;
                    } else if (worker.equals("w22")) {
                        do {
                            if (game1.isTrapped("w22")) {//check if the worker is trapped player will move the other worker
                                System.out.println("\nThis worker can't do any move try your other worker");
                                worker = "w21";
                                System.out.print("\nIts new position < x,y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                                MovingFlag = w21.move(x, y, game1);
                                
                            }else {
                            	System.out.print("Its new position < x,y > ");
	                            x = input.nextInt();
	                            y = input.nextInt();
	                            MovingFlag = w22.move(x, y, game1);
                            }
                            System.out.println("\nMoving worker : " + worker + " to " + x + ", " + y);
                        } while (!MovingFlag);MovingFlag = false;
                    } else {
                        do {
                        	
                            System.out.println("Invalid Worker Name : Please choose one of your workers < w21 / w22 > ");
                            worker = input.next();
                            if (game1.isTrapped(worker)) {//check if the worker is trapped player will move the other worker
                                System.out.println("\nThis worker can't do any move try your other worker");
                                System.out.print("\nIts new position < x,y > ");
                                x = input.nextInt();
                                y = input.nextInt();
                                if (worker.equals("w21")) {
                                    worker = "w22";
                                    MovingFlag = w22.move(x, y, game1);
                                } else {
                                    worker = "w21";
                                    MovingFlag = w21.move(x, y, game1);
                                }
                                
                            }else {
                            System.out.print("Put its new position < x,y > ");
                            x = input.nextInt();
                            y = input.nextInt();
                            if (worker.equals("w22")) 
                                MovingFlag = w22.move(x, y, game1);
                            else
                                MovingFlag = w21.move(x, y, game1);
                            }
                            System.out.println("\nMoving worker : " + worker + " to " + x + ", " + y);

                        } while (!MovingFlag);MovingFlag = false;
                    }
                }

//this condition to determine if there is a winner or not before he build ..
                if (game1.hasWon(worker)) {
                    break;
                }
//this loop will handle the buildings position if it's possible or not..
                System.out.print("\nbuild position < x,y > ");
                do {
                    a = input.nextInt();
                    b = input.nextInt();
                    System.out.println("\nBuilding at position : " + a + ", " + b);
                } while (!game1.build(a, b, worker));

//this condition to check isTrapped method ..
                if (game1.isTrapped("w11") && game1.isTrapped("w12")) {
                    winner = names[1];
                    break;
                } else if (game1.isTrapped("w21") && game1.isTrapped("w22")) {
                    winner = names[0];
                    break;
                }

            } while (!game1.hasWon(worker));

            System.out.println("\n******************************************\n");
            System.out.println("    ** Congratulations **");

//This condition to determine the winner ..

            if (game1.hasWon(worker)) {
                System.out.println("Player : { " + Worker.player + " } has won!");
            } else if (winner != null) {
                System.out.println("Player : { " + winner + " } has won!");
                System.out.println("Sorry player : " + Worker.player + ", you are trapped");
            }
            System.out.println("\n******************************************\n\n");     
            System.out.println("Do you want to reset the game < yes / no > ");
            answer = input.next();
        } while (SantoriniGame.reset(answer));
//The End of the game : )
        System.out.println("\n\n       __    __"
        		+ "\n      /**\\  /**\\  "
    			+ "\n     /****\\/****\\"
    			+ "\n     \\**********/"
    			+ "\n      \\********/ \tThank You for Playing :)"
    			+ "\n       \\******/"
    			+ "\n        \\****/"
    			+ "\n         \\**/"
    			+ "\n          \\/\n\n"
        		+ "\nTeam: 09"
        		+ "\n1. Ali Alnimer\t\t201780990"
        		+ "\n2. Fozan Alkhalawi\t201727090"
        		+ "\n3. Khaled Khamis\t201734550");
    }
}


