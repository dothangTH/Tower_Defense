package Object;

public interface ClickableObject {
    void Click(int mouseX, int mouseY);

    void Hover(int mouseX, int mouseY);

    boolean onHover(int mouseX, int mouseY);

}
