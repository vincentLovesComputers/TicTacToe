import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;
import java.util.*;

public class TicTacToe extends ConsoleProgram {


    public void run() {
        println("Welcome to a game of TicTacToe designed by Vincent.");

        for(int i=0; i<3; i++){
            char[][] board = {{' ', '|', ' ', '|', ' '},
                    {' ', '|', ' ', '|', ' '},
                    {' ', '|', ' ', '|', ' '}};
            ArrayList<Integer> playerPos = new ArrayList<Integer>();
            ArrayList<Integer> cpuPos = new ArrayList<Integer>();
            playGame(board, playerPos, cpuPos);
            int playAgain = readInt("Play again? Press 1 for Yes and 0 for No: ");
            if(playAgain == 1){
                //clear the board
                for(int row=0; row<3;row++){
                    for(int col=0;col<=4;col=col+2){
                        board[row][col] = ' ';
                    }
                }
                //clear arrays
                playerPos.clear();
                cpuPos.clear();
            }
            else{
                break;
            }

        }
        println("Game over!");
    }


    public void playGame(char[][] board, ArrayList playerPos, ArrayList cpuPos)
    {
        displayBoard(board);
        while(true)
        {
            /*players chance to play
             * ask user to enter position to play and check if the position exists in playerPos array or cpuPos array
             * */
            int pos = readInt("Enter position(1-9): ");           //ask player for position

            while(playerPos.contains(pos)|| cpuPos.contains(pos)) {
                //check if entered position exists in player or cpu array
                pos = readInt("Position taken! Enter position(1-9): ");
            }
            play(board, pos, "Player");
            //add entered position into playerPos array
            playerPos.add(pos);
            //display board again
            displayBoard(board);
            //check for a winner
            String result = checkWinner(playerPos, cpuPos);
            if(result.length()>0)
            {
                println(result);
                break;
            }




            /*CPU chance to play
             * check if the position exists in playerPos array or cpuPos array
             * */
            int cpuAI = rgen.nextInt(1, 9);
            while(playerPos.contains(cpuAI)|| cpuPos.contains(cpuAI)) {
                cpuAI = rgen.nextInt(1, 9);
            }
            play(board, cpuAI , "CPU");
            //add randomly generated number into cpuPos array
            cpuPos.add(cpuAI);
            //display board again
            displayBoard(board);
            //check for a winner
            result = checkWinner(playerPos, cpuPos);
            if(result.length()>0)
            {
                println(result);
                break;
            }
        }
    }

    /**displays the board on console*/
    private void displayBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (row > 0) println("-+-+-");
            for (int col = 0; col <= board[0].length - 1; col++) {
                print(board[row][col]);

            }
            println();
        }

    }

    /**game panel */
    public void play(char[][] board, int pos, String user) {
        char symbol = 'X';
        //dynamically change symbol between player and cpu
        if (user.equals("Player")) symbol = 'X';
        else if (user.equals("CPU")) symbol = 'O';

        //assign symbol to board position according to condition satisfied
        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[1][0] = symbol;
                break;
            case 5:
                board[1][2] = symbol;
                break;
            case 6:
                board[1][4] = symbol;
                break;
            case 7:
                board[2][0] = symbol;
                break;
            case 8:
                board[2][2] = symbol;
                break;
            case 9:
                board[2][4] = symbol;
                break;

        }
    }

    /**method to check for the winner*/
    public String checkWinner(ArrayList playerPos, ArrayList cpuPos)
    {
        List topRow = Arrays.asList(1, 2, 3);
        List midHorRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftRow = Arrays.asList(1, 4, 7);
        List midVertRow = Arrays.asList(2, 5, 8);
        List rightRow = Arrays.asList(3, 6, 9);

        List diag1  = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(3, 5, 7);

        //list interface that accepts list object
        List<List> winnerCond = new ArrayList<>();
        winnerCond.add(topRow);
        winnerCond.add(midHorRow);
        winnerCond.add(botRow);
        winnerCond.add(leftRow);
        winnerCond.add(midVertRow);
        winnerCond.add(rightRow);
        winnerCond.add(diag1);
        winnerCond.add(diag2);

        for(int i=0; i<winnerCond.size(); i++)
        {
            if(playerPos.containsAll(winnerCond.get(i)))
            {
                return("Congratulations. You have won!");
            }
            else if(cpuPos.containsAll(winnerCond.get(i)))
            {
                return("Sorry. Computer wins!");
            }
            else if(playerPos.size() + cpuPos.size() == 9){
                return("Draw!");
            }
        }
        return "";
    }

    /*private instance variables*/
    private RandomGenerator rgen = new RandomGenerator();;


}
