import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Button {

    private final int x, y, width, height;
    private String text;
    private boolean highlighted = false;
    private final boolean hasInfill;
    private boolean selected = false;
    private final Font buttonFont = new Font("arial", Font.PLAIN, 70);
    private final Font buttonHighlightFont = new Font("arial", Font.PLAIN, 80);
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

    public void render(Graphics g) {
        int fontHight;
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        if (this.selected) {
            g.setColor(this.buttonColorSelected);
            g.setFont(this.buttonFont);
            fontHight = (int) (buttonHighlightFont.getStringBounds(this.text, frc).getHeight() / 2 - g.getFontMetrics().getDescent());
        }else if (this.highlighted) {
            g.setColor(this.buttonColorHighlighted);
            g.setFont(buttonHighlightFont);
            fontHight = (int) (buttonHighlightFont.getStringBounds(this.text, frc).getHeight() / 2) - g.getFontMetrics().getDescent();
        }else {
            g.setColor(this.buttonColor);
            g.setFont(this.buttonFont);
            fontHight = (int) (buttonFont.getStringBounds(this.text, frc).getHeight() / 2) - g.getFontMetrics().getDescent();
        }
        if (this.hasInfill) {
            g.fillRect(this.x, this.y, this.width, this.height);
        }else if (this.selected) {
            g.fillRect(this.x, this.y, this.width, this.height);
        }else {
            g.drawRect(this.x, this.y, this.width, this.height);
        }
        g.setColor(this.textColor);
        g.drawString(this.text, ((this.width - g.getFontMetrics().stringWidth(this.text)) / 2) + this.x,  this.y + (this.height / 2) + fontHight);
    }

    public void setText(String text) {
        this.text = text;
    }
    public void changeHighlight(boolean temp) {
        this.highlighted = temp;
    }
    public void changeSelected() {
        this.selected = !this.selected;
    }

}
