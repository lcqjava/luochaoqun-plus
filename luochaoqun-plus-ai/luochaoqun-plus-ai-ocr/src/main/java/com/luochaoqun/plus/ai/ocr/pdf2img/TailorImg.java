package com.luochaoqun.plus.ai.ocr.pdf2img;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TailorImg {

    public static BufferedImage imgTailor(String srcImgPath,int x,int y,int width,int height) {

        BufferedImage srcBufferedImage = file2Image(srcImgPath);

        BufferedImage targetBufferedImage = srcBufferedImage.getSubimage(x,y,width,height);

        return targetBufferedImage;
    }

    public static BufferedImage file2Image(String imagePath) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            System.out.println("width="+bufferedImage.getWidth()+",height="+bufferedImage.getHeight());
            return bufferedImage;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //保存图片,extent为格式，"jpg"、"png"等
    public static void img2file(BufferedImage img,String extent,String newfile) {
        try {
            ImageIO.write(img, extent, new File(newfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String srcImagePath = "/Users/luochaoqun/Documents/xinHuaZiDian/imgs/13.png";
        String targetImgPath =  "/Users/luochaoqun/Documents/xinHuaZiDian/t.png";
        int height = 10269;
        int y = 1100;
        BufferedImage bufferedImage = imgTailor(srcImagePath,0,y,7059,height-y);
        img2file(bufferedImage,"png",targetImgPath);
    }
}
