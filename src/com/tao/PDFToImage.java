package com.tao;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFToImage {
    public static void main(String[] args) {
        String pdfFilePath = "G:\\pdf\\filename.pdf";
        String outputDir = "G:\\pdf\\pdfToImage\\filename.png";

        try {
            convertPDFToImage(pdfFilePath, outputDir);
            System.out.println("转化成功.");
        } catch (IOException e) {
            System.err.println("文件转化出错!");
            e.printStackTrace();
        }
    }

    public static void convertPDFToImage(String pdfFilePath, String outputImagePath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        int numPages = document.getNumberOfPages();
        int maxWidth = 0;
        int totalHeight = 0;

        for (int pageIndex = 0; pageIndex < numPages; pageIndex++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300);
            maxWidth = Math.max(maxWidth, image.getWidth());
            totalHeight += image.getHeight();
        }

        BufferedImage combinedImage = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = combinedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, maxWidth, totalHeight);

        int y = 0;
        for (int pageIndex = 0; pageIndex < numPages; pageIndex++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300);
            graphics.drawImage(image, 0, y, null);
            y += image.getHeight();
        }

        graphics.dispose();

        ImageIO.write(combinedImage, "PNG", new File(outputImagePath));
        document.close();
    }
}
