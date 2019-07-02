import java.io.*;
import java.util.Scanner;
public class Command{
	public Scanner sc = new Scanner(System.in); //allow input
	private boolean botMoveEnable; // when finging all bot position that bot can input, bot can't place its number to the position.
	private boolean botTextDisplay; // BOT will show what it input if the bot mode is on.
	private boolean force; // skip ENTER confirm for BOT vs BOT.
	private boolean liveScore; // show live score on the top.
	private boolean skip; // skip printing process of the BOT vs BOT, print the last result only.
	private boolean debug; // show debug information.
	private boolean root; // admin that can use some command and will not be limited to set the size of reversi table.
	private boolean iconChange; // change the player's icon.
	private boolean classicMode; // Use ¡³¡´ to play the game. 
	private boolean classicModeDisplay; // Use ¡³¡´ to play the game. 
	private int botMode; //set bot variable.
	private int[] gameSize = new int[2]; //game size
	private int[] sizeLimit = new int[2]; // limit the game size 
	private int[] player = new int[3]; // player 1, player 2 and empty position.
	private String[] newIcon = new String[3]; // declare variable to store new size.
	
	//constructor
	public Command(String[] inNewIcon, boolean inClassicMode, boolean inClassicModeDisplay ,boolean inIconChange, boolean inBotMoveEnable, boolean inBotTextDisplay,boolean inForce, boolean inLiveScore, boolean inSkip, boolean inDebug, boolean inRoot, int inBotMode, int[] inGameSize, int[] inSizeLimit, int[] inPlayer){
		newIcon = inNewIcon; //set default value.
		classicMode = inClassicMode; //set default value.
		classicModeDisplay = inClassicModeDisplay; //set default value.
		iconChange = inIconChange; //set default value.
		botMoveEnable = inBotMoveEnable; //set default value.
		botTextDisplay = inBotTextDisplay; //set default value.
		force = inForce; //set default value.
		liveScore = inLiveScore; //set default value.
		skip = inSkip; //set default value.
		debug = inDebug; //set default value.
		root = inRoot; //set default value.
		botMode = inBotMode; //set default value.
		gameSize = inGameSize; //set default value.
		sizeLimit = inSizeLimit; //set default value.
		player = inPlayer; //set default value.
	}
	
	public Command(){
		newIcon[0] = "1"; //set default value.
		newIcon[1] = "2"; //set default value.
		newIcon[2] = "0"; //set default value.
		classicMode = false; //set default value.
		classicModeDisplay = false; //set default value.
		iconChange = false; //set default value.
		botMoveEnable = false; //set default value.
		botTextDisplay = false; //set default value.
		force = false; //set default value.
		liveScore = false; //set default value.
		skip = false; //set default value.
		debug = false; //set default value.
		root = false; //set default value.
		botMode = 0; //set default value.
		gameSize[0] = 6; //set default value.
		gameSize[1] = 6; //set default value.
		sizeLimit[0] = 4; //set default value.
		sizeLimit[1] = 30; //set default value.
		player[0] = 1; //set default value.
		player[1] = 2; //set default value.
		player[2] = 0; //set default value.
	}
	
	public void setClassicMode(boolean b){
		classicMode = b;  //set classicMode
	}
	
	public void setClassicModeDisplay(boolean b){
		classicModeDisplay = b; //set classicModeDisplay
	}
	
	public void setNewIcon(String[] s){
		newIcon = s;
	}
	
	public void setNewIcon(int[] integer){
		for(int i = 0; i<newIcon.length; i++){
			newIcon[i] = ""+integer[i];
		}
	}
	
	public void setIconChange(boolean b){
		iconChange = b;
	}
	
	public void setPlayer(int p1, int p2, int p3){
		player[0] = p1;
		player[1] = p2;
		player[2] = p3;
	}
	
	public void setGameSize(int column, int row){
		gameSize[0] = column;
		gameSize[1] = row;
	}
	
	public void setSizeLimit(int column, int row){
		sizeLimit[0] = column;
		sizeLimit[1] = row;
	}
	
	public void setBotMoveEnable(boolean b){
		botMoveEnable = b;
	}

	public void setBotTextDisplay(boolean b){
		botTextDisplay = b;
	}
	
	public void setForce(boolean b){
		force = b;
	}
	
	public void setLiveScore(boolean b){
		liveScore = b;
	}
	
