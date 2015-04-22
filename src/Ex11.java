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

  private static void processGame(Cell[][] table, int row, int nextStartingRow, int col) {
    int rowChoice = table[row][col].getRowChoice();
    int colChoice = table[row][col].getColChoice();
    int rowChoiceCompareTo =
        row < table.length - 1 ? table[row + 1][col].getTotalScorePossible() : 0;
    int colChoiceCompareTo =
        col > 0 ? table[row][col - 1].getTotalScorePossible() : 0;

    table[row][col].setBestChoice(
        (rowChoice + rowChoiceCompareTo) > (colChoice + colChoiceCompareTo) ? rowChoice
                                                                            : colChoice);
    table[row][col].setTotalScorePossible(
        Math.max((rowChoice + rowChoiceCompareTo), (colChoice + colChoiceCompareTo)));

    if (row == 0 && col == table.length - 1) {
      displayGameState(table);
    } else if (row == 0 && col != table.length - 1) {
      processGame(table, nextStartingRow, --nextStartingRow, table.length - 1);
    } else {
      processGame(table, --row, nextStartingRow, --col);
    }
  }

  private static void displayGameState(Cell[][] table) {
    System.out.println("\n\n\t\t\u001B[34mBEST SCORE : CHOICE FOR BEST SCORE\u001B[0m");
    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table[row].length; col++) {
        int score = table[row][col].getTotalScorePossible();
        int choice = table[row][col].getBestChoice();
        System.out.print("\t\t");
        System.out.printf("%-12s",
                          ((score == 0) ? "-" : score) + " : " + ((choice == -1) ? "-" : choice));
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int row = args.length - 2, nextStartingRow = args.length - 3, col = args.length - 1;
    processGame(buildInitialTable(args), row, nextStartingRow, col);
  }
}

class Cell {

  private int rowChoice = -1;
  private int colChoice = -1;
  private int totalScorePossible = -1;
  private int bestChoice = -1;

  public Cell(int rowChoice, int colChoice, int totalScorePossible) {
    this.rowChoice = rowChoice;
    this.colChoice = colChoice;
    this.totalScorePossible = totalScorePossible;
  }

  protected void setBestChoice(int bestChoice) {
    this.bestChoice = bestChoice;
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

  protected int getBestChoice() {
    return this.bestChoice;
  }
}
