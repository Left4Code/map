package service;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Applicant;
import entities.Question;
import entities.Test;

@Stateless
public class QuestionService implements QuestionServiceLocal{
	@PersistenceContext(unitName = "l4c_map-v2-ejb")
	EntityManager em;

	@Override
	public int insertQuestion(int idTest,Question question) {
		Test test = em.find(Test.class, idTest);
		if(test !=null) {
			question.setTest(test);
			em.persist(question);
			return question.getIdQuestion();
		}
		return 0;
	}

	@Override
	public ArrayList<Question> getAllQuestionByTest(int idTest) {
		TypedQuery<Question> query = em.createQuery("SELECT a FROM Question a where a.test=:idtest", Question.class);
		Test test = em.find(Test.class, idTest);
		return (ArrayList<Question>) query.setParameter("idtest",test).getResultList();
	}

	@Override
	public void CheckAndUpdateQuestionAndTest(int idQuestion,String choix) {
		choix=choix.trim();
		Question question = em.find(Question.class, idQuestion);
		System.out.println(question);
		System.out.println(choix);
		if(choix.equals(question.getCorrect())) {
			Test test = em.find(Test.class, question.getTest().getIdTest());
			int mark = test.getMark();
			mark ++ ;
			test.setMark(mark);
			em.merge(test);
			System.out.println(test);
		}
		
	}
}
