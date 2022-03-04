import javax.swing.text.PlainDocument;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A utility class to restrict the input of a given JTextField to only allow 5 digits to be typed
 */
public class FieldRestrictor extends PlainDocument{
    private final int limit;
    private final JTextField field;

    /**
     * Constructs limiter
     * @param limit number of characters to be limited to
     * @param field JTextField box to be limited
     */
    public FieldRestrictor(int limit, JTextField field) {
        super();
        this.limit = limit;
        this.field = field;
    }
    
    
    @Override   
    public void insertString(int offset, String  string, AttributeSet attr ) throws BadLocationException {
        if (string == null){
            return;
        }
        else if((getLength() + string.length()) <= limit){
            super.insertString(offset, string, attr);
        }
    }

    public void digitLimit(){
        KeyListener listener = new KeyListener(){
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if ( ((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  
                }
            }
            @Override
            public void keyPressed(KeyEvent e){} //Empty method to meet interface requirements of KeyListener
            @Override
            public void keyReleased(KeyEvent e){} //Empty method to meet interface requirements of KeyListener
         };
        field.addKeyListener(listener); 
    }
}