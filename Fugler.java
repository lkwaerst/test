import java.util.Scanner;
import java.io.*;

//Hovedklasse med main metode og valgregistrering
    class Fugler {
	try {
	    public static void main(String[] args) {
		
		Metoder metode = new Metoder(); //handtak til hjelpeklassen og metodene i den
		int valg = 0; 
		Scanner s = new Scanner(System.in); //Scanner for a registrere valg
		
		//lokke som registrerer valg og kaller pa metoden som blir valgt
		while (valg !=4) {
		    metode.Meny();
		    valg = s.nextInt();
		    
		    switch (valg) {
		    case 1: metode.Skrivfil();break;
		    case 2: metode.Fugletype();break;
		    case 3: metode.Sted();break;
		    case 4: break;
		    default: System.out.println("Prov igjen");
		    }
		}
	    }
	}
	catch (IOException e) {
	    System.out.println("Du grusa programmet gitt");
	}
    }
       
//hjelpeklasse
class Metoder {
    
    //intput scanner til alle metodene
    Scanner s = new Scanner(System.in);
    
    //metoden som skriver ut menyen
    void Meny() {                                                                              

	System.out.println();
	System.out.println("1. Registrer en fugleobservasjon");
	System.out.println("2. Skriv ut alle observasjoner av en fugletype");
	System.out.println("3. Skriv ut alle observasjonene pa ett bestemt sted");
	System.out.println("4. Avslutt systemet");
	System.out.println();
	System.out.print("Gi en kommando: ");

    }

    // metode for a legge inn nye observasjoner
    void Skrivfil() throws IOException {
	
	String[] observasjon = new String[4];

	//Ber om info of lagrer i arrayen
	System.out.print("Fugletype: ");
	observasjon[0] = s.next().trim();
	System.out.print("Kjonn: ");
	observasjon[1] = s.next().trim();
	System.out.print("Sted: ");
	observasjon[2] = s.next().trim();
	System.out.print("Dato: ");
	observasjon[3] = s.next().trim();
	
	//skriver til fil
	    
	PrintWriter skriv = new PrintWriter(new FileWriter("fugler.txt", true));

	skriv.println(observasjon[0] + ","+observasjon[1] + "," + observasjon[2] + "," + observasjon[3]);
	skriv.close();
    }
    
    //metode for oppramsing av observasjoner med felles fugletype
    void Fugletype() throws IOException {
	
	File fil = new File("fugler.txt");     
	Scanner sok = new Scanner(fil); //scanner for a soke gjennom filen
	System.out.println();
	System.out.print("Fugletype: ");
	String type = s.next(); 
        sok.useDelimiter(",|\n"); //scanneren settes til a behandle "," og linjeskift som skille mellom to ord
	
	/*scanneren soker gjennom tekstfilen linje for linje
	  og skriver ut linjen om det forste ordet matcher fugletypen*/
	while (sok.hasNext()) { 
	    if (sok.hasNext(type)) { 
		String linje = sok.nextLine();
		System.out.println(linje);
	    }
	    else {
		String neste = sok.nextLine();
	    }
	}
    }
    
    //metode for oppramsing av observasjoner pa samme sted
    void Sted() throws IOException {
	
	//samme som metoden for fugletype
	File fil = new File("fugler.txt");
	Scanner sok = new Scanner(fil);
	System.out.println();
	System.out.print("Sted: ");
	String sted = s.next();
	sok.useDelimiter(",|/n");

	/* while lokken gar igjennom en og en linje i tekstfilen
	   med en egen scanner, og skriver ut linjen om scanneren finner
	   stedsnavnet i linjen*/
	while (sok.hasNext()) { //lokke for hele tekstfilen
	    String linje = sok.nextLine();
	    Scanner linjesok = new Scanner(linje);
	    linjesok.useDelimiter(",");
	    while (linjesok.hasNext()) { //lokke for hver linje
		if (linjesok.hasNext(sted)) {
		    System.out.println(linje);
		    break;
		}
		else {
		    String ord = linjesok.next();
		}
	    }
	}
    }
}
	    
		