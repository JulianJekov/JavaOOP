package _01_WorkingWithAbstraction.Lab.HotelReservation;

public enum Season {
    Autumn(1),
    Spring(2),
    Summer(4),
    Winter(3);

    private int multiplier;

    Season(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
