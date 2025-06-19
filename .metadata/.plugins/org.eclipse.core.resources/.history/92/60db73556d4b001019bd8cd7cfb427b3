package interfaces;

public interface IPersona extends IObtenerId {
    int getDni();
    void setDni(int dni);

    @Override
    default int getId() {
        return getDni();
    }
}
