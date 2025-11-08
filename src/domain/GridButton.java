package domain;

public class GridButton {

    private final int coordinateX;
    private final int coordinateY;
    private int strikeCount;
    private int ballCount;

    public GridButton(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public boolean isOut(OutZone outZone) {
        return outZone.contains(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GridButton that = (GridButton) o;
        return coordinateX == that.coordinateX && coordinateY == that.coordinateY;
    }
}
