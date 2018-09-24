package testfilecopyusingjava;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class TestBatchScript {
	
	Logger logger = Logger.getLogger("TestBatchScript.class");
	
	String inputFolder = "C:\\Projects\\input";
	String batchFilePath = "C:\\projects\\copyfile.bat";
	String archiveFilePath = "C:\\Projects\\input\\Archive";

	public static void main(String[] args) {
		
		TestBatchScript testBatchScript = new TestBatchScript();
		testBatchScript.copyFile();
	}
	
	public void copyFile(){
		
		
		
		try {
			
			File file = new File(inputFolder);
				
			
			File[] inputFilesList = file.listFiles();
			
			for(File inputFile : inputFilesList){	
				
				if((!FileUtils.isFileOlder(inputFile, 500)) && inputFile.isFile()){
					
					Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", batchFilePath, inputFile.getPath()});
					logger.info("Copied the file using batch script" + inputFile.getPath());
					p.waitFor();				
					
						File fileArchive = new File(archiveFilePath + "\\" + inputFile.getName());									
						FileUtils.copyFile(inputFile, fileArchive);						
						inputFile.delete();
					
					
									
				}				
			}	
			
			
			
		} catch (IOException e) {
			logger.info("inside IOException block");
			e.printStackTrace();
		} catch (Exception e){
			logger.info("inside Exception block");
			e.printStackTrace();
		}

	
	}

}
