<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<div class="breadcrumb-label" style="display: none;">
  <s:label
    title="%{getText('breadcrumbs.simulation.configuration.title')}"
    value="%{getText('breadcrumbs.simulation.configuration.label')}"
  />
</div>

<sj:tabbedpanel id="simulation_tabs" animate="true" cssClass="giane-tab-container" selectedTab="%{selectedTab}">
  <sj:tab id="set_trapTargetGroup_to_snmpAgent_tab" target="set_trapTargetGroup_to_snmpAgent_tab_content" label="%{getText('simulation.set.trapTargetGroup.to.snmpAgent.tab.label')}" />
  <s:url var="set_trapTargetGroup_to_snmpAgent_tab_content_url" action="set-trap-target-group-to-snmp-agent-tab-content" escapeAmp="false">
    <s:param name="simulation_id" value="%{simulation_id}" />
    <s:param name="tabIndex" value="0" />
    <s:param name="breadcrumbsId" value="%{#parameters.breadcrumbsId}" />
  </s:url>
  <sj:div id="set_trapTargetGroup_to_snmpAgent_tab_content" href="%{set_trapTargetGroup_to_snmpAgent_tab_content_url}" indicator="set_trapTargetGroup_to_snmpAgent_tab_indicator" cssClass="giane-tab-content" />

  <sj:tab id="set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab" target="set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab_content" label="%{getText('simulation.set.realNetworkInterfaceConfiguration.to.realNetworkInterface.tab.label')}" />
  <s:url var="set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab_content_url" action="set-real-network-interface-configuration-to-real-network-interface-tab-content" escapeAmp="false">
    <s:param name="simulation_id" value="%{simulation_id}" />
    <s:param name="tabIndex" value="1" />
    <s:param name="breadcrumbsId" value="%{#parameters.breadcrumbsId}" />
  </s:url>
  <sj:div id="set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab_content" href="%{set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab_content_url}" indicator="set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab_indicator" cssClass="giane-tab-content" />

  <sj:tab id="set_additionalIpV4RouteGroup_to_node_tab" target="set_additionalIpV4RouteGroup_to_node_tab_content" label="%{getText('simulation.set.additionalIpV4RouteGroup.to.node.tab.label')}" />
  <s:url var="set_additionalIpV4RouteGroup_to_node_tab_content_url" action="set-additional-ip-v4-route-group-to-node-tab-content" escapeAmp="false">
    <s:param name="simulation_id" value="%{simulation_id}" />
    <s:param name="tabIndex" value="2" />
    <s:param name="breadcrumbsId" value="%{#parameters.breadcrumbsId}" />
  </s:url>
  <sj:div id="set_additionalIpV4RouteGroup_to_node_tab_content" href="%{set_additionalIpV4RouteGroup_to_node_tab_content_url}" indicator="set_additionalIpV4RouteGroup_to_node_tab_indicator" cssClass="giane-tab-content" />

  <sj:tab id="set_additionalIpV6RouteGroup_to_node_tab" target="set_additionalIpV6RouteGroup_to_node_tab_content" label="%{getText('simulation.set.additionalIpV6RouteGroup.to.node.tab.label')}" />
  <s:url var="set_additionalIpV6RouteGroup_to_node_tab_content_url" action="set-additional-ip-v6-route-group-to-node-tab-content" escapeAmp="false">
    <s:param name="simulation_id" value="%{simulation_id}" />
    <s:param name="tabIndex" value="3" />
    <s:param name="breadcrumbsId" value="%{#parameters.breadcrumbsId}" />
  </s:url>
  <sj:div id="set_additionalIpV6RouteGroup_to_node_tab_content" href="%{set_additionalIpV6RouteGroup_to_node_tab_content_url}" indicator="set_additionalIpV6RouteGroup_to_node_tab_indicator" cssClass="giane-tab-content" />
</sj:tabbedpanel>

<img id="set_trapTargetGroup_to_snmpAgent_tab_indicator" src="images/loading_big.gif" alt="Loading..." style="display: none;" class="giane-tab-indicator" />
<img id="set_realNetworkInterfaceConfiguration_to_realNetworkInterface_tab_indicator" src="images/loading_big.gif" alt="Loading..." style="display: none;" class="giane-tab-indicator" />
<img id="set_additionalIpV4RouteGroup_to_node_tab_indicator" src="images/loading_big.gif" alt="Loading..." style="display: none;" class="giane-tab-indicator" />
<img id="set_additionalIpV6RouteGroup_to_node_tab_indicator" src="images/loading_big.gif" alt="Loading..." style="display: none;" class="giane-tab-indicator" />
