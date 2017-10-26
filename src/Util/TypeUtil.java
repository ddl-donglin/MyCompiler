package Util;

import Lexer.KeyTypes;

import java.lang.reflect.Field;

/**
 * �Զ�ȡ�ַ�������ز���
 */
public class TypeUtil {
	private final String keyWords[] = { "abstract", "boolean", "break", "byte",
			"case", "catch", "char", "class", "continue", "default", "do",
			"double", "else", "extends", "final", "finally", "float", "for",
			"if", "implements", "import", "instanceof", "int", "interface",
			"long", "native", "new", "package", "private", "protected",
			"public", "return", "short", "static", "super", "switch",
			"synchronized", "this", "throw", "throws", "transient", "try",
			"void", "volatile", "while","strictfp","enum","goto","const","assert" }; // �ؼ�������
	private final char operators[] = { '+', '-', '*', '/', '=', '>', '<', '&', '|',
			'!' }; // ���������
	private final char separators[] = { ',', ';', '{', '}', '(', ')', '[', ']', '_',
			':', '.', '"','\\'}; // �������
	
	/**
	 * �ж��Ƿ�Ϊ��ĸ
	 * @param ch ���жϵ��ַ�
	 * @return boolean
	 */
	public boolean isLetter(char ch) {
		return Character.isLetter(ch);
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * @param ch ���жϵ��ַ�
	 * @return boolean
	 */
	public boolean isDigit(char ch) {
		return Character.isDigit(ch);
	}

	/**
	 * �ж��Ƿ�Ϊʮ�������е�a-f
	 * @param ch
	 * @return
	 */
	public boolean isHEXletter(char ch){
		char c = Character.toLowerCase(ch);
		if(c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c =='f')
			return true;
		else
			return false;
	}
	/**
	 * �ж��Ƿ�Ϊ�ؼ���
	 * @param s ���жϵ��ַ���
	 * @return boolean
	 */
	public boolean isKeyWord(String s) {
		for (int i = 0; i < keyWords.length; i++) {
			if (keyWords[i].equals(s))
				return true;
		}
		return false;
	}

	/**
	 * �ж��Ƿ�Ϊ�����
	 * @param ch ���жϵ��ַ�
	 * @return boolean
	 */
	public boolean isOperator(char ch) {
		for (int i = 0; i < operators.length; i++) {
			if (ch == operators[i])
				return true;
		}
		return false;
	}

	/**
	 * �ж��Ƿ�Ϊ�ָ���
	 * @param ch ���жϵ��ַ�
	 * @return boolean
	 */
	public boolean isSeparators(char ch) {
		for (int i = 0; i < separators.length; i++) {
			if (ch == separators[i])
				return true;
		}
		return false;
	}
	/**
	 * ���÷����ȡ�ֱ����
	 * @return	�ֱ����
	 */
	public int getType(String args) {
		int type = -1;
		Field[] fields = KeyTypes.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(args)) {
				try {
					type = (Integer) field.get(new KeyTypes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}

}