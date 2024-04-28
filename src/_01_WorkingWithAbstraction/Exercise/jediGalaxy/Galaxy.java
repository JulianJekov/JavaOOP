package _01_WorkingWithAbstraction.Exercise.jediGalaxy;

public class Galaxy {
    private Field field;
    private Jedi jedi;
    private Evil evil;

    public Galaxy(Field field) {
        this.field = field;
        this.jedi = new Jedi();
        this.evil = new Evil();
    }
    public long moveJedi(int jediRow, int jediCol) {
     return jedi.move(jediRow,jediCol,field);
    }

    public void moveEvil(int rowEil, int colEvil) {
       evil.move(rowEil,colEvil,field);
    }
}
