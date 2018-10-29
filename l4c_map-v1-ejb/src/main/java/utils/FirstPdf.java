package utils;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Element;

public class FirstPdf {
	private static int id;
	private static String name;
	private static String lastname;
	private static String specialty;
	private static String businessSector;
	private static float rateSelling;
	private static float cost;
	private static String typeContrat;
	private static int seniority;
	private static int note;

    

	
	public void SetInfos(int id, String name, String lastname,  String specialty, String businessSector,
			float rateSelling, float cost, String typeContrat, int seniority, int note) {
		FirstPdf.id = id;
		FirstPdf.name = name;
		FirstPdf.lastname = lastname;
		FirstPdf.specialty = specialty;
		FirstPdf.businessSector = businessSector;
		FirstPdf.rateSelling = rateSelling;
		FirstPdf.cost = cost;
		FirstPdf.typeContrat = typeContrat;
		FirstPdf.seniority = seniority;
		FirstPdf.note = note;
	}



	public FirstPdf() {

	}



	public static final String FONT = "Font/trebuc.ttf";
	public static final String FONTBOLD = "Font/trebucbd.ttf";
	
	public void createPDF() throws IOException {

		/* Create CV */
		Document CV = new Document();
		try {

			/* Creating a PDF Document */

			PdfWriter.getInstance(CV, new FileOutputStream(name+"-"+lastname+".pdf"));

			/* Open the Document */

			CV.open();

			/* Font Package */

			BaseFont basefont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont basefontBold = BaseFont.createFont(FONTBOLD, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font fontTitle = new Font(basefontBold, 40);
			Font fontHeader = new Font(basefontBold, 30);
			Font fontWord = new Font(basefont, 20);

			/* LINE SPERATOR */

			LineSeparator line = new LineSeparator(fontWord);

			/* ADDING TITLE */

			Paragraph HeadCV = new Paragraph("CURRICULAM VITAE", fontTitle);
			HeadCV.setAlignment(Paragraph.ALIGN_CENTER);
			CV.add(HeadCV);
			CV.add(Chunk.NEWLINE);

			/* ADDING BASIC INFORMATION */

			CV.add(line);
			Paragraph idRessource = new Paragraph("ID No: " + id, fontWord);
			idRessource.setAlignment(Paragraph.ALIGN_LEFT);
			CV.add(idRessource);

			
			Paragraph firstName = new Paragraph("Name :" +name, fontWord);
			firstName.setAlignment(Paragraph.ALIGN_LEFT);
			Paragraph LastName = new Paragraph("LastName :" +lastname, fontWord);
			LastName.setAlignment(Paragraph.ALIGN_LEFT);
			Paragraph Specialty = new Paragraph("Specialty: " + specialty, fontWord);
			Specialty.setAlignment(Paragraph.ALIGN_LEFT);
			Paragraph BusinessSector = new Paragraph("businessSector: " + businessSector, fontWord);
			BusinessSector.setAlignment(Paragraph.ALIGN_LEFT);
			Paragraph RateSelling = new Paragraph("RateSelling: " + rateSelling, fontWord);
			
			Paragraph Cost = new Paragraph("Cost: " + cost, fontWord);
			Cost.setAlignment(Paragraph.ALIGN_LEFT);
			
			Paragraph TypeContrat = new Paragraph("TypeContrat: " + typeContrat, fontWord);
			TypeContrat.setAlignment(Paragraph.ALIGN_LEFT);
			
			Paragraph Seniority = new Paragraph("Seniority: " + seniority, fontWord);
			Seniority.setAlignment(Paragraph.ALIGN_LEFT);
			
			Paragraph Note = new Paragraph("Note: " + note, fontWord);
			Note.setAlignment(Paragraph.ALIGN_LEFT);
			CV.add(firstName);
			CV.add(LastName);
			CV.add(Specialty);
			CV.add(BusinessSector);
			CV.add(RateSelling);
			CV.add(Cost);
			CV.add(TypeContrat);
			CV.add(Seniority);
			
			CV.close();
		} catch (Exception x) {
			System.out.println(x);
		}
	}

	
}
