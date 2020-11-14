package QuestionsHandler;


import QuestionsHandler.Categories.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/13/2020
 * 10:49 AM
 * Quiz
 * Copyright: MIT
 */
public class Database{

    List<Object> categories = new ArrayList<>();

    public AnimalsNature animalsNature = new AnimalsNature();
    public ArtLiterature artLiterature = new ArtLiterature();
    public GeneralKnowledge generalKnowledge = new GeneralKnowledge();
    public History history = new History();
    public Music music = new Music();
    public PopCulture popCulture = new PopCulture();
    public Sports sports = new Sports();
    public Technology technology = new Technology();
    public TVShows tvShows = new TVShows();


    public Database(){

        categories.add(animalsNature);
        categories.add(artLiterature);
        categories.add(generalKnowledge);
        categories.add(history);
        categories.add(music);
        categories.add(popCulture);
        categories.add(sports);
        categories.add(technology);
        categories.add(tvShows);

        Collections.shuffle(categories);

    }

    public List getCategories() {
        return categories;
    }

    public static void main(String[] args) {



    }



}
