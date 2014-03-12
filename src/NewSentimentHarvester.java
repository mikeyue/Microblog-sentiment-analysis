
import java.io.BufferedReader;

import org.json.JSONException;
import org.json.JSONObject;

import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Tagger.TaggedToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
/*
 * 
 * */
public class NewSentimentHarvester {
	
	static NewSentimentHarvester nsh;

	private void run(String testDataFile) throws IOException, JSONException {
		
		// read in training tweets
		String line;
		int or=0, and=0, count=0;
		
		String modelFilename = "/cmu/arktweetnlp/model.20120919";
		List<TaggedToken> taggedTokens;
		Tagger tagger = new Tagger();
		tagger.loadModel(modelFilename);
		

		BufferedReader br = new BufferedReader(new FileReader(testDataFile));
		while((line = br.readLine()) != null){
			
			JSONObject tweet = new JSONObject(line);
			String text = tweet.getString("text");
			
			if (text.contains(" or ") || text.contains(" and ")){
				taggedTokens = tagger.tokenizeAndTag(text);
				
				 
			}
			
			// run POS-tagger on tweet
			/*z
			 * Run rule-set on tweet's text, using seed set of terms (
			 * 	- Using 'and/or' and 'but'
			 *  - Using words that occur nearby in the same document
			 *  - Using WordNet synonyms and antonyms
			 *  
			 *  add new terms to +ve and -ve lexicon 
			 * */
		}
		
	}
	
	public static void main(String[] args) throws IOException, JSONException{
		final String testDataFile = "TEST/TEST.txt";
		//System.out.println(new File(".").getAbsolutePath());
		
		nsh = new NewSentimentHarvester();
		nsh.run("data\\tweets_train.txt");
	}
}
