/*_##########################################################################
  _##
  _##  Copyright (C) 2013 Kaito Yamada
  _##
  _##########################################################################
*/

package com.github.kaitoy.sneo.giane.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.github.kaitoy.sneo.giane.action.message.AssociateActionMessage;
import com.github.kaitoy.sneo.giane.action.message.BreadCrumbsMessage;
import com.github.kaitoy.sneo.giane.action.message.DialogMessage;
import com.github.kaitoy.sneo.giane.action.message.FormMessage;
import com.github.kaitoy.sneo.giane.action.message.LagMessage;
import com.github.kaitoy.sneo.giane.interceptor.GoingBackward;
import com.github.kaitoy.sneo.giane.interceptor.GoingForward;
import com.github.kaitoy.sneo.giane.model.Lag;
import com.github.kaitoy.sneo.giane.model.LagIpAddressRelation;
import com.github.kaitoy.sneo.giane.model.dao.IpAddressRelationDao;
import com.github.kaitoy.sneo.giane.model.dao.LagDao;
import com.github.kaitoy.sneo.giane.model.dao.NodeDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("giane-default")
@InterceptorRef("gianeDefaultStack")
public class LagAction
extends ActionSupport
implements ModelDriven<Lag>, ParameterAware, FormMessage, LagMessage, BreadCrumbsMessage,
  AssociateActionMessage, DialogMessage {

  /**
   *
   */
  private static final long serialVersionUID = 6917277494829749632L;

  private Lag model = new Lag();
  private Map<String, String[]> parameters;
  private LagDao lagDao;
  private NodeDao nodeDao;
  private IpAddressRelationDao ipAddressRelationDao;
  private String uniqueColumn;
  private String uniqueDomain;
  private String deletingIdList;

  public Lag getModel() { return model; }

  @VisitorFieldValidator(appendPrefix = true)
  public void setModel(Lag model) { this.model = model; }

  public void setParameters(Map<String, String[]> parameters) {
    this.parameters = parameters;
  }

  // for DI
  public void setLagDao(LagDao lagDao) {
    this.lagDao = lagDao;
  }

  // for DI
  public void setNodeDao(NodeDao nodeDao) {
    this.nodeDao = nodeDao;
  }

  // for DI
  public void setIpAddressRelationDao(IpAddressRelationDao ipAddressRelationDao) {
    this.ipAddressRelationDao = ipAddressRelationDao;
  }

  public String getUniqueColumn() {
    return uniqueColumn;
  }

  public String getUniqueDomain() {
    return uniqueDomain;
  }

  public void setDeletingIdList(String deletingIdList) {
    this.deletingIdList = deletingIdList;
  }

  @Override
  @GoingForward
  public String execute() throws Exception {
    ValueStack stack = ActionContext.getContext().getValueStack();
    Map<String, Object> valueMap = new HashMap<String, Object>();
    setModel(lagDao.findByKey(model.getId()));
    valueMap.put("node_id", model.getNode().getId());
    valueMap.put("lag_id", model.getId());
    valueMap.put("lag_name", model.getName());
    valueMap.put(
      "ipAddressRelation_id", model.getIpAddressRelation().getId()
    );
    stack.push(valueMap);

    return "config";
  }

  @Action(
    value = "back-to-lag-config",
    results = { @Result(name = "config", location = "lag-config.jsp")}
  )
  @SkipValidation
  @GoingBackward
  public String back() throws Exception {
    ValueStack stack = ActionContext.getContext().getValueStack();
    Map<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put("node_id", parameters.get("node_id")[0]);
    valueMap.put("lag_id", parameters.get("lag_id")[0]);
    valueMap.put("lag_name", parameters.get("lag_name")[0]);
    valueMap.put("ipAddressRelation_id", parameters.get("ipAddressRelation_id")[0]);
    stack.push(valueMap);

    return "config";
  }

  @Action(
    value = "lag-tab-content",
    results = { @Result(name = "tab", location = "lag-tab-content.jsp")}
  )
  @SkipValidation
  public String tab() throws Exception {
    return "tab";
  }

  @Action(
    value = "associate-lag-with-physical-network-interfaces-tab-content",
    results = {
      @Result(
        name = "tab",
        location = "associate-lag-with-physical-network-interfaces-tab-content.jsp"
      )
    }
  )
  @SkipValidation
  public String associatePhysicalNetworkInterfacesTab() throws Exception {
    return "tab";
  }

  @Action(
    value = "lag-create",
    results = { @Result(name = "success", location = "empty.jsp") }
  )
  public String create() throws Exception {
    Integer node_id = Integer.valueOf(parameters.get("node_id")[0]);
    model.setNode(nodeDao.findByKey(node_id));

    LagIpAddressRelation relation
      = new LagIpAddressRelation();
    relation.setLag(model);
    ipAddressRelationDao.create(relation);

    model.setIpAddressRelation(relation);
    lagDao.create(model);

    return "success";
  }

  @Action(
    value = "lag-update",
    results = { @Result(name = "success", location = "empty.jsp") }
  )
  public String update() throws Exception {
    Lag update = lagDao.findByKey(model.getId());
    update.setName(model.getName());
    update.setChannelGroupNumber(model.getChannelGroupNumber());
    lagDao.update(update);

    return "success";
  }

  @Action(
    value = "lag-delete",
    results = { @Result(name = "success", location = "empty.jsp") }
  )
  @SkipValidation
  public String delete() throws Exception {
    List<Lag> deletingList = new ArrayList<Lag>();
    for (String idStr: deletingIdList.split(",")) {
      deletingList.add(lagDao.findByKey(Integer.valueOf(idStr)));
    }
    lagDao.delete(deletingList);
    return "success";
  }

  @Override
  public void validate() {
    String contextName = ActionContext.getContext().getName();

    if (contextName.equals("lag")) {
      if (model.getId() == null) {
        addActionError(getText("select.a.row"));
        return;
      }
    }

    if (contextName.equals("lag-update")) {
      if (model.getId() == null) {
        addActionError(getText("select.a.row"));
      }

      if (model.getName() != null) {
        Integer nodeId = Integer.valueOf(parameters.get("node_id")[0]);
        Lag someone= lagDao.findByNameAndNodeId(model.getName(), nodeId);
        if (someone != null && !someone.getId().equals(model.getId())) {
          uniqueDomain = getText("lag.node.label");
          uniqueColumn = getText("lag.name.label");
          addFieldError("name", getText("need.to.be.unique.in.domain"));
          return;
        }
      }
    }

    if (contextName.equals("lag-create")) {
      Map<String, Object> params = ActionContext.getContext().getParameters();
      Integer nodeId = Integer.valueOf(((String[])params.get("node_id"))[0]);
      if (
           model.getName() != null
        && lagDao.findByNameAndNodeId(model.getName(), nodeId) != null
      ) {
        uniqueDomain = getText("lag.node.label");
        uniqueColumn = getText("lag.name.label");
        addFieldError("name", getText("need.to.be.unique.in.domain"));
        return;
      }
    }
  }

}
