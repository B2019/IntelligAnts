
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to Build a brain and separate it into tokens
 * @V 0.0.1
 * @Author 35769
 */
public class BrainBuilder {
	

    /**
     * The constructor method for the BrainBuild class
     * Takes some tokens and makes them into instructions
     * @Param ArrayList<TokenParent> inList - Takes an array list of tokens (read from file)
     * @Return InstructionParent[] brain - Produces an instruction array to be used in the brain
     */
	public static InstructionParent[] buildBrain(ArrayList<TokenParent> inList) throws BrainIncorrectException{
		
		//count instructions
		int instructionCount=0;
		for(TokenParent t : inList){
			if(t instanceof T_instruction){
				instructionCount++;
			}
		}
		
		//build empty brain array
		InstructionParent[] brain = new InstructionParent[instructionCount];
		// controls which instruction
		int instructionLocation = 0;
		// cycles through all the tokens
		Iterator it;
                it = inList.iterator();
		while(it.hasNext()){
			TokenParent instructGet = null;
			try{
				instructGet = (T_instruction)it.next();
			}
			catch(Exception e){
				throw new BrainIncorrectException();
			}
			
			int instructID = instructGet.getInstructionID();
			int param1 = 0;
			int param2 = 0;
			int param3 = 0;
			int param4 = 0;
			int param5 = 0;
			
			switch(instructID){
			
			case 1:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_direction){
					param1 = (((T_direction) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param3 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				if(!it.hasNext()){fail();}                               // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_condition){
					param4 = ((((T_condition) instructGet).getID()));
					if(((T_condition) instructGet).getID() == 7){
						
						if(!it.hasNext()){ fail(); }                     // fail()
						instructGet = (TokenParent)it.next();
						if(instructGet instanceof T_int){
							param5 = (((T_int) instructGet).getID());
						}
						else{ fail(); }                                 // fail()
					}
					else{
						param5 = 999;
					}
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building S Instruction");
				brain[instructionLocation] = new Sense(param1,param2,param3,param4,param5);
				instructionLocation++;
			}
			break;
			
			case 2:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param1 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				
				if(!it.hasNext()){ fail(); }                             // fail()
				
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building M Instruction");
				brain[instructionLocation] = new Mark(param1,param2);
				instructionLocation++;
				
			}
			break;
			
			case 3:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param1 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				
				if(!it.hasNext()){ fail(); }                             // fail()
				
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building UnM Instruction");
				brain[instructionLocation] = new UnMark(param1,param2);
				instructionLocation++;
			}
			break;
			
			case 4:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param1 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				
				if(!it.hasNext()){ fail(); }                             // fail()
				
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building P Instruction");
				brain[instructionLocation] = new Pickup(param1,param2);
				instructionLocation++;
			}
			break;
			
			case 5:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param1 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building D Instruction");
				brain[instructionLocation] = new Drop(param1);
				instructionLocation++;
			}
			break;
			
			case 6:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_turn){
					param1 = (((T_turn) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building T Instruction");
				brain[instructionLocation] = new Turn(param1,param2);
				instructionLocation++;
				
			}
			break;
			
			case 7:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param1 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				
				if(!it.hasNext()){ fail(); }                             // fail()
				
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building M Instruction");
				brain[instructionLocation] = new Move(param1,param2);
				instructionLocation++;
			}
			break;
			
			case 8:{
				if(!it.hasNext()){ fail(); }                             // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param1 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				
				if(!it.hasNext()){ fail(); }                             // fail()
				
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param2 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				instructGet = (TokenParent)it.next();
				if(instructGet instanceof T_int){
					param3 = (((T_int) instructGet).getID());
				}
				else{ fail(); }                                         // fail()
				//System.out.println("Building F Instruction");
				brain[instructionLocation] = new Flip(param1,param2,param3);
				instructionLocation++;
			}
			break;
			}	
		}
		return brain;
	}
    
    /** The FAIL method
     */
	public static void fail() throws BrainIncorrectException {
		throw new BrainIncorrectException();
	}
}
