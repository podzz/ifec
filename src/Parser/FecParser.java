package Parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FecParser
{
	private List<FecModel> rows = null;

	public FecParser(String path)
	{
		rows = new ArrayList<FecModel>();
		parse_fec(path);
	}

	public void parse_fec(String path)
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			br.readLine();
			String line = null;
			while ((line = br.readLine()) != null)
			{
				FecModel fec = new FecModel(line);
				rows.add(fec);
			}
			br.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getColumnNames()
	{
		return new String[]
		{ "Journal/Code", "Journal/Lib", "EcritureNum", "EcritureDate", "CompteNum", "CompteLib", "CompteAuxNum", "CompteAuxLib", "PieceRef",
				"PieceDate", "Ecriture/Lib","Montant", "Sens", "Ecriture/Let", "Date/let", "Vali/date", "Montant devise", "IDevise" };
	}
	
	public Object[][] getRenderingJTable()
	{
		Object[][] object_array = new Object[rows.size()][18];
		int i = 0;
		for (FecModel r : rows)
		{
			object_array[i] = r.getArray();
			i++;
		}
		return object_array;
	}

}
