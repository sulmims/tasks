public class FiguresMap {
    private char[][] figuresMapData;
    private Size size;

    public FiguresMap(char[][] figuresMapData, Size size) {
        this.figuresMapData = figuresMapData;
        this.size = size;
    }

    public void setMapPointData(Point point, char data){
        figuresMapData[point.getY()][point.getX()] = data;
    }

    public char[][] getFiguresMapData() {
        return figuresMapData;
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }
}