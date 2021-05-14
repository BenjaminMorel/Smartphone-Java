package Demo;

import javax.swing.*;

public class SwitchApp extends Smartphone{
    public SwitchApp(JPanel app, JPanel newApp) {
        super(app);
        switchApplication(newApp);
    }

    @Override
    public void switchApplication(JPanel newApp) {
        super.switchApplication(newApp);
    }
}
