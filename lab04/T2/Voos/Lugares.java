package Voos;

public interface Lugares {
    public abstract void M(String flight_code);
    public abstract void F(String flight_code, int num_seats_executive, int num_seats_tourist);
    public abstract void R(String flight_code, char classe, int number_seats);
    public abstract void C(String reservation_code);
}
