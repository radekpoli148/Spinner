package spinner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class Spinner extends JFrame{

    public Spinner()
    {
        initComponents();
    }
    public void initComponents()
    {
        this.setTitle("Spinery");
        this.setBounds(300, 320, 400, 200);
        
        String[] miesiace = new DateFormatSymbols().getMonths();
        
        MySpinnerListModel modelMiesiecy = new MySpinnerListModel(obetnijArray(miesiace,0 ,11));
        
        SpinnerNumberModel modelLiczb = new SpinnerNumberModel(1, 1, 31, 1);
        
        JSpinner spinner = new JSpinner(modelMiesiecy);
        JSpinner spinner2 = new JSpinner(modelLiczb);
        
        Calendar kalendarz = Calendar.getInstance();
        Date poczatkowaWartosc = kalendarz.getTime();
        kalendarz.add(Calendar.DAY_OF_MONTH, -20);
        Date minimalnaWartosc = kalendarz.getTime();
        kalendarz.add(Calendar.DAY_OF_MONTH, 40);
        Date maksymalnaWartosc = kalendarz.getTime();
        
        
        SpinnerDateModel spinerDat = new SpinnerDateModel(poczatkowaWartosc, minimalnaWartosc, maksymalnaWartosc, Calendar.DAY_OF_MONTH);
        JSpinner spinner3 = new JSpinner(spinerDat);
        
        JPanel panel = new JPanel();
        
        panel.add(spinner);
        panel.add(spinner2);
        panel.add(spinner3);
        
        this.getContentPane().add(panel, BorderLayout.NORTH);
        
        this.setDefaultCloseOperation(3);
    }
    
    private class MySpinnerListModel extends SpinnerListModel
    {
        public MySpinnerListModel(Object[] values)
        {
            super(values);
            firstValue = values[0];
            lastValue = values[values.length-1];
        }
        public Object getNextValue()
        {
            if(super.getNextValue() == null)
                return firstValue;
            else
                return super.getNextValue();
        }
        public Object getPreviousValue()
        {
            if(super.getPreviousValue() == null)
                return lastValue;
            else
                return super.getPreviousValue();
        }
        Object firstValue, lastValue;
    }
    
    /**
     * obcina tablicę od <code>poczatek</code> do <code>koniec</code>
     * @param operowany operowana tablica
     * @param poczatek gdzie rozpocząć obcinanie
     * @param koniec gdzie zakończyć obcinanie
     * @return zwraca obciętą tablicę 
     */
    private Object[] obetnijArray(Object[] operowany, int poczatek, int koniec)
    {
        //jeżeli podana długość obcięcia jest więkrza niż długość tablicy
        //lub mniejsza od 0, zmien wartość konca na długość tej tablicy. 
        //W inym wypadku nie rob nic
        koniec = koniec>operowany.length-1 || koniec < 0 ? operowany.length-1 : koniec;
        poczatek = poczatek <0 || poczatek > koniec ? 0 : poczatek;  
        
        Object[] tmp = new Object[koniec+1-poczatek];
        
        for(int i = poczatek, j = 0; i < koniec+1; i++, j++)
            tmp[j] = operowany[i];
        
        return operowany;
    }
    
    public static void main(String[] args) {
        new Spinner().setVisible(true);
    }
    
}