	public void setSkip(boolean b){
		skip = b;
	}

	public void setDebug(boolean b){
		debug = b;
	}
	
	public void setRoot(boolean b){
		root = b;
	}
	
	public void setBotMode(int i){
		botMode = i;
	}
	
	public boolean getClassicMode(){
		return classicMode;
	}
	
	public boolean getClassicModeDisplay(){
		return classicModeDisplay;
	}
	
	public boolean getIconChange(){
		return iconChange;
	}
	
	public boolean getBotMoveEnable(){
		return botMoveEnable;
	}

	public boolean getBotTextDisplay(){
		return botTextDisplay;
	}
	
	public boolean getForce(){
		return force;
	}
	
	public boolean getLiveScore(){
		return liveScore;
	}
	
	public boolean getSkip(){
		return skip;
	}

	public boolean getDebug(){
		return debug;
	}
	
	public boolean getRoot(){
		return root;
	}
	
	public int getBotMode(){
		return botMode;
	}
	
	public int[] getGameSize(){
		return gameSize;
	}
	
	public int[] getSizeLimit(){
		return sizeLimit;
	}
	
	public int[] getPlayer(){
		return player;
	}
	
	public String[] getNewIcon(){
		return newIcon;
	}
	
	public void gameStart(int[][] reversiTable, int[] inGameSize, int client, int[] player, ReversiPrint rp, ReversiCheck rc,Command cmd){
		//System.out.printf("\nHi, welcome to my program! \nType -c to print the command list \n");
		//System.out.printf("enjoy the Reversi game!\ninitializing...\n");
		rp.gameStart(reversiTable,inGameSize,client,player,cmd,rc); // print the game when the game starts.
		while(!rc.endGame(reversiTable,inGameSize)){ // if the game is not end, run the program.
			int[] input = cmd.prompt(reversiTable,inGameSize,client,player,rp,rc,cmd); // ask player for inputing until the inputing is true.
			int checkReplyNo = rc.basicCheck(input,reversiTable,inGameSize); // check reply number.
			if(checkReplyNo==0){ // 0 = no error message replys.
				if(rc.positionCheck(true, input, reversiTable, inGameSize, client, cmd)){
					if(!getSkip()&& !getClassicModeDisplay()){ //check skip and classic mode.
						rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table //print the reversi table.
					}
					bot3Command();
					int pass = rc.switchRound(client,cmd.getPlayer()); // switch to next player.
					String strPass = ""+pass;// for printing reversi table in string data type. 
					String strClient = ""+client;// for printing reversi table in string data type. 
					if(pass == getPlayer()[0]){
						strPass = ""+newIcon[0]; // for printing reversi table in string data type. 
						strClient = ""+newIcon[1];// for printing reversi table in string data type. 
					}else{
						strPass = ""+newIcon[1];// for printing reversi table in string data type. 
						strClient = ""+newIcon[0];// for printing reversi table in string data type. 
					}
					if(rc.placePossible(pass,reversiTable,inGameSize,cmd)){
						client = pass; // switch player to next player.
					}else{
						if(rc.placePossible(client,reversiTable,inGameSize,cmd)){
							System.out.printf("[Switch Round] Switch to '%s' round unsuccessfully ('%s' has not position to place).\n",strPass,strPass); // the fail message for switching round unsuccessfully shows..
							System.out.printf("[Switch Round] Switch to '%s' round back.\n",strClient);// the fail message for switching round unsuccessfully shows.
						}else{
							break; // Switch to all player unsuccessfully, break while-loop.
						}
					}
				}else if(getBotMode()==0){
					System.out.printf("Error - invalid move.\n"); // if placeing the position can not kill enemy(s), error message shows.
				}
			}else if(checkReplyNo==1){
				System.out.printf("Error - input numbers should be 0 to %d!\n",inGameSize[0]-1); //check column
			}else if(checkReplyNo==2){
				System.out.printf("Error - input numbers should be 0 to %d!\n",inGameSize[1]-1); //check row
			}else if(checkReplyNo==3){
				System.out.printf("Error - input cell is not empty.\n"); // check empty position
			}
		}
		if(getSkip()){
			rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
		}
		if(rp.countScore(reversiTable, inGameSize, cmd)[2]!=0){ // find why the game is over.
			System.out.printf("[Game Detector] No position can be placed, finish the game early.\n");
		}
		classicPrint(reversiTable,inGameSize,player,client,cmd,rp,rc);// print more time when the game is over in the classic mode.
		rp.result(reversiTable,inGameSize,cmd);
	}
	
