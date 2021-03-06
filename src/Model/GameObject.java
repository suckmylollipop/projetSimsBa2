package Model;

public abstract class GameObject {
    private int posX;
    private int posY;
    private int color;
    private int width;
    private int height;

    public GameObject(int X, int Y, int color, int width, int height) {
        this.posX = X;
        this.posY = Y;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public int getPosX() {
        return this.posX;
    }
    
    public void setPosX(int X) {
    	this.posX = X;
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public void setPosY(int Y) {
    	this.posY = Y;
    }
    
    public int getColor() {
        return this.color;
    }

    public int getWidthRatio() {
    	return this.width;
    }
    
    public int getHeightRatio() {
    	return this.height;
    }
    
    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();
    
}
