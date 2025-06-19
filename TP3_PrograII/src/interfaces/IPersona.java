package interfaces;

public interface IPersona extends IObtenerId {
    int getDni();
    void setDni(int dni);
    
    String getNombre();
    void setNombre(String nombre);

    @Override
    default int getId() {
        return getDni();
    }
}
