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

    public void showMenu(){
        picture.draw();

    }

    public boolean isOver(MouseEvent mouseEvent){
        int mouseX =(int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();
        System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);

        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;

    }




}
