
import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import java.util.Date;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.util.Collections;

public class IstoricVoluntariat {
	private int id_istoric;
	private int ore_acumulate;
	private Map <Activitate, Integer> listaIstoric;
	
	public IstoricVoluntariat(int id_istoric)
	{
		this.id_istoric = id_istoric;
		ore_acumulate = 0 ;
		listaIstoric = new HashMap<>();
	}
	
	public void adaugareActivitate(Activitate activitate, int nrOre)
	{
		listaIstoric.put(activitate, nrOre);
	}
	
	public int calculeazaOre()
	{
		int total[] = {0};
		
		listaIstoric.forEach((a,i) -> {
			total[0] = total[0] + i;
		});
		
		return total[0];
	}
	
	public void genereazaAdeverinta()
	{
		try {
			PdfWriter writer = new PdfWriter("Raport.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            String[] content = {""};
            content[0] = "ID Istoric Voluntariat: " + id_istoric + "\n\n\n";
            
            listaIstoric.forEach((a,i) -> {
            	content[0] = content[0] + "ID Activitate: " + a.getId_activitate() + "\n";
            	content[0] = content[0] + "Titlu: " + a.getTitlu() + "\n";
            	content[0] = content[0] + "Ore petrecute: " + i + "\n\n";
            });
			
			content[0] = content[0] + "\n" + "NR. DE ORE PETRECUTE IN TOTAL: " + calculeazaOre() + "\n";
			
			
            
            document.add(new Paragraph(content[0]));
            document.close();
		}
		catch (IOException e) {
            System.out.println("A apărut o eroare la scrierea fișierului.");
            e.printStackTrace();
        }
	}
	
	public int getId_istoric()
	{
		return id_istoric;
	}
	
	public Map<Activitate, Integer> getListaIstoric()
	{
		return Collections.unmodifiableMap(listaIstoric);
	}
}
