<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<s:url var="fixedIpV4Route_grid_url" action="fixed-ip-v4-route-grid">
  <s:param name="node_id" value="%{#parameters.node_id}" />
</s:url>
<s:url var="fixedIpV4Route_edit_grid_entry_url" action="fixed-ip-v4-route-edit-grid-entry">
  <s:param name="node_id" value="%{#parameters.node_id}" />
</s:url>

<sjg:grid
  id="fixedIpV4Route_grid"
  caption="%{getText('fixedIpV4Route.grid.caption')}"
  dataType="json"
  href="%{fixedIpV4Route_grid_url}"
  pager="true"
  toppager="false"
  navigator="true"
  navigatorAdd="false"
  navigatorEdit="false"
  navigatorView="true"
  navigatorViewOptions="{modal:true}"
  navigatorDelete="true"
  navigatorDeleteOptions="{modal:true, drag:true, reloadAfterSubmit:true, width:300, left:0}"
  navigatorSearch="true"
  navigatorSearchOptions="{modal:true, drag:true, closeAfterSearch:true, closeAfterReset:true}"
  editurl="%{fixedIpV4Route_edit_grid_entry_url}"
  editinline="false"
  multiselect="false"
  viewrecords="true"
  viewsortcols="[true, 'vertical', true]"
  gridModel="gridModel"
  rowNum="15"
  rownumbers="true"
  width="650"
  shrinkToFit="true"
  altRows="true"
  gridview="true"
  onSelectRowTopics="rowSelected"
  onCompleteTopics="gridCompleted"
  onDblClickRowTopics="fixedIpV4Route_rowDblClicked"
  reloadTopics="fixedIpV4RouteTableUpdated"
>
  <sjg:gridColumn
    name="id"
    index="id"
    title="%{getText('fixedIpV4Route.id.label')}"
    formatter="integer"
    key="true"
    sortable="true"
    search="true"
    searchoptions="{sopt:['eq','ne','lt','gt']}"
    width="50"
  />
  <sjg:gridColumn
    name="networkDestination"
    index="networkDestination"
    title="%{getText('fixedIpV4Route.networkDestination.label')}"
    sortable="true"
    editable="true"
    edittype="text"
    search="true"
    searchoptions="{sopt:['eq','ne','bw','en','cn']}"
    width="100"
  />
  <sjg:gridColumn
    name="netmask"
    index="netmask"
    title="%{getText('fixedIpV4Route.netmask.label')}"
    sortable="true"
    editable="true"
    edittype="text"
    search="true"
    searchoptions="{sopt:['eq','ne','bw','en','cn']}"
    width="50"
  />
  <sjg:gridColumn
    name="gateway"
    index="gateway"
    title="%{getText('fixedIpV4Route.gateway.label')}"
    sortable="true"
    editable="true"
    edittype="text"
    search="true"
    searchoptions="{sopt:['eq','ne','bw','en','cn']}"
    width="50"
  />
  <sjg:gridColumn
    name="metric"
    index="metric"
    title="%{getText('fixedIpV4Route.metric.label')}"
    sortable="true"
    editable="true"
    edittype="text"
    search="true"
    searchoptions="{sopt:['eq','ne','lt','gt']}"
    width="50"
  />
</sjg:grid>