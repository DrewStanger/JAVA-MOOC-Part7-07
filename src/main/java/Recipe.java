
import java.util.ArrayList;

public class Recipe {
    private String name;
    private int cookingTime;
    private ArrayList<String> ingredients;
    
    public Recipe(String name, int cookingTime, ArrayList ingredients){
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }
    
    public int getTime(){
        return this.cookingTime;
    }
    
    public String getName(){
        return this.name;
    }
    
    public ArrayList getIngredients(){
        return this.ingredients;
    }
    
}
