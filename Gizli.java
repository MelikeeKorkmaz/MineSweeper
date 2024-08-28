import java.util.Random;

public class MineSweeper {
    int row;
    int col;
    double mine;
    boolean isGameEnd = false;

    String[][] matrix;
    String[][] game;
    int[] list;

    MineSweeper(int row, int col) {
        this.row = row + 2;
        this.col = col + 2;
        this.mine = 0;
        this.matrix = new String[this.row][this.col];
        this.game = new String[this.row - 2][this.col - 2];
        this.list = new int[row * col];
    }

    void calcMine() {
        this.mine = Math.round(((this.row - 2) * (this.col - 2)) / 4.0);
    }

    void placeMine() {
        calcMine();
        Random rand = new Random();
        int i = 0;
        while (i < (this.mine)) {
            int x = rand.nextInt(this.row - 1);
            int y = rand.nextInt(this.col - 1);
            if (!(matrix[x][y]).equals("*") && (x != 0) && (y != 0)) {
                this.matrix[x][y] = "*";
                i++;
            }
        }
    }

    void baseMatrix() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j] = "0";
            }
        }
        placeMine();
    }

    void frame() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (i == 0 || (i == this.row - 1)) {
                    matrix[i][j] = "_";
                } else if (j == 0 || (j == this.col - 1)) {
                    matrix[i][j] = "|";
                }
            }
        }
    }

    void code(String[][] arr, int i, int j) {
        if (arr[i][j] != "*") {
            int x;
            String str = arr[i][j];
            int number = Integer.parseInt(str);
            number++;
            arr[i][j] = Integer.toString(number);
        }
    }


    void clue() {
        baseMatrix();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (matrix[i][j] == "*") {
                    code(this.matrix, i - 1, j);
                    code(this.matrix, i - 1, j - 1);
                    code(this.matrix, i - 1, j + 1);
                    code(this.matrix, i + 1, j - 1);
                    code(this.matrix, i + 1, j);
                    code(this.matrix, i + 1, j + 1);
                    code(this.matrix, i, j - 1);
                    code(this.matrix, i, j + 1);
                }
            }
        }
        frame();
    }


    void printMatrix() {
        clue();
        for (String[] i : this.matrix) {
            for (String j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    void game() {
        for (int i = 0; i < this.row - 2; i++) {
            for (int j = 0; j < this.col - 2; j++) {
                game[i][j] = "-";
            }
        }
    }

    void play(int x, int y) {
        isNumSame(x, y);
        if ((x < this.row - 2) && (y < this.col - 2) && (0 <= x) && (0 <= y) && (isNumSame(x, y))) {
            if (this.matrix[x + 1][y + 1] == "*") {
                System.out.println("Mayın!!! Oyunu kaybettiniz.");
                printMatrix();
                isGameEnd = true;
            } else {
                this.game[x][y] = this.matrix[x + 1][y + 1];
                printGame();
                if (check()) {
                    System.out.println("Tebrikler!!! Oyunu kazandınız.");
                    isGameEnd = true;
                }
            }
        } else {
            if (!isNumSame(x, y)) {
                System.out.println("Bu koordinat daha önce seçildi, başka bir koordinat girin!");
            } else {
                System.out.println("Yanlış değer girdiniz!");
                System.out.println("Satır ve sütun sayınız 0 ve 0'dan büyük olmalı. Satır sayınız " + (this.row - 2) + " değerinden, sütun sayınız " + (this.col - 2) + " değerinden küçük olmalı.");
            }
        }
    }

    boolean check() {
        for (int i = 0; i < this.row - 2; i++) {
            for (int j = 0; j < this.col - 2; j++) {
                if (this.game[i][j] == "-") {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isNumSame(int x, int y) {
        int a = 0;
        int number = ((x * 10) + y);

        int count = 0;
        for (int i : list) {
            if (list[i] == number) {
                if (x == 0 && y == 0 && a==0){
                    a++;
                    count++;
                    return true;
                }
                return false;
            } else {
                list[count++] = number;
                return true;
            }
        }
        return true;
    }

    void printGame() {
        for (String[] i : this.game) {
            for (String j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
