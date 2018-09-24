package testfilecopyusingjava;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;


public class TestBatchScriptFutureTask {
	
	
	private  static String inputFolder1 = "C:\\Projects\\input\\test.txt";
	private  static String batchFilePath1 = "C:\\projects\\copyfile.bat";
	private  static String archiveFilePath1 = "C:\\Projects\\input\\Archive\\test.txt";
	
private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
	
	public static void main(String args[]) throws InterruptedException, ExecutionException{
		
		
		FactorialCalculator task = new FactorialCalculator(inputFolder1,batchFilePath1, archiveFilePath1); 
		
		System.out.println("Submitting Task ..."); 
		
		Future future = threadpool.submit(task);
		
		System.out.println("Task is submitted");
		File file = new File(inputFolder1);
		File fileArchive = new File(archiveFilePath1);									

		
		while (!future.isDone()) { 
			System.out.println("Task is not completed yet....");
			Thread.sleep(100);
			 
			}
		System.out.println("Completed moving file");
		long factorial = (long)future.get(); 
											
		try {
			if(factorial != 0){
				FileUtils.copyFile(file, fileArchive);
				
				//Thread.sleep(1000);
				file.delete();
				System.out.println("Deleted the file");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Factorial of 1000000 is : " + factorial); 
		threadpool.shutdown();

	}
	
	private static class FactorialCalculator implements Callable { 
		private final String inputFolder ;
		private final  String batchFilePath ;
		private  final String archiveFilePath ;
		
	
		public FactorialCalculator(String inputFolder, String batchFilePath, String archiveFilePath) 
		{ 
			this.inputFolder = inputFolder; 
			this.batchFilePath = batchFilePath;
			this.archiveFilePath = archiveFilePath; 
		} 
		
		
		@Override 
		public Long call() 
		{ 
			long output = 0;
			
			try { 
				output =  factorial(inputFolder,batchFilePath,archiveFilePath); 
			} 
			catch (InterruptedException ex) { 
				ex.printStackTrace();
				//Logger.getLogger(FutureDemo.class.getName()).log(Level.SEVERE, null, ex);
				} 
			return output;
		} 
		
		private long factorial(String inputFolder, String batchFilePath, String archiveFilePath) throws InterruptedException 
		{
			try {
				Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", batchFilePath, inputFolder});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 13456;
			}
		
	}
}
