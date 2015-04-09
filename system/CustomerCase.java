/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class CustomerCase {
    private int ID;
    private String center_name;
    private String genre;
    private String question;
    private String solution;
    private boolean solved;

    public CustomerCase(int ID, String center_name, String genre, String question, String solution, boolean solved){
        this.ID = ID;
        this.center_name = center_name;
        this.genre = genre;
        this. question = question;
        this.solution = solution;
        this.solved = solved;
    }

    int getId(){
        return ID;
    }
    String getCenterName(){
        return center_name;
    }
    String getGenre(){
        return genre;
    }
    String getQuestion(){
        return question;
    }
    String getSolution(){
        return solution;
    }
    boolean getSolved(){
        return solved;
    }
    void setGenre(String newGenre){
        genre = newGenre;
    }
    void setQuestion(String newQuestion){
        question = newQuestion;
    }
    void setSolution(String newSolution){
        solution = newSolution;
    }
    void setSolved(boolean newValue){
        solved = newValue;
    }





}
