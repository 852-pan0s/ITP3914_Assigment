public class ReversiCheck{
	private int changeCol;
	private int changeRow;
	
	public ReversiCheck(int defCol, int defRow){
		changeCol = defCol;
		changeRow = defRow;
	}
	
	public ReversiCheck(){
		changeCol = 0;
		changeRow = 0;
	}
	
	public void setChangeValue(int c, int r){
		if(c >= -1 && c <= 1 && r >= -1 && r <= 1){
			changeCol = c;
			changeRow = r;
		}else
			System.out.println("Support -1 to 1 only.");
	}
	
	public void setChangeCol(int c){
		if(c >= -1 && c <= 1){
			changeCol = c;
		}else
			System.out.println("Support -1 to 1 only.");
	}
	
	public void setChangeRow(int r){
		if(r >= -1 && r <= 1){
			changeRow = r;
		}else
			System.out.println("Support -1 to 1 only.");
	}
	
	public int getChangeCol(){
		return changeCol;
	}
	
	public int getChangeRow(){
		return changeRow;
	}
	
	public int switchRound(int client, int[] player){
		if(client == player[0]){ // NOT gate to client.
			return player[1];
		}else{
			return player[0];
		}
	}
	
	public int basicCheck(int[] input, int[][] reversiTable,int[] gameSize){
		if(input[0]<0 || input[0]>=gameSize[0]){ //check whether the input is out of range in the value of Reversi table.
			return 1;
		}
		if(input[1]<0 || input[1]>=gameSize[1]){ //check whether the input is out of range in the value of Reversi table.
			return 2;
		}
		if(reversiTable[input[0]][input[1]]!=0){ // player / bot can input the position that is 0 only .
			return 3;
		}
		return 0;
	}
	
	public boolean positionCheck(boolean isPlayer, int[] input, int[][] reversiTable,int[] gameSize,int client, Command cmd){
		boolean check = false;
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				if(i == 0 && j == 0){
					continue;
				}
				setChangeValue(i,j); //Check the position from different angles.
				if(findNearest(isPlayer,input,reversiTable,gameSize,client, cmd)){ // Check the position from different angles.
					if(isPlayer){
						reversiTable[input[0]][input[1]]=client; // place the value of player / bot's inputing that passed chcking to reversi table.
					}
					check = true;
				}
			}
		}
		if(check){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isDigit(String[] s){ //whether player enters the integer number.
		try{
			for(int i=0; i<s.length; i++){ //start from s[0] , check whether s[i] is integer.
				int toInt = Integer.parseInt(s[i]);
			}
		}catch(Exception e){ // if it can't convert to integer, drop it.
			return false;
		}
		return true;
	}
	
	public boolean findNearest(boolean isPlayer, int[] input, int[][] reversiTable,int[] gameSize,int client, Command cmd){
		int tempCol = input[0]; //use for move
		int tempRow = input[1];	//use for move
		while(true){ //Check from different angles.
			tempCol += changeCol; // check all 8 angles including top, bottom, left, right, top left, top right, bottom left, bottom right.
			tempRow += changeRow; // check all 8 angles including top, bottom, left, right, top left, top right, bottom left, bottom right.
			if(tempCol < 0 || tempRow < 0 || tempCol >= gameSize[0] || tempRow >= gameSize[1] || reversiTable[tempCol][tempRow]==0){//if it finds 0 in reversi table , stop finding. When array index out of bounds, stop finding.
				return false; // exit finding if no result. Can't find 0 when finding teammate.
			}
			if(reversiTable[tempCol][tempRow]==client){ //find the nearest teammate
				if(roundTrips(isPlayer,tempCol,tempRow,input[0],input[1],client,reversiTable,cmd)>0){ //check kill number when round trips.
					return true;
				}
				break; // exit finding if it do not find the nearest teammate.
			}
		}
		return false; 
	}
	
	public int roundTrips(boolean isPlayer, int foundCol,int foundRow,int inCol ,int inRow, int client, int[][] reversiTable, Command cmd){
		int tempCol = foundCol;
		int tempRow = foundRow;
		int killEnemy = 0; //count the numbers of killed enemy
		while(true){
			tempCol -= changeCol; // Round trips from the found position of the nearest teammate to the position of inputting.
			tempRow -= changeRow; // Round trips from the found position of the nearest teammate to the position of inputting.
			if(tempCol == inCol && tempRow == inRow){// Are the round trips end?
				break;
			}
			if(isPlayer){
				reversiTable[tempCol][tempRow]=client; //fill teammate to killed enemy position.
			}
			killEnemy++; //kill number 1
		}
		return killEnemy;
	}
	
	public boolean placePossible(int client, int[][] reversiTable,int[] gameSize,Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						return true;
					}else if(y==gameSize[0]-1 && x==gameSize[1]-1 && !endGame(reversiTable,gameSize)){ //if no position that can be input by player.
						return false;
					}
				}
			}
		}
		return false;
	}
	
	public int[] getClassicCol(int client, int[][] reversiTable,int[] gameSize,Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		int[] displayCol = new int[new ReversiPrint().getStrInteger().length];
		int arrNumber = 0;
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						displayCol[arrNumber]=y;
						arrNumber++;
					}
				}
			}
		}
		return displayCol;
	}
	
	public int[] getClassicRow(int client, int[][] reversiTable,int[] gameSize,Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		int[] displayRow = new int[new ReversiPrint().getStrInteger().length];
		int arrNumber = 0;
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						displayRow[arrNumber]=x;
						arrNumber++;
					}
				}
			}
		}
		return displayRow;
	}
	
	public int getClassicArrNumber(int client, int[][] reversiTable,int[] gameSize,Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		int arrNumber = 0;
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						arrNumber++;
					}
				}
			}
		}
		return arrNumber;
	}
	
	public int botGetBestPositionArrNum(int client, int[][] reversiTable,int[] gameSize,Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		int arrNumber = 0;
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						arrNumber++;
						if(bestPosition(y,x,cmd)){ // find the best position.
							arrNumber++;
							return arrNumber;
						}
					}
				}
			}
		}
		return arrNumber;
	}
	
	public int[] botGetClassicColRow(int client, int[][] reversiTable,int[] gameSize,Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						if(bestPosition(y,x,cmd)){ // find the best position.
							return npc;
						}
					}
				}
			}
		}
		return npc;
	}
	
	public int[] botPlace(int client, int[][] reversiTable,int[] gameSize, Command cmd){
		int[] npc = new int[2]; // non player control tests reversi table.
		int botMoveAvailable = 0;
		int[][] botSaveMove = new int[gameSize[0]*gameSize[1]][2];
		for(int y=0;y<gameSize[0];y++){
			for(int x=0;x<gameSize[1];x++){
				npc[0]=y;
				npc[1]=x;
				if(reversiTable[y][x]==0){
					if(positionCheck(false,npc,reversiTable,gameSize,client,cmd)){ // find which column and row that can be input by player or bot.
						if(bestPosition(y,x,cmd)){ // find the best position.
							return npc; // return the best position.
						}
						botSaveMove[botMoveAvailable][0]=y; //save column for bot.
						botSaveMove[botMoveAvailable][1]=x; //save row for bot.
						botMoveAvailable++; // bot move available + 1.
						continue;
					}
				}
			}
		}
		return botRandomMove(botMoveAvailable,botSaveMove,client); // find best place that bot can input.
	}
	
	public int[] botRandomMove(int botMoveAvailable, int[][] botSaveMove, int client){
		int random = (int)(Math.random()*botMoveAvailable);
		return botPlace(client,botSaveMove[random]);
	}
	
	public int[] botPlace(int client, int[] botSaveMove){
		int[] botMove = new int[2]; // declare that bot moves position
		botMove[0] = botSaveMove[0];
		botMove[1] = botSaveMove[1];
		return botMove;	
	}
	
	public boolean botPlayer(int client, int[] player,Command cmd){ //set bot player in different bot mode.
		if(cmd.getBotMode() == 1 && client == player[0]){
			return true;
		}else if(cmd.getBotMode() == 2 && client == player[1]){
			return true;
		}else if(cmd.getBotMode() == 3){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean bestPosition (int y, int x, Command cmd){
		if(y==0 && x==0) // find left top that equals 0.
			return true;
		if(y==0 && x==cmd.getGameSize()[1]-1) // find right top that equals 0.
			return true;
		if(y==cmd.getGameSize()[0]-1 && x==cmd.getGameSize()[1]-1) // find right bottom that equals 0.
			return true;
		if(y==cmd.getGameSize()[0]-1 && x==0) // find left bottom that equals 0.
			return true;
		return false;
	}
	
	public int inputChar(String input){
		if(input.length() == 1){
			for(int i =65; i<=90; i++){ // the ascii number of A to Z in decimal.
				if(input.charAt(0) == i){ // find the capital letter.
					return i-55; // return at least 10 that can't display on the reversi table correctly.
				}
			}
			for(int i =97; i<=122; i++){ // the ascii number of a to z in decimal.
				if(input.charAt(0) == i){ // find the capital letter.
					return i-87; // return at least 10 that can't display on the reversi table correctly.
				}
			}
		}
		return -1;
	}
	
	public int classicCheck(int[][] reversiTable, int[] gameSize, int client, String[] clientInput, Command cmd){
		int getNumber = getClassicArrNumber(client,reversiTable,gameSize,cmd);// get the maximun number that can be input
		clientInput[1] = "0"; // second index is integer given because it needs to check the first inputing only.
		int toInt = 0;
		if(isDigit(clientInput)){ // check whether the inputting is digit.
			toInt = Integer.parseInt(clientInput[0]);
		}else{
			toInt = inputChar(clientInput[0]);
		}
		if(toInt < 0 || toInt >= getNumber){ // can't input the number that is out of the range.
			return -1;
		}else if(toInt>=0){
			return toInt; // ask player to re input.
		}
		return -2;
	}
	
	public boolean endGame(int[][] reversiTable,int[] gameSize){
		for(int i=0;i<gameSize[0];i++){
			for(int j=0; j<gameSize[1]; j++){
				if(reversiTable[i][j]==0) // find 0 in the resersi table.
					return false; // if there is any 0, return false.
			}
		}
		return true; // if there is not 0 in the table, return true.
	}
}