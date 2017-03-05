package weibo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BlahDAOjdbcImpl implements BlahDAO {
	private DataSource dataSource;
	public BlahDAOjdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Blah> getBlahs(Blah blah) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		List<Blah> blahs = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT date,txt FROM t_blah WHERE name = ?");
			stmt.setString(1, blah.getUsername());
			ResultSet rs = stmt.executeQuery();
			blahs = new ArrayList<Blah>();
			while(rs.next()){
				blahs.add(new Blah(
						blah.getUsername(),
						rs.getTimestamp(1),
						rs.getString(2)));
			}
		}catch(SQLException e){
			ex = e;
		}
		finally{
			  if(stmt!=null){
				  try{
					  stmt.close();
				  }catch(SQLException e){
					  if(ex==null){
						  ex=e;
					  }
				  }
			  }
			  if(conn!=null){
				  try{
					  conn.close();
				  }catch(SQLException e){
					  if(ex==null){
						  ex=e;
					  }
				  }
			  }
		  }
		return blahs;
	}

	public void addBlah(Blah blah) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("INSERT INTO t_blah(name,date,txt) VALUES(?,?,?)");
			stmt.setString(1, blah.getUsername());
			stmt.setTimestamp(2, new Timestamp(blah.getDate().getTime()));
			stmt.setString(3, blah.getTxt());
			stmt.executeUpdate();
		}catch(SQLException e){
			ex = e;
		}
		finally{
			  if(stmt!=null){
				  try{
					  stmt.close();
				  }catch(SQLException e){
					  if(ex==null){
						  ex=e;
					  }
				  }
			  }
			  if(conn!=null){
				  try{
					  conn.close();
				  }catch(SQLException e){
					  if(ex==null){
						  ex=e;
					  }
				  }
			  }
		    }
	}

	public void deleteBlah(Blah blah) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("DELETE FROM t_blah WHERE date = ?");
			stmt.setTimestamp(1, new Timestamp(blah.getDate().getTime()));
			stmt.executeUpdate();
		}catch(SQLException e){
			ex = e;
		}finally{
			  if(stmt!=null){
				  try{
					  stmt.close();
				  }catch(SQLException e){
					  if(ex==null){
						  ex=e;
					  }
				  }
			  }
			  if(conn!=null){
				  try{
					  conn.close();
				  }catch(SQLException e){
					  if(ex==null){
						  ex=e;
					  }
				  }
			  }
		    }
	}

}
