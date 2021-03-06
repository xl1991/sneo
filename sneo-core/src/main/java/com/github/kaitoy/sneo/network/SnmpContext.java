/*_##########################################################################
  _##
  _##  Copyright (C) 2012  Kaito Yamada
  _##
  _##########################################################################
*/

package com.github.kaitoy.sneo.network;

import org.pcap4j.packet.Packet;

public class SnmpContext {

  private final Packet requestPacket;
  private final NetworkInterface getter;

  public SnmpContext(Packet requestPacket, NetworkInterface getter) {
    this.requestPacket = requestPacket;
    this.getter = getter;
  }

  public Packet getRequestPacket() {
    return requestPacket;
  }

  public NetworkInterface getGetter() {
    return getter;
  }

}
