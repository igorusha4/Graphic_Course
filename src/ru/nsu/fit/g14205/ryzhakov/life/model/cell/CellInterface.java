package ru.nsu.fit.g14205.ryzhakov.life.model.cell;

public interface CellInterface {
    int getWidth();
    int getHeight();
    char getCellState(int x, int y);
    double getCellValue(int x, int y);
}
