import processing.core.PApplet;

/**
 * This code shows snowflakes. The player have three lives, but if knocks into
 * the snowflakes they lose a life. After loseing three lives the game is over.
 * @author: Sierra Rajabali
 */

public class Sketch extends PApplet {
	
  // x and y position for the snowflakes position 
  float [] snowflakeX = new float[15];
  float [] snowflakeY = new float[15];
  int snowfakeDiameter = 20;
  
  // x and y position for player 
  float circleX = 50;
  float circleY = 50;

  // The player had three lives
  int playerLives = 3;

  // Defines WASD variables
  boolean wPressed = false;
  boolean aPressed = false;
  boolean sPressed = false;
  boolean dPressed = false;

 // Snow speed variable
  boolean upPressed = false;
  boolean downPressed = false;

  boolean[] ballHideStatus;
	
  /**
   * Setting and window size
   * @sierrarajabali  
   */
  
  public void settings() {
	// put your size call here
    size(400, 400);
    ballHideStatus = new boolean [snowflakeX.length];
  }

  /**
   * Setup values
   * @sierrarajabali  
   */

  public void setup() {
    background(0);

    ballHideStatus = new boolean [snowflakeX.length];

    // put the snowfalkes at random locations
    for(int i = 0; i < snowflakeX.length; i++){
      snowflakeX[i] = random(width);
      snowflakeY[i] = random(height);
    }
  }

  /**
   * Everything drawn on the screen
   * @sierrarajabali
   */
  public void draw() {
	 background(0);

   // draw snow
   snowflake();

   // Drawing squares showing the players lives
   playerLives();

   // Still shows the player circle  if player still have lives
   if (playerLives > 0) {
    playerCircle();
   }
  
   // move player when pressing WASD
   if (wPressed) {
    circleY--;
   }
   if (sPressed) {
    circleY++;
   }
   if (aPressed) {
    circleX--;
   }
   if (dPressed) {
    circleX++;
   }

   // Snow flake dissapears when clicked
   snowflakeClicked();
  }

  // All other methods

  public void snowflake() {
    for (int i = 0; i < snowflakeX.length; i++) {
      if (!ballHideStatus[i]) {
        fill (255,255,255);
        ellipse(snowflakeX[i], snowflakeY[i], 20, 20);

        if (dist(snowflakeX[i], snowflakeY[i], circleX, circleY) < 35) {
          playerLives--;
          snowflakeY [i] = 0;
          snowflakeX [i] = random(width);
          ballHideStatus[i] = true;
        }
      }
    }

    fill(255);
    for(int i = 0; i < snowflakeX.length; i++) {
      if (!ballHideStatus[1]) {
        circle (snowflakeX[i], snowflakeY[i], (float)snowfakeDiameter);
      }
      if (downPressed) {
        snowflakeY[i] += 4;
      }
      else if (upPressed) {
        snowflakeY[i] += 0.5;
      }
      else {
        snowflakeY[i] += 2;
      }
  
      if (snowflakeY[i] > height) {
        snowflakeY [i] = 0;
        ballHideStatus[i] = false;
      }
    }
  }

  public void playerLives() {
    for (int i = 0; i < playerLives; i++) {
      float x = width - 50 - i * 50;
      float y = 20;
      fill (255);
      rect (x, y, 30, 30);
    }

    // Game ends if all lives are lost
    // Shows that the game is over
    if (playerLives <= 0) {
      background(255);
      textSize(48);
      fill(53, 186, 219);
      text("Game Over!", 80, 200);
    }
  }

  /*
   * Draw player circle
   * @sierrarajabali
   */

  public void playerCircle() {
    fill (0, 0, 255);
    ellipse(circleX, circleY, 50, 50);
  }

  /**
   * If up arrow is press, the snow falls faster
   * If down arrow is pressed the snow falls slower
   * If 'w' is pressed the circle moves up
   * if 'a' is pressed circle moves left
   * if 's' is pressed the circle moves down
   * if 'd' is pressed the circle moves right
   */

   public void keyPressed() {
    circleX = constrain (circleX, 0, width);
    circleY = constrain (circleY, 0, height);

    if (keyCode == UP) {
      upPressed = true;
    }
    else if (keyCode == DOWN) {
      downPressed = true;
    }

    if (key == 'w' || key == 'w') {
      wPressed = true;
    }
    if (key == 'a' || key == 'a') {
      aPressed = true;
    }
    if (key == 's' || key == 's') {
      sPressed = true;
    }
    if (key == 'd' || key == 'd') {
      dPressed = true;
    }
   }

  /**
   * Key released for up and down is how fall the snow falls
   * Release for WASD is the direction the player circle goes
   */

  public void keyReleased() {
    if (keyCode == UP) {
      upPressed = false;
    }
    else if (keyCode == DOWN) {
      downPressed = false;
    }

    if (key == 'w' || key == 'W') {
      wPressed = false;
    }
    if (key == 'a' || key == 'A') {
      aPressed = false;
    }
    if (key == 's' || key == 'S') {
      sPressed = false;
    }
    if (key == 'd' || key == 'D') {
      dPressed = false;
    }
  }

  /*
   * Snowflakes disappearing
   * @sierrarajabali
   */

   public void snowflakeClicked(){
    float clickRadius = 10;
    for (int i = 0; i < snowflakeX.length; i++){
        float distance = dist(snowflakeX[i], snowflakeY[i], mouseX, mouseY);
        if (distance < clickRadius && mousePressed) {
          ballHideStatus[i] = true;
        }
    }
  }
}