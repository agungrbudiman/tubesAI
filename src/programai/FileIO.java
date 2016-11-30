/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programai;

import java.io.File;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author agungrb
 */
public class FileIO {
    private String namafile;
    
    public FileIO(String namafile) {
        this.namafile = namafile;
    }
    
    public ArrayList baca(int kolom) throws Exception {
        ArrayList l = new ArrayList();
        File fileExcel = new File(namafile);
        Workbook w;
        w = Workbook.getWorkbook(fileExcel);
        Sheet sheet = w.getSheet(0);
        for (int i = 1; i < sheet.getRows(); i++) {
            Cell cell = sheet.getCell(kolom,i);
            l.add(cell.getContents());
        }
        w.close();
        return l;
    }
    
    public void tulis (ArrayList out,ArrayList x4, ArrayList x7) throws Exception {
        Workbook w = Workbook.getWorkbook(new File(namafile));
        WritableWorkbook copy = Workbook.createWorkbook(new File("Output.xls"),w);
        WritableSheet sheet = copy.getSheet(0);
        sheet.addCell(new Label(12,0,"x4"));
        sheet.addCell(new Label(13,0,"x7"));
        sheet.addCell(new Label(14,0,"out"));
        for (int i = 0; i < out.size(); i++) {
            sheet.addCell(new Label(14,i+1,out.get(i).toString()));
            sheet.addCell(new Label(12,i+1,x4.get(i).toString()));
            sheet.addCell(new Label(13,i+1,x7.get(i).toString()));
        }
        copy.write();
        copy.close();
        w.close();
    }
}
