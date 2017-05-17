import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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
	public static final String abjurationFileName = "./res/images/abjuration.jpg";

	public enum SpellType {
		Abjuration
	}
	
	public static void main(String[] args) throws IOException {
		CSVParser parser = CSVParser.parse(new File(csvFileName), Charset.defaultCharset(), CSVFormat.DEFAULT.withFirstRecordAsHeader());
		
		int cardCount = 1;
		
		for(CSVRecord record : parser) {
			BufferedImage img = ImageIO.read(new File(templateFileName));
			
			Graphics2D g = img.createGraphics();
			
			writeTop(g, record.get("Spell Name"));
			writeDesc(g, record.get("Spell Description"));
			writeImg(g, SpellType.Abjuration);

			
			ImageIO.write(img, "png", new File("./res/images/sample-" + cardCount + ".png"));
			
			cardCount++;
		}	
		
		System.out.println("Done!");
	}
	
	public static void writeImg(Graphics2D g, SpellType type) throws IOException {
		
		BufferedImage img;
		
		switch(type) {
		case Abjuration:
			img = ImageIO.read(new File(abjurationFileName));
			break;
		default:
			img = ImageIO.read(new File(abjurationFileName));
			break;
		}
		
		g.drawImage(img, 21, 40, 215-21, 182-40, getEmptyObserver()); //TODO yuck it's not square
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
	
	public static ImageObserver getEmptyObserver() {
		return new ImageObserver() {

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
			
		};
	}
}