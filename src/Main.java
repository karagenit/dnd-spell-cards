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
//	public static final String templateFileName = "./res/images/template.jpg";
	public static final int height = 100;
	public static final int width = 100;

	public static void main(String[] args) throws IOException {
		CSVParser parser = CSVParser.parse(new File(csvFileName), Charset.defaultCharset(), CSVFormat.DEFAULT.withFirstRecordAsHeader());
		
		int cardCount = 1;
		
		for(CSVRecord record : parser) {
			System.out.println(record.get("Spell Name") + "\t" + record.get("Spell Description"));
		}
		
		BufferedImage img = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
		
//		BufferedImage img = ImageIO.read(new File)
		
		Graphics2D g = img.createGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, height, width);
		g.setColor(Color.BLACK);
		g.drawString("Hello", 5, 10);
		
		ImageIO.write(img, "png", new File("./res/images/sample-" + cardCount+ ".png"));
	}

}