	public void bot3Command(){
		if(this.getBotMode()==3 && !this.getForce()){
			Console enter = System.console(); // declare a variable, enter, in System.console.
			System.out.println(); // print a new line
			char[] showNext = enter.readPassword("Press ENTER to continue."); //hide input, press enter can print next result.
			String hideCMD ="";
			for(int i=0; i<showNext.length;i++){
				hideCMD += showNext[i]; 
			}
			if(hideCMD.equals("-q")){ // exit -bot 3 mode.
				setBotMode(0); // exit -bot 3 mode.
			}
			if(hideCMD.equals("-f")){ // if player inputs -f, the result will print automatically.
				setForce(true);// don't ask for prassing ENTER.
			}
			if(hideCMD.equals("-s")){ // skip printing until game finish.
				setForce(true);// don't ask for prassing ENTER.
				setSkip(true); // skip printing until the game is over.
			}
		}
	}
	
	public int[] prompt(int[][] reversiTable, int[] inGameSize, int client, int[] player,ReversiPrint rp, ReversiCheck rc,Command cmd){
		while(true){ //ask player for inputting until the inputting is true.
			String strClient = ""+client;
			if(client == getPlayer()[0]){
				strClient = ""+newIcon[0];
			}else{
				strClient = ""+newIcon[1];
			}
			
			if(rc.botPlayer(client,cmd.getPlayer(),cmd)){
				if(rc.placePossible(client,reversiTable,inGameSize,cmd)){
					return botInput(strClient,reversiTable,inGameSize,client,player,rp,rc,cmd);
				}
			}
			
			classicPrint(reversiTable,inGameSize,player,client,cmd,rp,rc);
			
			System.out.printf("Please enter the position of '%s':", strClient);
			String[] clientInput = new String[2];
			clientInput[0] = sc.next(); // ask player for first inputting
			
			boolean firstCommand = firstCommandAct(client, clientInput[0],reversiTable,inGameSize,player,rp,rc,cmd);
			
			if(firstCommand){ // check the first inputing.
				continue;
			}
			
			if(getClassicModeDisplay()){
				int toInt = rc.classicCheck(reversiTable, inGameSize,client,clientInput,cmd);
				if(toInt >= 0){
					return intToIntArr(reversiTable,inGameSize,client,player,rp,rc,cmd,toInt); // deencapsulation of the input.
				}else if(toInt == -1){
					int getNumber = rc.getClassicArrNumber(client,reversiTable,gameSize,cmd);// get the maximun number that can be input
					System.out.printf("Error - input number should be 0 to %s!\n", (getNumber-1>9)? ""+ (rp.getStrInteger()[getNumber-1]): (getNumber-1)); // display the number or letter that can be input, if the number > 9, it will tranfer to letter.
				}else{
					System.out.println("[Classic Mode] Error - input values should be an integer or a letter.");
				}
				continue;	
			}
			
			clientInput[1] = sc.next(); // ask player for second inputting
			if(!rc.isDigit(clientInput)){ // check whether the inputting is digit.
				System.out.println("Error - input values should be integers.");
				continue; // ask player to re input.
			}
			
			int stringToInt[] = {Integer.parseInt(clientInput[0]), Integer.parseInt(clientInput[1])}; // transfer data type, String to integer.
			return stringToInt; // return real player's input
		}
	}
	
