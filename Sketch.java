import processing.core.PApplet;

public class Sketch extends PApplet {

  //; Related array for the (x,y) coordiante of the snowflakes 
  float[] snowX = new float[42];
  float[] snowY = new float [42];
  boolean[] ballHideStatus = new boolean[42];
  int snowDiameter = 30;

  //starting positon for the circle
  int CircleX = 600;
  int CircleY = 600;

  boolean Wpressed = false;
  boolean Spressed = false;
  boolean Apressed = false; 
  boolean Dpressed = false; 

  int lives = 3;

  public void settings() {
    size(1000, 1000);
  }

  public void setup() {
    background(0);
      //generate random x and y values for snowflakes 
    for (int i=0; i < snowX.length; i++){
      snowX[i] = random(width);
      snowY[i] = random(height);
      ballHideStatus [i] = false;
  }
}

  public void draw() {
    background(0);
    
    //drawing circle player
      snow();
    //draw snow 
      circle();
    //drawing lives
     drawinglives();

     
  }
    
  // All other defined methods are written below:
  public void snow() {
    
    for(int i = 0; i < snowX.length; i++){
        if (!ballHideStatus[i]) {
          fill(255);
          circle(snowX[i], snowY[i], snowDiameter);

          //making the snow look like it's falling 
          snowY[i] += 2;

          //reseting the snowflakes 
          if (snowY[i] > height){
            snowY[i] = 0;
          }
          if (keyPressed){
            if (keyCode == UP){
            snowY[i] -= 1; 
          }
            else if (keyCode == DOWN){
              snowY[i] += 1;
            }
          }
          // Collision detection with snowflake and player
          if (dist(snowX[i], snowY[i], CircleX, CircleY) < snowDiameter / 10 * 25 / 2){
            snowY[i] = 0;
            lives--;
          }

          // Collision detection with disappearing snowflake
          if (mousePressed) {
            if (dist(snowX[i], snowY[i], mouseX, mouseY) < snowDiameter / 2 ) {
              ballHideStatus[i] = true;
            }
          }  
        }
    }
  }
    public void circle (){
      //creating the player circle 
      fill(0,0,255);
      ellipse(CircleX,CircleY,40,40);
      
        if (keyPressed){
          if (key == 'w'){
            CircleY -= 5;
          }
          else if (key == 's'){
            CircleY += 5;
          }
          else if (key == 'a'){
            CircleX -= 5;
          }
          else if (key == 'd'){
            CircleX += 5;
          }
        }
        
    }
    
      //making controls smoother
      public void keyPressed(){
          if (key == 'w') {
            Wpressed = true; 
          }
          else if (key == 's'){
            Spressed = true; 
          }
          else if (key == 'a'){
            Apressed = true;
          }
          else if (key == 'd'){
            Dpressed = true; 
          }
        }
         public void released(){
          if (key == 'w') {
            Wpressed = false; 
          }
          else if (key == 's'){
            Spressed = false;
          }
          else if (key == 'a'){
            Apressed = false;
          }
          else if (key == 'd'){
            Dpressed = false; 
          }
        }

        //drawing lives top right corner 
      void drawinglives(){
        fill(255,0,0);
        for(int i = 0; i < lives; i++) {
          rect(width - 30 - i * 30, 30, 25, 25);
        }
        if (lives == 0){
      background(255);
    }
      }
      
}