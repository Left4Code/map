package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Question implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idQuestion;
	private String task;
	private String syn1;
	private String syn2;
	private String syn3;
	private String correct;
	@ManyToOne
	private Test test;

	public Question() {

	}
	@XmlElement
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	@XmlElement
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	@XmlElement
	public String getSyn1() {
		return syn1;
	}

	public void setSyn1(String syn1) {
		this.syn1 = syn1;
	}
	@XmlElement
	public String getSyn2() {
		return syn2;
	}

	public void setSyn2(String syn2) {
		this.syn2 = syn2;
	}
	@XmlElement
	public String getSyn3() {
		return syn3;
	}

	public void setSyn3(String syn3) {
		this.syn3 = syn3;
	}
	@XmlElement
	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public Question(String task, String syn1, String syn2, String syn3, String correct) {
		super();
		this.task = task;
		this.syn1 = syn1;
		this.syn2 = syn2;
		this.syn3 = syn3;
		this.correct = correct;
	}

	@Override
	public int hashCode() {
		return 5;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (idQuestion != other.idQuestion)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Question [idQuestion=" + idQuestion + ", task=" + task + ", syn1=" + syn1 + ", syn2=" + syn2 + ", syn3="
				+ syn3 + ", correct=" + correct + ", test=" + test + "]";
	}

}
