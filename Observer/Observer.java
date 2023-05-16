import java.util.*;

interface Observer {
    public void update(Observable o);
}

class Observable {
    List<Observer> observers = new ArrayList<>();

    public void add(Observer ob) {
        observers.add(ob);
    }

    public void remove(Observer ob) {
        observers.remove(ob);
    }

    public void notifyObservers() {
        Iterator<Observer> it = observers.iterator();
        while (it.hasNext()) {
            Observer ob = it.next();
            ob.update(this);
        }
    }
}


class PCD extends Observable {
    private int temp;
    private int pH;

    public PCD() {
        this.temp = 0;
        this.pH = 0;
    }

    public void setData(int temp, int pH) {
        this.temp = temp;
        this.pH = pH;
        notifyObservers();
    }

    public int getTemp() {
        return this.temp;
    }

    public int getPh() {
        return this.pH;
    }
}

class SaoPaulo implements Observer {
    private int usableTemp;
    private int usablepH;

    public void update(Observable o) {
        PCD data = (PCD) o;
        if (10 <= data.getTemp() || data.getPh() == 4) {
            this.usableTemp = data.getTemp();
            this.usablepH = data.getPh();
        }
    }

    public int getTemp() {
        /* Do something with the data */
        return this.usableTemp;
    }

    public int getpH() {
        /* Do something with the data */
        return this.usablepH;
    }
}

class RioJaneiro implements Observer {
    private int usableTemp;
    private int usablepH;

    public void update(Observable o) {
        PCD data = (PCD) o;
        if (data.getPh() < 7) {
            this.usableTemp = data.getTemp();
            this.usablepH = data.getPh();
        }
    }

    public int getTemp() {
        /* Do something with the data */
        return this.usableTemp;
    }

    public int getpH() {
        /* Do something with the data */
        return this.usablepH;
    }
}

class Recife implements Observer {
    private int usableTemp;
    private int usablepH;

    public void update(Observable o) {
        PCD data = (PCD) o;
        if (data.getTemp() != 30) {
            this.usableTemp = data.getTemp();
            this.usablepH = data.getPh();
        }
    }

    public int getTemp() {
        /* Do something with the data */
        return this.usableTemp;
    }

    public int getpH() {
        /* Do something with the data */
        return this.usablepH;
    }
}

class Main {
    public static void main(String[] args) {
        SaoPaulo instSP = new SaoPaulo();
        Recife instPE = new Recife();
        RioJaneiro instRJ = new RioJaneiro();

        PCD pcd1 = new PCD();
        PCD pcd2 = new PCD();
        
        pcd1.add(instSP);
        pcd1.add(instPE);
        pcd2.add(instRJ);

        pcd1.setData(100, 100);
        pcd2.setData(100, 100);

        System.out.println("SP:");
        System.out.println(instSP.getTemp());
        System.out.println(instSP.getpH());
        System.out.println("PE:");
        System.out.println(instPE.getTemp());
        System.out.println(instPE.getpH());
        System.out.println("RJ:");
        System.out.println(instRJ.getTemp());
        System.out.println(instRJ.getpH());

        pcd1.setData(10, 5);
        pcd2.setData(10, 7);

        System.out.println("\nUpdated: \n");
        System.out.println("SP:");
        System.out.println(instSP.getTemp());
        System.out.println(instSP.getpH());
        System.out.println("PE:");
        System.out.println(instPE.getTemp());
        System.out.println(instPE.getpH());
        System.out.println("RJ:");
        System.out.println(instRJ.getTemp());
        System.out.println(instRJ.getpH());
    }
}