	public int[] botInput(String strClient, int[][] reversiTable, int[] inGameSize, int client, int[] player,ReversiPrint rp, ReversiCheck rc, Command cmd){
		if(getClassicModeDisplay()){
			int[] botTemp = new int[2];
			int[] botGotCol = rc.getClassicCol(client,reversiTable,inGameSize,cmd);
			int[] botGotRow = rc.getClassicRow(client,reversiTable,inGameSize,cmd);
			int botGetArrNumber = rc.getClassicArrNumber(client,reversiTable,inGameSize,cmd);
			int botGetBestPositionArrNum = rc.botGetBestPositionArrNum(client,reversiTable,inGameSize,cmd);
			if(botGetArrNumber!=botGetBestPositionArrNum){
				botTemp = rc.botGetClassicColRow(client,reversiTable,inGameSize,cmd);
				classicPrint(reversiTable,inGameSize,player,client,cmd,rp,rc);
				System.out.printf("\n[BOT] Please enter the position of '%s': %s\n",strClient, (botGetBestPositionArrNum-2>9)? rp.getStrInteger()[botGetBestPositionArrNum-2] :""+(botGetBestPositionArrNum-2));
				return botTemp;
			}else{
				botGetArrNumber *= Math.random();
			}
			classicPrint(reversiTable,inGameSize,player,client,cmd,rp,rc);
			System.out.printf("\n[BOT] Please enter the position of '%s': %s\n",strClient, (botGetArrNumber>9)? rp.getStrInteger()[botGetArrNumber] :""+botGetArrNumber);
			botTemp[0] = botGotCol[botGetArrNumber];
			botTemp[1] = botGotRow[botGetArrNumber];
			return botTemp;
		}else{
			int[] botTemp = rc.botPlace(client,reversiTable,inGameSize,cmd);
			System.out.printf("\n[BOT] Please enter the position of '%s':%d %d\n",strClient,botTemp[0],botTemp[1]);
			return botTemp;
		}
	}
	
	public void classicSetting(int[][] reversiTable, int[] inGameSize, int[] player,int client, ReversiPrint rp, ReversiCheck rc, Command cmd){
		if(getGameSize()[0] >10 || getGameSize()[1]>10){ // check row or column whether is more then 10.
			System.out.println("[Classic Mode] Error - It does't support more the size more then (10) x (10)."); // check row or column whether is more then 10.
		}else{
			setIconChange(true);
			setClassicMode(true);
			newIcon[0] = toUtf8("¡´"); // set the icon of player 1 to ¡´.
			newIcon[1] = toUtf8("¡³"); // set the icon of player 2 to ¡³.
			newIcon[2] = toUtf8("¡@");  // set the icon of empty  to ¡@.
			rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table.
		}
	}
	
	public String toUtf8(String str) {
		try{
			return new String(str.getBytes("UTF-8"),"UTF-8");
		} catch ( UnsupportedEncodingException e){
            return "1";
        }
	}
	
	public void iconDefault(int[][] reversiTable, int[] inGameSize, int[] player,int client, ReversiPrint rp, ReversiCheck rc, Command cmd){
		setIconChange(false);
		setClassicMode(false);
		for(int i=0; i<newIcon.length;i++){
			newIcon[i] = ""+getPlayer()[i]; // reset the icon of player 1 to 1.
		}
		rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
	}
	
	public boolean classicModeOn(String input, int[][] reversiTable, int[] inGameSize, int[] player,int client, ReversiPrint rp, ReversiCheck rc, Command cmd){
		if(input.equals("-classic")){
			if(getClassicMode() && !getClassicModeDisplay()){
				iconDefault(reversiTable,inGameSize,player,client,rp,rc,cmd); // set all icon to default.
			}else if(!getClassicMode()){
				classicSetting(reversiTable,inGameSize,player,client,rp,rc,cmd); // turn on Classic Mode.
			}else{
				System.out.println("[Classic Mode] Error - You must turn off the classic display first."); // can't turn off the classic mode when classic display is on.
			}
			return true;
		}
		return false;
	}
	
	public boolean changeIcon(int[][] reversiTable, int[] inGameSize, int[] player,int client, ReversiPrint rp, ReversiCheck rc, Command cmd){
		String input[] = new String [3];
		if(getClassicMode()){
			if(newIcon[2].equals("¡O"))
				newIcon[2] = "¡@";
			else
				newIcon[2] = "¡O"; // set the icon of empty position.
			if(!classicModeDisplay){
				rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
			}
			return true;
		}
		
		for(int i=0; i<input.length; i++){
			if(i != input.length - 1)
				System.out.printf("[New Icon] Player %d: ",i+1); 
			else
				System.out.print("[New Icon] Empty Position: ");
			
			input[i] = sc.next(); // ask player to input new icon.
			if(input[i].equals("-def")){ // set all icon to default.
				iconDefault(reversiTable,inGameSize,player,client,rp,rc,cmd); // set all icon to default.
				return true;
			}
			if(classicModeOn(input[i],reversiTable,inGameSize,player,client,rp,rc,cmd)){ // check the inputting whether is -classic
				return true;
			}
			newIcon[i] = ""+input[i].charAt(0); // ask player to input new icon but read the first charactor only.
		}
		
		setIconChange(true);
		rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
		return true;
	}
	
