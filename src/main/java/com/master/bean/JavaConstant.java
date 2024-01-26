package com.master.bean;

public class JavaConstant {
		
	public final static String HEAD_OFFICE_LIST = "select new map(me.headOfficeId as headOfficeId,me.headOfficeName as headOfficeName,me.activeFlag as activeFlag) from MasterHeadOffice as me where 1=1 ";
	public final static String SEARCH_HEAD_OFFICE="select new map(mh.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,mh.activeFlag as activeFlag) from MasterHeadOffice as mh ";
	public final static String HEAD_OFFICE_LIST_BY_ID = "select new map(me.headOfficeId as headOfficeId,me.headOfficeName as headOfficeName,me.activeFlag as activeFlag) from MasterHeadOffice as me where me.headOfficeId=?1";

	
	public final static String ZONE_LIST = "select new map(mz.zoneId as zoneId,mz.zoneName as zoneName) from MasterZone as mz where mz.activeFlag=true";
	public final static String ALL_ZONE_LIST = "select new map(mz.zoneId as zoneId,mz.zoneName as zoneName,mz.headOfficeId as headOfficeId,mz.activeFlag as activeFlag,mz.organizationInternalCode as organizationInternalCode,mh.headOfficeName as headOfficeName) from MasterZone as mz left join MasterHeadOffice mh on mh.headOfficeId=mz.headOfficeId ";
	public final static String ALL_ZONE_LIST_BY_ID = "select new map(mz.zoneId as zoneId,mz.zoneName as zoneName,mz.headOfficeId as headOfficeId,mz.activeFlag as activeFlag,mz.organizationInternalCode as organizationInternalCode,mh.headOfficeName as headOfficeName) from MasterZone as mz left join MasterHeadOffice mh on mh.headOfficeId=mz.headOfficeId where mz.zoneId=?1 ";
	public final static String ALL_ZONE_LIST_BY_HEAD_OFFICE_ID = "select new map(mz.zoneId as zoneId,mz.zoneName as zoneName,mz.headOfficeId as headOfficeId,mz.activeFlag as activeFlag,mz.organizationInternalCode as organizationInternalCode,mh.headOfficeName as headOfficeName) from MasterZone as mz left join MasterHeadOffice mh on mh.headOfficeId=mz.headOfficeId where mz.headOfficeId=?1 ";

	public final static String MASTER_REGION = "from MasterRegion where activeFlag=true ";
	public final static String SEARCH_REGION = "select new map(mr.regionId as regionId,mr.regionName as regionName,mr.zoneId as zoneId,mz.zoneName as zoneName,mr.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,mr.activeFlag as activeFlag,mr.organizationInternalCode as organizationInternalCode) from MasterRegion as mr left join MasterZone as mz on mz.zoneId=mr.zoneId left join MasterHeadOffice mh on mh.headOfficeId=mr.headOfficeId ";
	public final static String SEARCH_REGION_BY_REGION_ID = "select new map(mr.regionId as regionId,mr.regionName as regionName,mr.zoneId as zoneId,mz.zoneName as zoneName,mr.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,mr.activeFlag as activeFlag,mr.organizationInternalCode as organizationInternalCode) from MasterRegion as mr left join MasterZone as mz on mz.zoneId=mr.zoneId left join MasterHeadOffice mh on mh.headOfficeId=mr.headOfficeId where mr.regionId=?1";
	public final static String SEARCH_REGION_BY_REGION_ZONE_ID = "select new map(mr.regionId as regionId,mr.regionName as regionName,mr.zoneId as zoneId,mz.zoneName as zoneName,mr.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,mr.activeFlag as activeFlag,mr.organizationInternalCode as organizationInternalCode) from MasterRegion as mr left join MasterZone as mz on mz.zoneId=mr.zoneId left join MasterHeadOffice mh on mh.headOfficeId=mr.headOfficeId where mr.zoneId=?1";

