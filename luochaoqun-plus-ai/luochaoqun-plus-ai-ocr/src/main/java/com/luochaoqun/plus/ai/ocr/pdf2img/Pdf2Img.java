package com.luochaoqun.plus.ai.ocr.pdf2img;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

public class Pdf2Img {

    public static String pdfParseImg(String pdfPath,String imgType,String imgPath) {
        InputStream byteInputStream = null;
        FileInputStream inputStream =null;
        ImageOutputStream imOut = null;
        ByteArrayOutputStream bs = null;
        FileOutputStream fops = null;

        try {
            inputStream = new FileInputStream(new File(pdfPath));
            PDDocument document = PDDocument.load(inputStream);
            PDFRenderer renderer= new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();
            if (pageCount > 0) {
                for (int i = 0;i<20;i++) {
                    BufferedImage image = renderer.renderImage(i, 2.0f);
                    image.flush();
                    bs = new ByteArrayOutputStream();
                    imOut = ImageIO.createImageOutputStream(bs);
                    ImageIO.write(image, imgType, imOut);
                    byteInputStream = new ByteArrayInputStream(bs.toByteArray());
                    fops = new FileOutputStream(new File(String.format(imgPath,i)));
                    fops.write(readInputStream(byteInputStream));
                    fops.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(bs);
            if(imOut != null){
                try {
                    imOut.close();
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            IOUtils.closeQuietly(byteInputStream);
            IOUtils.closeQuietly(fops);
        }

        return null;

    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        return outStream.toByteArray();
    }

    public static void main(String[] args) {
        // String pdfPath = "/Users/luochaoqun/Downloads/java.pdf";
        String xinHuaZiDianPath = "/Users/luochaoqun/Documents/xinHuaZiDian/xinHuaDiction.pdf";
        String imgPath = "/Users/luochaoqun/Documents/xinHuaZiDian/imgs/%s.png";

        pdfParseImg(xinHuaZiDianPath,"png",imgPath);
    }
}
