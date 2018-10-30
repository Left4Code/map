package BusinessLayer;

import java.util.List;

import javax.ejb.Local;
@Local
public interface ISkillsBusiness {
 public List<List<Object>> getRatioSkills();
}
