package _01_WorkingWithAbstraction.Exercise.jediGalaxy;

public class Jedi {
    public long move(int jediRow, int jediCol, Field field){
        int starsCollected = 0;
        while (jediRow >= 0 && jediCol < field.getColLength()) {
            if (field.isInBounds(jediRow, jediCol)) {
                starsCollected += field.getValue(jediRow, jediCol);
            }

            jediCol++;
            jediRow--;
        }
        return starsCollected;
    }
}
