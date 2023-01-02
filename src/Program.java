public class Program {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku("Sudoku.txt");
      //  Sudoku sudoku1 = new Sudoku(sudoku)
        Sudoku sudoku1 = new Sudoku(sudoku.grid2);
        sudoku1.printState();
        sudoku1.writeToFile(sudoku.grid2, "write.txt");


    }
}
