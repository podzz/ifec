package Parser;

import comptedit_db.Fec;
import comptedit_db.FecRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FecParser {

    private List<FecModel> rows = null;

    public FecParser(String path) {
        rows = new ArrayList<FecModel>();
        parse_fec(path);
    }

    public FecParser(int id) {
        rows = new ArrayList<FecModel>();
        List<Fec> l = FecRequest.getInstance().getListFecOn(id);
        for (Fec fec : l)
        {
            rows.add(fecToModel(fec));
        }

    }

    public final void parse_fec(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            br.readLine();
            String line = null;
            while ((line = br.readLine()) != null) {
                FecModel fec = new FecModel(line);
                rows.add(fec);
            }
            br.close();
        } catch (IOException e) {
        }
    }

    public String[] getColumnNames() {
        return new String[]{"Journal/Code", "Journal/Lib", "EcritureNum", "EcritureDate", "CompteNum", "CompteLib", "CompteAuxNum", "CompteAuxLib", "PieceRef",
            "PieceDate", "Ecriture/Lib", "Montant", "Sens", "Ecriture/Let", "Date/let", "Valid/date", "Montant devise", "IDevise", "Affectation"};
    }

    public Object[][] getRenderingJTable() {
        Object[][] object_array = new Object[rows.size()][19];
        int i = 0;
        for (FecModel r : rows) {
            object_array[i] = r.getArray();
            i++;
        }
        return object_array;
    }

    public Date getLowDate() {
        Date date = null;
        for (FecModel row : rows) {

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(new Date());
            try {
                Date aux_date = format.parse((String) row.getField(FecModel.FecField.Ecriture_date));
                if (date == null || aux_date.compareTo(date) < 0) {
                    date = aux_date;
                }
            } catch (ParseException ex) {
                Logger.getLogger(FecParser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return date;
    }

    public Date getUpDate() {
        Date date = null;
        for (FecModel row : rows) {

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(new Date());
            try {
                Date aux_date = format.parse((String) row.getField(FecModel.FecField.Ecriture_date));
                if (date == null || aux_date.compareTo(date) > 0) {
                    date = aux_date;
                }
            } catch (ParseException ex) {
                Logger.getLogger(FecParser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return date;
    }

    public Date getFormatedDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateString = format.format(new Date());
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(FecParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Fec modelToFec(FecModel mod, int id_fec) {
        Fec fec = new Fec();
        fec.setFec(id_fec);
        fec.setJournalCode((String) mod.getField(FecModel.FecField.Journal_code));
        fec.setJournalLib((String) mod.getField(FecModel.FecField.Journal_lib));
        if (!mod.getField(FecModel.FecField.Ecriture_num).equals("")) {
            fec.setEcritureNum(Integer.parseInt((String) mod.getField(FecModel.FecField.Ecriture_num).toString().trim()));
        } else {
            fec.setEcritureNum(null);
        }
        fec.setEcritureDate(getFormatedDate((String) mod.getField(FecModel.FecField.Ecriture_date)));

        fec.setCompteNum((String) mod.getField(FecModel.FecField.Compte_num));
        fec.setCompteLib(mod.getField(FecModel.FecField.Compte_lib).toString().trim());
        fec.setCompteAuxNum((String) mod.getField(FecModel.FecField.Compte_aux_num));
        fec.setCompteAuxLib((String) mod.getField(FecModel.FecField.Compte_aux_lib));
        fec.setPieceRef(((String) mod.getField(FecModel.FecField.Piece_ref)).substring(0, 2));
        fec.setPieceDate(getFormatedDate((String) mod.getField(FecModel.FecField.Piece_date)));
        fec.setEcritureLib((String) mod.getField(FecModel.FecField.Ecriture_lib));
        fec.setMontant(Float.parseFloat(((String) mod.getField(FecModel.FecField.Montant)).replace(",", ".")));
        fec.setSens(((String) mod.getField(FecModel.FecField.Sens)).charAt(0));
        fec.setEcritureLet((String) mod.getField(FecModel.FecField.Ecriture_let));
        if (!mod.getField(FecModel.FecField.Date_let).equals("")) {
            fec.setDateLet(getFormatedDate((String) mod.getField(FecModel.FecField.Date_let)));
        }

        fec.setValidDate(getFormatedDate((String) mod.getField(FecModel.FecField.Valid_date)));
        
        if (!mod.getField(FecModel.FecField.Montant_devise).equals("")) {
            fec.setMontantDevise(Float.parseFloat(((String) mod.getField(FecModel.FecField.Montant_devise)).replace(",", ".")));
        } else {
            fec.setMontantDevise(null);
        }

        fec.setIDevise((String) mod.getField(FecModel.FecField.IDevise));
        fec.setAffectation((String) mod.getField(FecModel.FecField.Affectation));
        return fec;
    }

    public final FecModel fecToModel(Fec fec) {
        Object[] new_model = new Object[19];
        new_model[0] = fec.getJournalCode();
        new_model[1] = fec.getJournalLib();
        new_model[2] = fec.getEcritureNum();
        new_model[3] = fec.getEcritureDate();
        new_model[4] = fec.getCompteNum();
        new_model[5] = fec.getCompteLib();
        new_model[6] = fec.getCompteAuxNum();
        new_model[7] = fec.getCompteAuxLib();
        new_model[8] = fec.getPieceRef();
        new_model[9] = fec.getPieceDate();
        new_model[10] = fec.getEcritureLib();
        new_model[11] = fec.getMontant();
        new_model[12] = fec.getSens();
        new_model[13] = fec.getEcritureLet();
        new_model[14] = fec.getDateLet();
        new_model[15] = fec.getValidDate();
        new_model[16] = fec.getMontantDevise();
        new_model[17] = fec.getIDevise();
        new_model[18] = fec.getAffectation();

        return new FecModel(new_model);
    }

    public int add_to_database() {
        FecRequest fr = FecRequest.getInstance();
        Integer id_fec = fr.get_first_id_avaible();
        for (FecModel mod : rows) {
            fr.addFec(modelToFec(mod, id_fec));
        }
        return id_fec;
    }

    private Date dateLet;
    private Date validDate;
    private Float montantDevise;
    private String IDevise;

}
