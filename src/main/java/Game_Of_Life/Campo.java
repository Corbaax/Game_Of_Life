package Game_Of_Life;

import static Game_Of_Life.Status.*;

public class Campo {
    private static final int KERNEL_SIZE = 3;
    private static final int KERNEL_OFFSET = 1;
    public static final int LIMITE_ISOLAMENTO = 2;
    public static final int LIMITE_SOVRAPPOPOLAZIONE = 3;
    public static final int VALORE_RIPRODUZIONE = 3;

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

        for (int i = 0; i < row(); i++) {
            for (int j = 0; j < column(); j++) {
                // Count Kernel
                int kernelCount = getKernelCount(i, j);
                // rule apply
                newMatrix[i][j] = matrix[i][j];
                if(kernelCount < LIMITE_ISOLAMENTO) newMatrix[i][j]=Dead;
                if(kernelCount > LIMITE_SOVRAPPOPOLAZIONE) newMatrix[i][j]=Dead;
                if(kernelCount == VALORE_RIPRODUZIONE) newMatrix[i][j]=Alive;
            }
        }
        this.matrix = newMatrix;

    }

    private int getKernelCount(int coreRow, int coreColumn) {
        int kernelCount = 0;
        for (int k = 0; k < KERNEL_SIZE; k++) {
            for (int l = 0; l < KERNEL_SIZE; l++) {
                if(isKernelCore(k, l)) continue; // center
                if(isKernelCellAlive(coreRow, coreColumn, k, l)) kernelCount++;
            }
        }
        return kernelCount;
    }

    private boolean isKernelCellAlive(int coreRow, int coreColumn, int kernelRow, int kernelColumn) {
        return this.cellAt(
                coreRow + kernelRow - KERNEL_OFFSET,
                coreColumn + kernelColumn - KERNEL_OFFSET
        ) == Alive;
    }

    private static boolean isKernelCore(int k, int l) {
        return k == l && l == KERNEL_SIZE - KERNEL_OFFSET - 1;
    }

    private int row(){
        return this.matrix.length;
    }
    private int column(){
        return this.matrix[0].length;
    }
}
