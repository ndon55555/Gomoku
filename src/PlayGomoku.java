import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class PlayGomoku {
  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(1200, 900);
    panel.setBackground(new Color(180, 102, 65));
    Graphics g = panel.getGraphics();

    for (int i = 0; i < 16; i++) { //lines
      int s = 50 * (i + 1);
      g.drawLine(s, 50, s, 800); //x1 y1 x2 y2   vertical
      g.drawLine(50, s, 800, s);               //horizontal
    }

    for (int i = 0; i < 15; i++) { //number grid
      int s = 50 * (i + 1);
      g.drawString("" + i, s + 20, 40); //across the board
      g.drawString("" + i, 30, s + 25); //down the board
    }

    Scanner console = new Scanner(System.in);
    System.out.print("Name of Player 1: ");
    String player1 = console.nextLine();
    System.out.print("Name of Player 2: ");
    String player2 = console.nextLine();
    GomokuBoard board = new GomokuBoard(player1, player2);

    while (!board.isOver()) {
      System.out.println(board.getCurrentPlayer() + "'s turn.");
      System.out.print("Row: ");
      int row = console.nextInt();
      System.out.print("Col: ");
      int col = console.nextInt();
      System.out.println();

      if (board.getPlayerTurn() == 0) {
        g.setColor(Color.BLACK);
      } else {
        g.setColor(Color.WHITE);
      }

      try {
        board.place(row, col);
        g.fillOval(50 * (col + 1) + 1, 50 * (row + 1) + 1, 49, 49); //x y w h
      } catch (Exception e) {
        System.out.println("Try again.");
      }
    }
  }
}


