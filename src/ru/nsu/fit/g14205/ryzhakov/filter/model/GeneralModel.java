package ru.nsu.fit.g14205.ryzhakov.filter.model;

import ru.nsu.fit.g14205.ryzhakov.filter.view.FilterView;

import java.io.*;
import java.util.Scanner;
import javax.imageio.*;
import java.awt.image.*;

public class GeneralModel implements ImageModel {
    private FilterView filterView;
    private FilterSettings filterSettings = new FilterSettings();
    BufferedImage firstImage;
    BufferedImage secondImage;
    BufferedImage thirdImage;

    public GeneralModel() {
    }

    public void connectCellView(FilterView filterView) {
        this.filterView = filterView;
    }

    public BufferedImage getFirstImage(){
        return firstImage;
    }

    public BufferedImage getSecondImage(){
        return secondImage;
    }

    public BufferedImage getThirdImage(){
        return thirdImage;
    }

    private int parseNextInt(Scanner in){
        while(!in.hasNextInt()){
            in.nextLine();
        }

        return in.nextInt();
    }

    @Override
    public void load(String path) {
        try{
            firstImage = ImageIO.read(new File(path));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setFilterSettings(FilterSettings filterSettings) {
        this.filterSettings = filterSettings;
    }

    @Override
    public void save(String path) {
        try{
            PrintWriter out = new PrintWriter(new File(path));



            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void clear(){


    }

    public FilterSettings getFilterSettings() {
        try {
            return filterSettings.clone();
        }
        catch (CloneNotSupportedException ex){
            return null;
        }
    }
}
