import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//Класс будет представлять из себя поле судоку.
public class Sudoku {
    private List<Integer> grid;     //Само поле судоку.

    public Sudoku(List<Integer> grid) {
        this.grid = grid;
    }


    //Получаем index в нашем grid по номеру строки и столбца
    private int getIndex(int row, int column) {
        return row * 9 + column;
    }

    //Возвращает true, если схема судоку валидная, false в ином случае
    /*
    Нужно сделать 3 проверки:
    1. Проверка уникальности чисел в каждой строке
    2. Проверка уникальности чисел в каждом столбце
    3. Проверка уникальности чисел в каждом квадрате 3х3

     */
    public boolean isValid() {
        //1, 2. Проверка уникальности по строкам и столбцам.
        for (int i = 0; i < 9; ++i) {
            Set<Integer> numbersInRow = new HashSet<>();
            Set<Integer> numbersInColumn = new HashSet<>();
            for (int j = 0; j < 9; ++j) {
                int numberInRow = grid.get(getIndex(i, j));
                int numberInColumn = grid.get(getIndex(j, i));
                if (numberInRow != 0) {
                    //Возникло повторение
                    if (numbersInRow.contains(numberInRow)) {
                        return false;
                    }
                    numbersInRow.add(numberInRow);
                }
                if (numberInColumn != 0) {
                    //Возникло повторение
                    if (numbersInColumn.contains(numberInColumn)) {
                        return false;
                    }
                    numbersInColumn.add(numberInColumn);
                }
            }
        }

        //3. Проверка уникальности внутри квадратов 3х3
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                //На каждом шаге мы имеем какой-то левый верхний угол квадрата 3х3.
                Set<Integer> numbersInSquare = new HashSet<>();
                //Перебираем всевозможные клетки внутри квадрата
                for (int deltaRow = 0; deltaRow < 3; deltaRow++) {
                    for (int deltaColumn = 0; deltaColumn < 3; deltaColumn++) {
                        int row = i + deltaRow;
                        int column = j + deltaColumn;
                        int number = grid.get(getIndex(row, column));
                        if (number != 0) {
                            if (numbersInSquare.contains(number)) {
                                return false;
                            }
                            numbersInSquare.add(number);
                        }
                    }
                }
            }
        }
        return true;
    }

    //Даёт нам значение, которое лежит в позиции row, column
    public int get(int row, int column) {
        int index = getIndex(row, column);
        return grid.get(index);
    }

    //Устанавливает в позиции row, column значение value
    public void set(int row, int column, int value) {
        int index = getIndex(row, column);
        grid.set(index, value);
    }

    public void printState() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(get(i, j) + " ");
            }
            System.out.println();
        }
    }
    //..............................................................

//  1.  Добавьте в класс Sudoku перегрузку конструктора public Sudoku(String filename),
//      который будет читать из файла нашу схему судоку.ffff


    List<Integer> grid2 = new ArrayList<>();


    public Sudoku(String filename) {
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s = null;
            while ((s = bufferedReader.readLine()) != null) {

                String s1 = s.replace(" ", "");
                String[] stringArray = s1.split(",");
                for (String s2 : stringArray) {
                    int num = Integer.parseInt(s2);
                    grid2.add(num);
                }

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    //    2. Добавьте в класс Sudoku метод writeToFile(List<Integer> grid,String fileName), который будет записывать схему судоку в файл
    public void writeToFile(List<Integer> grid, String fileName) {
        this.grid = grid;
        int j=0;
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Integer i : grid) {
                j++;
                fileWriter.write(i + " ");
                if(j%9==0) {
                    fileWriter.write("\n");
                }

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    //..............................................................

}


