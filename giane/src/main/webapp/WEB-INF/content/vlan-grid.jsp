<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<s:url var="vlan_grid_url" action="vlan-grid">
  <s:param name="node_id" value="%{#parameters.node_id}" />
</s:url>

<sjg:grid
  id="vlan_grid"
  caption="%{getText('vlan.grid.caption')}"
  dataType="json"
  href="%{vlan_grid_url}"
  pager="true"
  toppager="false"
  navigator="true"
  navigatorAdd="false"
  navigatorEdit="false"
  navigatorView="true"
  navigatorViewOptions="{modal:true}"
  navigatorDelete="false"
  navigatorSearch="true"
  navigatorSearchOptions="{modal:true, drag:true, closeAfterSearch:true, closeAfterReset:true}"
  navigatorExtraButtons="{
    config: { 
      title: 'Configure selected item',
      icon: 'ui-icon-gear',
      topic: 'gridConfigButtonClicked'
    },
    delete: { 
      title: 'Delete selected item',
      icon: 'ui-icon-trash',
      topic: 'gridDeleteButtonClicked'
    }
  }"
  editinline="false"
  multiselect="false"
  viewrecords="true"
  viewsortcols="[true, 'vertical', true]"
  gridModel="gridModel"
  rowNum="12"
  rownumbers="true"
  width="550"
  shrinkToFit="true"
  altRows="true"
  gridview="true"
  onSelectRowTopics="rowSelected"
  onCompleteTopics="gridCompleted"
  onDblClickRowTopics="vlan_rowDblClicked"
  reloadTopics="vlanTableUpdated"
>
  <sjg:gridColumn
    name="id"
    index="id"
    title="%{getText('vlan.id.label')}"
    formatter="integer"
    key="true"
    sortable="true"
    search="true"
    searchoptions="{sopt:['eq','ne','lt','gt']}"
    hidden="true"
  />
  <sjg:gridColumn
    name="name"
    index="name"
    title="%{getText('vlan.name.label')}"
    sortable="true"
    search="true"
    searchoptions="{sopt:['eq','ne','bw','en','cn']}"
    width="200"
  />
  <sjg:gridColumn
    name="vid"
    index="vid"
    title="%{getText('vlan.vid.label')}"
    sortable="true"
    search="true"
    searchoptions="{sopt:['eq','ne','lt','gt']}"
    width="50"
  />
</sjg:grid>

<s:form id="vlan_delete_form">
  <s:hidden id="vlan_deletingIdList" name="deletingIdList" />
  <s:url var="delete_confirmation_url" action="confirmation-dialog" escapeAmp="false">
    <s:param name="okTopic" value="'vlan_delete'" />
    <s:param name="textKey" value="'confirmationDialog.vlan.delete.text'" />
  </s:url>
  <sj:submit
    listenTopics="vlan_deleteConfirmation"
    href="%{delete_confirmation_url}"
    targets="shared_dialog_box"
    replaceTarget="false"
    validate="true"
    validateFunction="validation"
    clearForm="false"
    cssStyle="display: none;"
  />
  <s:url var="vlan_delete_url" action="vlan-delete" />
  <sj:submit
    listenTopics="vlan_delete"
    href="%{vlan_delete_url}"
    targets="trash_box"
    replaceTarget="false"
    onSuccessTopics="vlanTableUpdated"
    onErrorTopics="deleteError"
    clearForm="true"
    cssStyle="display: none;"
  />
</s:form>
