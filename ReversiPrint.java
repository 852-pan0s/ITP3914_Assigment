public class ReversiPrint{
	private String[] strInteger = {"¢¯","¢°","¢±","¢²","¢³","¢´","¢µ","¢¶","¢·","¢¸","¢Ï","¢Ð","¢Ñ","¢Ò","¢Ó","¢Ô","¢Õ","¢Ö","¢×","¢Ø","¢Ù","¢Ú","¢Û","¢Ü","¢Ý","¢Þ","¢ß","¢à","¢á","¢â","¢ã","¢ä","¢å","¢æ","¢ç","¢è"}; //position that can be input will show in the strPrintReversi.
	
	public String[] getStrInteger (){
		return strInteger;
	}
	
	public void gameStart(int[][] reversiTable, int[] gameSize, int client, int[] player,Command cmd, ReversiCheck rc){
		int[] defaultColumn = {((gameSize[0]-1)/2) ,((gameSize[0]-1)/2)+1}; // Proofreading the table.
		int[] defaultRow = {((gameSize[1]-1)/2) , ((gameSize[1]-1)/2)+1}; // Proofreading the table.
		reversiTable[defaultColumn[0]][defaultRow[0]] = player[0]; //set the default value.
		reversiTable[defaultColumn[0]][defaultRow[1]] = player[1]; //set the default value.
		reversiTable[defaultColumn[1]][defaultRow[0]] = player[1]; //set the default value.
		reversiTable[defaultColumn[1]][defaultRow[1]] = player[0]; //set the default value.
		if(!cmd.getClassicModeDisplay()){
			print(reversiTable,gameSize,player,client,cmd,rc); //print the reversi table.
		}
	}
	public void print(int[][] reversiTable,int[] gameSize,int[] player,int client,Command cmd, ReversiCheck rc){
		if(cmd.getLiveScore()){
			int[] countPlayer = countScore(reversiTable,gameSize,cmd);
			System.out.printf("Live Score: '%s' [%d - %d] '%s'\n",cmd.getNewIcon()[0],countPlayer[0],countPlayer[1],cmd.getNewIcon()[1]);
		}
		for(int i=0; i<gameSize[0] ;i++){
			if(i==0 && !cmd.getClassicModeDisplay()){ //print header
				//System.out.printf("%3c",' '); //print space to header for indenting.
				if(cmd.getClassicMode()){
					System.out.printf("\n%3c",' ');
				}else{ 
					System.out.printf("\n%3c",' '); //print space to header for indenting, \n for fixing the bug of display that input more than 2 value at the same time.
				}
				for(int j=0; j<gameSize[1]+1; j++){ 
					if(j<gameSize[1]){ //print number first, then print hyphons, so gameSize[1]+1
						if(gameSize[1]>10){
								System.out.printf("%3d",j); //print the numbers that are aligned on the header.
							continue; 
						}else{
							if(cmd.getClassicMode()){
								System.out.printf("%2s",strInteger[j]); //print the numbers that are aligned on the header.
							}else{
								System.out.printf("%2d",j); //print the numbers that are aligned on the header.
							}
							continue; //print the size of row.
						}
					}
					System.out.printf("\n%4c",' ');	//before printing the hyphons, print a new line and indent first.
					if(gameSize[1]>10){
						System.out.printf("%1c",' '); // fix the size of the reversi row that more that 10 problem.
						for (int n=0; n<gameSize[1]*3-2; n++){ //calculate the suitable interface.
							if(n==gameSize[1]*3-3){ //calculate the suitable interface.
								System.out.print("-\n"); //print hyphon and print new line.
							}else{
								System.out.print("-"); //print hyphons.
							}
						}
					}else if(cmd.getClassicMode()){
						for (int n=0; n<gameSize[1]*3-1; n++){ //calculate the suitable interface.
							if(n==gameSize[1]*3-2){  //calculate the suitable interface.
								System.out.print("-\n"); //print hyphon and print new line.
							}else{
								System.out.print("-"); //print hyphons.
							}
						}
					}else{
						for (int n=0; n<gameSize[1]*2-1; n++){ //calculate the suitable interface.
							if(n==gameSize[1]*2-2){  //calculate the suitable interface.
								System.out.print("-\n"); //print hyphon and print new line.
							}else{
								System.out.print("-"); //print hyphons.
							}
						}
					}
				}
			}
			if(!cmd.getClassicModeDisplay()){ // delete the column in the most left if classic display is on.
				if(gameSize[0]>10 && i>=10){
					System.out.printf("%d%c",i,'|'); //print first column
				}else{
					System.out.printf("%d%2c",i,'|'); //print first column
				}
			}
			for(int j=0; j<gameSize[1] ;j++){
				if(cmd.getIconChange()){
					String[] newIcon = cmd.getNewIcon(); // get icon value.
					String[][] strPrintReversi = strPrintReversi(reversiTable,gameSize,player,cmd,newIcon); //get strPrintReversi that edited icon.
					if(cmd.getClassicModeDisplay()){ // check classic mode
						int arrNum = rc.getClassicArrNumber(client,reversiTable,gameSize,cmd); // get the maximun number that can be input
						int[] gotCol = rc.getClassicCol(client,reversiTable,gameSize,cmd); // get the value of column that can be input
						int[] gotRow = rc.getClassicRow(client,reversiTable,gameSize,cmd); // get the value of row that can be input
						for(int n = 0; n<arrNum; n++){
							strPrintReversi[gotCol[n]][gotRow[n]]=strInteger[n]; // show the position that can be input.
						}
						System.out.printf("%s",strPrintReversi[i][j]); //print reversi table.
					}else{
						if(gameSize[1]>10){
							System.out.printf("%3s",strPrintReversi[i][j]); //print reversi table.
						}else{
							System.out.printf("%2s",strPrintReversi[i][j]); //print reversi table.
						}
					}
				}else{
					if(gameSize[1]>10){
						System.out.printf("%3s",reversiTable[i][j]); //print reversi table.
					}else{
						System.out.printf("%2s",reversiTable[i][j]); //print reversi table.
					}
				}
			}
			System.out.println(); //print new line.
		}
		System.out.println(); //print new line.
	}
	public String[][] strPrintReversi(int[][] reversiTable,int[] gameSize, int[] player, Command cmd, String[] newIcon){
		String[][] strReversi = new String[gameSize[0]][gameSize[1]];
		for(int i=0; i<reversiTable.length; i++){
			for(int j=0; j<reversiTable[i].length; j++){
				if(reversiTable[i][j]==player[0]){ //find player 1.
					strReversi[i][j]=newIcon[0]; // set the icon of player 1.
				}
				if(reversiTable[i][j]==player[1]){//find player 2.
					strReversi[i][j]=newIcon[1]; // set the icon of player 2.
				}
				if(reversiTable[i][j]==0){ //find empty.
					strReversi[i][j]=newIcon[2];// set the icon of empty 2.
				}
			}
		}
		return strReversi; // return edited String reversi table.
	}
	
	public void result(int[][] reversiTable,int[] gameSize, Command cmd){
		int[] countPlayer = countScore(reversiTable,gameSize,cmd);
		String winner = (countPlayer[0]!=countPlayer[1])? ((countPlayer[0]>countPlayer[1])?"Black wins": "White wins"): "Draw"; // find who wins the game.
		System.out.printf("Game Finishes.\n  '%s' - %d\n  '%s' - %d\n%s.",cmd.getNewIcon()[0],countPlayer[0],cmd.getNewIcon()[1],countPlayer[1],winner);// display result.
	}
	
	public int[] countScore(int[][] reversiTable,int[] gameSize, Command cmd){
		int[] countPlayer = {0,0,0}; // declare thc count of player number that in the reversi table. [0] = player 1, [1] = player 2
		for(int i=0;i<gameSize[0];i++){
			for(int j=0; j<gameSize[1]; j++){
				if(reversiTable[i][j]==cmd.getPlayer()[0]){
					countPlayer[0]++; // the number of '1' +1
				}else if(reversiTable[i][j]==cmd.getPlayer()[1]){
					countPlayer[1]++; // the number of '2' +1
				}else{
					countPlayer[2]++; // the number of '0' +1
				}
			}
		}
		return countPlayer;
	}
}