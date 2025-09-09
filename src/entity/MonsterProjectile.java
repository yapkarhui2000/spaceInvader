package entity;




public class MonsterProjectile {
    private int x, y;
    private boolean isActive;
    private int speed = 5; // Default bullet speed

    public MonsterProjectile() {
        reset();
        this.isActive = false;
    }
    /*
     * reset the projectile position
     */
    public void reset(){
        this.x = -1;  // Off-screen position
        this.y = -1;  // Off-screen position
        this.isActive = false; // Deactivate the projectile
    }
    /*
     * move the projectile along the screen
     */
    public void projectileUpdate(){
        if(isActive){
            y+=5; //move the bullet down
        }
    }

    public void activate(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.isActive = true;
    }

    public void move() {
        if (isActive) {
            y += speed;
        }
        if (y > 670) { // Off-screen condition
            deactivate();
        }
    }

    public void deactivate() {
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
 
}
