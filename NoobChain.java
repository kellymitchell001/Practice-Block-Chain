package noobchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {
	
	
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	public static int difficulty = 655360000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		
		Block genesisBlock = new Block("This is IFT 520: Advance Information Security", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		
		Block secondBlock = new Block("Arizona State University",genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		
		Block thirdBlock = new Block("Type Your Name",secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
		
		Block fourthBlock = new Block("Type Your Instructor Name",thirdBlock.hash);
		System.out.println("Hash for block 4 : " + fourthBlock.hash);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);

	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
				for(int i=1; i < blockchain.size(); i++) {
					currentBlock = blockchain.get(i);
					previousBlock = blockchain.get(i-1);
					//compare registered hash and calculated hash:
					if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
						System.out.println("Current Hashes not equal");			
						return false;
					}
					//compare previous hash and registered previous hash
					if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
						System.out.println("Previous Hashes not equal");
						return false;
					}
					//check if hash is solved
					if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
						System.out.println("This block hasn't been mined");
						return false;
					}
				}
				return true;
	}
	

}