	public final static String MASTER_DEPOT = "from MasterDepot where activeFlag=true ";
	public final static String SEARCH_DEPOT = "select new map(md.depotId as depotId,md.depotName as depotName,md.regionId as regionId,mr.regionName as regionName,md.zoneId as zoneId,mz.zoneName as zoneName,md.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,md.activeFlag as activeFlag,md.organizationInternalCode as organizationInternalCode) from MasterDepot md left join MasterRegion as mr on mr.regionId=md.regionId left join MasterZone as mz on mz.zoneId=md.zoneId left join MasterHeadOffice mh on mh.headOfficeId=md.headOfficeId ";
	public final static String SEARCH_DEPOT_BY_DEPOT_ID = "select new map(md.depotId as depotId,md.depotName as depotName,md.regionId as regionId,mr.regionName as regionName,md.zoneId as zoneId,mz.zoneName as zoneName,md.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,md.activeFlag as activeFlag,md.organizationInternalCode as organizationInternalCode) from MasterDepot md left join MasterRegion as mr on mr.regionId=md.regionId left join MasterZone as mz on mz.zoneId=md.zoneId left join MasterHeadOffice mh on mh.headOfficeId=md.headOfficeId where md.depotId=?1 ";
	public final static String SEARCH_DEPOT_BY_REGION_ID = "select new map(md.depotId as depotId,md.depotName as depotName,md.regionId as regionId,mr.regionName as regionName,md.zoneId as zoneId,mz.zoneName as zoneName,md.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,md.activeFlag as activeFlag,md.organizationInternalCode as organizationInternalCode) from MasterDepot md left join MasterRegion as mr on mr.regionId=md.regionId left join MasterZone as mz on mz.zoneId=md.zoneId left join MasterHeadOffice mh on mh.headOfficeId=md.headOfficeId where md.regionId=?1 ";

	
	public final static String MASTER_STATE = "from MasterState where activeFlag=true ";
	public final static String SEARCH_MASTER_STATE = "select new map(stateId as stateId,stateCode as stateCode,stateName as stateName,activeFlag as activeFlag)from MasterState ";
	public final static String SEARCH_MASTER_STATE_BY_ID = "select new map(stateId as stateId,stateCode as stateCode,stateName as stateName,activeFlag as activeFlag)from MasterState where stateId=?1 ";

	public final static String MASTER_DISTRICT = "from MasterDistrict where activeFlag=true ";
	public final static String SEARCH_MASTER_DISTRICT = "select new map(md.districtCode as districtCode,md.districtName as districtName,md.stateCode as stateCode,ms.stateName as stateName,md.activeFlag as activeFlag)from MasterDistrict md left join  MasterState ms on ms.stateCode=md.stateCode ";
	public final static String SEARCH_MASTER_DISTRICT_BY_ID = "select new map(md.districtCode as districtCode,md.districtName as districtName,md.stateCode as stateCode,ms.stateName as stateName,md.activeFlag as activeFlag)from MasterDistrict md left join  MasterState ms on ms.stateCode=md.stateCode where md.districtCode=?1 ";
	public final static String SEARCH_MASTER_DISTRICT_BY_STATE_CODE = "select new map(md.districtCode as districtCode,md.districtName as districtName,md.stateCode as stateCode,ms.stateName as stateName,md.activeFlag as activeFlag)from MasterDistrict md left join  MasterState ms on ms.stateCode=md.stateCode where md.stateCode=?1 ";

	public final static String MASTER_TALUK = "from MasterTaluk where activeFlag=true ";
	public final static String SEARCH_MASTER_TALUK = "select new map(mt.talukCode as talukCode,mt.talukName as talukName,mt.districtCode as districtCode,md.districtName as districtName,mt.stateCode as stateCode,ms.stateName as stateName,mt.activeFlag as activeFlag)from MasterTaluk mt left join MasterDistrict md on md.districtCode=mt.districtCode left join  MasterState ms on ms.stateCode=mt.stateCode ";
	public final static String SEARCH_MASTER_TALUK_BY_ID = "select new map(mt.talukCode as talukCode,mt.talukName as talukName,mt.districtCode as districtCode,md.districtName as districtName,mt.stateCode as stateCode,ms.stateName as stateName,mt.activeFlag as activeFlag)from MasterTaluk mt left join MasterDistrict md on md.districtCode=mt.districtCode left join  MasterState ms on ms.stateCode=mt.stateCode where mt.talukCode=?1 ";
	public final static String SEARCH_MASTER_TALUK_BY_DISTRICT_CODE = "select new map(mt.talukCode as talukCode,mt.talukName as talukName,mt.districtCode as districtCode,md.districtName as districtName,mt.stateCode as stateCode,ms.stateName as stateName,mt.activeFlag as activeFlag)from MasterTaluk mt left join MasterDistrict md on md.districtCode=mt.districtCode left join  MasterState ms on ms.stateCode=mt.stateCode where mt.districtCode=?1 ";

