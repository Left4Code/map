package service;

import java.util.ArrayList;

import javax.ejb.Local;

import entities.Question;

@Local
public interface QuestionServiceLocal {
	public int insertQuestion(int idQuestion,Question question);
	public ArrayList<Question> getAllQuestionByTest(int idTest);
	public void CheckAndUpdateQuestionAndTest(int  idQuestion,String choix);
}
