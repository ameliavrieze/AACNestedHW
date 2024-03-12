/**
 * AACMappings: handles the mapping between images, categories, and text
 * @author Amelia Vrieze
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import structures.AssociativeArray;

public class AACMappings {
  AssociativeArray<String, AACCategory> catmap = new AssociativeArray<String, AACCategory>(); //String = category name
  AACCategory imagemap = new AACCategory("");
  String currentCat;

  public AACMappings(String filename) {
    File mappings = new File(filename);
    try {
      Scanner fileReader = new Scanner(mappings);
      while (fileReader.hasNextLine()) {
        String next = fileReader.nextLine();
        if (next.startsWith(">")) {
          String imageLoc = next.substring(next.indexOf(">" + 1, next.indexOf(" ")));
          String text = next.substring(next.indexOf(" ") + 1);
          String temp = next.substring(next.indexOf("/") + 1);
          String category = temp.substring(0, temp.indexOf("/"));
          try {
            this.catmap.get(category).addItem(imageLoc, text);
          } catch (Exception e) {
            this.catmap.set(category, new AACCategory(category));
            this.catmap.get(category).addItem(imageLoc, text);
          }
        } else {
          String imageLoc = next.substring(0, next.indexOf(" "));
          String text = next.substring(next.indexOf(" ") + 1);
          this.catmap.set(text, new AACCategory(text));
          this.imagemap.addItem(imageLoc, text);
        }
      }
    } catch (Exception e) {}
    reset();
  }

  //Adds the mapping to the current category (or the default category if that is the current category)
  void add(String imageLoc, String text) {
    try {
      if (isCategory(imageLoc)) {
        this.catmap.set(text, new AACCategory(text));
        this.imagemap.addItem(imageLoc, text);
      } else {
        catmap.get(this.currentCat).addItem(imageLoc, text);
      }
    } catch (Exception e) {

    }
  }

  //Gets the current category
  String getCurrentCategory() {
    return this.currentCat;
  }

  //Provides an array of all the images in the current category
  String[] getImageLocs() {
    try {
      if (this.currentCat.equals("")) {
        return imagemap.getImages();
      }
      return this.catmap.get(this.currentCat).getImages();
    } catch (Exception e) {
      return new String[] {"error"};
    }
  }

  //Given the image location selected, it determines the associated text with the image.
  String getText(String imageLoc) {
    try {
      if (isCategory(this.currentCat)) {
        String text = this.imagemap.getText(imageLoc);
        this.currentCat = text;
        return text;
      } 
      return this.catmap.get(this.currentCat).getText(imageLoc);
    } catch (Exception e) {
      return "error";
    }
  }

  //Determines if the image represents a category or text to speak
  boolean isCategory(String imageLoc) {
      if (imagemap.getText(imageLoc).equals("image not found")) {
        return false;
      }
      return true;
  }	

  //Resets the current category of the AAC back to the default category
  void reset() {
    this.currentCat = "";
  }

  //Writes the ACC mappings stored to a file.
  void writeToFile(String filename) {

    File mappings = new File(filename);
    try {
      PrintWriter pen = new PrintWriter(mappings);
      String[] categories = this.catmap.getKeys();
      String[] catimages = this.imagemap.getImages();
      int i = 0;
      for (String cat : categories) {
        pen.println(catimages[i]);
        String[] images = this.catmap.get(cat).getImages();
        for (String image : images) {
          pen.println(">" + image);
        }
        i++;
      }

    } catch (Exception e) {

    }
    
    //stub
  }

  
}
