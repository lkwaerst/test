class ForelesnEks {

    ForelesnEks() {
	ListeAvPersoner mineVenner  =
	    new ListeAvPersoner();

	Person ane = new Person("Ane");
	mineVenner.settInnForst(ane);

	Person jonas = new Person("Jonas");
	mineVenner.settInnForst(jonas);

	Person imran = new Person("Imran");
	mineVenner.settInnSist(imran);

	Person siri = new Person("Siri");
	mineVenner.settInnEtter(ane, siri);

	Person jan = new Person("Jan");
	mineVenner.settInnEtter(ane, jan);
        
	ane = mineVenner.finnPerson("Ane");
	mineVenner.settInnEtter(ane, new Person("Mari"));
	mineVenner.skrivAlle();
    }
}



class Oblig3 {

    public static void main(String[] klargs) {
	Person.skriv();
	new ForelesnEks();

    }
}


class Person {
    String navn;
    Person neste;

    Person(String n) {
	navn = n;
    }

    static public void skriv(){
        System.out.println(navn);
    }
}



class ListeAvPersoner {
    private Person personliste, sistePerson;
    private int antall;
    
    ListeAvPersoner(){
        Person lh = new Person("LISTEHODE!!");
	personliste = lh;
	sistePerson = lh;
	antall = 0;
    }       
    
    public void settInnForst(Person nypers){
	if (sistePerson == personliste) {
	    settInnSist(nypers);
	    return;
	}
	if (hasPerson(nypers)) return;
	nypers.neste = personliste.neste;
	personliste.neste = nypers;
	antall++;
    }
    
    public void settInnSist(Person inn){
	if (hasPerson(inn)) {
	    return;
	}
	sistePerson.neste = inn;
	sistePerson = inn;
	antall++;
    }

    public void settInnEtter(Person denne, Person inn) {
	if (hasPerson(inn)) return;
	if (denne == inn || !hasPerson(denne)) {
	    return;
	}
	inn.neste = denne.neste;
	denne.neste = inn;
	antall++;
	if (sistePerson == denne) {
	    sistePerson = inn;
	}
    }

    public Person finnPerson(String s) {
	Person p = personliste.neste;
        for (int i = antall; i>0; i--) {
	    if (p.navn.equals(s)) return p;
	    else p = p.neste;
        }
	return null;
    }

    
    public void skrivAlle() {
        Person p = personliste.neste;
        for (int i = antall; i>0; i--) {
            p.skriv();
            p = p.neste;
        }
    }
    
    public boolean hasPerson (Person sjekk){
	Person p = personliste.neste;
	for (int i = antall; i > 0; i--) {
	    if (p == sjekk) {
		return true;
	    }
	    p = p.neste;
	}
	return false;
    }
}