package ru.nsu.fit.g14205.ryzhakov.filter;

import ru.nsu.fit.g14205.ryzhakov.filter.model.GeneralModel;
import ru.nsu.fit.g14205.ryzhakov.filter.view.GeneralWindow;

import java.util.Timer;

public class Main {
    public static double VERSION = 0.01;

    public static void main(String[] args) {
        GeneralModel model = new GeneralModel();
        GeneralWindow view = new GeneralWindow(model);

        model.connectCellView(view);

        view.setVisible(true);
    }
}
