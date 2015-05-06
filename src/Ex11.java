import java.util.Scanner;

/**
 * Created by matthewmcguire on 4/19/15.
 */
public class Ex11 {

  private static Cell[][] buildInitialTable(String[] fromCommandLine) {
    Cell[][] table = new Cell[fromCommandLine.length][fromCommandLine.length];
    for (int i = 0; i < fromCommandLine.length; i++) {
      for (int j = 0; j < fromCommandLine.length; j++) {
        if (i == j) {
          Cell c = new Cell(Integer.parseInt(fromCommandLine[i]), Integer.parseInt(fromCommandLine[j]),
                            Integer.parseInt(fromCommandLine[i]));
          c.setBestChoice(Integer.parseInt(fromCommandLine[i]));
          table[i][j] = c;
        } else {
          table[i][j] = new Cell(Integer.parseInt(fromCommandLine[i]), Integer.parseInt(fromCommandLine[j]), 0);
        }
      }
    }
    return table;
  }

  private static void processGame(Cell[][] table, int row, int nextStartingRow, int col) {
    int rowChoice = table[row][col].getRowChoice();
    int colChoice = table[row][col].getColChoice();

    int opponentsNextChoiceA = ((col - 2) >= 0) ? table[row][col - 2].getTotalScorePossible() : 0;
    int
        opponentNextChoiceB =
        ((row + 1) < table.length && (col - 1) >= 0) ? table[row + 1][col - 1].getTotalScorePossible() : 0;
    int opponentNextChoiceC = ((row + 2) < table.length) ? table[row + 2][col].getTotalScorePossible() : 0;

    int colChoiceMin = Math.min(opponentsNextChoiceA, opponentNextChoiceB);
    int rowChoiceMin = Math.min(opponentNextChoiceB, opponentNextChoiceC);

    int colChoiceScore = colChoice + colChoiceMin;
    int rowChoiceScore = rowChoice + rowChoiceMin;

    table[row][col].setTotalScorePossible(Math.max(colChoiceScore, rowChoiceScore));
    table[row][col].setBestChoice((colChoiceScore > rowChoiceScore) ? colChoice : rowChoice);

    if (row == 0 && col == table.length - 1) {
      displayGameState(table);
    } else if (row == 0 && col != table.length - 1) {
      processGame(table, nextStartingRow, --nextStartingRow, table.length - 1);
    } else {
      processGame(table, --row, nextStartingRow, --col);
    }
  }

  private static void displayGameState(Cell[][] table) {
    System.out.println("\n\n\t\u001B[34mBEST SCORE : CHOICE FOR BEST SCORE\u001B[0m");
    for (int row = 0; row < table.length; row++) {
      for (int col = 0; col < table[row].length; col++) {
        int score = table[row][col].getTotalScorePossible();
        int choice = table[row][col].getBestChoice();
        if ((row == 0) && (col == table.length - 1)) {
          System.out.print("\t");
          System.out.printf("%-12s", ((score == 0) ? "-" : "\u001B[34m" + score + "\u001B[0m") + " : " +
                                     ((choice == -1) ? "-" : "\u001B[34m" + choice + "\u001B[0m"));
        } else {
          System.out.print("\t");
          System.out.printf("%-12s", ((score == 0) ? "-" : score) + " : " + ((choice == -1) ? "-" : choice));
        }
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Cell[][] initialTable = buildInitialTable(UserData.getUserInput());
    int row = initialTable.length - 2, nextStartingRow = initialTable.length - 3, col = initialTable.length - 1;
    processGame(initialTable, row, nextStartingRow, col);
  }

}

class UserData {

  static String[] getUserInput() {
    Scanner in = new Scanner(System.in);
    System.out.println("");

    System.out.println("\tPlease enter the how many numbers you would like to play with:");
    int amountOfNumbers = -9;
    while (!in.hasNextInt()) {
      System.err.println("Please enter a valid integer for the amount of numbers to play with.");
      try {
        amountOfNumbers = Integer.getInteger(in.next());
      } catch (Exception e) {
      }
    }
    if (amountOfNumbers == -9) {
      amountOfNumbers = in.nextInt();
    }

    String[] initialValues = new String[amountOfNumbers];

    for (int i = 0; i < amountOfNumbers; i++) {
      int value = -9;
      System.out.println("\tPlease enter a value to play with.");
      while (!in.hasNextInt()) {
        System.err.println("Please enter a valid integer to play with.");
        try {
          value = Integer.getInteger(in.next());
        } catch (Exception ignored) {
        }
      }
      if (value == -9) {
        value = in.nextInt();
      }
      initialValues[i] = String.valueOf(value);
    }
    return initialValues;
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
