package jto.colorscheme;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * This is the ColorScheme class which holds a list of {@link ColorSet} objects that contain {@link
 * jto.colorscheme.Color} objects.  </br>Currently, ColorScheme supports loading .xml files created
 * from http://colorschemedesigner.com </br>as well as .ase files (Adobe Swatch Exchange).
 *
 * The .ase parsing code is a slightmy modified version of the code from the kulerviewer by Daniel
 * Weber.
 *
 * @author J. Taylor O'Connor (jtomusic@gmail.com)
 * @version 2013.08.15
 */
public class ColorScheme {

  private Palette palette;

  /**
   * This is the constructor for <code>ColorScheme</code> objects.
   *
   * @param filename - the file to be parsed.
   */
  public ColorScheme(final String filename, final PApplet parent) {
    String fileName = filename;
    if (!Parser.isAnInternetImageFile(filename)) {
      fileName = parent.dataPath(filename);
    }
    if (null == fileName) {
      throw new RuntimeException("Please enter a file name.");
    }

    try {
      this.palette = Parser.readFile(fileName, parent);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("The color file " + fileName + "could not be found.");
    } catch (IOException e) {
      throw new RuntimeException("An error occured while parsing " + fileName);
    }
  }

  /**
   * This returns an {@link java.util.ArrayList} of all the {@link ColorSet} objects in the
   * palette.
   *
   * @return ArrayList<ColorSet> colorSets - the color sets.
   */
  public ArrayList<ColorSet> getColorSets() {
    return palette.getColorSets();
  }

  /**
   * This returns an {@link java.util.ArrayList} of {@link jto.colorscheme.Color} objects that
   * contains all the colors in the palette.
   *
   * @return ArrayList<Color> colors - all the colors.
   */
  public ArrayList<Color> getColors() {
    ArrayList<Color> colors = new ArrayList<Color>();

    for (ColorSet colorSet : palette.getColorSets()) {
      for (Color c : colorSet.getColors()) {
        colors.add(c);
      }
    }

    return colors;
  }

  /**
   * Generates and returns an {@link java.util.ArrayList} of {@link Integer} colors.  You can use
   * these the same way you would use the Processing 'color' type.
   *
   * @return ArrayList<Integer> intArray - the colors as an Integer array.
   */
  public ArrayList<Integer> toIntArray() {
    ArrayList<Integer> intArray = new ArrayList<Integer>();

    for (ColorSet colorSet : palette.getColorSets()) {
      for (Color c : colorSet.getColors()) {
        intArray.add(c.toInt());
      }
    }

    return intArray;
  }
}
