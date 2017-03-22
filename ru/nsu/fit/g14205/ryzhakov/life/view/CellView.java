package ru.nsu.fit.g14205.ryzhakov.life.view;

import ru.nsu.fit.g14205.ryzhakov.life.CellSettings;
import ru.nsu.fit.g14205.ryzhakov.life.model.cell.CellInterface;
import ru.nsu.fit.g14205.ryzhakov.life.model.CellModel;

public interface CellView {
    void connectCellModel(CellModel CellModel);
    void updateCell(CellInterface Cell);
    void updateCellViewSettings(CellSettings cellSettings);
}
