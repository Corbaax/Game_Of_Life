package Game_Of_Life;

import static Game_Of_Life.Status.*;

public class Campo {
    private Status[][] matrix;

    public Campo(int row, int column) {
        this.matrix = new Status[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = Dead;
            }
        }
    }

    public void makeLife(int row, int column) {
        matrix[row][column] = Alive;
    }

    public Status cellAt(int row, int column) {
        row = (row + row()) % row();
        column = (column + column()) % column();
        return matrix[row][column];
    }

    public void tick() {
        Status[][] newMatrix = new Status[row()][column()];

        // Rule 0
        for (int i = 0; i < row(); i++) {
            for (int j = 0; j < column(); j++) {
                // Count Kernel
                int kernelCount = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(k == l && l == 1) continue; // center
                        if(this.cellAt(i+k-1, j+l-1) == Alive) kernelCount++;
                    }
                }
                // rule apply
                newMatrix[i][j] = matrix[i][j];
                if(kernelCount < 2) newMatrix[i][j]=Dead;
            }
        }
        this.matrix = newMatrix;

    }

    private int row(){
        return this.matrix.length;
    }
    private int column(){
        return this.matrix[0].length;
    }
}
