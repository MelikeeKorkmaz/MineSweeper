import java.util.Arrays;
import java.util.Random;

public class MineSweeper {
    int row;
    int col;
    double mine;

    String[][] matrix;
    String[][] game;

    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.mine = 0;
        this.matrix = new String[row][col];
        this.game = new String[row][col];
    }

    void calcMine() {
        this.mine = Math.round((this.row * this.col) / 4.0);
    }

    void placeMine() {
        calcMine();
        Random rand = new Random();
        int i = 0;
        while (i < (this.mine)) {
            int x = rand.nextInt(this.row);
            int y = rand.nextInt(this.col);
            if (!(matrix[x][y]).equals("*")) {
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

    boolean isBig(int a) {
        if (a > (this.row-1)){
            return false;
        }
        if (a > (this.col-1)){
            return false;
        }
        return true;
    }

    boolean isSmall(int a) {
        if (a < 0) {
            return false;
        }
        return true;
    }

    void clue() {
        baseMatrix();

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {

                if(i != 0){
                    this.matrix[i-1][j] += 1;
                    if(j != 0){
                        this.matrix[i-1][j-1] += 1;
                    }
                    if(j != col-1){
                        this.matrix[i-1][j+1] += 1;
                    }
                }
                if(i != row-1){
                    this.matrix[i+1][j] += 1;
                    if(j != 0){
                        this.matrix[i+1][j-1] += 1;
                    }
                    if(j != col-1){
                        this.matrix[i+1][j+1] += 1;
                    }
                }
                if(j != 0){
                    this.matrix[i][j-1] += 1;
                }
                if(j != col-1){
                    this.matrix[i][j+1] += 1;
                }

            }
        }




    }


/*
    void game(){
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.col; j++){
                this.matrix[i][j] = "-";
            }
        }
    }

 */

    void printMatrix() {
        clue();
        for (String[] i : this.matrix) {
            for (String j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}




