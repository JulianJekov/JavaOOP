package _01_WorkingWithAbstraction.Lab.HotelReservation;

public enum Discount {
    None(0),
    SecondVisit(10),
    VIP(20);

    private int discount;

    Discount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

}
