package coin_collector;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import static com.googlecode.lanterna.input.KeyType.Escape;

public class Game {
    private static Screen screen;
    private Map map;

    Game(){
        try{
            AWTTerminalFontConfiguration fontConfig = this.loadFont();
            TerminalSize terminalSize = new TerminalSize(50, 40);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            map = new Map(40,50);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Screen getScreen() {
        return screen;
    }

    public void run() throws IOException {
        boolean x = true;
        while(x){
            drawMap();
            KeyStroke key = screen.readInput();
            if(key.getKeyType()==Escape){
                screen.close();
            } else if(key.getKeyType() == KeyType.EOF){
                x = false;
            }
            processKey(key);
        }
    }

    private void drawMap() throws IOException {
        screen.clear();
        map.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        map.processKey(key);
    }

    public AWTTerminalFontConfiguration loadFont() throws FontFormatException, IOException {
        URL resource = this.getClass().getClassLoader().getResource("fonts/coincollector.ttf");
        File fontFile = null;
        try {
            fontFile = new File(resource.toURI());
        } catch (URISyntaxException var6) {
            System.exit(0);
        }
        Font font = Font.createFont(0, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(0, 25.0F);
        return AWTTerminalFontConfiguration.newInstance(new Font[]{loadedFont});
    }
}
