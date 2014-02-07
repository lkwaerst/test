class Oblig2 {

    public static void main(String[] args) {

	Test test = new Test();

    }
}



class Person {

    private String navn ;
    private Person[] kjenner;
    private Person[] likerIkke ;
    private Person forelsketI;
    private Person sammenMed;
    private int antVenner = 0; 

    private Bok[] boksamling;
    private Plate[] platesamling;
    private String favorittForfatter;
    private int grenseAar;

    Person (String n, int lengde) {	
	kjenner = new Person[lengde];
	likerIkke = new Person[lengde];
	navn = n;
    }

    public String hentNavn () {
	return navn;
    }
    
    //
    public boolean erKjentMed (Person p) {
	for (int i = 0; i < kjenner.length; i++) {
	    if (kjenner[i] == p && kjenner[i] != null) {
		return true;
	    }
	}
	return false;
    }
    
    //legger til personen i kjenner[] om personen ikke er der allerede og legger 1 til antVenner
    public void blirKjentMed(Person p) {
	int i = 0;
	if (p == this || p == null || erKjentMed(p)) {
	    return;
	}
	while  (i < kjenner.length) {
	    if (kjenner[i] == null) {
		kjenner[i] = p;
		antVenner++;
		break;
	    }
	    i++;	
	}
    }
    
    //legger personen i likerIkke[] og trekker 1 fra antVenner
    public void blirUvennMed (Person p) {	
	int i = 0;
	if (p == this) {
	    return;
	}
	
	while (i < likerIkke.length) {
	    if (likerIkke[i] == null) {
		likerIkke[i] = p;
		blirKjentMed(p); //i tilfelle personen ikke allerede er kjent
		antVenner--;
		return;
	    }
	    i++;
	}
    }

    public void blirForelsketI (Person p) {
	if (p == this) {
	    return;
	}
	forelsketI = p;
    }

    //sjekker forst om personen er kjent, saa om personen ligger i likerIkke arrayen
    public boolean erVennMed (Person p) {
	if (erKjentMed(p)) {
	    for (int i = 0; i < likerIkke.length; i++) {
		if (likerIkke[i] == p) {
		    return false;
		}
	    }
	    return true; //personen ligger ikke i likerIkke
	}
	return false; //om personen ikke er kjent
    }

    //blir kjent med ny person hvis personen ikke var uvenn, ellers fjernes personen fra likerIkke[] og antVenner okes med 1
    public void blirVennMed (Person p) {
	if (this == p || erVennMed(p)) {
	    return;
	}
	for (int i = 0; i < likerIkke.length; i++) {
	    if (likerIkke[i] == p) {  //hvis personen var uvenn for
		likerIkke[i] = null;
		antVenner++;
		return;
	    }
	}
	blirKjentMed(p); //hvis personen ikke var uvenn for
    }

    //skriver ut en linje med navn for hver venn
    public void skrivUtVenner() {
	for (Person p: kjenner) {
	    if (erVennMed(p)) {
		System.out.println (p.hentNavn());
	    }
	}
    }
    
    public Person hentBestevenn () {
	return kjenner[0];
    }

    //gaar igjennom kjenner[], gjor en vennesjekk paa personene, og returnerer vennene i en array
    public Person[] hentVenner () {
	int i = 0;
	Person[] venner = new Person[antVenner];

	for (Person p: kjenner) {
	    if (erVennMed(p)) {
		venner[i] = p;
		i++;
	    }
	}
	return venner;
    }
				  
    public int antVenner () {
	return antVenner;
    }
    
    public void skrivUtKjenninger () {
	for (Person p : kjenner) {
	    if (p != null) {
		System.out.print (p.hentNavn() + " ");
 		
	    }
	}
	System.out.println();
    }

    public void skrivUtLikerIkke () {
	for (Person p : likerIkke) {
	    if(p != null) {
		System.out.print (p.hentNavn() + " ");
	    }
	}
	System.out.println();
    }

