
import java.io.BufferedReader;

import org.json.JSONException;
import org.json.JSONObject;

import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Tagger.TaggedToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
/*
 * 
 * */
public class NewSentimentHarvester {
	
	static NewSentimentHarvester nsh;

	private void run(String testDataFile) throws IOException, JSONException {
		
		//////////////////////////////////////////////	
		// 1. Read in adjectives from general inquirer
		String line;
		BufferedReader br;
		HashMap<String, Integer> Lexicon = new HashMap<String, Integer>();
		HashMap<String, Integer> newLexicon = new HashMap<String, Integer>();
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("data\\GI_positive.txt");
		arr.add("data\\GI_negative.txt");
		arr.add("data\\GI_neutral.txt");
		
		for (int i=0; i<arr.size(); i++){
			String fn = arr.get(i);
			br = new BufferedReader(new FileReader(fn));
			while((line = br.readLine()) != null){
				Lexicon.put(line, i);
			}
		}
		
		
		/////////////////////////////
		// 2. read in training tweets
		int or=0, and=0, count=0;
		int adjcount, conjcount;
		
		String modelFilename = "/cmu/arktweetnlp/model.20120919";
		List<TaggedToken> taggedTokens, selectedTokens;
		Tagger tagger = new Tagger();
		tagger.loadModel(modelFilename);
		

		br = new BufferedReader(new FileReader(testDataFile));
		while((line = br.readLine()) != null){
			
			JSONObject tweet = new JSONObject(line);
			String text = tweet.getString("text");
			
			if (text.contains(" or ") || text.contains(" and ")){
				taggedTokens = tagger.tokenizeAndTag(text); // run POS-tagger on tweet
				selectedTokens = new Vector<TaggedToken>();
				
				/*
				 * Run rule-set on tweet's text, using seed set of terms (
				 * 	- Using 'and/or' and 'but'
				 *  - Using words that occur nearby in the same document
				 *  
				 *  add new terms to +ve and -ve lexicon 
				 * */
				
				adjcount = 0; conjcount=0;
				for (TaggedToken token : taggedTokens){
					//System.out.printf("%s\t%s\n", token.tag, token.token);		
					if (token.tag.equalsIgnoreCase("&")){
						selectedTokens.add(token);
						conjcount++;
					}
					if (token.tag.equalsIgnoreCase("A")){
						selectedTokens.add(token);
						adjcount++;
					}
					System.out.printf("%s\t%s\n", token.tag, token.token);
				}
				
				/*
				 * Apply conjunction rules to mine new lexicon
				 * */
				TaggedToken token;
				if (adjcount > 1 && conjcount > 0){
					// adjectives to left and right of conjunction
					for (int i=0; i<selectedTokens.size(); i++){
						token = selectedTokens.get(i);
						if (token.tag.equalsIgnoreCase("&")){
							getNextAdj
							getPrevAdj
						}
					}
				}
				
				
				
				System.out.println();
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException, JSONException{
		final String testDataFile = "TEST/TEST.txt";
		//System.out.println(new File(".").getAbsolutePath());
		
		nsh = new NewSentimentHarvester();
		nsh.run("data\\tweets_train.txt");
	}
}
