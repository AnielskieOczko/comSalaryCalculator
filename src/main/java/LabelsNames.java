public enum LabelsNames {

    /*
    wynagrodzenie_brutto
    stawka_podatkowa: 18.0%
    składka emerytalna: 9.76%
    składka rentowa:  1.5%
    składka chorobowa: 2.45%
    składka zdrowotna: 9.0%
    składka zdrowotna do odliczenia: 7.75%
    prog doch.: 85 528 zł
    kwota wolna od podatki: 46,33 zł
    koszt uzyskania przychodu: 111,25 zł
    skladki spoleczne razem
    */

    WYNAGRODZENIE_BRUTTO ("Wynagrodzenie brutto: ", 2300),
    STAWKA_PODATKOWA ("Stawka podatkowa: ", 18),
    STAWKA_EMERYTALNA ("Stawka emerytalna: ", 9.76),
    STAWKA_RENTOWA ("Stawka rentowa: ", 1.5),
    STAWKA_CHOROBOWA ("Stawka chorobowa: ", 2.45),
    STAWKA_ZDROWOTNA ("Stawka zdrowotna: ", 9.0),
    STAWKA_ZDROWOTNA_DO_ODLICZENIA ("Stawka zdrowotna do odliczenia: ", 7.75),
    PROG_DOCH ("Prog dochodowy: ",85528),
    KWOTA_WOLNA_OD_PODATKU ("Kwota wolna od podatku: ", 46.33),
    KOSZT_UZYSKANIA_PRZYCHODU ("Koszt uzyskania przychodu: ", 111.25),
    SKLADKI_SPOLECZNE_RAZEM ("Skladki spoleczne razem: ", 0);

    private final String myLabel;
    private final double defualText;

    LabelsNames(String myLabel, double defualText) {
        this.myLabel = myLabel;
        this.defualText = defualText;
    }

    public String GetMyLabel() {
        return myLabel;
    }

    public double GetDefaultText() {
        return defualText;
    }

}
