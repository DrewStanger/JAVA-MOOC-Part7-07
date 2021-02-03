import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private ArrayList<Recipe> recipes;
    private ArrayList<String> list;
    
    public UserInterface(Scanner scanner){
        this.scanner = new Scanner(System.in);
        this.recipes = new ArrayList<>();
        this.list = new ArrayList<>();
    }
    
    public void init(){
        // basic layout + read into files.
        System.out.print("File to read:");
        String input = scanner.nextLine();
        this.readFile(input);
        
        
        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");
        
        //loop to get commands until user chooses to quit.
        while (true){
           System.out.print("Enter Command:");
           String command = scanner.nextLine();
           if(command.equals("list")){
               this.printRecipes();
            }
            if(command.equals("find name")){
                System.out.print("Searched word:");
                String search = scanner.nextLine();
                this.searchRecipe(search);
            }
            if(command.contains("find cooking time")){
                System.out.print("Max cooking time:");
                int maxTime = Integer.valueOf(scanner.nextLine());
                this.getRecipesWithinTimeLimit(maxTime);
            }
            if(command.equals("find ingredient")){
                System.out.print("Ingredient:");
                String ingredient = scanner.nextLine();
                this.getRecipeBasedOnIngredient(ingredient);
            }
            if(command.equals("stop")){
                break;
            } 
        }  
    }
    
    public void readFile(String input){
        //read the data into the ArrayList list 
        try(Scanner reader = new Scanner(Paths.get(input))){
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                this.list.add(line);
            }
        } catch (Exception e) {
            System.out.println("An error occured " + e);
        }
        this.addToRecipes();
    }
    
    public void addToRecipes(){
        // add the recipes to the Recipe object
        int i = 0;
        while(i<list.size()){
            ArrayList<String> ingredients = new ArrayList<>();
            if(list.get(i).isEmpty()){
                i++;
                continue;
            }
            String name = list.get(i);
            i++;
            int time = Integer.valueOf(list.get(i));
            i++;
            for(i = i; i<list.size() && (!(list.get(i).isEmpty())); i++){
                ingredients.add(list.get(i)); 
            }
            this.recipes.add(new Recipe(name, time, ingredients));
        }
    }
    
    public void searchRecipe(String search){
        for(Recipe recipe:this.recipes){
            if(recipe.getName().contains(search)){
                System.out.println(recipe.getName() + ", cooking time: " + recipe.getTime());
            }
        }
    }
    
    public void getRecipesWithinTimeLimit(int maxTime){
        for(Recipe recipe:this.recipes){
            if(recipe.getTime() <= maxTime){
                System.out.println(recipe.getName() + ", cooking time: " + recipe.getTime());
            }
        }    
    }
    
    public void getRecipeBasedOnIngredient(String ingredient){
        for(Recipe recipe:this.recipes){
            ArrayList list = recipe.getIngredients();
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i));
                if(list.get(i).equals(ingredient)){
                    System.out.println(recipe.getName() + ", cooking time: " + recipe.getTime());
                }             
            }
        }
    }    
    
    public void printRecipes(){
        System.out.println("Recipes:");
        for(Recipe recipe:recipes){
            System.out.println(recipe.getName() + ", cooking time: " + recipe.getTime() + "\n");
        }
    }
    
}
