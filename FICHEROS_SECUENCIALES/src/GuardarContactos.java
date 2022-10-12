import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuardarContactos {

	public static void main(String[] args) {

		boolean noSalir = true;
		while (noSalir) {
			try {
				FileWriter objFileWriter = new FileWriter("Contactos.txt", true);
				BufferedWriter out = new BufferedWriter(objFileWriter);

				Scanner sc = new Scanner(System.in);
				String temp = "";

				System.out.println("Introduce el nombre del contacto (000 para salir.)");
				temp = sc.next();
				if (temp.equals("000")) {
					noSalir = false;
					break;
				}
				out.write(temp);
				out.flush();

				System.out.println("Introduce el apellido del contacto.");
				temp = sc.next();
				out.write(" | " + temp + " | ");
				out.flush();

				System.out.println("Introduce el numero del contacto.");
				temp = sc.next();
				out.write(temp + "\n");
				out.flush();

			} catch (IOException e) {
				System.out.println("Error.");
			}
		}
	}

}
