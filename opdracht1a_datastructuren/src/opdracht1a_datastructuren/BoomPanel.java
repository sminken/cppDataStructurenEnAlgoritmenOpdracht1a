package opdracht1a_datastructuren;

/**
 * Opdracht 1a Steven Minken t.b.v. CPP Java datastructuren, algoritmen en het java collections framework
 */

/**
 * Klasse verantwoordelijk voor het panel waarop de boom wordt getekend.
 * De boom wordt getekend op op basis van de lengte van de stam, de hoek tussen de takken
 * en de diepte van de boom.
 * Wanneer de stamlengte 0 wordt dan worden geen nieuwe aanroepen meer gedaan op tekenBoom
 * Er wordt gebruik gemaakt van een pen die relatief tekent
 * (vanuit een huidige positie en richting).
 */

import java.awt.*;
import javax.swing.*;

public class BoomPanel extends JPanel {
  // attributen

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final double FACTOR = 0.7; // de verkortingsfactor van
  // de takken

  private static double HOEK;; // hoek tussen de takken
  private int diepte; // de diepte van de boom
  private double stamLengte; // lengte van de stam
  private Pen pen; // de pen waarmee getekend wordt

  /**
   * Maak een nieuw BoomPanel.
   * 
   * @param stamlengte, de initiële lengte van de stam
   * @param hoek, de hoek tussen de takken
   * @param diepte, de diepte van de boom.
   */
  BoomPanel(int stamLengte, int hoek, int diepte) {
    this.stamLengte = stamLengte;
    HOEK = hoek;
    this.diepte = diepte;
    this.setSize(350, 300);
    this.setBackground(Color.white);
  }

  /**
   * paintComponent maakt een nieuwe pen en roept de recursieve tekenmethode
   * tekenBoom aan.
   * 
   * @param g
   */
  public void paintComponent(Graphics g) {
    // Maak een nieuwe pen, die iets van de rand onderin het midden van
    // het panel staat en naar boven wijst.
    pen = new Pen(g, getSize().width / 2, getSize().height - 20, 270);
    pen.setKleur(Color.red);

    // Aanroep naar tekenBoom.
    tekenBoom(stamLengte, diepte);
  }

/**
 * Tekent de boom met een bepaalde stamlengte en diepte
 * @param stamLengte de lengte van de stam
 * @param diepte de diepte van de boom
 */
  public void tekenBoom(double stamLengte, int diepte) {
//    Pen wordt uitgezet wanneer de pen terug
//    gaat naar de startpositie
    if (diepte == 1) {
      pen.vooruit(stamLengte);
      pen.uit();
      pen.vooruit(-stamLengte);
      pen.aan();
    }
    else {
//      Als de stamlengte kleiner wordt dan de minimale waarde voor een double dan 
//      wordt er geen aanroep meer gedaan op tekenBoom()
      if ((stamLengte * FACTOR) >= Double.MIN_VALUE) {
        pen.vooruit(stamLengte);
        pen.draai(-0.5 * HOEK);
        tekenBoom(stamLengte * FACTOR, diepte-1);
        pen.draai(HOEK);
        tekenBoom(stamLengte * FACTOR, diepte-1);
        pen.uit();
        pen.draai(-0.5 * HOEK);
        pen.vooruit(-stamLengte);
        pen.aan();
      }  else  {
        return;
      } 
    }
  }
}

