import java.io.File;
import java.util.Scanner;
import structures.AssociativeArray;

public class AACMappings {
  AssociativeArray<String, AACCategory> catmap;
  String currentCat;

  public AACMappings(String filename) {
    File mappings = new File(filename);
    try {
      Scanner fileReader = new Scanner(mappings);
      while (fileReader.hasNextLine()) {
        String start = fileReader.findInLine(">");
        if (start.equals(null)) {
          
        }

      }
    } catch (Exception e) {

    }
    
    reset();
  }

  //Adds the mapping to the current category (or the default category if that is the current category)
  void add(String imageLoc, String text) {
    try {
      if (isCategory(this.currentCat)) {
        catmap.set(imageLoc, new AACCategory(text));
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
    return this.catmap.get(this.currentCat).getImages();
    } catch (Exception e) {
      return new String[] {"error"};
    }
  }

  //Given the image location selected, it determines the associated text with the image.
  String getText(String imageLoc) {
    try {
      if (isCategory(this.currentCat)) {
        String text = this.catmap.get(imageLoc).name;
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
    if (this.currentCat.equals("")) {
      return true;
    }
    return false;
  }	

  //Resets the current category of the AAC back to the default category
  void reset() {
    this.currentCat = "";
  }

  //Writes the ACC mappings stored to a file.
  void writeToFile(String filename) {

    File mappings = new File(filename);
    //stub
  }

  
}
