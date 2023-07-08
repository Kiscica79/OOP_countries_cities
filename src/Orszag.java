import java.util.List;

public class Orszag {

    String orszagKod;
    String orszagNev;
    String kontinens;
    String regio;
    double terulet;
    int fuggetlenseg;
    int populacio;
    String allamfoNev;
    List<Varos> varosok;

    public Orszag(String orszagKod, String orszagNev, String kontinens, String regio,
                  double terulet, int fuggetlenseg, int populacio, String allamfoNev) {
        this.orszagKod = orszagKod;
        this.orszagNev = orszagNev;
        this.kontinens = kontinens;
        this.regio = regio;
        this.terulet = terulet;
        this.fuggetlenseg = fuggetlenseg;
        this.populacio = populacio;
        this.allamfoNev = allamfoNev;

    }

    public void addVaros(Varos varos) {
        this.varosok.add(varos);
    }

    // TODO Ez a metódus visszaadja az adott ország népsűrűségét fő/km2-ben!
    //  Ha az ország területe vagy népessége nincs megadva, akkor a metódusod -1-gyel térjen vissza!
    public double getPopulationDensity() {
        if (getTerulet() != 0.0 || getPopulacio() != 0.0) {
            return populacio / terulet;
        }
        return -1;
    }

    // TODO Ez a metódus visszaadja, hogy az adott országban hány fő él a felsorolt városokon kívül!
    public double getRuralPopulation() {
        int sumPopulacio = 0;
        for (Varos varos : varosok){
            sumPopulacio += varos.getPopulacio();
        }
        return populacio - sumPopulacio;
    }



    public double getTerulet() {
        return terulet;
    }

    public int getPopulacio() {
        return populacio;
    }

    public String getOrszagKod() {
        return orszagKod;
    }

    public String getOrszagNev() {
        return orszagNev;
    }

    public String getKontinens() {
        return kontinens;
    }

    public String getRegio() {
        return regio;
    }

    public int getFuggetlenseg() {
        return fuggetlenseg;
    }

    public String getAllamfoNev() {
        return allamfoNev;
    }

    public List<Varos> getVarosok() {
        return varosok;
    }

    @Override
    public String toString() {
        return "Orszag{" +
                "orszagKod='" + orszagKod + '\'' +
                ", orszagNev='" + orszagNev + '\'' +
                ", kontinens='" + kontinens + '\'' +
                ", regio='" + regio + '\'' +
                ", terulet=" + terulet +
                ", fuggetlenseg=" + fuggetlenseg +
                ", populacio=" + populacio +
                ", allamfoNev='" + allamfoNev + '\'' +
                ", varosok=" + varosok.size() +
                '}';
    }
}
