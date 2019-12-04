package Object;

import java.io.FileNotFoundException;

public interface ClickableObject {
    void Click(int mouseX, int mouseY) throws FileNotFoundException;

    void Hover(int mouseX, int mouseY);

    boolean onHover(int mouseX, int mouseY);

}
