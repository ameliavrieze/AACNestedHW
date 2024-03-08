import structures.AssociativeArray;

public class AACCategory {
  String name;
  AssociativeArray<String, String> map = new AssociativeArray<String, String>();

  public AACCategory (String name) {
    this.name = name;
  }

 //Adds the mapping of the imageLoc to the text to the category.
  void addItem(String imageLoc, String text) {
    try {
    this.map.set(imageLoc, text);
    } catch (Exception e) {

    }
  } 	

  // Returns the name of the category
  String 	getCategory() {
    return this.name;
  }	

  // Returns an array of all the images in the category
  String[] getImages() {
    return this.map.getKeys();
  }

  // Returns the text associated with the given image loc in this category
  String getText(String imageLoc) {
    try {
    return this.map.get(imageLoc);
    } catch (Exception e) {
      return "image not found";
    }
  }

  //Determines if the provided images is stored in the category
  boolean hasImage(String imageLoc) {
    return this.map.hasKey(imageLoc);
  } 	

}
