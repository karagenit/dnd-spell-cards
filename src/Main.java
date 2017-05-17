import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {
	
	public static final String csvFileName = "./res/csv/sample.csv";
	public static final String templateFileName = "./res/images/template.jpg";

	public static void main(String[] args) throws IOException {
		CSVParser parser = CSVParser.parse(new File(csvFileName), Charset.defaultCharset(), CSVFormat.DEFAULT.withFirstRecordAsHeader());
		
		int cardCount = 1;
		
		for(CSVRecord record : parser) {
			BufferedImage img = ImageIO.read(new File(templateFileName));
			
			Graphics2D g = img.createGraphics();
			
			writeTop(g, record.get("Spell Name"));
			writeDesc(g, record.get("Spell Description"));
			
			ImageIO.write(img, "png", new File("./res/images/sample-" + cardCount + ".png"));
			
			cardCount++;
		}		
	}
	
	public static void writeTop(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.drawString(s, 22, 31);
	}
	
	public static void writeMiddle(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.drawString(s, 22, 199);
	}
	
	public static void writeDesc(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.drawString(s, 24, 220);
	}
	
	public static void writeBottom(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.drawString(s, 181, 307);
	}
}