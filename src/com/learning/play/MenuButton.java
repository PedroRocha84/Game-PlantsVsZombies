package com.learning.play;

import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;


public class MenuButton{

    private Picture picture;
    private int x, y, width, height;
    private String picturePath;

    public MenuButton() {
        this.x = 700;
        this.y = 10;
        this.picturePath = "resources/images/menus.png";

        this.picture = new Picture(x,y,picturePath);
        this.width = picture.getWidth();
        this.height = picture.getHeight();
    }

//    public int getWidth() {
//        return picture.getWidth();
//    }
//    public int getHeight() {
//        return picture.getHeight();
//    }

    public void showMenu(){
        picture.draw();

    }

    public boolean isOver(MouseEvent mouseEvent){
        int mouseX =(int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();
        System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
        System.out.println("Button bounds: " + x + "," + y + " to " + (x + width) + "," + (y + height));

        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;

    }

    Picture newMenu = new Picture(250,20,"resources/images/originals1.png");

    public void openNewMenu(){
        newMenu.draw();

    }
    public void closeMenu(){
        newMenu.delete();

    }
}
