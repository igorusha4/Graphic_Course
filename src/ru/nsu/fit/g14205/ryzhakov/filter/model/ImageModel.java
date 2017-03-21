package ru.nsu.fit.g14205.ryzhakov.filter.model;

import java.awt.image.BufferedImage;

public interface ImageModel {
    FilterSettings getFilterSettings();
    public BufferedImage getFirstImage();
    public BufferedImage getSecondImage();
    public BufferedImage getThirdImage();
    void setFilterSettings(FilterSettings filterSettings);
    void load(String path);
    void save(String path);
    void clear();
}
