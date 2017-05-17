import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {
	
	public static final String csvFileName = "./res/csv/sample.csv";

	public static void main(String[] args) throws IOException {
		CSVParser parser = CSVParser.parse(new File(csvFileName), Charset.defaultCharset(), CSVFormat.DEFAULT.withFirstRecordAsHeader());
		
		
		for(CSVRecord record : parser) {
			System.out.println(record.get("Spell Name") + "\t" + record.get("Spell Description"));
		}
	}

}
