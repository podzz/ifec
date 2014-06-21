package Parser;

public class FecModel
{
	public enum FecField
	{
           
		Journal_code, Journal_lib, Ecriture_num, Ecriture_date, Compte_num, Compte_lib, Compte_aux_num, Compte_aux_lib, Piece_ref, Piece_date, Ecriture_lib, Montant, Sens, Ecriture_let, Date_let, Valid_date, Montant_devise, IDevise, Affectation
	
        
        }

        
	private Object[] array_field = null;

	public FecModel(String line)
	{
            array_field = new Object[19];
                for (int i = 0; i < 18; i++)
                    array_field[i] = line.split("\\t")[i];
                array_field[18] = null;
	}
        
        public FecModel(Object[] l)
        {
            array_field = l;
        }
        

	public Object getField(FecField ff)
	{
		switch (ff)
		{
		case Journal_code:
			return array_field[0];
		case Journal_lib:
			return array_field[1];
		case Ecriture_num:
			return array_field[2];
		case Ecriture_date:
			return array_field[3];
		case Compte_num:
			return array_field[4];
		case Compte_lib:
			return array_field[5];
		case Compte_aux_num:
			return array_field[6];
		case Compte_aux_lib:
			return array_field[7];
		case Piece_ref:
			return array_field[8];
		case Piece_date:
			return array_field[9];
		case Ecriture_lib:
			return array_field[10];
		case Montant:
			return array_field[11];
		case Sens:
			return array_field[12];
		case Ecriture_let:
			return array_field[13];
		case Date_let:
			return array_field[14];
		case Valid_date:
			return array_field[15];
		case Montant_devise:
			return array_field[16];
		case IDevise:
			return array_field[17];
                case Affectation:
                        return array_field[18];
		default:
			return null;
		}
	}
        
        public Object getField(int ff)
        {
            switch (ff)
		{
		case 0:
			return array_field[0];
		case 1:
			return array_field[1];
		case 2:
			return array_field[2];
		case 3:
			return array_field[3];
		case 4:
			return array_field[4];
		case 5:
			return array_field[5];
		case 6:
			return array_field[6];
		case 7:
			return array_field[7];
		case 8:
			return array_field[8];
		case 9:
			return array_field[9];
		case 10:
			return array_field[10];
		case 11:
			return array_field[11];
		case 12:
			return array_field[12];
		case 13:
			return array_field[13];
		case 14:
			return array_field[14];
		case 15:
			return array_field[15];
		case 16:
			return array_field[16];
		case 17:
			return array_field[17];
                case 18:
                        return array_field[18];
		default:
			return null;
		}
        }

        
	public Object[] getArray()
	{
		return array_field;
	}
}
