package com.bah.cloudengine.domain;

/**
 * Created on:
 * Date: 7/8/14
 * Time: 2:59 PM
 */
public class MiqVM {
    private String id;
    private String vendor;
    private String name;
    private String location;
    private String created_on;
    private String updated_on;
    private String guid;
    private Integer ems_id;
    private String uid_ems;
    private String power_state;
    private String state_changed_on;
    private String last_perf_capture_on;
    private Boolean template;
    private Boolean vdi;
    private String type;
    private String ems_ref;
    private Integer flavor_id;
    private Integer availability_zone_id;
    private Boolean cloud;
    private Integer cloud_network_id;
    private Integer cloud_subnet_id;
    private Integer int_id;


    public Integer getIdAsInteger() {
        String[] segments = getId().split("/");
        return Integer.valueOf(segments[segments.length - 1]);
    }

    public Integer getInt_id() {
        return int_id;
    }

    public void setInt_id(Integer int_id) {
        this.int_id = int_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getEms_id() {
        return ems_id;
    }

    public void setEms_id(Integer ems_id) {
        this.ems_id = ems_id;
    }

    public String getUid_ems() {
        return uid_ems;
    }

    public void setUid_ems(String uid_ems) {
        this.uid_ems = uid_ems;
    }

    public String getPower_state() {
        return power_state;
    }

    public void setPower_state(String power_state) {
        this.power_state = power_state;
    }

    public String getState_changed_on() {
        return state_changed_on;
    }

    public void setState_changed_on(String state_changed_on) {
        this.state_changed_on = state_changed_on;
    }

    public String getLast_perf_capture_on() {
        return last_perf_capture_on;
    }

    public void setLast_perf_capture_on(String last_perf_capture_on) {
        this.last_perf_capture_on = last_perf_capture_on;
    }

    public Boolean getTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public Boolean getVdi() {
        return vdi;
    }

    public void setVdi(Boolean vdi) {
        this.vdi = vdi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEms_ref() {
        return ems_ref;
    }

    public void setEms_ref(String ems_ref) {
        this.ems_ref = ems_ref;
    }

    public Integer getFlavor_id() {
        return flavor_id;
    }

    public void setFlavor_id(Integer flavor_id) {
        this.flavor_id = flavor_id;
    }

    public Integer getAvailability_zone_id() {
        return availability_zone_id;
    }

    public void setAvailability_zone_id(Integer availability_zone_id) {
        this.availability_zone_id = availability_zone_id;
    }

    public Boolean getCloud() {
        return cloud;
    }

    public void setCloud(Boolean cloud) {
        this.cloud = cloud;
    }

    public Integer getCloud_network_id() {
        return cloud_network_id;
    }

    public void setCloud_network_id(Integer cloud_network_id) {
        this.cloud_network_id = cloud_network_id;
    }

    public Integer getCloud_subnet_id() {
        return cloud_subnet_id;
    }

    public void setCloud_subnet_id(Integer cloud_subnet_id) {
        this.cloud_subnet_id = cloud_subnet_id;
    }
}
