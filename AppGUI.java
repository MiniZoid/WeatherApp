import javax.swing.*;
import java.awt.*;
/**
 * GUI for Weather App.
 */
public class AppGUI extends Canvas{

    private FieldRestrictor restrictor;
    private JTextField field;
    private Rectangle rectangle;
    private JFrame frame;
    Button searchButton, refreshButton;
    Rectangle background;
    AppController controller;
    String displayString;
    Image icon;
    
    /**
     * Constructor for GUI.
     * Creates a JFrame with 2 buttons, a text field, and one canvas for displaying data and user interaction.
     * @param controller AppController running the app.
     * @param listener Event Listener to be used with the GUI buttons
     */
    public AppGUI(AppController controller, EventListener listener){
        this.controller = controller;
        rectangle = new Rectangle(0,0,600,600);
        setSize(600,600);
        frame = new JFrame("Weather"); 
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.decode("#2F2F2F"));
        JPanel buttons = new JPanel();
        JLabel instructions = new JLabel("Please Enter a 5 Digit US zip code.");
        instructions.setForeground(Color.white);
        buttons.add(instructions);
        buttons.setLayout(new FlowLayout());
        searchButton = new Button("Search");
        refreshButton = new Button("Refresh");
        searchButton.addActionListener(listener);
        refreshButton.addActionListener(listener);
        field = new JTextField(10);
        restrictor = new FieldRestrictor(5,field);
        restrictor.digitLimit();
        field.setDocument(restrictor);
        buttons.add(searchButton);
        buttons.add(refreshButton);
        buttons.setBackground(Color.decode("#2F2F2F"));
        frame.add(this);
        frame.add(buttons);
        frame.add(field);
        frame.pack();
        frame.setVisible(true);         
    }
    
    @Override
    public void paint(Graphics g){
        g.drawRect(0,0,600,600);
        g.setColor(Color.decode("#4A4A4A"));
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if(icon != null) g.drawImage(icon, 200, 405, null); 
        else{
            g.drawRect(0,0,64,64);
            g.fillRect(200,405,64,64);
        }
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(20f));
        int lineHeight = g.getFontMetrics().getHeight();
        int y = 5;
        try{
            for (String line : displayString.split("\n")){
                g.drawString(line, 50, y += lineHeight);
            }
        }
        catch(Exception e){
            //do nothing, just catching error for when output string is null.
        }
    }

    /**
     * Getter Method for Field value
     * @return data typed by user into TextField
     */
    public String getField(){
        return field.getText();
    }

    /**
     * Setter method to changed the string displayed on the GUI canvas
     * @param string String to be displayed on canvas 
     */
    public void setDisplayString(String string){
        displayString = string;
    }

    /**
     * Setter method to change the icon displayed on the GUI canvas
     * @param icon icon to be displayed on Canvas
     */
    public void setIcon(Image icon){
        this.icon = icon;
    }
}