	public boolean changeSize(int[] inGameSize, int[] player,int client, ReversiPrint rp, ReversiCheck rc, Command cmd){ // change reversi table size.
		String[] newSizeInput = new String[2]; // declare variable to store new size.
		System.out.print("[New Size] Column size? ");
		newSizeInput[0] = sc.next(); //ask player to input new game size.
		System.out.print("[New Size] Row size? ");
		newSizeInput[1] = sc.next(); //ask player to input new game size.
		if(rc.isDigit(newSizeInput)){ // check whethe inputting is digit.
			int[] newGameSize = {Integer.parseInt(newSizeInput[0]),Integer.parseInt(newSizeInput[1])}; //player's inputting is used to declare new size.
			for(int i=0; i<newGameSize.length; i++){
				if(!getRoot() && (newGameSize[i]<getSizeLimit()[0]|| newGameSize[i]>getSizeLimit()[1])){ //check player's inputting fits sizeLimit.
					System.out.printf("[New Size] The size must be between %d and %d.\n",getSizeLimit()[0],getSizeLimit()[1]);
					return true; //change fail.
				}
				if(getClassicMode() && newGameSize[i]>10){
					System.out.printf("[New Size] The size in the Classic Mode must be between %d and %d.\n",getSizeLimit()[0],10);
					return true; //change fail.
				}
			}
			System.out.print("[New Size] Change size of reversi table will restart the game, are you sure?(y/n) ");
			String confirm = sc.next();
			if(confirm.charAt(0)=='y'){
				int[][] newreversiTable = new int[newGameSize[0]][newGameSize[1]]; // create new reversi table.
				setGameSize(newGameSize[0],newGameSize[1]);
				gameStart(newreversiTable,inGameSize,getPlayer()[0],player,rp,rc,cmd); //use new reversi table to restart the game. player 1 goes first.
				System.exit(0); // stop the program when the new game is over.
			}
		}else{
			System.out.println("[New Size] Error - You shound input digits."); //if it's not digit, print error.
			return true;
		}
		return true;
	}
	
	public int[] intToIntArr(int[][] reversiTable, int[] inGameSize, int client, int[] player,ReversiPrint rp, ReversiCheck rc,Command cmd, int input){
		int[] gotCol = rc.getClassicCol(client,reversiTable,inGameSize,cmd); // get the value of column that can be input
		int[] gotRow = rc.getClassicRow(client,reversiTable,inGameSize,cmd);// get the value of row that can be input
		int[] classicInput = new int[2];
		classicInput[0]=gotCol[input]; // set the column.
		classicInput[1]=gotRow[input]; // set the row.
		return classicInput; // encapsulation of input to int[].
	}
	
	public void classicPrint(int[][] reversiTable,int[] inGameSize,int[] player,int client,Command cmd, ReversiPrint rp, ReversiCheck rc){
		if(getClassicModeDisplay()){
			rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
		}
	}
	
