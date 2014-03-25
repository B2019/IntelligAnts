import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * A file to read a brain and separate it into tokens (does not work with netbeans due to exception error)
 * @V 0.0.1
 * @Author 35769
 */
public class BrainReader {

    /**A method to read a brain from file and create a list of tokens
     * @Param File f - A Brain File
     * @Return ArrayList<TokenParent> tokenList - an array of tokens */
	public static ArrayList<TokenParent> readBrain(File f) throws IOException, BrainIncorrectException{

		//build a reader and pass the file into it
		FileReader myReader = new FileReader(f);
		Yylex myLexer = new Yylex(myReader);
		ArrayList<TokenParent> tokenList = new ArrayList<TokenParent>();
		//while there is still some file to look at
		while (!myLexer.getEndOf()) {						
			TokenParent nextToken = myLexer.yylex();
			if (nextToken != null) {							
				tokenList.add(nextToken);
			}
		}
        // check to see if there is a syntax problem
		if(BrainReader.didFail(tokenList)){
			throw new BrainIncorrectException();
		}
		else{
			//Print contents of tokenList
			Iterator<TokenParent> token = tokenList.iterator();												
			while(token.hasNext()){													
				token.next();
			}
		}
		myReader.close();
		return tokenList;
	}

    /**A method to check that a token has a correct next token attached to it
     * @Param arrayList tokenList - An array of tokens
     * @Return boolean - true if next token false otherwise  */
	public static boolean didFail(ArrayList tokenList){
		Iterator t = tokenList.iterator();
		while(t.hasNext()){
			if(t.next() instanceof ErrorFail){
				return true;
			}
		}
		return false;		
	}
}
