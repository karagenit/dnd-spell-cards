import java.awt.Color;
import java.awt.Font;
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
	
	/*
	 * Normal MtG card is 2.5 x 3.5 inches
	 * Which, at a "normal printing of 300 ppi, gives us a required resolution of
	 * 750x1050 px
	 * 
	 * For printing:
	 * portrait could print 3x3 = 9
	 * landscape could print 4x2 = 8
	 * 
	 * System.out.println(Arrays.toString(Font.getFamilies().toArray()));
	 * 
	 */
	
	public static final String csvFileName = "./res/csv/sample.csv";
	public static final String templateFileName = "./res/images/template-2.jpg";
	public static final String abjurationFileName = "./res/images/abjuration.jpg";
	public static final Font font = new Font("Times New Roman", Font.BOLD, 32);

	public enum SpellType {
		Abjuration
	}
	
	public static void main(String[] args) throws IOException {
		CSVParser parser = CSVParser.parse(new File(csvFileName), Charset.defaultCharset(), CSVFormat.DEFAULT.withFirstRecordAsHeader());
		
		int cardCount = 1;
		
		for(CSVRecord record : parser) {
			BufferedImage img = ImageIO.read(new File(templateFileName));
			
			//img.getr //TODO background color change
			
			Graphics2D g = img.createGraphics();
			
			writeTop(g, record.get("Spell Name"));
			writeDesc(g, record.get("Spell Description"));
			writeImg(g, SpellType.Abjuration);			
			
			ImageIO.write(img, "png", new File("./res/images/test-" + cardCount + ".png"));
			
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
		
		g.drawImage(img, 64, 125, 685-64, 580-125, getEmptyObserver()); //TODO make non-stretching
	}

	public static void writeTop(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, 70, 95); 
	}
	
	public static void writeMiddle(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, 70, 630);
	}
	
	public static void writeDesc(Graphics2D g, String s) { //TODO multiple lines
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, 75, 690);
	}
	
	public static void writeBottom(Graphics2D g, String s) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, 575, 975);
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