package Scanner;

import java.awt.*;
import java.awt.event.*;
class OKAction implements ActionListener
{
public void actionPerformed (ActionEvent e)
{
ThreadScan.DLGError.dispose();
}
}