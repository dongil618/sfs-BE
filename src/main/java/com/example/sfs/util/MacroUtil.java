package com.example.sfs.util;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MacroUtil {

    /**
     * 마우스 x,y 좌표로 이동 후 클릭
     * @param x
     * @param y
     */
    public static void mouseMoveAndClick(int x, int y) {
        mouseMove(x, y);
        mouseClick();
    }

    /**
     * 마우스 x,y 좌표로 이동
     * @param x
     * @param y
     */
    public static void mouseMove(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 마우스 왼쪽 클릭
     */
    public static void mouseClick() {
        try {
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void ctrlV() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Robot robot = new Robot();
            if(os.contains("mac")) {
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
            } else {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
            }


        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void enter() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

//    public static Point getMouseLocation() {
//        Point point = new Point();
//        Timer t = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                point = MouseInfo.getPointerInfo().getLocation();
//                return point;
//            }
//        };
//        t.schedule(timerTask,0, 3000);
//        return point;
//    }
}
