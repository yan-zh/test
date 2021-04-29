package com.example.demo.digitalSigniture;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.PdfTrueTypeFont;
import com.spire.pdf.security.GraphicMode;
import com.spire.pdf.security.PdfCertificate;
import com.spire.pdf.security.PdfCertificationFlags;
import com.spire.pdf.security.PdfSignature;
import com.spire.pdf.widget.PdfFormFieldWidgetCollection;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfSignatureFieldWidget;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Signiture {
    public static Boolean creat(String fileURL,String pfxURL,String password,String nameOfViewer) {
        // write your code here
        //加载PDF文档
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(fileURL);

        //加载pfx证书，及证书秘钥
        PdfCertificate cert = new PdfCertificate(pfxURL, password);

        //加一页为证书
        doc.getPages().add();


        //添加数字签名到指定页面，并设置其位置和大小
        PdfSignature signature = new PdfSignature(doc, doc.getPages().get(doc.getPages().getCount()-1), cert, "MySignature");
        Rectangle2D rect = new Rectangle2D.Float();
        rect.setFrame(new Point2D.Float((float) doc.getPages().get(0).getActualSize().getWidth() - 340, (float) doc.getPages().get(0).getActualSize().getHeight() - 230), new Dimension(280, 150));
        signature.setBounds(rect);

        //设置签名为文本模式
        signature.setGraphicMode(GraphicMode.Sign_Detail);

        //设置签名的内容

        signature.setNameLabel("签字者：");
        signature.setName(nameOfViewer);
        signature.setDateLabel("日期：");
        signature.setDate(new java.util.Date());
        signature.setLocationInfoLabel("地点：");
        signature.setLocationInfo("Shandong");


        //设置签名的字体
        signature.setSignDetailsFont(new PdfTrueTypeFont(new Font("Arial Unicode MS", Font.BOLD, 9)));

        //设置文档权限为禁止更改
        signature.setDocumentPermissions(PdfCertificationFlags.Forbid_Changes);
        signature.setCertificated(true);

        //保存文档
        doc.saveToFile(fileURL);
        doc.close();

        return true;
    }

    public static Boolean verify(String fileURL){
        //创建PdfDocument实例
        PdfDocument doc = new PdfDocument();
        //加载含有签名的PDF文件
        doc.loadFromFile(fileURL);

        Boolean result = false;
        //获取域集合
        PdfFormWidget pdfFormWidget = (PdfFormWidget) doc.getForm();
        PdfFormFieldWidgetCollection pdfFormFieldWidgetCollection = pdfFormWidget.getFieldsWidget();

        //遍历域
        for (int i = 0; i < pdfFormFieldWidgetCollection.getCount(); i++) {
            //判定是否为签名域
            if (pdfFormFieldWidgetCollection.get(i) instanceof PdfSignatureFieldWidget) {
                //获取签名域
                PdfSignatureFieldWidget signatureFieldWidget = (PdfSignatureFieldWidget) pdfFormFieldWidgetCollection.get(i);
                //获取签名
                PdfSignature signature = signatureFieldWidget.getSignature();

                //判定签名是否有效
                result = signature.verifySignature();
                if (result) {

                } else {
                    return false;
                }
            }
        }

        return result;
    }


}
