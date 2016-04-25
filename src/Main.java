import java.io.*;
import java.util.*;


public class Main {
	
	private String text;
	private Map<String, String> map;
	
	public Main()
	{
		
	}
	
	public static void main(String[] args)
	{
		Main app = new Main();
		app.readInFile();
	}
	
	public void readInFile()
	{
		map = new HashMap<String, String>();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("SpanishWords.txt")))
		{
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			while(line != null)
			{
				stringBuilder.append(line);
				String[] spanishAndEnglish = line.split("	");
				String spanish = spanishAndEnglish[0];
				String english = spanishAndEnglish[1];
				map.put(spanish, english);
				line = bufferedReader.readLine();
			}
			text = stringBuilder.toString();
			bufferedReader.close();
			System.out.println("The text from the input file: ");
			System.out.println(text);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println(map.get("thanks"));
	}
	
	
	
}
