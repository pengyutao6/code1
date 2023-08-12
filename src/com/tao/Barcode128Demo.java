package com.tao;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class Barcode128Demo {

    public static final String DefaultFont = "c:/windows/fonts/arialuni.ttf";

    public static void main(String[] args) {
        try {
            createLocationPDFLabel_LeftUpAndRightDown("生成的文件路径","excel文件路径");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("报错了");
        }
    }

    /**
     * 	创建货位标签，左上右下排版
     * @param fileName 生成文件路径
     * @param excelFilePath excel文件路径
     * 	test版本
     */
    public static void createLocationPDFLabel_LeftUpAndRightDown(String fileName,String excelFilePath) throws Exception {
        // 创建文件和必要的目录
        File file = new File(fileName);
        file.getParentFile().mkdirs();

        // 创建新的 PDF 文档并设置字体和字体大小
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(fileName));
        PdfFont font = PdfFontFactory.createFont(DefaultFont, PdfEncodings.IDENTITY_H);
        int fontSize = 13;

        // 定义页面大小、高度和宽度
        float height = 110;
        float width = 200;
        Rectangle pageSize = new Rectangle(width, height);

        // 创建新的 Document 实例并设置边距、字体和字体大小
        Document doc = new Document(pdfDoc, new PageSize(pageSize));
        doc.setMargins(0, 15, 0, 15);
        doc.setFont(font).setFontSize(fontSize);

        ExcelReader reader = ExcelUtil.getReader(excelFilePath);
        List<Map<String,Object>> readAll = reader.readAll();
        int page = 1;

        float barHeight = 35;
        float baseLine = 13;
        float size = 15f;
        float x = 0.64f;

        for (int i = 0; i < readAll.size(); i++) {
            if("".equals(toString(readAll.get(i).get("货位号")))) {
                break;
            }

            if(i!=0) {
                pdfDoc.addNewPage();
            }

            if("单个货位".equals(toString(readAll.get(i).get("备注")))) {
                // 创建和配置用于 image1 和 image2 的 Barcode128 对象
                Barcode128 code = createBarcode128(pdfDoc, toString(readAll.get(i).get("货位号")), barHeight, baseLine, x, size);
                // 从 Barcode128 对象创建 Image 对象
                Image image = new Image(code.createFormXObject(Color.BLACK, Color.BLACK, pdfDoc));
                doc.add(new Paragraph()
                        .add(image)
                        .setTextAlignment(TextAlignment.CENTER)
                );
            }else {
                Barcode128 code1 = createBarcode128(pdfDoc, toString(readAll.get(i).get("货位号")), barHeight, baseLine, x, size);
                Barcode128 code2 = createBarcode128(pdfDoc, toString(readAll.get(i+1).get("货位号")), barHeight, baseLine, x, size);

                Image image1 = new Image(code1.createFormXObject(Color.BLACK, Color.BLACK, pdfDoc));
                Image image2 = new Image(code2.createFormXObject(Color.BLACK, Color.BLACK, pdfDoc));

                // 左上角添加 货位号
                doc.add(new Paragraph().add(image1));

                // 右下角添加 货位号
                doc.add(new Paragraph().add(image2).setFixedPosition(page, width - image2.getImageScaledWidth()-5, 0, width));

                page+=1;
                i++;
            }
        }

        // 关闭文档
        doc.close();
    }

    /**
     *
     * @param pdfDoc	pdfDom对象
     * @param codeContent	条形码内容
     * @param barHeight		条形码高度
     * @param baseLine		条形码下文字与条形码间距
     * @param x				水平位置
     * @param size			字体大小
     * @return
     */
    private static Barcode128 createBarcode128(PdfDocument pdfDoc,String codeContent,float barHeight,float baseLine,float x,float size) {
        Barcode128 code128 = new Barcode128(pdfDoc);
        code128.setCode(codeContent);
        code128.setCodeType(Barcode128.CODE128);
        code128.setBarHeight(barHeight);
        code128.setBaseline(baseLine);//设置文字与条形码的距离
        code128.setSize(size);//bar底部字体大小
        code128.setX(x);
        return code128;
    }

    public static String toString(Object obj){
        if(obj == null){
            return "";
        }else{
            return obj.toString();
        }
    }
}
