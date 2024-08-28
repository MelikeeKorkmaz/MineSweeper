import java.util.Random;

public class MineSweeper {
    int row;
    int col;
    double mine;
    boolean isGameEnd = false;
    int a = 0;
    int count;

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
        this.a = 0;
        this.count = 0;
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

    void play(int k, int l) {
        if ((k < this.row - 2) && (l < this.col - 2) && (0 <= k) && (0 <= l) && (isNumSame(k, l))) {
            if (this.matrix[k + 1][l + 1] == "*") {
                System.out.println("Mayın!!! Oyunu kaybettiniz.");
                printMatrix();
                isGameEnd = true;
            } else {
                this.game[k][l] = this.matrix[k + 1][l + 1];
                printGame();
                if (check()) {
                    System.out.println("Tebrikler!!! Oyunu kazandınız.");
                    isGameEnd = true;
                }
            }
        } else {
            if (!isNumSame(k, l)) {
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

    boolean isNumSame(int k, int l) {
        int number = ((k * 10) + l);

        for (int i : list) {
            if (list[i] == number) {
                if (k == 0 && l == 0 && this.a==0){
                    a++;
                    this.count++;
                    return true;
                }
                return false;
            } else {
                list[this.count++] = number;
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
