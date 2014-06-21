/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models.TreeTable;

import comptedit_db.Exercice;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Flash
 */
public class RowData {
    private Exercice exercice_;
    private ColumnData cd_;
    private final String acount_;
    private String result_;
    
    public RowData(String account, Exercice e, ColumnData cd)
    {
        acount_ = account;
        exercice_ = e;
        cd_ = cd;
        result_ = null;
        init();
    }

    private void init() {
        result_ = "0";
    }
    
    public String getResult(GregorianCalendar date)
    {
        Integer n = Integer.parseInt(acount_.substring(0,6));
        return String.valueOf(n);
    }
    
    public String getAcount()
    {
        return acount_;
    }
}
