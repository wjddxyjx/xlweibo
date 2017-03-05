package weibo.model;

import java.util.List;

public interface BlahDAO {
	List<Blah> getBlahs(Blah blah);
	void addBlah(Blah blah);
	void deleteBlah(Blah blah);
	 
}
