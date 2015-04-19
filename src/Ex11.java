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

  private static void processGame(Cell[][] table, int row, int col) {
    if (table.length != 1) {
      if (row == 0 && col == 0) {
        displayGameState(table);
        //TODO decide which move to make at Cell [0][row.length]
      } else {
        //TODO recursively process table
      }
    }
  }

  private static void displayGameState(Cell[][] table) {

  }

  public static void main(String[] args) {
    processGame(buildInitialTable(args), args.length, args.length);
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
