public class Varos {

    String varosNev;
    String orszagKod;
    Integer populacio;
    Orszag orszag;

    public Varos(String varosNev, String orszagKod, Integer populacio) {
        this.varosNev = varosNev;
        this.orszagKod = orszagKod;
        this.populacio = populacio;
    }

    // TODO A metódus visszaadja, hogy az adott város populációja hány százaléka az
    //  anyaország populációjának! Ha akár a város, akár az anyaország populációja ninc
    //  megadva, a metódusod -1-gyel térjen vissza
    public double getPopulacioSzazalek() {
        if (orszag.getPopulacio() == 0) {
            return -1;
        }
        return (double) populacio / orszag.getPopulacio();
    }

    @Override
    public String toString() {
        return "Varos{" +
                "varosNev='" + varosNev + '\'' +
                ", orszagKod='" + orszagKod + '\'' +
                ", populacio='" + populacio + '\'' +
                ", orszag=" + orszag.getOrszagNev() +
                '}';
    }

    public String getVarosNev() {
        return varosNev;
    }

    public void setVarosNev(String varosNev) {
        this.varosNev = varosNev;
    }

    public String getOrszagKod() {
        return orszagKod;
    }

    public void setOrszagKod(String orszagKod) {
        this.orszagKod = orszagKod;
    }

    public Integer getPopulacio() {
        return populacio;
    }

    public void setPopulacio(Integer populacio) {
        this.populacio = populacio;
    }

    public Orszag getOrszag() {
        return orszag;
    }

    public void setOrszag(Orszag orszag) {
        this.orszag = orszag;
    }
}
