package models;
import java.util.Date;
import java.time.LocalDate;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Raport {
	private int id_raport;
	private String tip_raport;
	private Date data_generare;
	
	private Administrator admin;
	
	public Raport(int id_raport, String tip_raport, Administrator admin)
	{
		this.id_raport = id_raport;
		this.tip_raport = tip_raport;
		this.data_generare = new Date();
		
		this.admin = admin;
	}
	
	public void exportPDF()
	{
		try {
			String content = "ID raport: " + id_raport + "\n";
			content = content + "Tip raport: " + tip_raport + "\n";
			content = content + "Data generare: " + data_generare + "\n\n";
			
			content = content + "Nume administrator: " + admin.getNume() + "\n\n";

			content = content + "\n--- LISTA ACTIVITĂȚI ONG ---";
	        for (Activitate a : admin.getActivitati()) {
	            content = content + "ID: " + a.getId_activitate() + " | " + a.getTitlu() + " (" + a.getTip_activitate() + ") - Locuri: " + a.getLocuri_disponibile() + "\n";
	        }
			
			PdfWriter writer = new PdfWriter("Raport.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            document.add(new Paragraph(content));
            document.close();
		}
		catch (IOException e) {
            System.out.println("A apărut o eroare la scrierea fișierului.");
            e.printStackTrace();
        }
	}
	
	public void afisareDetaliata()
	{
		String content = "ID raport: " + id_raport + "\n";
		content = content + "Tip raport: " + tip_raport + "\n";
		content = content + "Data generare: " + data_generare + "\n\n";
		
		content = content + "Nume administrator: " + admin.getNume() + "\n\n";

		content = content + "\n--- LISTA ACTIVITĂȚI ONG ---";
        for (Activitate a : admin.getActivitati()) {
            content = content + "ID: " + a.getId_activitate() + " | " + a.getTitlu() + " (" + a.getTip_activitate() + ") - Locuri: " + a.getLocuri_disponibile() + "\n";
        }
        
        System.out.println(content);
	}
	
	public int getId_raport() {return id_raport;}
	public String getTip_raport() {return tip_raport;}
	public Date getData_generare() {return data_generare;}
	public Administrator getAdmin() {return admin;}
	
}
