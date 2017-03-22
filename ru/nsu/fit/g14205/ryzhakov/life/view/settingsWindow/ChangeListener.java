package ru.nsu.fit.g14205.ryzhakov.life.view.settingsWindow;

import ru.nsu.fit.g14205.ryzhakov.life.CellSettings;
import ru.nsu.fit.g14205.ryzhakov.life.model.GameSettings;

public interface ChangeListener {
    void run(CellSettings cellSettings, int width, int height, GameSettings gameSettings, boolean xorMode);
}
