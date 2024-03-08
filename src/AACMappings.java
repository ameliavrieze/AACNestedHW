public class AACMappings {
  String filename;

  public AACMappings(String filename) {
    this.filename = filename;
  }

  //Adds the mapping to the current category (or the default category if that is the current category)
  void add(String imageLoc, String text) {
    //stub
  }

  //Gets the current category
  String getCurrentCategory() {
    return "";
    //stub
  }

  //Provides an array of all the images in the current category
  String[] getImageLocs() {
    return new String[0];
    //stub
  }

  //Given the image location selected, it determines the associated text with the image.
  String getText(String imageLoc) {
    return "";
    //stub
  }

  //Determines if the image represents a category or text to speak
  boolean isCategory(java.lang.String imageLoc) {
    return false;
    //stub
  }	

  //Resets the current category of the AAC back to the default category
  void reset() {
    //stub
  }

  //Writes the ACC mappings stored to a file.
  void writeToFile(String filename) {
    //stub
  }

  
}
