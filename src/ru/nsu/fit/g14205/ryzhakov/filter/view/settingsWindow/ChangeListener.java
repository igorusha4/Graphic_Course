package ru.nsu.fit.g14205.ryzhakov.filter.view.settingsWindow;

import ru.nsu.fit.g14205.ryzhakov.filter.model.FilterSettings;

public interface ChangeListener {
    void run(int width, int height, FilterSettings filterSettings, boolean xorMode);
}
