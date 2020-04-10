import java.util.Scanner;

/*
* Connect4Grid interface
    An interface with the following (abstract) methods:
    public void emptyGrid();
    public String toString();
    public boolean isValidColumn(int column);
    public boolean isColumnFull(int column);
    public void dropPiece(ConnectPlayer player, int column);
    public boolean didLastPieceConnect4();
    public boolean isGridFull();
Connect4Grid2DArray class
    This class implements the Connect4Grid interface and provides appropriate functionality for each of the methods in the interface
-ConnectPlayer abstract class
    An abstract class that provides the operations (methods) which any type of Connect Four Player class must override.
-C4HumanPlayer class
    A class which extends the ConnectPlayer abstract class and whose objects represent a human player (a user) who is involved in a Connect Four game.
-C4RandomAIPlayer class
    A class which extends the ConnectPlayer abstract class and whose objects represent Random AI Player (a computer) which is involved in a Connect Four game.
*/
/* SELF ASSESSMENT

Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players.
It asks the user whether he/she would like to play/quit inside a loop.
 If the user decides to play then:
 1. Connect4Grid2DArray is created using the Connect4Grid interface,
 2. the two players are initialised - must specify the type to be ConnectPlayer, and
 3. the game starts. In the game, I ask the user where he/she would like to drop the piece.
    I perform checks by calling methods in the Connect4Grid interface.
    Finally a check is performed to determine a win.
Comment: I think I did what I should and the class does what it should.

Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
Comment: I think I did what I should and the interface does what it should.

Connect4Grid2DArray class (25 marks)
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: I think I did what I should and the class does what it should.

ConnectPlayer abstract class (10 marks)
My class provides at lest one non-abstract method and at least one abstract method.
Comment:I think I did what I should and the class does what it should.

C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment:I think I did what I should and the class does what it should.

C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality.
Comment:I think I did what I should and the class does what it should.

Total Marks out of 100:100

*/

import java.util.Scanner;

public class Connect4Game {
    public static final char PLAYER1_SYMBOL = '+';
    public static final char PLAYER2_SYMBOL = 'O';
    public static void main(String[] args)
    {
        Connect4Grid2DArray board = new Connect4Grid2DArray();
        Scanner input = new Scanner(System.in);
        ConnectPlayer player1 = null;
        ConnectPlayer player2 = null;
        boolean finished = false;
        while(!finished)
        {
            board.emptyGrid();
            String player1String =   "";
            String player2String = "";
            String instruction;
            while(!player1String.equals("Human")&&
                    !player1String.equals("human")&&
                    !player1String.equals("AI")&&
                    !player1String.equals("ai") &&
                    !player1String.equals("Quit")&&
                    !player1String.equals("quit"))
            {
                instruction = "Choose Player One -> 'Human' or 'AI' or you can'Quit':";
                player1String = getString(input,instruction);
                if(!player1String.equals("Human")&&
                        !player1String.equals("human")&&
                        !player1String.equals("AI")&&
                        !player1String.equals("ai") &&
                        !player1String.equals("Quit")&&
                        !player1String.equals("quit"))
                {
                    System.out.println("Invalid input. Please enter 'Human or 'AI' or 'Quit'.\n");
                }
                if(player1String.equals("Human")||player1String.equals("human"))
                {
                    player1 = new C4HumanPlayer(PLAYER1_SYMBOL);
                }
                else if(player1String.equals("AI")||player1String.equals("ai"))
                {
                    player1 = new C4RandomAIPlayer(PLAYER1_SYMBOL);
                }
                else if(player1String.equals("Quit")||player1String.equals("quit"))
                {
                    finished = true;
                }
            }
            while(!player2String.equals("Human") &&
                    !player2String.equals("human")&&
                    !player2String.equals("AI")&&
                    !player2String.equals("ai") &&
                    !player2String.equals("Quit")&&
                    !player2String.equals("quit")&&
                    !finished)
            {
                instruction = "Choose Player Two -> 'Human' or 'AI' or you can'Quit' :";
                player2String = getString(input,instruction);
                if(!player2String.equals("Human")&&
                        !player2String.equals("human")&&
                        !player2String.equals("AI")&&
                        !player2String.equals("ai"))
                {
                    System.out.println("Invalid input. Please enter 'Human or 'AI' or 'Quit'.\n");
                }
                switch (player2String) {
                    case "Human":
                    case "human":
                        player2 = new C4HumanPlayer(PLAYER2_SYMBOL);
                        break;
                    case "AI":
                    case "ai":
                        player2 = new C4RandomAIPlayer(PLAYER2_SYMBOL);
                        break;
                    case "Quit":
                    case "quit":
                        finished = true;
                        break;
                }
            }
            if(!finished)
            {
                System.out.print(""+board.toString());
                while(!board.didLastPieceConnect4()&&!board.isGridFull())
                {
                    boolean validColumn=false;
                    while(!validColumn)
                    {
                        int player1Column = player1.columnToPlay();
                        if(board.isValidColumn(player1Column))
                        {
                            validColumn = true;
                            board.dropPiece(player1, player1Column);
                            player1Column++;
                            System.out.print("\nPlayer One placing piece "+player1.getPiece()
                                    +" in column "+(player1Column)+".");
                        }
                        else
                        {
                            System.out.print("Invalid column, please try again.");
                        }
                    }
                    System.out.print(""+board.toString());
                    validColumn = false;
                    if(!board.didLastPieceConnect4())
                    {
                        while(!validColumn)
                        {
                            int player2Column = player2.columnToPlay();
                            if(board.isValidColumn(player2Column))
                            {
                                validColumn = true;
                                board.dropPiece(player2, player2Column);
                                player2Column++;
                                System.out.print("\nPlayer Two placing piece "+player2.getPiece()+
                                        " in column "+(player2Column)+".");
                                if(board.didLastPieceConnect4())
                                {
                                    System.out.print("\nPlayer Two WON!\n");
                                }
                            }
                        }
                        System.out.print(""+board.toString());
                    }
                    else
                    {
                        System.out.print("\nPlayer One WON!\n");
                    }
                    if(board.isGridFull()&!board.didLastPieceConnect4())
                    {
                        System.out.print("\nIt is a tie!");
                    }
                }
            }
            else if(finished)
            {
                System.out.println("Kono, Giorno Giovanna, niwa yume ga aru");
                System.out.println(" Arrivederci !!!! ");

            }
        }
    }

    public static String getString(Scanner scanner,String instruction) {
        boolean gotName = false;
        while(!gotName)
        {
            System.out.println(instruction);
            String inputtedPlainText = scanner.nextLine();
            boolean validName = inputtedPlainText.matches("[a-zA-Z]+");
            if (validName)
            {
                gotName = true;
                return inputtedPlainText;
            }
            else if (!validName)
            {
                System.out.println("Invalid input.");
            }
        }
        return null;
    }
}

