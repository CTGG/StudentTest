package dao;

import java.util.ArrayList;
import java.util.List;

import model.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ScoreDaoImpl implements ScoreDao{
	private static ScoreDaoImpl scoreDao = new ScoreDaoImpl();
	private Session session;

	
	private ScoreDaoImpl() {
	}
	
	public static ScoreDaoImpl getInstance(){
		return scoreDao;
	}


	@Override
	public List<String> findAllUsers() {
        session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<String> users = new ArrayList<>();
        List list=session.createNativeQuery("select distinct id from `scores` where 1").list();
        transaction.commit();
        session.close();
        for (int i=0; i < list.size() ; i++){
            users.add((String)list.get(i));
        }
        return users;

	}

	@Override
	public List<Score> findScoresById(String id) {

        session = HibernateUtil.getSession();
        String hql = "from Score sc where sc.id =:id";
        Query query = session.createQuery(hql);
        query.setString("id",id);
        List<Score> list = query.list();

        for (Score s:list
             ) {
            System.out.println(s.getCourse());
        }
        return list;
	}
	public static void main(String[] args){
	    ScoreDaoImpl imp = ScoreDaoImpl.getInstance();
	    ;
        System.out.println(imp.findScoresById("浩然"));
    }

}
