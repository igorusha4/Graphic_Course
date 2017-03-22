package ru.nsu.fit.g14205.ryzhakov.life.model.cell;

public class CellField implements CellInterface {
    static private final int DEFAULT_SIZE = 10;

    private int width;
    private int height;
    private double value[][];
    private char state[][];

    public CellField(){
        this(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public CellField(int width, int height){
        this.width = width;
        this.height = height;

        value = new double[width][height];
        state = new char[width][height];
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public int getHeight(){
        return height;
    }

    @Override
    public char getCellState(int x, int y){
        return state[x][y];
    }

    @Override
    public double getCellValue(int x, int y){
        return value[x][y];
    }

    public void setCellState(int x, int y, char newState) { state[x][y] = newState; }

    public void setCellValue(int x, int y, double newValue) { value[x][y] = newValue; }
}
