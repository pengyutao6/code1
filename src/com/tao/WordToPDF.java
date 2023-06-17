package com.tao;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.junit.Test;


/**
 * 以下源码转载于：https://blog.csdn.net/u011134399/article/details/118892122
 * 以下代码是经过以上代码的优化出来
 */

public class WordToPDF {

    /**
     * 测试Word文档转图片
     */
    @Test
    public void TestWordToImages(){
        try {
            // 设置Word文件路径
            String wordFilePath = "Word文件的路径";
            // 设置JPG图片路径
            String jpgImagePath = "jpg图片路径";
            // 设置PDF文件路径
            String pdfFilePath = "PDF文件的路径";
            // 创建Word文件对象
            File wordFile = new File(wordFilePath);
            // 创建输入流
            InputStream inputStream = new FileInputStream(wordFile);
            // 将Word文件转换为图片列表
            List<BufferedImage> wordImages = convertWordToImages(inputStream);
            // 合并图片并保存为JPG格式
            BufferedImage mergedImage = mergeImages(false, wordImages);
            saveImageAsJPEG(mergedImage, jpgImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试Word文档转PDF文件
     */
    @Test
    public void TestWordToPDF(){
        try {
            // 设置Word文件路径
            String wordFilePath = "Word文件的路径";
            // 设置PDF文件路径
            String pdfFilePath = "PDF文件的路径";
            //word转DPF
            wordToPDF(wordFilePath,pdfFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 检查是否有Word许可证
    public static boolean isWordLicense() {
        boolean result = false;
        try {
            // Word许可证XML
            String licenseXml = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            // 创建许可证输入流
            InputStream licenseInputStream = new ByteArrayInputStream(licenseXml.getBytes());
            // 创建许可证对象
            License license = new License();
            // 设置许可证
            license.setLicense(licenseInputStream);
            // 许可证验证成功
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 将Word文件转换为图片列表
    private static List<BufferedImage> convertWordToImages(InputStream inputStream) throws Exception {
        // 检查是否有Word许可证
        if (!isWordLicense()) {
            return null;
        }

        try {
            // 创建Word文档对象
            Document document = new Document(inputStream);
            // 创建图片保存选项
            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
            //是否启用格式化选项
            options.setPrettyFormat(true);
            //是否启用抗锯齿选项
            options.setUseAntiAliasing(true);
            //是否启用高质量渲染选项
            options.setUseHighQualityRendering(true);
            // 获取文档页数
            int pageCount = document.getPageCount();

            // 创建图片列表
            List<BufferedImage> imageList = new ArrayList<>();
            for (int i = 0; i < pageCount; i++) {
                // 创建输出流
                OutputStream output = new ByteArrayOutputStream();
                // 设置当前页索引
                options.setPageIndex(i);
                // 将当前页保存为图片
                document.save(output, options);
                // 从输出流中读取图片并添加到列表
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(((ByteArrayOutputStream) output).toByteArray()));
                imageList.add(image);
            }
            return imageList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 合并图片
    private static BufferedImage mergeImages(boolean isHorizontal, List<BufferedImage> images) {
        // 计算合并后的图片宽度和高度
        int width = 0;
        int height = 0;
        for (BufferedImage image : images) {
            if (isHorizontal) {
                width += image.getWidth();
                height = Math.max(height, image.getHeight());
            } else {
                width = Math.max(width, image.getWidth());
                height += image.getHeight();
            }
        }

        // 创建合并后的图片对象
        BufferedImage mergedImage;
        if (isHorizontal) {
            mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        } else {
            mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }

        // 创建Graphics2D对象并设置背景颜色
        Graphics2D g2 = mergedImage.createGraphics();
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.RED);

        int x = 0;
        int y = 0;
        // 在合并图片对象上绘制各个图片
        for (BufferedImage image : images) {
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            if (isHorizontal) {
                g2.drawImage(image, x, 0, null);
                x += imageWidth;
            } else {
                g2.drawImage(image, 0, y, null);
                y += imageHeight;
            }
        }

        // 释放Graphics2D对象资源
        g2.dispose();
        return mergedImage;
    }

    // 将图片保存为JPEG格式
    private static void saveImageAsJPEG(BufferedImage image, String filePath) throws IOException {
        // 创建输出文件对象
        File outputFile = new File(filePath);
        // 将图片保存为JPEG格式
        ImageIO.write(image, "jpg", outputFile);
    }

    // 将Word文件转换为PDF
    public static void wordToPDF(String docPath, String savePath) {
        try {
            // Word许可证XML
            String licenseXml = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            // 创建许可证输入流
            InputStream licenseInputStream = new ByteArrayInputStream(licenseXml.getBytes());
            // 创建许可证对象
            License license = new License();
            // 设置许可证
            license.setLicense(licenseInputStream);
            // 创建Word文档对象
            Document document = new Document(docPath);
            // 将Word文档保存为PDF格式
            document.save(new FileOutputStream(new File(savePath)), SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




