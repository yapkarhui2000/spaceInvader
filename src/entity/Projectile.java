package entity;

public class Projectile {
    private int x, y;
    private int speed = 6;
    private boolean active = false;
    
    public Projectile(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.active = false;
    }
    /*
     * projectile move along the map
     */
    public void move() {
        y -= speed; //projectile move upward
        if(y<0){ // if it reach off-screen delete it
            deactivate();
        }
    }
    /*
     * deactivate after it reach offscreen
     */
    public boolean isOffScreen() {
        return !active;
    }
    /*
     * getter for projectile x position
     */
    public int getX() {
        return x;
    }
    /*
     * getter for projectile y position
     */
    public int getY() {
        return y;
    }
    
    /*
     * reset the projectile to the default position
     */
    public void reset(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.active = true;
    }
    /*
     * deactivate the projectile
     */
    public void deactivate(){
        this.active = false;
    }
    /*
     * activate the projectile
     */
    public boolean isActive(){
        return active;
    }
}