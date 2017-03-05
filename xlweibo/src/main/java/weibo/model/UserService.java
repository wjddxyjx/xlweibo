package weibo.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class UserService {
    private AccountDAO accountDAO;
    private BlahDAO blahDAO;

    
    public UserService(AccountDAO accountDAO, BlahDAO blahDAO) {
		
		this.accountDAO = accountDAO;
		this.blahDAO = blahDAO;
	}

	public boolean isUserExisted(Account account) {
        return accountDAO.isUserExisted(account);
    }

	public void add(Account account){
		accountDAO.addAccount(account);
	}
    
 

    public boolean checkLogin(Account account) throws IOException {
        if(account.getName()!= null&&account.getPassword()!=null){
        	Account storedAcct = accountDAO.getAccount(account);
        	return storedAcct !=null && storedAcct.getPassword().equals(account.getPassword());
        }
        return false;
    }
    
    private class TxtFilenameFilter implements FilenameFilter {
      
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }
    
    private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();
    
    private class DateComparator implements Comparator<Blah> {
      
        public int compare(Blah d1, Blah d2) {
            return d1.getDate().compareTo(d2.getDate());
        }
    }
    
    private DateComparator comparator = new DateComparator();
    
    public List<Blah> getBlahs(Blah blah) throws IOException {
        List<Blah> blahs = blahDAO.getBlahs(blah);
        Collections.sort(blahs,comparator);
        return blahs;
    }
    
    public void addBlah(Blah blah) throws IOException {
        blahDAO.addBlah(blah);
    }
    
    public void deleteBlah(Blah blah) {
       blahDAO.deleteBlah(blah);
    }
}