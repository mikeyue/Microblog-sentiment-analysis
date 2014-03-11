
import java.io.BufferedReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
 * 
 * */
public class NewSentimentHarvester {
	
	static NewSentimentHarvester nsh;
	
	public static void main(String[] args) throws IOException, JSONException{
		final String testDataFile = "TEST/TEST.txt";
		
		
		nsh = new NewSentimentHarvester();
		nsh.run("../data/tweets_train.txt");
	}


	private void run(String testDataFile) throws IOException, JSONException {
		
		// read in training tweets
		String line;
		BufferedReader br = new BufferedReader(new FileReader(testDataFile));
		while((line = br.readLine()) != null){
			
			JSONObject tweet = new JSONObject(line);
			String text = tweet.getString("text");
			
			// run POS-tagger on tweet
			/*
			 * Run rule-set on tweet's text, using seed set of terms (
			 * 	- Using 'and/or' and 'but'
			 *  - Using words that occur nearby in the same document
			 *  - Using WordNet synonyms and antonyms
			 *  
			 *  add new terms to +ve and -ve lexicon 
			 * */
		}
	}
}
