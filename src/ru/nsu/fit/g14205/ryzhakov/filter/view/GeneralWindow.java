package ru.nsu.fit.g14205.ryzhakov.filter.view;

import ru.nsu.fit.g14205.ryzhakov.filter.Main;
import ru.nsu.fit.g14205.ryzhakov.filter.view.imagePanel.ImagePanel;
import ru.nsu.fit.g14205.ryzhakov.filter.view.imagePanel.ImagePanelClickListener;
import ru.nsu.fit.g14205.ryzhakov.filter.model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class GeneralWindow extends JFrame implements FilterView {
    private ImagePanel imagePanel;
    private ImageModel imageModel;
    private JScrollPane scrollPane;
    private MenuBar menuBar;
    private StatusBar statusBar;
    private JToolBar toolBar;

    private Map<String, Menu> menus = new TreeMap<>();
    private Map<String, AbstractButton> buttons = new TreeMap<>();
    private Map<String, MenuItem> menuItems = new TreeMap<>();

    private boolean autoRunEnabled = false;

    private void addButton(String name, String menuName, String tooltip, boolean autoDisengaging, Runnable onClickAction){
        AbstractButton button;
        MenuItem menuItem;

        if(autoDisengaging){
            button = new JButton(name);
            menuItem = new MenuItem(name);

            menuItem.addActionListener(e -> {
                if(menuItem.isEnabled()) {
                    onClickAction.run();
                }
            });
        }
        else{
            button = new JToggleButton(name);
            CheckboxMenuItem cmenuItem = new CheckboxMenuItem(name);

            cmenuItem.addItemListener(e -> {
                if(cmenuItem.isEnabled()) {
                    onClickAction.run();
                }
            });

            menuItem = cmenuItem;
        }

        button.setToolTipText(tooltip);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            boolean this_button = false;

            @Override
            public void mouseReleased(MouseEvent e) {
                if(button.isEnabled() && this_button) {
                    onClickAction.run();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                this_button = true;
                statusBar.setText(tooltip);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statusBar.setText("");
                this_button = false;
            }
        };

        button.addMouseListener(mouseAdapter);
        toolBar.add(button);

        if(!menus.containsKey(menuName)){
            Menu menu = new Menu(menuName);
            menus.put(menuName, menu);
            menuBar.add(menu);
        }

        menus.get(menuName).add(menuItem);

        menuItems.put(name, menuItem);
        buttons.put(name, button);
    }

    public GeneralWindow(ImageModel model)
    {
        setSize(1300, 600);
        setLocationByPlatform(true);
        setTitle("Фильтры");

        imageModel = model;

        imagePanel = new ImagePanel(new ImagePanelClickListener() {
            @Override
            public void onClickOnCell(int x, int y) {
                if(null != imageModel && !autoRunEnabled){
                }
            }
        }, imageModel);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                onExit();
            }
        });

        scrollPane = new JScrollPane(imagePanel);

        add(scrollPane, BorderLayout.CENTER);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        menuBar = new MenuBar();
        this.setMenuBar(menuBar);

        statusBar = new StatusBar();
        toolBar = new JToolBar();

        addButton("Save", "File", "Сохранить текущую сессию", true, () -> {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\Data\\");
            fileChooser.setDialogTitle("Сохранить сессию");
            int chosenFile = fileChooser.showSaveDialog(GeneralWindow.this);
            if (chosenFile == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if(null != imageModel){
                    imageModel.save(file.getAbsolutePath());
                    //imagePanel.paintComponents(imagePanel.getGraphics());
                }
            }
        });

        addButton("Load", "File", "Загрузить сессию", true, () -> {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\Data\\");
            fileChooser.setDialogTitle("Загрузить сессию");
            int chosenFile = fileChooser.showOpenDialog(GeneralWindow.this);
            if (chosenFile == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if(null != imageModel){
                    imageModel.load(file.getAbsolutePath());
                    imagePanel.repaint();
                }
            }
        });


        addButton("Info", "Help", "Показать информацию об авторе", true, () ->
                JOptionPane.showMessageDialog(null, "Задание: \t Фильтры\n" +
                                            "Автор:\t Рыжаков Игорь\n" +
                                            "Группа: \t 14205\n" +
                                            "Версия: \t " + Double.toString(Main.VERSION),
                "Информация об авторе", JOptionPane.INFORMATION_MESSAGE));

        add(toolBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
    }



    public void onExit(){
//        if (imagePanel.getIsChanged()){
//            int reply = JOptionPane.showConfirmDialog(null, "Сохранить изменения?", "Выход",JOptionPane.YES_NO_OPTION);
//            if (reply == JOptionPane.OK_OPTION){
//                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\Data\\");
//                fileChooser.setDialogTitle("Сохранить сессию");
//                int chosenFile = fileChooser.showSaveDialog(GeneralWindow.this);
//                if (chosenFile == JFileChooser.APPROVE_OPTION) {
//                    File file = fileChooser.getSelectedFile();
//                    if(null != CellModel){
//                        CellModel.save(file.getAbsolutePath(), imagePanel.getCellSettings());
//                    }
//                }
//            }
//        }
        System.exit(0);
    }

    @Override
    public void connectFilterModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

}
