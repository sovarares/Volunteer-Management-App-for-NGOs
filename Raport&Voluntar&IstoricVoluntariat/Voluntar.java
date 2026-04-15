import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Voluntar extends Utilizator{
	private String telefon;
	private List<Activitate> activitati;
	private IstoricVoluntariat istoric;
	
	public Voluntar(int id_utilizator, String nume, String email, String parola, String telefon, int id_istoric)
	{
		super(id_utilizator, nume, email, parola);
		this.telefon = telefon;
		
		activitati = new ArrayList<>();
		istoric = new IstoricVoluntariat(id_istoric);
	}
	
	public boolean aplicaLaActivitate(Activitate activitate)
	{

		if (this.activitati.contains(activitate)) {
			System.out.println("Eroare: Ești deja înscris la activitatea '" + activitate.getTitlu() + "'!");
			return false; 
		}
		
		if (activitate.verificaDisponibilitate())
		{
			activitate.adaugareVoluntar(this);
			activitati.add(activitate);
			return true; 
		}
		
		return false; 
	}
	
	public void renuntalaActivitate(Activitate activitate)
	{
		activitate.stergereVoluntar(this);
		if (activitati.contains(activitate))
		{
			activitati.remove(activitate);
		}
	}
	
	public void vizualizareIstoric()
	{
		String[] content = {""};
        content[0] = "ID Istoric Voluntariat: " + istoric.getId_istoric() + "\n\n\n";
        
        istoric.getListaIstoric().forEach((a,i) -> {
        	content[0] = content[0] + "ID Activitate: " + a.getId_activitate() + "\n";
        	content[0] = content[0] + "Titlu: " + a.getTitlu() + "\n";
        	content[0] = content[0] + "Ore petrecute: " + i + "\n\n";
        });
		
		content[0] = content[0] + "\n" + "NR. DE ORE PETRECUTE IN TOTAL: " + istoric.calculeazaOre() + "\n";
		
		System.out.println(content[0]);
	}
	
	public void generareIstoricPdf()
	{
		try {
			String[] content = {""};
	        content[0] = "ID Istoric Voluntariat: " + istoric.getId_istoric() + "\n\n\n";
	        
	        istoric.getListaIstoric().forEach((a,i) -> {
	        	content[0] = content[0] + "ID Activitate: " + a.getId_activitate() + "\n";
	        	content[0] = content[0] + "Titlu: " + a.getTitlu() + "\n";
	        	content[0] = content[0] + "Ore petrecute: " + i + "\n\n";
	        });
			
			content[0] = content[0] + "\n" + "NR. DE ORE PETRECUTE IN TOTAL: " + istoric.calculeazaOre() + "\n";
			
			PdfWriter writer = new PdfWriter("istoric_voluntar_" + id_utilizator + "_" + nume + ".pdf");
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf);
	        
	        document.add(new Paragraph(content[0]));
	        document.close();
	        
		}
		catch (IOException e) {
	        System.out.println("A apărut o eroare la scrierea fișierului.");
	        e.printStackTrace();
	    }
	}
	
	public String getTelefon()
	{
		return telefon;
	}
	
	public void setTelefon(String telefon)
	{
		this.telefon = telefon;
	}
	
	
}
