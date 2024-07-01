package SnakeGameZoho;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {
    private char[][] board;
    private Queue<Node> snake;
    private Queue<Node> food;
    private int currentRow, currentCol;
    private int score;

    Snake(int row, int col){
        board = new char[row][col];
        snake = new LinkedList<>();
        food = new LinkedList<>();
        currentRow = 0;
        currentCol = 0;
        score = 0;

        initializeBoard();
        snake.add(new Node(0, 0));
        board[0][0] = 'S';

        food.add(new Node(1, 2));
        food.add(new Node(0, 2));
        food.add(new Node(2, 0));
        displayFood();

    }

    public void initializeBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '|';
            }
        }
    }

    public void displayFood() {
        if(!food.isEmpty()){
            Node node = food.peek();
            int r = node.getRow();
            int c = node.getCol();
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length){
                board[r][c] = 'F';
            }
        }
    }

    public void moveForward(int row, int col){
        if(row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            board[row][col] = 'S';
            printSnake();
        }
        else {
            System.out.println("Invalid move.");
            System.exit(0);
        }
    }

    public void printSnake() {
        for (char[] ch : board){
            for (int i = 0; i < board[0].length; i++) {
                System.out.print(ch[i] + " ");
            }
            System.out.println();
        }
        System.out.println("Score: " + score);
    }
    public void movement(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            if (board[row][col] == 'F') {
                food.poll();
                displayFood();
                score++;
                if (food.isEmpty()){
                    System.out.println("Score: " + score);
                    System.out.println("Congratulations you won the game. ");
                    System.exit(0);
                }
            } else if (board[row][col] == 'S') {
                System.out.println("Game over. You hit yourself.");
                System.exit(0);
            } else {
                Node tail = snake.poll();
                board[tail.getRow()][tail.getCol()] = '|';
            }

            snake.add(new Node(row, col));
            moveForward(row, col);
            currentRow = row;
            currentCol = col;
        } else {
            System.out.println("Invalid move. Game over.");
            System.exit(0);
        }
    }
    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        printSnake();
        while(true){
            System.out.print("Enter the direction: ");
            char dir = scanner.next().charAt(0);
            switch (dir){
                case 'U':
                    movement(currentRow - 1, currentCol);
                    break;
                case 'D':
                    movement(currentRow + 1, currentCol);
                    break;
                case 'L':
                    movement(currentRow, currentCol - 1);
                    break;
                case 'R':
                    movement(currentRow, currentCol + 1);
                    break;
                default:
                    System.out.println("Invalid direction. Use U, D, L, R.");
                    break;
            }
        }
    }
}
