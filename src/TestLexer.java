/**
 * 词法分析
 * 关键字，运算符一符一类  
 * 标识符，常数，分隔符各自一类
 * 运算符未处理组合运算 ++、--、+= 等
 */
public class TestLexer extends TypeUtil{
	private StringBuffer buffer = new StringBuffer(); // 缓冲区
	private int i = 0;
	private char ch; // 字符变量，存放最新读进的源程序字符
	private String strToken; // 字符数组，存放构成单词符号的字符串
	private String DFAStr = " "; //DFA转换表
	private String tab = ") &nbsp;&nbsp;&nbsp;&nbsp;";
	
	public TestLexer() {
	}
	/**
	 * 读取指定路径文件
	 * @param fileSrc 读取文件路径
	 */
	public TestLexer(String fileSrc) {
		FileUtil.readFile(buffer, fileSrc);
	}

	/**
	 * 词法分析
	 */
	public void analyse() {
		strToken = ""; // 置strToken为空串
		FileUtil.clearFile();//清空文件
		int error = 0; //报错的个数
		while (i < buffer.length()) {
			getChar();
			getBC();
			if (isLetter(ch)) { // 如果ch为字母
				while (isLetter(ch) || isDigit(ch)) {
					concat();
					getChar();
				}
				retract(); // 回调
				if (isKeyWord(strToken)) { 
					writeFile(strToken,"_"+tab+"    <0,letter_,1>");//strToken为关键字
				} else { 
					writeFile("IDN",strToken +tab+ "    <0,letter_,1> <1,letter/digit,1>");//strToken为标识符
				}
				strToken = "";
			} else if (isDigit(ch)) {
				if(ch == '0'){
					getChar();
					if(ch != '.' && isSeparators(ch)){
						writeFile("DEC", "0"+tab+ "    <0,digit,2> <2,0,4/5/7>");
					}else if(ch =='.'){
						getChar();
						while (isDigit(ch)) {//ch为数字
							concat();
							getChar();
						}
						writeFile("FLOAT","0." + strToken+tab+ "    <0,digit,2> <2,1-9,3> <3,0-9,3>"); // 是浮点数
						strToken = "";
					}
					else if(ch == 'x'){
						boolean err = false;
						while (isDigit(ch) || isLetter(ch)) {//ch为数字或者字母
							concat();
							getChar();
							if(!isHEXletter(ch))
								err = true;
						}
						if(err){
							retract(); // 回调
							writeFile("HEX","0"+strToken +tab+ "    <0,digit,2> " +
									"<2,0,4/5/7> <4/5/7,x,8> <8,0-9/a-f/A-F,9> <9,0-9/a-f/A-F,9>"); // 是十六进制整形

						}else{
							error++;
							writeFile("ERROR","0" + strToken+tab); // 非法
						}
						strToken = "";
					}else{
						boolean err = false;
						while (isDigit(ch)) {//ch为数字
							if(ch > '7')
								err = true;
							concat();
							getChar();

						}
						if(!err && !isLetter(ch)){//不能数字+字母
							retract(); // 回调
							writeFile("OCT","0"+strToken+tab+ "    <0,digit,2> " +
									"<2,0,4/5/7> <4/5/7,0-7,6> <6,0-7,6>"); // 是八进制整形
						}else{
							error++;
							writeFile("ERROR","0" + strToken+tab); // 非法
						}
						strToken = "";
					}
				}else{
					boolean sciencedigit = false;
					boolean floatdigit = false;
					while (isDigit(ch)||ch == 'e' ||ch == 'E' || ch =='.') {//ch为数字或e或.
						concat();
						getChar();
						if(ch == 'E' || ch == 'e')
							sciencedigit = true;
						if(ch == '.')
							floatdigit = true;
					}
					if(!isLetter(ch) ){//不能数字+字母
						if(!sciencedigit && !floatdigit){
							retract(); // 回调
							writeFile("DEC",strToken+tab+ "    <0,digit,2> <2,1-9,3> <3,0-9,3>"); // 是十进制整形
						}else if(!sciencedigit && floatdigit){
							retract(); // 回调
							writeFile("FLOAT",strToken+tab+ "    <0,digit,2> <2,1-9,3> <3,0-9,3>"); // 是浮点数
						}else if(sciencedigit && !floatdigit){
							retract(); // 回调
							writeFile("SN",strToken+tab+ "    <0,digit,2> <2,1-9,3> <3,0-9,3>"); // 是科学计数法
						}else{
							retract(); // 回调
							writeFile("FLOAT SN",strToken+tab+ "    <0,digit,2> <2,1-9,3> <3,0-9,3>"); // 是十进制整形
						}

					}else {
						error++;
						writeFile("ERROR",strToken+tab); // 非法
					}
					strToken = "";
				}
			} else if (isOperator(ch)) { //运算符
				if(ch == '/'){
					getChar();
					if(ch == '*') {//为/*注释
						while(true){
							getChar();
							if(ch == '*'){// 为多行注释结束
								getChar();
								if(ch == '/') {
									writeFile("NOTE","/**/"+tab+ "    <0,/,14> <14,*,15> " +
											"<15,others,15> <15,*,16> <16,others,15> <16,*,16> <16,/,17>");
									break;
								}
							}
						}
					}/*else if(ch == '/'){

						while(ch != '\n'){
							//System.out.println(ch+"   "+(int)ch);
							getChar();
						}
						writeFile("NOTE","//");
					}*/
					//retract();
				}else {
					//System.out.println(ch+"   "+(int)ch);
					switch (ch) {
						case '+':
							//writeFile("plus", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '-':
							//writeFile("min", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '*':
							//writeFile("mul", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '/':
							//writeFile("div", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '>':
							//writeFile("gt", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '<':
							//writeFile("lt", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '=':
							//writeFile("eq", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '&':
							//writeFile("and", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '|':
							//writeFile("or", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						case '~':
							//writeFile("not", ch + "");
							writeFile(ch +"", "_"+tab+"    <0,operator,19> <19,operator,19>");
							break;
						default:
							break;
					}
				}
			} else if (isSeparators(ch)) { // 界符
				//writeFile("separators",ch+"");
				writeFile(ch +"", "_"+tab+"    <0,separator,18>");
			} else {
				error++;
				writeFile("ERROR",ch+"" + tab);
			}
		}
		writeFile("<br>There were " + error +" errors.<br><br>Output DFA:<br>");
		writeFile(HTMLDFA.DFAStr);
	}

	/**
	 * 将下一个输入字符读到ch中，搜索指示器前移一个字符
	 */
	public void getChar() {
		ch = buffer.charAt(i);
		i++;
	}
	/** 检查ch中的字符是否为空白，若是则调用getChar()直至ch中进入一个非空白字符*/
	public void getBC() {
		//isSpaceChar(char ch) 确定指定字符是否为 Unicode 空白字符。
		//上述方法不能识别换行符
		while (Character.isWhitespace(ch))//确定指定字符依据 Java 标准是否为空白字符。
			getChar();
	}

	/**将ch连接到strToken之后*/
	public void concat() {
		strToken += ch;
	}
	/** 将搜索指示器回调一个字符位置，将ch值为空白字 */
	public void retract() {
		i--;
		ch = ' ';
	}
	/**
	 * 按照二元式规则写入文件
	 * @param file 字符类型
	 * @param s	当前字符
	 */
	public void writeFile(String file,String s) {
		int temp = getType(file.toUpperCase());
		System.out.println("("+file+", "+s + DFAStr);
		file = "("+file+", "+s+"\r\n<br>";
		FileUtil.writeFile(file);
	}

	public void writeFile(String s){
		System.out.println(s);
		FileUtil.writeFile(s);
	}
}