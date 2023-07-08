import java.util.*;



public class WorldStatistics {
    static List<String> linesOrszagok = FileInputHandler.getLinesOfTextFile("res/orszagok.txt");
    static List<String> linesVarosok = FileInputHandler.getLinesOfTextFile("res/varosok.txt");
    static List<Orszag> orszagok = new ArrayList<>();
    static List<Varos> varosok = new ArrayList<>();

    public static void main(String[] args) {
        /*
        // egy elem példányosítása, csak példa
        //  linesVarosok.get(0); // kiírja az 1. elemet, ha van ilyen
        for (String varosLine : linesVarosok) {
            System.out.println(varosLine);
        }
        String kabulAsString = linesVarosok.get(0); // "KABUl, AFG, 1780000"
        String[] kabulAdatai = kabulAsString.split(","); // {"Kabul", "AFG", "1780000"}
        Varos kabul = new Varos(kabulAdatai[0], kabulAdatai[1], Integer.parseInt(kabulAdatai[2]));
        System.out.println(kabul);
        */
        createVarosok();
        createOrszagok();
        connectVarosokEsOrszagok();

        for (Varos varos : varosok) {
            System.out.println(varos);
        }


    }

    private static void createVarosok() {
        for (String varosline : linesVarosok) {
            String[] lineAsArrayVaros = varosline.split(",");
            varosok.add(new Varos(
                    lineAsArrayVaros[0],
                    lineAsArrayVaros[1],
                    Integer.parseInt(lineAsArrayVaros[2])
            ));
        }
    }

    private static void createOrszagok() {
        for (String orszagLine : linesOrszagok) {
            String[] lineAsArrayOrszag = orszagLine.split(",");
            orszagok.add(new Orszag(
                    lineAsArrayOrszag[0],
                    lineAsArrayOrszag[1],
                    lineAsArrayOrszag[2],
                    lineAsArrayOrszag[3],
                    Double.parseDouble(lineAsArrayOrszag[4]),
                    Integer.parseInt(lineAsArrayOrszag[5]) == 0 ? -1 : Integer.parseInt(lineAsArrayOrszag[5]),
                    Integer.parseInt(lineAsArrayOrszag[6]),
                    lineAsArrayOrszag[7].length() <= 7 ? "" : lineAsArrayOrszag[7]
            ));
        }
    }


    private static void connectVarosokEsOrszagok() {
        for (Orszag orszag : orszagok) {
            for (Varos varos : varosok) {
                if (varos.getOrszagKod().equals(orszag.getOrszagKod())) {
                    varos.setOrszag(orszag);
                    orszag.addVaros(varos);
                }
            }
        }
    }

    // TODO amely az országkód alapján visszaadja egy ország összes adatát!
    public Orszag findCountryByISoCode(String isoCode) {
        for (Orszag orszag : orszagok) {
            if (orszag.getOrszagKod().equals(isoCode)) {
                return orszag;
            }
        }
        throw new NoSuchElementException("Nincs ilyen kódú ország");
    }

    // TODO Írj metódust, amely visszaadja egy paraméterül kapott kontinens országainak az
    //  országkódjait! A metódus szignatúrája az alábbi legyen:
    public List<String> getCountriesOfContinent(String continentName) {
        List<String> orszagKontinens = new ArrayList<>();
        for (Orszag orszag : orszagok) {
            if (continentName.equals(orszag.getKontinens())) {
                orszagKontinens.add(orszag.getOrszagKod());
            }
        }
        return orszagKontinens;
    }

    // TODO Írj metódust, amely visszaadja egy paraméterül kapott ország városainak a neveit (az
    //  országot országkóddal adjuk meg)! A metódus szignatúrája az alábbi legyen:
    public Set<String> getCitiesOfCountry(String countryCode) {
        Orszag orszag = findCountryByISoCode(countryCode);
        Set<String> varosokAzOrszagban = new HashSet<>();
        for (Varos varos : orszag.getVarosok()) {
            varosokAzOrszagban.add(varos.getVarosNev());
        }
        return varosokAzOrszagban;
    }

    // TODO Hány országnak az államfőjének a nevében szerepel “Hamad” vagy “Ahmad” vagy “Ahmed”?
    //  Írj metódust, amely válaszol erre a kérdésre!
    public int countAhmeds() {
        int counts = 0;
        for (Orszag orszag : orszagok) {
                if (orszag.getAllamfoNev().contains("Ahmad") ||
                    orszag.getAllamfoNev().contains("Ahmed") ||
                    orszag.getAllamfoNev().contains("Hamad")) {
                counts++;
            }
        }
        return counts;
    }

    // TODO Melyik betűvel kezdődik a legtöbb országkód? Írj metódust, amely válaszol erre
    //  kérdésre.
    // Betű (key) melyikhez tartozik a legnagyobb szám (value)
    public String getPopularFirstLetter() {
        Map<Character, Integer> popularFirstLetterCounter = new HashMap<>();
        for (Orszag orszag : orszagok) {
            Character firstLetter = orszag.getOrszagKod().charAt(0);
            /*
            if (!popularFirstLetterCounter.containsKey(firstLetter)) {
//                popularFirstLetterCounter.put(firstLetter, 1);
//            } else {
//                popularFirstLetterCounter.put(firstLetter, popularFirstLetterCounter.get(firstLetter) + 1);
//            }
             */
            // ez a fenti kód ugyanaz, mint az alábbi egy sor
            popularFirstLetterCounter.put(firstLetter, popularFirstLetterCounter.getOrDefault(firstLetter, 0) + 1);
        }
        System.out.println(popularFirstLetterCounter);

        // betű(key) melyikhez tartozik a legnagyobb szám (value)
        // entryset
        Integer maxValueInMap = Integer.MIN_VALUE;
        Character mostCommonCharacter = null;
        for (Map.Entry<Character, Integer> entry : popularFirstLetterCounter.entrySet()) {
            if (entry.getValue() > maxValueInMap) {
                maxValueInMap = entry.getValue();
                mostCommonCharacter = entry.getKey();
            }
        }
        return mostCommonCharacter.toString();
    }

    // TODO Melyik ország nyerte el legkésőbb (a nyilvántartás szerint) a függetlenségét?
    //  Add vissza a megfelelő országkódot, a metódus szignatúrája pedig az alábbi legyen:
    public String lastIndependentCountryCode() {
        Orszag latestIndependentCountry = null;
        for (Orszag orszag : orszagok) {
            if (orszag.getFuggetlenseg() != 0) {
                latestIndependentCountry = orszag;
                break;
            }
        }
        for (Orszag orszag : orszagok) {
            if(orszag.getFuggetlenseg() != 0 && orszag.getFuggetlenseg() > latestIndependentCountry.getFuggetlenseg()) {
                latestIndependentCountry = orszag;
            }
        }
        return latestIndependentCountry.getOrszagNev();
    }

}

