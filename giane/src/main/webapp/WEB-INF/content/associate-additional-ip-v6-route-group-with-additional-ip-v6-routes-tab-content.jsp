<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<div class="association-container">
  <s:url
    var="additionalIpV6RouteGroup_associated_additionalIpV6Route_grid_box_url"
    action="associate-additional-ip-v6-route-group-with-additional-ip-v6-routes-grid-box"
    escapeAmp="false"
  >
    <s:param name="additionalIpV6RouteGroup_id" value="%{#parameters.additionalIpV6RouteGroup_id}" />
    <s:param name="gridId" value="'additionalIpV6RouteGroup_associated_additionalIpV6Route_grid'" />
    <s:param name="gridAction" value="'additional-ip-v6-route-group-associated-additional-ip-v6-route-grid'" />
    <s:param name="gridCaption" value="%{getText('additionalIpV6RouteGroup.associated.additionalIpV6Route.grid.caption')}" />
  </s:url>
  <sj:div
    href="%{additionalIpV6RouteGroup_associated_additionalIpV6Route_grid_box_url}"
    indicator="additionalIpV6RouteGroup_associated_additionalIpV6Route_grid_box_indicator"
    cssClass="association-associated-grid-box"
  />
  <img
    id="additionalIpV6RouteGroup_associated_additionalIpV6Route_grid_box_indicator"
    src="images/loading_middle.gif"
    alt="Loading..."
    style="display: none;"
    class="association-grid-indicator"
  />
  
  <div class="association-buttons-box">
    <div>
      <sj:a
        id="remove_from_additionalIpV6RouteGroup_unassociated_additionalIpV6Route_grid"
        onClickTopics="association"
        button="true"
      >
        <img src="images/arrow_left.png" alt="associate" class="association-arrow" />
      </sj:a>
    </div>
    <div>
      <sj:a
        id="remove_from_additionalIpV6RouteGroup_associated_additionalIpV6Route_grid"
        onClickTopics="association"
        button="true"
      >
        <img src="images/arrow_right.png" alt="unassociate" class="association-arrow" />
      </sj:a>
    </div>
    <div>
      <s:form id="save_additionalIpV6RouteGroup_associated_additionalIpV6Route_grid_form" theme="simple">
        <s:url var="associate_additionalIpV6RouteGroup_with_additionalIpV6Routes_url" action="associate-additional-ip-v6-route-group-with-additional-ip-v6-routes" escapeAmp="false">
          <s:param name="additionalIpV6RouteGroup_id" value="%{#parameters.additionalIpV6RouteGroup_id}" />
        </s:url>
        <s:hidden id="additionalIpV6RouteGroup_associated_additionalIpV6Route_grid_id_list" name="idList" value="undefined" />
        <div>
          <sj:submit
            id="save_additionalIpV6RouteGroup_associated_additionalIpV6Route_grid"
            href="%{associate_additionalIpV6RouteGroup_with_additionalIpV6Routes_url}"
            targets="shared_dialog_box"
            replaceTarget="false"
            validate="false"
            button="true"
            indicator="associate_additionalIpV6RouteGroup_with_additionalIpV6Routes_indicator"
            value="%{getText('associateAction.save.button.value')}"
          />
        </div>
        <img id="associate_additionalIpV6RouteGroup_with_additionalIpV6Routes_indicator" src="images/loading_small.gif" alt="Loading..." style="display: none;" />
      </s:form>
    </div>
  </div>
  
  <s:url
    var="additionalIpV6RouteGroup_unassociated_additionalIpV6Route_grid_box_url"
    action="associate-additional-ip-v6-route-group-with-additional-ip-v6-routes-grid-box"
    escapeAmp="false"
  >
    <s:param name="additionalIpV6RouteGroup_id" value="%{#parameters.additionalIpV6RouteGroup_id}" />
    <s:param name="gridId" value="'additionalIpV6RouteGroup_unassociated_additionalIpV6Route_grid'" />
    <s:param name="gridAction" value="'additional-ip-v6-route-group-unassociated-additional-ip-v6-route-grid'" />
    <s:param name="gridCaption" value="%{getText('additionalIpV6RouteGroup.unassociated.additionalIpV6Route.grid.caption')}" />
  </s:url>
  <sj:div
    href="%{additionalIpV6RouteGroup_unassociated_additionalIpV6Route_grid_box_url}"
    indicator="additionalIpV6RouteGroup_unassociated_additionalIpV6Route_grid_box_indicator"
    cssClass="association-unassociated-grid-box"
  />
  <img
    id="additionalIpV6RouteGroup_unassociated_additionalIpV6Route_grid_box_indicator"
    src="images/loading_middle.gif"
    alt="Loading..."
    style="display: none;"
    class="association-grid-indicator"
  />
</div>