	public final static String MASTER_VILLAGE = "from MasterVillage where activeFlag=true ";
	public final static String SEARCH_MASTER_VILLAGE = "select new map(mv.villageCode as villageCode,mv.villageName as villageName,mv.talukCode as talukCode,mt.talukName as talukName,mv.districtCode as districtCode,md.districtName as districtName,mv.stateCode as stateCode,ms.stateName as stateName,mv.activeFlag as activeFlag)from MasterVillage mv left join MasterTaluk mt on mt.talukCode=mv.talukCode left join MasterDistrict md on md.districtCode=mv.districtCode left join  MasterState ms on ms.stateCode=mv.stateCode ";
	public final static String SEARCH_MASTER_VILLAGE_BY_ID = "select new map(mv.villageCode as villageCode,mv.villageName as villageName,mv.talukCode as talukCode,mt.talukName as talukName,mv.districtCode as districtCode,md.districtName as districtName,mv.stateCode as stateCode,ms.stateName as stateName,mv.activeFlag as activeFlag)from MasterVillage mv left join MasterTaluk mt on mt.talukCode=mv.talukCode left join MasterDistrict md on md.districtCode=mv.districtCode left join  MasterState ms on ms.stateCode=mv.stateCode where mv.villageCode=?1 ";

	
	public final static String MASTER_USER_ROLE = "from MasterUserRoles where activeFlag=true ";
	public final static String SEARCH_MASTER_USER_ROLE = "select new map(roleId as roleId,roleName as roleName,activeFlag as activeFlag)from MasterUserRoles ";
	public final static String SEARCH_MASTER_USER_ROLE_BY_ID = "select new map(roleId as roleId,roleName as roleName,activeFlag as activeFlag)from MasterUserRoles where roleId=?1 ";
	public final static String ALL_USER_ROLE = "select new map(roleId as roleId,roleName as roleName,activeFlag as activeFlag)from MasterUserRoles where activeFlag=true ";

	
	public final static String MASTER_DESIGANTION = "select new map(md.designationId as designationId,md.designationName as designationName,md.activeFlag as activeFlag) from MasterDesignation as md where md.activeFlag=true ";
	public final static String SEARCH_DESIGANTION = "select new map(md.designationId as designationId,md.designationName as designationName,md.activeFlag as activeFlag) from MasterDesignation as md ";
	public final static String MASTER_DESIGANTION_BY_ID = "select new map(md.designationId as designationId,md.designationName as designationName,md.activeFlag as activeFlag) from MasterDesignation as md where md.designationId=?1 ";

	public final static String MASTER_USER_DETAILS = "from MasterUserDetails where activeFlag=true ";
	public final static String SEARCH_MASTER_USER_DETAILS = "select new map(mud.userId as userId,mud.loginId as loginId,mud.userName as userName,mud.email as email,mud.mobileNo as mobileNo,mud.designationId as designationId,md.designationName as designationName,mud.activeFlag as activeFlag)from MasterUserDetails mud left join MasterDesignation md on md.designationId=mud.designationId ";
	public final static String SEARCH_MASTER_USER_DETAILS_BY_USER_ID = "select new map(mud.userId as userId,mud.loginId as loginId,mud.userName as userName,mud.email as email,mud.mobileNo as mobileNo,mud.designationId as designationId,md.designationName as designationName,mud.activeFlag as activeFlag)from MasterUserDetails mud left join MasterDesignation md on md.designationId=mud.designationId where mud.userId=?1 ";

	public final static String ALL_USER_ROLE_MAPPING = "select new map(mum.id.userId as userId,mum.id.roleId as roleId,mr.roleName as roleName,mum.activeFlag as activeFlag)from MasterUserRoleMapping mum left join MasterUserRoles mr on mr.roleId=mum.id.roleId where mum.activeFlag=true and mum.id.userId=?1 ";
	public final static String DLETE_USER_ROLE_MAPPING="DELETE FROM MasterUserRoleMapping WHERE id.userId=?1 and id.roleId=?2";
	
	public final static String USER_JURICDICTION_MAPPING_BY_USER_ID="select new map(mujm.mappingId as mappingId,mujm.headOfficeId as headOfficeId,mh.headOfficeName as headOfficeName,mujm.zoneId as zoneId,mz.zoneName as zoneName,mujm.regionId as regionId,mr.regionName as regionName,mujm.depotId as depotId,md.depotName as depotName,mujm.userId as userId,mud.userName as userName) from MasterUserJuricdictionMapping mujm inner join MasterHeadOffice mh on mh.headOfficeId=mujm.headOfficeId inner join MasterZone mz on mz.zoneId=mujm.zoneId inner join MasterRegion mr on mr.regionId=mujm.regionId inner join MasterDepot md on md.depotId =mujm.depotId inner join MasterUserDetails mud on mud.userId=mujm.userId where mujm.userId=?1 ";
	public final static String DELETE_USER_JURICDICTION_MAPPPING="DELETE FROM MasterUserJuricdictionMapping WHERE mappingId=?1";

	
}
