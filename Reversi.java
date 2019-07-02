public class Reversi{
	public static void main(String[] args){
		gameSetup(); // setup the game
	}
	public static void gameSetup(){
		ReversiPrint rp = new ReversiPrint(); // use class ReversiPrint
		ReversiCheck rc = new ReversiCheck(); // use class ReversiCheck
		Command cmd = new Command(); // use class Command and set the default values 
		int[][] reversi = new int[cmd.getGameSize()[0]][cmd.getGameSize()[1]]; //game table.
		cmd.gameStart(reversi,cmd.getGameSize(),cmd.getPlayer()[0],cmd.getPlayer(),rp,rc,cmd); // '1' round starts the game first
	}
}