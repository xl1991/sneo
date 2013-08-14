/*_##########################################################################
  _##
  _##  Copyright (C) 2012 Kaito Yamada
  _##
  _##########################################################################
*/

package com.github.kaitoy.sneo.giane.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.github.kaitoy.sneo.giane.model.Vlan;
import com.github.kaitoy.sneo.giane.model.VlanIpAddressRelation;
import com.github.kaitoy.sneo.giane.model.dao.IpAddressRelationDao;
import com.github.kaitoy.sneo.giane.model.dao.NodeDao;
import com.github.kaitoy.sneo.giane.model.dao.VlanDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("giane-default")
@InterceptorRef("gianeDefaultStack")
public class VlanAction
extends ActionSupport implements ModelDriven<Vlan> {

  /**
   *
   */
  private static final long serialVersionUID = -5289730548784449639L;

  private Vlan model = new Vlan();
  private VlanDao vlanDao;
  private IpAddressRelationDao ipAddressRelationDao;
  private NodeDao nodeDao;
  private String uniqueColumn;
  private String uniqueDomain;

  public Vlan getModel() { return model; }

  @VisitorFieldValidator(appendPrefix = false)
  public void setModel(Vlan model) { this.model = model; }

  // for DI
  public void setVlanDao(VlanDao vlanDao) {
    this.vlanDao = vlanDao;
  }

  // for DI
  public void setIpAddressRelationDao(IpAddressRelationDao ipAddressRelationDao) {
    this.ipAddressRelationDao = ipAddressRelationDao;
  }

  // for DI
  public void setNodeDao(NodeDao nodeDao) {
    this.nodeDao = nodeDao;
  }

  public String getUniqueColumn() {
    return uniqueColumn;
  }

  public String getUniqueDomain() {
    return uniqueDomain;
  }

  @SkipValidation
  public String execute() throws Exception {
    @SuppressWarnings("unchecked")
    Map<String, Object> parameters
      = (Map<String, Object>)ActionContext.getContext().get("parameters");
    if (parameters.get("network_id") == null) {
      setModel(vlanDao.findByKey(model.getId()));
      parameters.put("network_id", model.getNode().getNetwork().getId());
      parameters.put("network_name", model.getNode().getNetwork().getName());
      parameters.put("node_id", model.getNode().getId());
      parameters.put("node_name", model.getNode().getName());
      parameters.put("vlan_id", model.getId());
      parameters.put("vlan_vid", model.getVid());
      parameters.put(
        "ipAddressRelation_id", model.getIpAddressRelation().getId()
      );
    }

    return "config";
  }

  @Action(
    value = "vlan-tab-content",
    results = { @Result(name = "tab", location = "vlan-tab-content.jsp")}
  )
  @SkipValidation
  public String tab() throws Exception {
    return "tab";
  }

  @Action(
    value = "associate-vlan-with-vlan-members-tab-content",
    results = {
      @Result(
        name = "tab",
        location = "associate-vlan-with-vlan-members-tab-content.jsp"
      )
    }
  )
  @SkipValidation
  public String associateVlanMembersTab() throws Exception {
    return "tab";
  }

  @Action(
    value = "vlan-create",
    results = { @Result(name = "success", location = "empty.jsp") }
  )
  public String create() throws Exception {
    Map<String, Object> params = ActionContext.getContext().getParameters();
    Integer node_id = Integer.valueOf(((String[])params.get("node_id"))[0]);
    model.setNode(nodeDao.findByKey(node_id));

    VlanIpAddressRelation relation
      = new VlanIpAddressRelation();
    relation.setVlan(model);
    ipAddressRelationDao.save(relation);

    model.setIpAddressRelation(relation);
    vlanDao.save(model);

    return "success";
  }

  @Action(
    value = "vlan-update",
    results = { @Result(name = "success", location = "empty.jsp") }
  )
  public String update() throws Exception {
    Vlan update = vlanDao.findByKey(model.getId());
    update.setName(model.getName());
    update.setVid(model.getVid());
    vlanDao.update(update);

    return "success";
  }

  public void validate() {
    String contextName = ActionContext.getContext().getName();

    if (contextName.equals("vlan-update")) {
      if (model.getId() == null) {
        addActionError(getText("select.a.row"));
      }

      if (model.getName() != null) {
        @SuppressWarnings("unchecked")
        Map<String, Object> params
          = (Map<String, Object>)ActionContext.getContext().get("parameters");
        Integer nodeId = Integer.valueOf(((String[])params.get("node_id"))[0]);

        Vlan someone= vlanDao.findByNameAndNodeId(model.getName(), nodeId);
        if (someone != null && !someone.getId().equals(model.getId())) {
          uniqueDomain = getText("vlan.node.label");
          uniqueColumn = getText("vlan.name.label");
          addActionError(getText("need.to.be.unique.in.domain"));
          return;
        }
      }
    }

    if (contextName.equals("vlan-create")) {
      Map<String, Object> params = ActionContext.getContext().getParameters();
      Integer nodeId = Integer.valueOf(((String[])params.get("node_id"))[0]);
      if (
           model.getName() != null
        && vlanDao.findByNameAndNodeId(model.getName(), nodeId) != null
      ) {
        uniqueDomain = getText("vlan.node.label");
        uniqueColumn = getText("vlan.name.label");
        addActionError(getText("need.to.be.unique.in.domain"));
        return;
      }
    }
  }

}