    public void skrivUtAltOmMeg() {
	System.out.print (navn + " kjenner: ");
	skrivUtKjenninger();

	if (forelsketI != null) {
	    System.out.println (navn + " er forelsket i: " + forelsketI.hentNavn());
	    System.out.print (navn + " liker ikke: ");
	    skrivUtLikerIkke();
	}
    }
    
    public void samlerAv (String smlp, int ant) {
	if (smlp.equals("boker")) {
	    boksamling = new Bok[ant];
	}
	else if (smlp.equals("plater")) {
	    platesamling = new Plate[ant];
	}
    }
    
    public void megetInteressertI (String artist) {
	favorittForfatter = artist;
    }

    public void megetInteressertI (int eldreEnn) {
	grenseAar = eldreEnn;
    }
}

class Bok {
    
    private String forfatter, tittel;
    private int utgivelsesaar;

    Bok (String forfatt, String tittel, int utgivelse) {
	forfatter = forfatt;
	this.tittel = tittel;
	utgivelsesaar = utgivelse;
    }
}

class Plate {
    
    private String artistnavn, tittel;
    private int antSpor;

    Plate (String artist, String tittel, int ant) {
	artistnavn = artist;
	this.tittel = tittel;
	antSpor = ant
    }
}
class Test {

    private Plate[] plagteGaver = new Plate[17];
    private Bok[] bokGaver = new Bok[10];
    
    
    Test () {

	plateGaver[0] = new Plate("Michael Jackson", "Thriller", 21);
	plateGaver[1] = new Plate("Pink Floyd", "The Dark Side Of The Moon", 10);
	plateGaver[2] = new Plate("AC/DC", "Back in Black", 10);
	plateGaver[3] = new Plate("Shania Twain", "Come On Over", 16);
	plateGaver[4] = new Plate("Led Zeppelin", "Led Zeppelin IV", 8);
	plateGaver[5] = new Plate("Meat Loaf", "Bat Out of Hell", 7);
	plateGaver[6] = new Plate("Alanis Morissette", "Jagged Little Pill", 13);
	plateGaver[7] = new Plate("The Beatles", "Sgt. Pepper's Lonely Hearts Club Band", 13);
	plateGaver[8] = new Plate("Eagles", "Hotel California", 9);
	plateGaver[9] = new Plate("Mariah Carey", "Music Box", 10);
	plateGaver[10] = new Plate("Elton John", "Goodbye Yellow Brick Road", 17);
	plateGaver[11] = new Plate("The Beatles", "1", 27);
	plateGaver[12] = new Plate("Queen", "Made In Heaven", 12);
	plateGaver[13] = new Plate("Queen", "Innuendeo", 13);
	plateGaver[14] = new Plate("Silya Nymoen", "Masterplan", 1);
	plateGaver[15] = new Plate("Bob Dylan", "Modern Times", 10);
	plateGaver[16] = new Plate("Bob Dylan", "Together Through Life", 10);

	
	bokGaver[0] = new Bok("Charles Dickens", "A Tale of Two Cities", 1859);
	bokGaver[1] = new Bok("J.R.R. Tolkien", "The Lord of the Rings", 1954);
	bokGaver[2] = new Bok("Agatha Christie", "And Then There Were None", 1939);
	bokGaver[3] = new Bok("Cao Xueqin", "Dream of the Red Chamber", 1754);
	bokGaver[4] = new Bok("Antoine de Saint-Exupéry", "The Little Prince", 1943);
	bokGaver[5] = new Bok("Dan Brown", "The Da Vinci Code", 2003 );
	bokGaver[6] = new Bok("J.D. Salinger", "The Catcher in the Rye", 1951);
	bokGaver[7] = new Bok("C.S.Lewis", "The Lion, the Witch and the Wardrobe", 1950);
	bokGaver[8] = new Bok("Ellen G. White", "Steps to Christ", 1892);
	bokGaver[9] = new Bok("Anna Sewell", "Black Beauty", 1877);
	

    }
}