	public boolean firstCommandAct(int client ,String firstInput, int[][] reversiTable,int[] inGameSize, int[] player, ReversiPrint rp, ReversiCheck rc, Command cmd){
		if(firstInput.equals("-display")){
			if(getClassicMode()){
				if(getClassicModeDisplay()){ // check classic display whether is on.
					setClassicModeDisplay(false); //disable the classic display
					System.out.println("[Classic Display] Disable.");
					rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
				}else{
					setClassicModeDisplay(true);//enable the classic display
					System.out.println("[Classic Display] Enable.");
				}
			}else{
				System.out.println("[Classic Display] You must trun on the Classic Mode first.");
			}
			return true;
		}
		
		if(firstInput.equals("-icon")){ // change the size of reversi table.
			if(changeIcon(reversiTable,inGameSize, player,client,rp,rc,cmd)){
				return true; // continus this method
			}
		}
		
		if(classicModeOn(firstInput,reversiTable,inGameSize,player,client,rp,rc,cmd)){
			return true;
		}
		
		if(firstInput.equals("-def")){
			iconDefault(reversiTable,inGameSize,player,client,rp,rc,cmd); // set all icon to default.
			return true;
		}
		
		if(firstInput.equals("-f")){
			if(!getLiveScore()){
				System.out.println("[Live Score] Enable."); // enable the live score function.
				setLiveScore(true);
				if(!getClassicModeDisplay()){
					rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
				}
			}else{
				System.out.println("[Live Score] Disable."); // disable the live score function.
				setLiveScore(false);
				if(!getClassicModeDisplay()){
					rp.print(reversiTable,inGameSize,player,client,cmd,rc); // print reversi table
				}
			}
			return true;
		}
		
		if(firstInput.equals("-debug")){
			if(getRoot()){ // root can use -debug command only.
				if(!getDebug()){
					setDebug(true); // enable debug.
					System.out.println("[Debug]: Enable debug mode.");
				}else{
					setDebug(false); // disable debug
					System.out.println("[Debug]: Disable debug mode.");
				}
			}else{
				System.out.println("[Debug]: Fail. You must have permission, you can ask Pan for password to login in root.");
			}
			return true;
		}
			
		if(firstInput.equals("-c")){
			commandShow(); // show all commands.
			return true;
		}
		
		if(getRoot() && (firstInput.equals("exit") || firstInput.equals("quit"))){
			setRoot(false); 
			System.out.println("logout."); //logout root.
			return true;
		}
		
		if(firstInput.equals("-size")){ // change the size of reversi table.
			if(changeSize(inGameSize,player,client,rp,rc,cmd)){
				return true; // if change the size fail, ask the inputting again.
			}
		}
		
		if(firstInput.equals("su") || firstInput.equals("-bot")){
			if(!secondInputAct(client,firstInput)){ // check second input
				System.out.printf("Error - input values should be integers.\n");
			}
			return true;
		}
		
		return false;
	}
	
	public boolean secondInputAct(int client, String firstInput){
		String secondInput = sc.next();
		if(firstInput.equals("su")){
			if(secondInput.equals("-")){
				rootEnable(); // login in root.
				return true;
			}
		}else{
			for(int i=0; i<=3; i++){
				if(secondInput.equals(""+i)){ // check second input number after -bot
					setBotMode(i); // bot player = i
					if(i==0){ //deactivate bot
						System.out.println("[BOT] BOT has been deactivated.");// Prompt message
					}
					if(i!=0 && i!=3){// activate BOT
						System.out.printf("[BOT] BOT '%s' has been activated.\n",newIcon[i-1]); // Prompt message
					}
					if(i==3){// activate two BOTs
						System.out.printf("[BOT] BOT '%s' and BOT '%s' have been activated.\n",newIcon[0],newIcon[1]); // Prompt message
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public void rootEnable(){ // login root method
		String su = "root";
		int a = 888, b =999;
		a=a^b; //
		b=a^b; //b=a^b^b
		a=a^b; //a=a^b^a
		a=(a<<2)-1999;
		su +=a;
		Console hidePassword = System.console();
		char[] pwd = hidePassword.readPassword("Password: "); //hide password input
		String password ="";
		for(int i=0; i<pwd.length;i++){
			password += pwd[i];
		}
		if(password.equals(su)){
			setRoot(true); 
			System.out.println("login in root.");
		}else{
			System.out.println("su: incorrect password");
		}
	}
	
	public void commandShow(){ // display messages method.
		System.out.printf("su -\t\t--login in root.\n");
		System.out.printf("exit, quit\t--logout root.\n");
		System.out.printf("-debug\t\t--enable the debug mode.(repeat to disable)\n");
		System.out.printf("-size\t\t--change the size of the reversi table.\n");
		System.out.printf("-icon\t\t--change the icon of players and the empty space.\n");
		System.out.printf("-classic\t--change the icon to classic, i.e. ¡´ ¡³.\n");
		System.out.printf("-display\t--display for the Classic Mode.\n");
		System.out.printf("-def\t\t--change the icon to the default.\n");
		System.out.printf("-f\t\t--display the score on the top of the reversi table.(repeat to disable)\n");
		System.out.printf("-bot 1\t\t--BOT '1' vs player '2'.\n");
		System.out.printf("-bot 2\t\t--player '1' vs BOT '2'.\n");
		System.out.printf("-bot 3\t\t--BOT '1' to BOT '2'.\n");
		System.out.printf("-f\t\t--auto display. it must enter in -bot3 mode (hiding input).\n");
		System.out.printf("-s\t\t--skip display until the end. it must enter in -bot 3 mode (hiding input).\n");
		System.out.printf("q\t\t--exit -bot 3 mode (hiding input.\n");
		System.out.println();
	}
}