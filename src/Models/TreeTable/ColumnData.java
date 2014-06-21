/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.TreeTable;

import comptedit_db.Exercice;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Flash
 */
public class ColumnData {

    private Exercice exercice_;
    private boolean previous_activated_;
    private List<Object> column_name_;

    public ColumnData(Exercice e, boolean previous_activated) {
        exercice_ = e;
        previous_activated_ = previous_activated;
        column_name_ = new ArrayList<Object>();
        init();
    }

    public void init() {

        GregorianCalendar begin = (GregorianCalendar) Calendar.getInstance();
        begin.setTime(exercice_.getDateBegin());

        GregorianCalendar end = (GregorianCalendar) Calendar.getInstance();
        end.setTime(exercice_.getDateEnd());

        column_name_.add("Structure");
        while (begin.compareTo(end) < 0) {
            column_name_.add(begin.clone());
            if (previous_activated_) {
                GregorianCalendar aux = (GregorianCalendar) begin.clone();
                aux.roll(Calendar.YEAR, -1);
                column_name_.add(aux);
                column_name_.add("#");

            }
            begin.add(Calendar.MONTH, 1);
        }
    }

    public List<Object> getColumnNames() {
        return column_name_;
    }

    public boolean isPreviousActivated() {
        return previous_activated_;
    }

}
