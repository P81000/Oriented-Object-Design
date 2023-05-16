import java.util.*;
interface Dispositivo {
  void turnOn();
  void turnOff();
  boolean getStatus();
}

class DVD implements Dispositivo {
  private boolean status = false;

  public void turnOn(){
    status = true;
    System.out.println("DVD is on!");
  }

  public void turnOff(){
    status = false;
    System.out.println("DVD is off!");
  }
  
  public boolean getStatus(){
    return status;
  }
}

class Amplifier implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("Amplifier is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("Amplifier is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class Syntonizer implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("Syntonizer is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("Syntonizer is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class Projector implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("Projector is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("Projector is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class CDPlayer implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("CDPlayer is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("CDPlayer is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class PopcornMachine implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("PopcornMachine is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("PopcornMachine is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class SodaMachine implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("SodaMachine is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("SodaMachine is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class AmbientLight implements Dispositivo {
    private boolean status = false;
    
    public void turnOn(){
        status = true;
        System.out.println("AmbientLight is on!");
    }
    
    public void turnOff(){
        status = false;
        System.out.println("AmbientLight is off!");
    }
    
    public boolean getStatus(){
        return status;
    }
}

class HomeTheater {
    public void turnOn(Dispositivo dispositivo){
        dispositivo.turnOn();
    }

    public void turnOff(Dispositivo dispositivo){
        dispositivo.turnOff();
    }

    public void turnOnAll(List<Dispositivo> dispositivos){
        for(Dispositivo dispositivo : dispositivos){
            dispositivo.turnOn();
        }
    }

    public void turnOffAll(List<Dispositivo> dispositivos){
        for (Dispositivo dispositivo : dispositivos){
            dispositivo.turnOff();
        }
    }
}

class HomeTheaterClient {
    private List<Dispositivo> dispositivos = new ArrayList<>();

    public HomeTheaterClient (HomeTheater client){
    }
  
    public void addDispositivo(Dispositivo dispositivo){
        dispositivos.add(dispositivo);
    }

    public void watchMovie(){
        for(Dispositivo dispositivo : dispositivos){
            if(dispositivo instanceof DVD){
                dispositivo.turnOn();
            }
            if(dispositivo instanceof Projector){
                dispositivo.turnOn();
            }
            if(dispositivo instanceof AmbientLight){
                dispositivo.turnOff();
            }
            if(dispositivo instanceof PopcornMachine){
                dispositivo.turnOn();
            }
            if(dispositivo instanceof SodaMachine){
                dispositivo.turnOn();
            }
        }
        System.out.println("Get ready for the movie!");
    }

    
}
class Main {
    public static void main(String[] args) {
        HomeTheater homeTheater = new HomeTheater();
        HomeTheaterClient client = new HomeTheaterClient(homeTheater);
        DVD dvd = new DVD();
        Projector projector = new Projector();
        AmbientLight light = new AmbientLight();
        PopcornMachine popcorn = new PopcornMachine();
        SodaMachine soda = new SodaMachine();

        client.addDispositivo(dvd);
        client.addDispositivo(projector);
        client.addDispositivo(light);
        client.addDispositivo(popcorn);
        client.addDispositivo(soda);

        client.watchMovie();
    }
}