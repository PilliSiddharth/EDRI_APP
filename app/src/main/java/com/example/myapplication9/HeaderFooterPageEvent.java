package com.example.myapplication9;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

import androidx.core.content.res.ResourcesCompat;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class HeaderFooterPageEvent extends PdfPageEventHelper {


    public void onEndPage(PdfWriter writer,Document document) {
//        Rectangle rect = writer.getBoxSize("art");
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("IIIT Hyderabad, https://www.iiit.ac.in/\nI-HUB https://ihub-data.iiit.ac.in/"), 110, 30, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("I-HUB https://ihub-data.iiit.ac.in/"), 500, 30, 0);

//        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("I-HUB, https://ihub-data.iiit.ac.in/"), rect.getRight(), rect.getBottom(), 0);

    }

}
