package databit.com.br.datamobile.domain;

/**
 * Created by Sidney on 11/03/2016.
 */
public class MenuOs {
    private int icon;
    private String label;

    public MenuOs(int icon, String label) {
        this.icon = icon;
        this.label = label;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
