import javax.swing.*;

public class Calculation {

    public static String calculate_output (JLabel[] labels, JTextField[] textFields) {

        // input variables
        double wynagrodzenieBrutto = 0;
        double stawkaPodatkowa = 0;
        double stawkaEmerytalna = 0;
        double stawkaRentowa = 0;
        double stawkaChorobowa = 0;
        double stawkaZdrowotna = 0;
        double stawkaZdrowotnaDoOdliczenia = 0;
        double kwotaWolnaOdPodatku = 0;
        double kosztUzyskaniaPrzychodu = 0;

        // output variables
        double wynagrodzenieNetto = 0;
        double kwotaStawkaPodatkowa = 0;
        double kwotaStawkaEmerytalna = 0;
        double kwotaStawkaRentowa = 0;
        double kwotaStawkaChorobowa = 0;
        double kwotaStawkaZdrowotna = 0;
        double kwotaStawkaZdrowotnaDoOdliczenia = 0;
        double kwotaSkladkiSpoleczneRazem;
        double roczneWynagrodzenie = 0;
        double progDochodowy = 0;

        for (int i = 0; i < labels.length; i++) {
            String text = labels[i].getText();

            switch (text) {
                case "Wynagrodzenie brutto: ":
                    wynagrodzenieBrutto = Double.parseDouble(textFields[i].getText());
                    break;
                case "Stawka podatkowa: ":
                    stawkaPodatkowa = Double.parseDouble(textFields[i].getText());
                    break;
                case "Stawka emerytalna: ":
                    stawkaEmerytalna = Double.parseDouble(textFields[i].getText());
                    break;
                case "Stawka rentowa: ":
                    stawkaRentowa = Double.parseDouble(textFields[i].getText());
                    break;
                case "Stawka chorobowa: ":
                    stawkaChorobowa = Double.parseDouble(textFields[i].getText());
                    break;
                case "Stawka zdrowotna: ":
                    stawkaZdrowotna = Double.parseDouble(textFields[i].getText());
                    break;
                case "Stawka zdrowotna do odliczenia: ":
                    stawkaZdrowotnaDoOdliczenia = Double.parseDouble(textFields[i].getText());
                    break;
                case "Kwota wolna od podatku: ":
                    kwotaWolnaOdPodatku = Double.parseDouble(textFields[i].getText());
                    break;
                case "Koszt uzyskania przychodu: ":
                    kosztUzyskaniaPrzychodu = Double.parseDouble(textFields[i].getText());
                    break;
                case "Prog dochodowy: ":
                    progDochodowy = Double.parseDouble(textFields[i].getText());
                    break;
            }
        }

        //double[] stawki = new double []{wynagrodzenieBrutto, stawkaPodatkowa, stawkaEmerytalna, stawkaRentowa, stawkaChorobowa, stawkaZdrowotna, stawkaZdrowotnaDoOdliczenia, kwotaWolnaOdPodatku, kosztUzyskaniaPrzychodu};
        roczneWynagrodzenie = wynagrodzenieBrutto * 12;

        // sprawdz czy dochod przekroczyl prog podatkowy
        double wynagrodzenieBrutto32 = 0;
        if (roczneWynagrodzenie > progDochodowy) {
            // policz prog podatkowy miesieczny
            double wynagrodzenieMiesieczneProg = progDochodowy / 12;
            // policz roznice dla ktorej liczy sie podatek 32 %
            wynagrodzenieBrutto32 = wynagrodzenieBrutto - wynagrodzenieMiesieczneProg;
            // redefiniuj wynagrodzenie brutto od ktorego beda odliczane liczone skladki
            // ? pytanie czy to jest dobre podejscie, skladki chyba powinny byc liczone od wynaprodzeniabrutto a tylko podatek 18% powinien byc liczony od kwoty progu podatkowego
            wynagrodzenieBrutto = progDochodowy / 12;
        }

        // https://wynagrodzenia.pl/artykul/jak-obliczyc-wynagrodzenie-netto
        /*
        Ubezpieczenia spoleczne:
        - emerytalne
        - rentowe
        - chorobowe
         */
        kwotaStawkaEmerytalna  = wynagrodzenieBrutto * (stawkaEmerytalna/100);
        kwotaStawkaRentowa = wynagrodzenieBrutto * (stawkaRentowa/100);
        kwotaStawkaChorobowa = wynagrodzenieBrutto * (stawkaChorobowa/100);
        kwotaSkladkiSpoleczneRazem = kwotaStawkaEmerytalna + kwotaStawkaRentowa + kwotaStawkaChorobowa;

        // kwota stawki zdrowotnej
        kwotaStawkaZdrowotna = (wynagrodzenieBrutto - kwotaSkladkiSpoleczneRazem) * (stawkaZdrowotna/100);
        // stawka podatkowa przed odliczeniem stawki zdrowotnej
        kwotaStawkaPodatkowa = ((wynagrodzenieBrutto - kwotaSkladkiSpoleczneRazem - kosztUzyskaniaPrzychodu) * (stawkaPodatkowa/100)) - kwotaWolnaOdPodatku;
        // obliczenie stawki zdrowotnej do odliczenia od podatku dochodowego
        kwotaStawkaZdrowotnaDoOdliczenia = (wynagrodzenieBrutto - kwotaSkladkiSpoleczneRazem) * (stawkaZdrowotnaDoOdliczenia/100);
        // stawka podatkowa ostateczna (pomniejszna o kwote skladki zdrowotnej (7.75%))
        kwotaStawkaPodatkowa = kwotaStawkaPodatkowa - kwotaStawkaZdrowotnaDoOdliczenia;
        // wynagrodzenie netto, prog I -> 18%
        wynagrodzenieNetto = wynagrodzenieBrutto - kwotaSkladkiSpoleczneRazem - kwotaStawkaZdrowotna - kwotaStawkaPodatkowa;

        // initialize results string
        String text = "";
        // sprawdz czy prog podatkowy przekroczony
        if (roczneWynagrodzenie > progDochodowy) {
            double stawka30 = 32;
            double podatek30;

            // policz podatek doch. dla kwoty ktora przekroczyla prog dochodowy
            podatek30 = my_rounder.round(wynagrodzenieBrutto32 * (stawka30/100),2) ;
            // wynagrodzenie netto po przekroczeniu progu dochodowego
            wynagrodzenieNetto = wynagrodzenieNetto + (wynagrodzenieBrutto32 - (wynagrodzenieBrutto32 * (stawka30/100)));
            // sumuj podatek dochodowy kwota 18% + kwota 32%
            kwotaStawkaPodatkowa = kwotaStawkaPodatkowa + podatek30;
            text = "RESULTS: \nPROG PODATKOWY: 32%\n Kwota podatku (32%): " + podatek30 + "\n";

        } else {
            text = "RESULTS: \nPROG PODATKOWY: 18%\n";
        }

        double[] outPut = new double[]{wynagrodzenieNetto, kwotaStawkaPodatkowa, kwotaSkladkiSpoleczneRazem, kwotaStawkaEmerytalna, kwotaStawkaRentowa, kwotaStawkaChorobowa, kwotaStawkaZdrowotna, kwotaStawkaZdrowotnaDoOdliczenia};
        String[] outPut_str = new String[]{"wynagrodzenie netto: ", "podatek dochodowy: ", "ub. spoleczne razem: ", "skladka emerytalna: ", "skladka rentowa: ", "skladka chorobowa: ", "skladka na ub zdrowotne (9%): ", "skladka na ub. zdrowotne (7.75%): "};


        for (int i = 0; i < outPut.length; i++) {
            text = text + outPut_str[i] + my_rounder.round(outPut[i],2)  + "\n";
        }
        

        return text;
    }

}
