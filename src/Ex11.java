/**
 * Created by matthewmcguire on 4/19/15.
 */
public class Ex11 {

  private static int[][] buildInitialTable(String[] fromCommandLine) {
    int[][] table = new int[fromCommandLine.length][fromCommandLine.length];
    for (int j = 0; j < fromCommandLine.length; j++) {
      for (int i = 0; i < fromCommandLine.length; i++) {
        if (j == 0 && i != 0) {
          table[i][j] = Integer.parseInt(fromCommandLine[i - 1]);
        } else if (i == 0 && j != 0) {
          table[i][j] = Integer.parseInt(fromCommandLine[j]);
        } else if (i == j) {
          table[i][j] = 0;
        }
      }
    }
    return table;
  }

  public static void main(String[] args) {
    int[][] initialTable = buildInitialTable(args);
    System.out.println("HERE:)");
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
