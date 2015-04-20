/**
 * Created by matthewmcguire on 4/19/15.
 */
public class Ex11 {

  private static Cell[][] buildInitialTable(String[] fromCommandLine) {
    Cell[][] table = new Cell[fromCommandLine.length][fromCommandLine.length];
    for (int i = 0; i < fromCommandLine.length; i++) {
      for (int j = 0; j < fromCommandLine.length; j++) {
        table[i][j] =
            new Cell(Integer.parseInt(fromCommandLine[i]), Integer.parseInt(fromCommandLine[j]),
                     0);
      }
    }
    return table;
  }

  private static void processGame(Cell[][] table, int row, int col, int gameState) {
    if (table.length >= 1) {
      int rowChoice = table[row][col].getRowChoice();
      int colChoice = table[row][col].getColChoice();
      int rowChoiceCompareTo =
          row < table.length - 1 ? table[row + 1][col].getTotalScorePossible() : 0;
      int colChoiceCompareTo =
          col > 0 ? table[row][col - 1].getTotalScorePossible() : 0;

      if (row == 0 && col == 0) {
        System.out
            .println(gameState % 2 == 0 && gameState != 0 ? "Player 2's choice table:"
                                                          : "Player 1's choice table:");
        displayGameState(table);

        if ((rowChoice + rowChoiceCompareTo) > (colChoice + colChoiceCompareTo)) {
          processGame(stripRowCol(table, table.length - 1), table.length - 2, table.length - 2,
                      gameState++);
        } else {
          processGame(stripRowCol(table, 0), table.length - 2,
                      table.length - 2, gameState++);
        }
      } else {
        if (col > row) {
          table[row][col].setTotalScorePossible(
              Math.max((rowChoice + rowChoiceCompareTo), (colChoice + colChoiceCompareTo)));

          processGame(table, row, col - 1, gameState);
        } else {
          processGame(table, row - 1, table.length - 1, gameState);
        }
      }
    }
  }

  private static void displayGameState(Cell[][] table) {

  }

  private static Cell[][] stripRowCol(Cell[][] table, int rowColToBeStripped) {
    Cell[][] newTable = new Cell[table.length - 2][table.length - 2];
    if (rowColToBeStripped == 0) {
      for (int i = 1; i < table.length - 1; i++) {
        for (int j = 1; j < table.length - 1; j++) {
          newTable[i][j] = table[i][j];
        }
      }
    } else if (rowColToBeStripped == table.length - 1) {
      for (int i = 0; i < table.length - 2; i++) {
        for (int j = 0; j < table.length - 2; j++) {
          newTable[i][j] = table[i][j];
        }
      }
    } else {
      System.err.println(
          "Problem with parameters passed when attempting to strip row and column for next game state.");
      System.exit(1);
    }
    return newTable;
  }

  public static void main(String[] args) {
    processGame(buildInitialTable(args), args.length - 1, args.length - 1, 0);
  }
}

class Cell {

  private int rowChoice = -1;
  private int colChoice = -1;
  private int totalScorePossible = -1;

  public Cell(int rowChoice, int colChoice, int totalScorePossible) {
    this.rowChoice = rowChoice;
    this.colChoice = colChoice;
    this.totalScorePossible = totalScorePossible;
  }

  protected void setTotalScorePossible(int totalScorePossible) {
    this.totalScorePossible = totalScorePossible;
  }

  protected int getRowChoice() {
    return this.rowChoice;
  }

  protected int getColChoice() {
    return this.colChoice;
  }

  protected int getTotalScorePossible() {
    return this.totalScorePossible;
  }
}
