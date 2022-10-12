import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AgendaDeContactosConArchivo {

	public static ArrayList<Contacto> agendaDeContactos = new ArrayList<Contacto>();

	public static void main(String[] args) {
		menuPrincipal();
	}

	private static void menuPrincipal() {
		boolean salir = false;
		while (!salir) {
			try {
				System.out.println("Menu principal, agenda de contactos:");
				System.out.println("1. Guardar contacto");
				System.out.println("2. Ver todos los contactos");
				System.out.println("3. Buscar contactos");
				System.out.println("4. Borrar contacto");
				System.out.println("5. Importar contactos desde archivo");
				System.out.println("6. Exportar contactos a archivo");
				System.out.println("7. Copiar archivos");
				System.out.println("8. Salir");

				try {
					Scanner input1 = new Scanner(System.in);
					String opcion = input1.next();

					switch (opcion) {
					case "1":
						guardarContacto();
						break;
					case "2":
						buscarContactos(true);
						break;
					case "3":
						buscarContactos(false);
						break;
					case "4":
						borrarContacto();
						break;
					case "5":
						importarContactos();
						break;
					case "6":
						exportarContactos();
						break;
					case "7":
						System.out.println(
								"Opcion de copia de archivos (intento desde parametros, si no funciona uno de los parametros, se pedira corregirlo):");

						Scanner tempCopy = new Scanner(System.in);
						System.out.println("Introduce el archivo a copiar:");
						String archivo = tempCopy.next();
						System.out.println("Introduce el archivo destino:");
						String destino = tempCopy.next();

						if (copiarArchivos(archivo, destino)) {
							System.out.println("La copia se completo correctamente.");
							pausa();
						} else {
							System.out.println("La copia fallo.");
							pausa();
						}

						break;
					case "8":
						salir = true;
						break;
					default:
						System.out.println("Opcion no reconocida.");
						pausa();
						break;
					}
				} catch (Exception e) {
					System.out.println("Ha ocurrido un error: " + e);
				}
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error: " + e);
				e.printStackTrace();
			}
		}

		try {
			System.in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.exit(0);
	}

	private static boolean copiarArchivos(String archivo, String destino) {
		// System.out.println("Opcion de copiar archivos:");
		// System.out.println("Introduce la ruta del archivo a copiar.");

		File objFile1;
		String str = archivo;
		boolean todaviaNoHayArchivoVálido = true;
		do {
			objFile1 = new File(str);
			if (objFile1.exists() && objFile1.isFile() && (objFile1.length() != 0)) {
				todaviaNoHayArchivoVálido = false;
				System.out.println("Archivo OK!");
				break;
			} else {
				System.out.println("El fichero no es valido.");
				System.out.println("Introduce el nombre de un archivo existente.");
				Scanner s = new Scanner(System.in);
				str = s.next();
			}
		} while (todaviaNoHayArchivoVálido);

		File objFileDestino;
		String str2 = destino;
		boolean todaviaNoHayArchivoVálido2 = true;
		do {
			objFileDestino = new File(str2);
			if (!objFileDestino.exists()) {
				todaviaNoHayArchivoVálido = false;
				System.out.println("Destino OK!");
				break;
			} else {
				System.out.println("El fichero no es valido.");
				System.out.println("Introduce el nombre de un archivo no existente.");
				Scanner s = new Scanner(System.in);
				str2 = s.next();
			}
		} while (todaviaNoHayArchivoVálido2);

		try {
			FileReader fr = new FileReader(objFile1);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(objFileDestino);
			BufferedWriter bw = new BufferedWriter(fw);

			String strTemp = "";
			while ((strTemp = br.readLine()) != null) {
				bw.write(strTemp);
				bw.flush();
			}

			br.close();
			bw.close();
			fr.close();
			br.close();

			return true;
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error: " + e);
			return false;
		}
	}

	private static void pausa() {
		System.out.println("Presiona ENTER para continuar.");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	private static void exportarContactos() {
		System.out.println("Opcion de exportar contactos:");
		System.out.println("Introduce la ruta del archivo de contactos para exportar.");

		File objFile;
		String Str;
		boolean todaviaNoHayArchivoVálido = true;
		do {
			System.out.println("Introduce el nombre de un archivo no existente.");
			Scanner s = new Scanner(System.in);
			Str = s.next();
			objFile = new File(Str);
			if (!objFile.exists()) {
				todaviaNoHayArchivoVálido = false;
				System.out.println("OK!");
				break;
			} else {
				System.out.println("El fichero no es valido.");
			}
		} while (todaviaNoHayArchivoVálido);

		int contador = 0;
		try {
			FileWriter fw = new FileWriter(objFile);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Contacto contacto : agendaDeContactos) {
				bw.write(contacto.getNombre() + ";");
				bw.flush();
				bw.write(contacto.getApellidos() + ";");
				bw.flush();
				bw.write(contacto.getTelefono() + ";");
				bw.flush();
				contador++;
			}

			bw.close();
			fw.close();
			System.out.println("Se han exportado " + contador + " contactos.");

		} catch (Exception e) {
			System.out.println("Se ha producido un error: " + e);
		}

		pausa();
	}

	private static void importarContactos() {
		System.out.println("Opcion de importar contactos:");
		System.out.println("Introduce la ruta del archivo de contactos a importar.");

		File objFile;
		boolean todaviaNoHayArchivoVálido = true;
		do {
			System.out.println("Introduce el nombre de un archivo existente.");
			Scanner s = new Scanner(System.in);
			String Str = s.next();
			objFile = new File(Str);
			if (objFile.exists() && objFile.isFile() && (objFile.length() != 0)) {
				todaviaNoHayArchivoVálido = false;
				System.out.println("OK!");
				break;
			} else {
				System.out.println("El fichero no es valido.");
			}
		} while (todaviaNoHayArchivoVálido);

		try {
			FileReader fr = new FileReader(objFile);
			BufferedReader br = new BufferedReader(fr);

			String importar = "";
			String strTemp = "";
			while ((strTemp = br.readLine()) != null) {
				importar += strTemp;
			}

			String tempNombre = "";
			String tempApellidos = "";
			String tempTelefono = "";
			int mode = 1;

			Contacto tempContacto = new Contacto();

			int contador = 0;
			for (int i = 0; i < importar.length(); i++) {
				System.out.print(importar.charAt(i));
				switch (mode) {
				case 1:
					if (importar.charAt(i) != ';') {
						tempNombre = tempNombre + importar.charAt(i);
					} else {
						tempContacto.setNombre(tempNombre);
						tempNombre = "";
						mode = 2;
					}
					break;
				case 2:
					if (importar.charAt(i) != ';') {
						tempApellidos = tempApellidos + importar.charAt(i);
					} else {
						tempContacto.setApellidos(tempApellidos);
						tempNombre = "";
						mode = 3;
					}
					break;
				case 3:
					if (importar.charAt(i) != ';') {
						tempTelefono = tempTelefono + importar.charAt(i);
					} else {
						tempContacto.setTelefono(tempTelefono);
						tempNombre = "";
						mode = 1;
						contador++;
						agendaDeContactos.add(tempContacto);
						tempContacto = new Contacto();
					}
					break;
				}
			}

			br.close();
			fr.close();
			System.out.println("Se han importado " + contador + " contactos.");
		} catch (Exception e) {
			System.out.println("No se ha encontrado el archivo.");
		}

		pausa();
	}

	private static void borrarContacto() {
		Scanner input4 = new Scanner(System.in);

		System.out.println("Opcion de borrar contacto:");
		System.out.println("Introduce el nombre del contacto a borrar.");

		String borrar = input4.next();

		for (Contacto contacto : agendaDeContactos) {
			if (contacto.getNombre().contains(borrar.strip().toLowerCase())
					|| contacto.getNombre().contains(borrar.strip().toUpperCase())
					|| contacto.getApellidos().contains(borrar.strip().toLowerCase())
					|| contacto.getApellidos().contains(borrar.strip().toUpperCase())
					|| contacto.getTelefono().contains(borrar.strip().toLowerCase())
					|| contacto.getTelefono().contains(borrar.strip().toUpperCase())) {

				System.out.println("Contacto encontrado: [" + contacto.getNombre() + "/" + contacto.getApellidos() + "/"
						+ contacto.getTelefono() + "]");

				System.out.println("¿Deseas borrar este contacto? (S/N)");
				Scanner input5 = new Scanner(System.in);
				String opcionBorrar = input5.next();
				if (opcionBorrar.equals("S")) {
					System.out.println("¿Confirmar borrar?");
					opcionBorrar = input5.next();
					if (opcionBorrar.equals("S")) {
						agendaDeContactos.remove(contacto);
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		}

		pausa();
	}

	private static void buscarContactos(boolean b) {
		if (!b) {
			Scanner input3 = new Scanner(System.in);

			System.out.println("Opcion de buscar contacto:");
			System.out.println("Introduce el nombre del contacto a buscar.");

			String buscar = input3.next();

			boolean encontrado = false;
			for (Contacto contacto : agendaDeContactos) {
				if (contacto.getNombre().contains(buscar.strip().toLowerCase())
						|| contacto.getNombre().contains(buscar.strip().toUpperCase())
						|| contacto.getApellidos().contains(buscar.strip().toLowerCase())
						|| contacto.getApellidos().contains(buscar.strip().toUpperCase())
						|| contacto.getTelefono().contains(buscar.strip().toLowerCase())
						|| contacto.getTelefono().contains(buscar.strip().toUpperCase())) {

					System.out.println("Contacto encontrado: [" + contacto.getNombre() + "/" + contacto.getApellidos()
							+ "/" + contacto.getTelefono() + "]");

					encontrado = true;
				}
			}

			if (!encontrado) {
				System.out.println("No se han encontrado contactos.");
			}
		} else {
			System.out.println("Opcion de buscar todos los contactos:");
			for (Contacto contacto : agendaDeContactos) {
				System.out.println("[" + contacto.getNombre() + "/" + contacto.getApellidos() + "/"
						+ contacto.getTelefono() + "]");
			}
		}

		pausa();
	}

	private static void guardarContacto() {
		Contacto tempContacto = new Contacto();
		Scanner input2 = new Scanner(System.in);

		System.out.println("Opcion de guardar contacto:");
		System.out.println("Introduce el nombre del nuevo contacto o escribe 000 para salir.");

		System.out.println("Nombre:");
		String nombre = input2.next();
		if (nombre.equals("000")) {
			return;
		} else {
			tempContacto.setNombre(nombre);
		}
		System.out.println("Apellidos:");
		String apellidos = input2.next();
		if (apellidos.equals("000")) {
			return;
		} else {
			tempContacto.setApellidos(apellidos);
		}
		System.out.println("Numero de telefono:");
		String telefono = input2.next();
		if (nombre.equals("000")) {
			return;
		} else {
			tempContacto.setTelefono(telefono);
		}

		agendaDeContactos.add(tempContacto);
		System.out.println("Se ha guardado el contacto.");
		pausa();
	}

}
