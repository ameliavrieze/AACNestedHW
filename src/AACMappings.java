/**
 * AACMappings: handles the mapping between images, categories, and text
 * @author Amelia Vrieze
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import structures.AssociativeArray;

public class AACMappings {
  AssociativeArray<String, AACCategory> catmap = new AssociativeArray<String, AACCategory>(); 
  String currentCat; 

  public AACMappings(String filename) {
    File mappings = new File(filename);
    try {
      Scanner fileReader = new Scanner(mappings);
      String next = fileReader.nextLine();
      while (fileReader.hasNextLine()) {
        String imageLoc = next.substring(0, next.indexOf(" "));
        this.currentCat = imageLoc;
        String name = next.substring(next.indexOf(" ") + 1);
        this.catmap.set(imageLoc, new AACCategory(name));
        next = fileReader.nextLine();
        while (next.startsWith(">")) {
          imageLoc = next.substring(next.indexOf(">") + 1, next.indexOf(" "));
          String text = next.substring(next.indexOf(" ") + 1);
          this.catmap.get(this.currentCat).addItem(imageLoc, text);
          next = fileReader.nextLine();
        }
      }
      fileReader.close();
    } catch (Exception e) {}
    reset();
  }

  //Adds the mapping to the current category (or the default category if that is the current category)
  void add(String imageLoc, String text) {
    try {
      if (this.currentCat == "") {
        this.catmap.set(imageLoc, new AACCategory(text));
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
        return this.catmap.getKeys();
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
        String text = this.catmap.get(this.currentCat).getText(imageLoc);
        return text;
      } 
      this.currentCat = imageLoc;
      return this.catmap.get(imageLoc).getCategory();
    } catch (Exception e) {
      return "error";
    }
  }

  //Determines if the image represents a category or text to speak
  boolean isCategory(String imageLoc) {
      try {
        this.catmap.get(imageLoc);
        return true;
      } catch (Exception e) {
        return false;
      }
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
      int i = 0;
      for (String cat : categories) {
        pen.println(categories[i]);
        String[] images = this.catmap.get(cat).getImages();
        for (String image : images) {
          pen.println(">" + image);
        }
        i++;
      }
      pen.close();
    } catch (Exception e) {}
  }

  
}
