package testfilecopyusingjava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateFile {

	public static void main(String args[]){
		
		CreateFile.createNetworkFile();
	}
	
	
	
	
	
	public static String createNetworkFile() {
		File myFile = new File("\\\\10.60.206.52\\Deepthi\\test.txt");
		
		//File myFile = new File("Y:\\test1.txt");
		PrintWriter fileWriter = null;
		try {

			fileWriter = new PrintWriter(new FileOutputStream(myFile));
			StringBuffer line;
			line = new StringBuffer("content of my file");
			fileWriter.println(line);

			fileWriter.flush();

			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (fileWriter != null)

				try {

					fileWriter.close();

				}

				catch (Exception e)
				{
					e.printStackTrace();
				}
		}
		return "Success";
	}
}
