/**
 * Handles rendering, highlighting and selecting of all Buttons
 */

package Screens;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Button {

    private final int x, y, width, height;  //size and location of the button
    private String text;    //text on the button
    private boolean highlighted = false;
    private final boolean hasInfill;
    private boolean selected = false;
    private final Font buttonFont = new Font("arial", Font.PLAIN, 45);  //text font for button
    private final Font buttonHighlightFont = new Font("arial", Font.PLAIN, 65); //text font when highlighted or selected
    private final Color textColor = Color.white;
    private final Color buttonColor = Color.black;
    private final Color buttonColorSelected = Color.black;
    private final Color buttonColorHighlighted = Color.gray;

    public Button(int x, int y, int width, int height, String text, boolean hasInfill) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.hasInfill = hasInfill;
    }
    public Button(int x, int y, int width, int height, boolean hasInfill) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "";
        this.hasInfill = hasInfill;
    }

    public void render(Graphics g) {    //method for all graphic calculations
        int fontHeight;
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        if (this.selected) {    //when button is selected
            g.setColor(this.buttonColorSelected);
            g.setFont(this.buttonFont);
            fontHeight = (int) (buttonFont.getStringBounds(this.text, frc).getHeight() / 2 - g.getFontMetrics().getDescent());
        }else if (this.highlighted) {   //when button is highlighted
            g.setColor(this.buttonColorHighlighted);
            g.setFont(buttonHighlightFont);
            fontHeight = (int) (buttonHighlightFont.getStringBounds(this.text, frc).getHeight() / 2) - g.getFontMetrics().getDescent();
        }else { //when neither selected nor highlighted
            g.setColor(this.buttonColor);
            g.setFont(this.buttonFont);
            fontHeight = (int) (buttonFont.getStringBounds(this.text, frc).getHeight() / 2) - g.getFontMetrics().getDescent();
        }
        if (this.hasInfill) {   //creates button box
            g.fillRect(this.x, this.y, this.width, this.height);
        }else if (this.selected) {
            g.fillRect(this.x, this.y, this.width, this.height);
        }else {
            g.drawRect(this.x, this.y, this.width, this.height);
        }
        g.setColor(this.textColor);
        g.drawString(this.text, ((this.width - g.getFontMetrics().stringWidth(this.text)) / 2) + this.x,  this.y + (this.height / 2) + fontHeight);
    }

    public void reset() {   //resets the button
        this.selected = false;
        this.highlighted = false;
        this.text = "";
    }

    //setter and toggle
    public void setText(String text) {
        this.text = text;
    }
    public void toggleHighlighted(boolean temp) {
        this.highlighted = temp;
    }
    public void toggleSelected() {
        this.selected = !this.selected;
    }

}
