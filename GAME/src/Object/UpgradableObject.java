package Object;

import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public interface UpgradableObject {
    int level = 1;
    void upgrade() throws FileNotFoundException;
//    void onHover(int mouseX, int mouseY, GraphicsContext gc) throws FileNotFoundException;
//    void onClick(int mouseX, int mouseY, GraphicsContext gc) throws FileNotFoundException;
}
