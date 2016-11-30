/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programai;

import java.util.ArrayList;

/**
 *
 * @author agungrb
 */
public class Fuzzy {
    public double min,max;
    private int vvvlow,vvlow,vlow,low,mlow,mhigh,high,vhigh,vvhigh,vvvhigh;
    public ArrayList l1,l2,out;

    public Fuzzy(ArrayList l1,ArrayList l2) {
        this.l1 = l1;
        this.l2 = l2;
    } 

    public void setBatas(int vvvlow, int vvlow, int vlow, int low, int mlow, int mhigh, int high, int vhigh, int vvhigh, int vvvhigh) {
        this.vvvlow = vvvlow;
        this.vvlow = vvlow;
        this.vlow = vlow;
        this.low = low;
        this.mlow = mlow;
        this.mhigh = mhigh;
        this.high = high;
        this.vhigh = vhigh;
        this.vvhigh = vvhigh;
        this.vvvhigh = vvvhigh;
    }

    

    
    
    public void cariMinMax(ArrayList l) {
        min = 0;
        max = 0;
        for (int i = 0; i < l.size(); i++) {
            if(Double.parseDouble((String) l.get(i)) < min) {
                min = Double.parseDouble((String) l.get(i));
            }  
            else if(Double.parseDouble((String) l.get(i)) > max) {
                max = Double.parseDouble((String) l.get(i));
            }     
        }
    }
    
    public void normalisasi(ArrayList l) {
        cariMinMax(l);
        ArrayList temp = new ArrayList();
        for (int i = 0; i < l.size(); i++) {
            double d = Double.parseDouble((String) l.get(i));
            int x = (int) Math.round((d-min)*100/(max-min));
            temp.add(x);
        }
        if(l == l1) {
           l1 = temp; 
        }
        else if(l == l2) {
            l2 = temp;
        }
    }
    
    public void fuzifikasi(ArrayList l) {
        ArrayList temp = new ArrayList();
        for (int i = 0; i < l.size(); i++) {
            if((Integer)l.get(i)<=vvvlow) {
                temp.add("VVVLOW");
            }
            else if((Integer)l.get(i)<=vvlow) {
                temp.add("VVLOW");
            }
            else if((Integer)l.get(i)<=vlow) {
                temp.add("VLOW");
            }
            else if((Integer)l.get(i)<=low) {
                temp.add("LOW");
            }
            else if((Integer)l.get(i)<=mlow) {
                temp.add("MLOW");
            }
            else if((Integer)l.get(i)<=mhigh) {
                temp.add("MHIGH");
            }
            else if((Integer)l.get(i)<=high) {
                temp.add("HIGH");
            }
            else if((Integer)l.get(i)<=vhigh) {
                temp.add("VHIGH");
            }
            else if((Integer)l.get(i)<=vvhigh) {
                temp.add("VVHIGH");
            }
            else if((Integer)l.get(i)<=vvvhigh) {
                temp.add("VVVHIGH");
            }
            
        }
        if(l == l1) {
           l1 = temp; 
        }
        else if(l == l2) {
            l2 = temp;
        }
    }
    
    public ArrayList inferensi(ArrayList l1, ArrayList l2) {
        ArrayList temp = new ArrayList();
        for (int i = 0; i < l1.size(); i++) {
            if(((String)l1.get(i)).equals("VVVLOW")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("VVLOW") && ((String)l2.get(i)).equals("VVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("VVLOW") && ((String)l2.get(i)).equals("VVVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("VVLOW")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("VLOW") && ((String)l2.get(i)).equals("VVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("VLOW") && ((String)l2.get(i)).equals("VVVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("VLOW")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("LOW") && ((String)l2.get(i)).equals("VHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("LOW") && ((String)l2.get(i)).equals("VVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("LOW") && ((String)l2.get(i)).equals("VVVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("LOW")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("MLOW") && ((String)l2.get(i)).equals("VHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("MLOW") && ((String)l2.get(i)).equals("VVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("MLOW") && ((String)l2.get(i)).equals("VVVHIGH")) {
                temp.add(0);
            }
            else if(((String)l1.get(i)).equals("MLOW")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("MHIGH") && ((String)l2.get(i)).equals("MHIGH")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("MHIGH") && ((String)l2.get(i)).equals("HIGH")) {
                temp.add(1);
            }
            else if(((String)l1.get(i)).equals("MHIGH")) {
                temp.add(0);
            }
            else {
               temp.add(0);
            }
        }
        return temp;
    }
    
    public void proses() {
        normalisasi(l1);
        fuzifikasi(l1);
        normalisasi(l2);
        fuzifikasi(l2);
        out = inferensi(l1,l2);
    }
    
    public String hitungAkurasi(ArrayList in, ArrayList out) {
        double sum=0;
        for (int i = 0; i < in.size(); i++) {
            if(in.get(i).toString().equals(out.get(i).toString())) {
                sum++;
            }
         }
        double d = sum/in.size()*100.0;
        String s = String.format("%.3f", d);
        return s;
    }
}
