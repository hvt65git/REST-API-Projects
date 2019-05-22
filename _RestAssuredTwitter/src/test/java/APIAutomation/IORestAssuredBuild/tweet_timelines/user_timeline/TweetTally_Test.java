package APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple demonstration of tallying word counts in Java - 
 * HVT comment: fixed bug with hardcoded index boundary
 * hvt - Print this code out, study it, and modify twitter
 * program to display tally of trumps tweets over the last 200 tweets
 * by day, showing number of tweets each day
 */



public final class TweetTally_Test {
	private static final Pattern WORD = Pattern.compile("\\w+");

	private static String user_dir = System.getProperty("user.dir") +
			"\\src\\test\\java\\APIAutomation\\IORestAssuredBuild\\tweet_timelines\\user_timeline";

	private static String testdatafilename = System.getProperty("user.dir") +
			"\\src\\test\\java\\APIAutomation\\IORestAssuredBuild\\tweet_timelines\\user_timeline"+
			"\\tweetdates.txt";


	/**
	 * Used to sort Map.Entry elements in decreasing order of value.
	 */
	private static final class EntryComparator<K,V extends Comparable<V>> implements Comparator<Map.Entry<K,V>> {
		public int compare(Entry<K, V> o1, Entry<K, V> o2) {
			return - o1.getValue().compareTo(o2.getValue());
		}		
	}

	/**
	 * @param line The input to parse words from.
	 * @return A list of lower-case words found in line.
	 */
	private static List<String> words(String line) {
		List<String> result = new ArrayList<String>();

		Matcher matcher = WORD.matcher(line);
		while(matcher.find()) {
			String word = line.substring(matcher.start(), matcher.end());
			result.add(word.toLowerCase());
		}

		return result;
	}

	/**
	 * @param filename The name of the file to open, parse, tally and close.
	 * @return A map of word associated with the number of times they appeared.
	 * @throws IOException
	 */
	private static Map<String,Integer> tallyWords(BufferedReader reader) throws IOException {
		Map<String,Integer> result = new HashMap<String,Integer>();

		while(reader.ready()) {
			String line = reader.readLine();
			for(String word : words(line)) {
				int count = 0;
				if(result.containsKey(word)) {
					count = result.get(word);
				}
				result.put(word, count+1);
			}
		}

		return result;
	}


	public static void tallyTweetResults() {
		BufferedReader reader = null;

		try {
			//read the data from file and store into hashmap
			//BufferedReader reader = new BufferedReader(new FileReader(user_dir + "\\" + inputfilename ));
			reader = new BufferedReader(new FileReader(testdatafilename));

			Map<String,Integer> tally = tallyWords(reader);
			reader.close();

			//sort the hash maps and put them into a list
			List<Map.Entry<String, Integer>> entries = 
					new ArrayList<Map.Entry<String,Integer>>(tally.entrySet());
			Collections.sort(entries, new EntryComparator<String, Integer>());


			//			//hvt code enhanced for loop to iterate over map nodes
			//			System.out.println("printing out the tally hash map... ");
			//			for (Map.Entry<String,Integer> entry : tally.entrySet()) {
			//				System.out.println(entry.getKey() + ": " + entry.getValue());
			//			}

			System.out.println("\r\nprinting out the sorted array list of the tally hash maps... ");	
			int size = entries.size();
			int index = 0;
			while(index < size) {
				Map.Entry<String, Integer> entry = entries.get(index++);
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

			//tally the results
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public static void main(String...a) {
		TweetTally_Test.tallyTweetResults();
	}
}

//OUTPUT - may 14 run
//printing out the sorted array list of the tally hash maps... 
//55: 13 - changes are in progress
//44: 11 - your angels are around you supporting you
//22: 9
//11: 8
//33: 6
//00: 2

//OUTPUT - MAY 15 RUN
//printing out the sorted array list of the tally hash maps... 
//55: 18
//22: 16
//44: 14
//11: 12
//33: 11 <---greatest rate of change 33 from 6 to 11
//00: 3
//88: 2
//99: 1
//OUTPUT - MAY 17 RUN
//printing out the sorted array list of the tally hash maps... 
//55: 22 <- THE WINNER! - changes are in progress
//22: 18
//44: 17
//11: 14
//33: 12
//88: 5 <---greatest rate of change 
//00: 3
//99: 1

