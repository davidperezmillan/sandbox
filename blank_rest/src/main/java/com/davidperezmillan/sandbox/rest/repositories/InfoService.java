package com.davidperezmillan.sandbox.rest.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.davidperezmillan.sandbox.rest.entities.Info;

@Service
public class InfoService {
	
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

	public void saveInfo(Info info) {
		list.add(info);
	}

	public boolean deleteInfo(long id) {
		for (Info info : list) {
			if (info.getId() == id) {
				list.remove(info);
				return true;
			}
		}
		return false;
	}
	
}
