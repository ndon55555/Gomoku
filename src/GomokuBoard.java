public class GomokuBoard {
  private static final int BLACK = 0;
  private static final int WHITE = 1;
  private int[][] board;
  private int playerTurn;
  private String namePlayer1; //black
  private String namePlayer2; //white
  private String winner;
  private int piecesOnBoard;
  private boolean playerWon;

  public GomokuBoard(String namePlayer1, String namePlayer2) {
    board = new int[15][15];

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        board[row][col] = -1;
      }
    }

    playerTurn = BLACK;
    this.namePlayer1 = namePlayer1;
    this.namePlayer2 = namePlayer2;
    winner = "Nobody";
    piecesOnBoard = 0;
    playerWon = false;
  }

  public String getCurrentPlayer() {
    return (playerTurn == BLACK) ? namePlayer1 : namePlayer2;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  public void place(int row, int col) {
    if (!isInBoard(row, col)) {
      throw new IllegalArgumentException("Position not in board.");
    }

    if (board[row][col] != -1) {
      throw new IllegalArgumentException("Position is filled.");
    }

    board[row][col] = playerTurn;
    checkForVictory(row, col);
    playerTurn = (playerTurn + 1) % 2;
    piecesOnBoard++;
  }

  private boolean isInBoard(int row, int col) {
    return (0 <= row && row < board.length) && (0 <= col && col < board[0].length);
  }

  private void checkForVictory(int row, int col) {
    checkRow(row);
    checkCol(col);
    checkDiagonal(row, col);
  }

  private void checkRow(int row) {
    int count = 0;

    for (int col = 0; col < board[0].length; col++) {
      if (board[row][col] == playerTurn) {
        count++;
      } else {
        count = 0;
      }

      if (count == 5) {
        playerWon = true;
        winner = (playerTurn == BLACK) ? namePlayer1 : namePlayer2;
        break;
      }
    }
  }

  private void checkCol(int col) {
    int count = 0;

    for (int row = 0; row < board.length; row++) {
      if (board[row][col] == playerTurn) {
        count++;
      } else {
        count = 0;
      }

      if (count == 5) {
        playerWon = true;
        winner = (playerTurn == BLACK) ? namePlayer1 : namePlayer2;
        break;
      }
    }
  }

  private void checkDiagonal(int row, int col) {
    int topLeftRow = row;
    int topLeftCol = col;

    while (topLeftRow > 0 && topLeftCol > 0) {
      topLeftRow--;
      topLeftCol--;
    }

    checkLeftDiagonal(topLeftRow, topLeftCol);

    int topRightRow = row;
    int topRightCol = col;

    while (topRightRow > 0 && topRightCol < board[0].length - 1) {
      topRightRow--;
      topRightCol++;
    }

    checkRightDiagonal(topRightRow, topRightCol);
  }

  private void checkLeftDiagonal(int topLeftRow, int topLeftCol) {
    int count = 0;

    for (int row = topLeftRow, col = topLeftCol;
         row < board.length - 1 && col < board[0].length - 1;
         row++, col++) {
      if (board[row][col] == playerTurn) {
        count++;
      } else {
        count = 0;
      }

      if (count == 5) {
        playerWon = true;
        winner = (playerTurn == BLACK) ? namePlayer1 : namePlayer2;
        break;
      }
    }
  }

  private void checkRightDiagonal(int topRightRow, int topRightCol) {
    int count = 0;

    for (int row = topRightRow, col = topRightCol;
         row < board.length - 1 && col >= 0;
         row++, col--) {
      if (board[row][col] == playerTurn) {
        count++;
      } else {
        count = 0;
      }

      if (count == 5) {
        playerWon = true;
        winner = (playerTurn == BLACK) ? namePlayer1 : namePlayer2;
        break;
      }
    }
  }

  public boolean isOver() {
    if (piecesOnBoard == 19 * 19 || playerWon) {
      System.out.println(winner + " wins!");
      return true;
    }

    return false;
  }

  public String toString() {
    String s = "";

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        int num = board[row][col];

        if (num == -1) {
          s += "- ";
        } else if (num == BLACK) {
          s += "B ";
        } else {
          s += "W ";
        }
      }

      s += "\n";
    }

    return s;
  }
}


