package hibernateTest;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Timo
 */
public class DBTestDAO implements DBTestDAO_IF{
    
    private final StandardServiceRegistry registry;
    private SessionFactory istuntotehdas = null;
    
    public DBTestDAO(){
        registry = new StandardServiceRegistryBuilder().configure().build();
		try{
                    istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();	
		}catch(Exception e){
                    System.out.println("Istuntotehtaan luonti epäonnistui");
                    StandardServiceRegistryBuilder.destroy(registry);
                    e.printStackTrace();			
		}
    }
    @Override
    public void finalize(){
            istuntotehdas.close();
	}
    
    @Override
    public DBTest[] readAll(){
        DBTest[] all = null ;
            Session istunto = istuntotehdas.openSession();
            istunto.beginTransaction();
                try{
                    @SuppressWarnings("unchecked")
                    List<Integer> result;
                    result = istunto.createQuery( "from DBTest" ).getResultList();
                    all = result.toArray(new DBTest[result.size()]);
                    istunto.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println("Lukeminen ei onnistunut");
			throw e;
		}finally{
			istunto.close();
		}
		return all;
    }
}
