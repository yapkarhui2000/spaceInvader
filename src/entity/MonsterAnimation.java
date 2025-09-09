package entity;

public class MonsterAnimation {
    private int frameCounter =0;
    private int frameNum =1;
    /*
     * monster's dancing animation
     */
    public int updatemonsterAnimation(){
        frameCounter++; //increase frame counter for monster to animate
        if(frameCounter>60){ //the interval for monster's animation
            if(frameNum==1){ //change monster's state
                frameNum=2;
            }else if (frameNum ==2){
                frameNum=1;
            }
            frameCounter=0; //reset the frame counter 
           }
           return frameNum;
   }

}
