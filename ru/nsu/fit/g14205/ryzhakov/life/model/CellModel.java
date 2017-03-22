package ru.nsu.fit.g14205.ryzhakov.life.model;

import ru.nsu.fit.g14205.ryzhakov.life.CellSettings;
import ru.nsu.fit.g14205.ryzhakov.life.view.CellView;

public interface CellModel {
    GameSettings getGameSettings();
    void setGameSettings(GameSettings gameSettings);

    void connectCellView(CellView CellView);
    void cellClicked(int x, int y);
    void setCellSize(int width, int height);
    void load(String path);
    void save(String path, CellSettings cellSettings);
    void clear();
    void setRunMode(boolean enabled);
    void setXorMode(boolean enabled);
    void doStep();
}
