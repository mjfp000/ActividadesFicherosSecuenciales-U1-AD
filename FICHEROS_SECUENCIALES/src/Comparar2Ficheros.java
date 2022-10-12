import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Comparar2Ficheros {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Introduce dos archivos para comparar:");

		File objFile1;
		String stringIntroducido1 = "";

		boolean todaviaNoHayArchivoVálido = true;
		do {
			System.out.println("Introduce el nombre de un archivo existente.");
			Scanner S = new Scanner(System.in);
			String Str = S.next();
			objFile1 = new File(Str);
			if (objFile1.exists() && objFile1.isFile() && (objFile1.length() != 0)) {
				todaviaNoHayArchivoVálido = false;
				stringIntroducido1 = Str;
				System.out.println("OK1!");
				break;
			} else {
				System.out.println("El fichero no es valido.");
			}
		} while (todaviaNoHayArchivoVálido);

		File objFile2;
		String stringIntroducido2 = "";

		boolean todaviaNoHayArchivoVálido2 = true;
		do {
			System.out.println("Introduce el nombre de un archivo existente.");
			Scanner S = new Scanner(System.in);
			String Str = S.next();
			objFile2 = new File(Str);
			if (objFile2.exists() && objFile2.isFile() && (objFile2.length() != 0)) {
				todaviaNoHayArchivoVálido = false;
				stringIntroducido2 = Str;
				System.out.println("OK2!");
				break;
			} else {
				System.out.println("El fichero no es valido.");
			}
		} while (todaviaNoHayArchivoVálido2);

		FileInputStream objetoFIS1 = new FileInputStream(objFile1);
		DataInputStream objDIS1 = new DataInputStream(objetoFIS1);
		FileInputStream objetoFIS2 = new FileInputStream(objFile2);
		DataInputStream objDIS2 = new DataInputStream(objetoFIS2);

		File objFileOut = new File("Diferencias.txt");
		FileOutputStream objetoFOS = new FileOutputStream(objFileOut);
		DataOutputStream objDOS = new DataOutputStream(objetoFOS);

		BufferedReader reader1 = new BufferedReader(new FileReader(objFile1));
		BufferedReader reader2 = new BufferedReader(new FileReader(objFile2));

		boolean noSalir = true;
		int linea = 1;
		while (noSalir) {
			try {
				String temp1 = reader1.readLine();
				String temp2 = reader2.readLine();

				if (temp1 == null || temp2 == null) {
					noSalir = false;
					break;
				}

				if (temp1.equals(temp2)) {
				} else {
					objDOS.writeUTF("Diferencia en linea " + linea + ". (" + temp1 + "|" + temp2 + ")\n");
					System.out.println("DIFERENCIA ENCONTRADA");
				}
			} catch (IOException e) {
				noSalir = false;
			}
			linea++;
		}
	}

}
