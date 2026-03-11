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
        return matrix[row][column];
    }

    public void tick() {
    }
}
