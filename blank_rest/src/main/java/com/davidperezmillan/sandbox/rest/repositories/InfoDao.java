package com.davidperezmillan.sandbox.rest.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.davidperezmillan.sandbox.rest.entities.Info;

@Repository
public class InfoDao {
	
    private static List<Info> list = new ArrayList<Info>();
    
    static
    {
        list.add(new Info());
        list.add(new Info());
        list.add(new Info());
    }
	
	public List<Info> getInfos(){
		return list;
	}

	public Info getInfo(long id) {
		for (Info info : list) {
			if (info.getId() == id)
				return info;
		}
		return null;
	}

	public void saveInfo(Info Info) {
		list.add(Info);
	}

	public boolean deleteInfo(long id) {
		boolean okko = Boolean.FALSE;
		for (Info info : list) {
			if (info.getId() == id) {
				list.remove(info);
				okko = true;
			}
		}
		return okko;
	}
	
}
