package com.learning.play;

import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.pictures.Picture;

public class NewMenu {

        private Picture picture;
        private int x, y, width, height;
        private String picturePath;

        public NewMenu() {
            this.x = 250;
            this.y = 20;
            this.picturePath = "resources/images/originals1.png";

            this.picture = new Picture(x,y,picturePath);
            this.width = picture.getWidth();
            this.height = picture.getHeight();
        }

    public void openNewMenu(){
        picture.draw();

    }
    public void closeMenu(){

        picture.delete();

    }
    public boolean isOver(MouseEvent mouseEvent){
        int mouseX =(int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();
        System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);

        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;

    }
}
