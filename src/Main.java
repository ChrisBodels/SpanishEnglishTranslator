import java.io.*;
import java.util.*;


public class Main {
	
	private String text;
	private Map<String, String> map;
	private Scanner input;
	
	public Main()
	{
		input = new Scanner(System.in);
	}
	
	public static void main(String[] args)
	{
		Main app = new Main();
		app.readInFileToHashMap();
		app.menu();
	}
	
	public void readInFileToHashMap()
	{
		map = new HashMap<String, String>();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("SpanishWords.txt")))
		{
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			while(line != null)
			{
				stringBuilder.append(line);
				String[] spanishAndEnglish = line.split(System.lineSeparator());
				for(String pairings : spanishAndEnglish)
				{
					String[] pairing = pairings.split("	");
					String spanish = pairing[0].substring(0, pairing[0].length()-1);
					String english = pairing[1];
					map.put(spanish, english);
					
				}
				stringBuilder.append(System.lineSeparator());
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
		
		System.out.println(map.toString());
		System.out.println(map.get("gracias"));
	}
	
	public void readInFile()
	{
		
	}
	
	public String getEnglishTranslation(String spanish)
	{
		if(map.containsKey(spanish))
		{
			return map.get(spanish);
		}
		else
		{
			return "Could not find this word in current database. Please make sure it is spelled correctly or add it to the database manually.";
		}
	}
	
	public int displayMenu()
	{
		System.out.println("What would you like to do?");
		System.out.println("--------------------------------------------");
		System.out.println("1)	Enter a spanish word and get the english translation");
		System.out.println("2)	Manually enter a new spanish word and it's english translation to the database");
		System.out.println("3)	View database");
		System.out.println("0)  Exit");
		boolean notGoodInput = false;
		int option = 0;
		do
		{
			try {
			option = input.nextInt();
			notGoodInput = true;
			}
			catch(Exception e) {
				String throwOut = input.nextLine();
				System.out.println("Number expected, you entered text: " + throwOut);
			}
		}while(!notGoodInput);
		return option;
	}
	
	public void menu()
	{
		int option = displayMenu();
		
		while(option != 0)
		{
			switch(option)	
			{
				case 1:
					translator();
					break;
				case 2:
					makeEntry();
					break;
				case 3:
					System.out.println(map.toString());
					break;
				default:
					System.out.println("Invalid option entered");
					break;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Press any key to continue...");
			input.next();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			option = displayMenu();
		}
	}
	
	public void translator()
	{
		System.out.println("Please enter the spanish word you want to translate (ensure spelling is correct and all accents are present): ");
		String word = input.nextLine();
		word = input.nextLine();
		System.out.println(getEnglishTranslation(word));
	}
	
	public void makeEntry()
	{
		System.out.println("Please enter the spanish word you want to add to the database (ensure spelling is correct and all accents are present): ");
		String spanishWord = input.nextLine();
		spanishWord = input.nextLine();
		System.out.println("Please enter the english translation for this word: ");
		String englishWord = input.nextLine();
		map.put(spanishWord, englishWord);
		System.out.println("Entry made to database.");
	}
	
}
