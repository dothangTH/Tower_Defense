package Object;

import java.io.FileNotFoundException;

public interface UpgradableObject {
    int level = 1;
    void upgrade();
    void onHover(int mouseX, int mouseY, Object caller) throws FileNotFoundException;
    void onClick(int mouseX, int mouseY, Object caller);
}
