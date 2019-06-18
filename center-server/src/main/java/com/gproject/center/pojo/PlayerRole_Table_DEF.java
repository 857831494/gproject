package com.gproject.center.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.gproject.common.db.DBEvent;
import com.gproject.common.utils.common.JSONUtil;


public interface PlayerRole_Table_DEF {

	//json  表
		public class PlayerRole{
			public long playerId;
			public String roleName;
			public int serverId;
		}
	
	//物理表
	@Entity(name = "tb_PlayerRole")
	public class PlayerRole_Table implements DBEvent{

		@Id
		String GID;
		
		@Column(columnDefinition = "text")
		String logicData;

		@Transient
		public List<PlayerRole> list=new ArrayList<>();
		
		@Override
		public void initAfterQueryDB() {
			// TODO Auto-generated method stub
			this.list=JSONUtil.getListType(this.logicData, PlayerRole.class);
			if (list==null) {
				list=new ArrayList<>();
			}
		}

		@Override
		public void beforeSaveDB() {
			// TODO Auto-generated method stub
			this.logicData=JSONUtil.toJsonString(list);
		}

		@Override
		public void setID(Object ID) {
			// TODO Auto-generated method stub
			this.GID=(String) ID;
		}

		
	}
	
	
	
	
}
