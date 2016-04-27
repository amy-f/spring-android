package com.example.manchotstudios.com.spring;
//// TODO: 2016-04-26 vérifier si la définition de cet objet de test est bien la bonne
/**
 * Created by annie on 2016-04-26.
 */
public class Task {
    String title;
    Boolean started;
    Boolean done;

    /**
     * Constructeur d'une tâche
     * @param title le titre d'une tache
     * @param started indique si la tâche a débuté
     * @param done indique si la tache est complété
     */
    public Task(String title, Boolean started, Boolean done) {
        this.title = title;
        this.started = started;
        this.done = done;
    }

    /**
     * obtient si la tâche est terminée
     * @return si la tâche est terminée
     */
    public Boolean getStarted() {
        return started;
    }

    /**
     * permet d'indiquer si la tâche est terminée
     * @param started indique si la tâche est terminée
     */
    public void setStarted(Boolean started) {
        this.started = started;
    }

    /**
     * obtient le titre de la tâche
     * @return le titre de la tâche
     */
    public String getTitle() {
        return title;
    }

    /**
     * défini le titre de la tâche
     * @param title le titre de de la tâche
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * obtient si la tâche est terminée ou non
     * @return si la tâche est terminée ou non
     */
    public Boolean getDone() {
        return done;
    }

    /**
     * permet d'indiquer si la tâche est terminée ou non
     * @param done indique si la tâche est terminée ou non
     */
    public void setDone(Boolean done) {
        this.done = done;
    }
}
