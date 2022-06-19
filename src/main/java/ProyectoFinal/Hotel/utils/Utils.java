package ProyectoFinal.Hotel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Utils {

	/**
	 * Te permite escribir por teclado numeros enteros.
	 * @return
	 */
	public static int Entero() {
		int x=-1;
		Scanner s=new Scanner(System.in);
		boolean valid=false;
		do {
			try {
				x=s.nextInt();
				valid=true;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error!!");
				s.next();
			}
		} while(valid);
		return x;
	}
	
	/**
	 * Te permite escribir por teclado diferentes caracteres.
	 * @return
	 */
	public static String String() {
		String x="";
		Scanner s=new Scanner(System.in);
		boolean valid=false;
		do {
			try {
				x=s.nextLine();
				valid=true;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error!!");
				s.next();
			}
		} while (valid);
		return x;
	}
	
	/**
	 * Sirve para mostrar texto por pantalla.
	 * @param s
	 */
	public static void print(String s) {
		System.out.println(s);
	}

	/**
	 * Encripta cualquier cadena usando SHA256
	 */
	public static String encrypt(String s){
		String f=null;
		try{
			MessageDigest md=MessageDigest.getInstance("SHA256");
			md.update(s.getBytes());
			StringBuilder sb=new StringBuilder();
			for(byte b: md.digest()){
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			f= sb.toString();
		} catch (NoSuchAlgorithmException e) {
			Errores.logWarning(e+"");
		}
		return f;
	}
}
