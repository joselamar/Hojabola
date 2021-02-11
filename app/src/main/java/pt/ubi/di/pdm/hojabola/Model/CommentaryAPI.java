package pt.ubi.di.pdm.hojabola.Model;


import java.util.ArrayList;

public class CommentaryAPI {
    private ArrayList<Commentary> Commentary;

    public CommentaryAPI(ArrayList<Commentary> commentary) {
        Commentary = commentary;
    }

    public ArrayList<Commentary> getCommentary() {
        return Commentary;
    }

    public void setCommentary(ArrayList<Commentary> commentary) {
        Commentary = commentary;
    }
}
